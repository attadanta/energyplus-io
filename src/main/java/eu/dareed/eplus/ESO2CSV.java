package eu.dareed.eplus;

import eu.dareed.eplus.model.Item;
import eu.dareed.eplus.model.eso.DataPoints;
import eu.dareed.eplus.model.eso.ESO;
import eu.dareed.eplus.model.eso.ESOItem;
import eu.dareed.eplus.parsers.eso.ESOParser;
import org.apache.commons.cli.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * <p>Usage:
 * <pre>eso2csv -e environment-name -i interval [-v var-code...] output.eso...</pre>
 * <p/>
 *
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class ESO2CSV {

    private String environment;
    private int interval;
    private int[] variables;

    // Defaults
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private CSVFormat csvFormat = CSVFormat.DEFAULT;

    public ESO2CSV(String environment, int interval, int[] variables) {
        this.environment = environment;
        this.interval = interval;
        this.variables = variables;
    }

    public static void main(String[] args) throws IOException {
        Options options = new Options();

        Option environmentOpt = Option.builder("e")
                .longOpt("environment-name")
                .argName("environment")
                .required()
                .hasArg()
                .numberOfArgs(1)
                .desc("name of environment")
                .build();

        Option intervalOpt = Option.builder("i")
                .longOpt("interval")
                .argName("interval")
                .required()
                .hasArg()
                .numberOfArgs(1)
                .type(Number.class)
                .desc("output interval (integer)")
                .build();

        Option variableOpt = Option.builder("v")
                .longOpt("variable")
                .argName("variable")
                .required()
                .hasArg()
                .type(Number.class)
                .desc("output variable (integer)")
                .build();

        options.addOption(environmentOpt);
        options.addOption(intervalOpt);
        options.addOption(variableOpt);

        CommandLineParser cliParser = new DefaultParser();

        CommandLine commandLine = null;
        ESO2CSV converter = null;
        try {
            commandLine = cliParser.parse(options, args);
            String environment = commandLine.getOptionValue('e');
            Number interval = (Number) commandLine.getParsedOptionValue("interval");
            String[] variableStrings = commandLine.getOptionValues("variable");
            int[] variables = new int[variableStrings.length];
            for (int i = 0; i < variableStrings.length; i++) {
                variables[i] = Integer.parseInt(variableStrings[i]);
            }

            converter = new ESO2CSV(environment, interval.intValue(), variables);
        } catch (ParseException e) {
            System.err.println(e.getMessage());
        }

        String[] outputs = (commandLine != null ? commandLine.getArgs() : new String[0]);
        if (outputs.length == 0) {
            exitWithUsage(1);
        }

        // warn that the rest of the arguments are ignored
        if (outputs.length > 2) {
            System.out.println("Ignoring some arguments.");
        }

        ESO eso = new ESOParser().parseFile(new FileInputStream(outputs[0]));

        assert converter != null;
        converter.writeCSV(System.out, eso);
    }

    protected static void exitWithUsage(int status) {
        System.err.println("Usage: ");
        System.exit(status);
    }

    public void writeCSV(Appendable out, ESO eso) throws IOException {
        CSVPrinter csvPrinter = new CSVPrinter(out, csvFormat);
        for (DataPoints dataPoints : selectDataPoints(eso)) {
            List<Object> records = line(dataPoints);
            csvPrinter.printRecord(records);
        }
    }

    protected Iterable<DataPoints> selectDataPoints(ESO eso) {
        List<DataPoints> result = new ArrayList<>();

        for (DataPoints dataPoints : eso.getDataPoints(environment)) {
            if (dataPoints.getItem().getField(0).integerValue() == interval) {
                result.add(dataPoints);
            }
        }

        return result;
    }

    /**
     * Creates a line for with the variables output.
     *
     * @param dataPoints the data points object in the requested environment.
     * @return a row in the csv file.
     */
    protected List<Object> line(DataPoints dataPoints) {
        List<Object> records = new ArrayList<>(variables.length + 1);

        records.add(dateFormat.format(timestamp(dataPoints).getTime()));
        for (int reportCode : variables) {
            ESOItem value = dataPoints.getValue(reportCode);
            String record;
            if (value != null) {
                record = Double.toString(value.getField(1).doubleValue());
            } else {
                record = "";
            }
            records.add(record);
        }

        return records;
    }

    protected Calendar timestamp(DataPoints dataPoints) {
        Item pointsDescriptor = dataPoints.getItem();

        Calendar time = Calendar.getInstance();
        int year = time.get(Calendar.YEAR);
        time.clear();
        time.set(Calendar.MONTH, pointsDescriptor.getField(2).integerValue() - 1);
        time.set(Calendar.DAY_OF_MONTH, pointsDescriptor.getField(3).integerValue());
        time.set(Calendar.YEAR, year);
        time.set(Calendar.HOUR_OF_DAY, pointsDescriptor.getField(5).integerValue());
        time.set(Calendar.MINUTE, (int) (pointsDescriptor.getField(6).doubleValue()));

        return time;
    }
}
