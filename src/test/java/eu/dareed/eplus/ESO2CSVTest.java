package eu.dareed.eplus;

import org.apache.commons.cli.*;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class ESO2CSVTest {

    @Test
    public void testSingleValueArgument() throws ParseException {
        Options options = new Options();

        Option variable = Option.builder("v").argName("variable")
                .required()
                .longOpt("variable")
                .hasArg()
                .desc("output variable")
                .build();

        options.addOption(variable);

        CommandLineParser parser = new DefaultParser();

        String[] args = new String[]{"-variable=12"};
        CommandLine commandLine = parser.parse(options, args);

        Assert.assertTrue(commandLine.hasOption('v'));
        Assert.assertTrue(commandLine.hasOption("variable"));
        Assert.assertEquals("12", commandLine.getOptionValue("variable"));
    }

    @Test
    public void testMultiValuedArgument() throws ParseException {
        Options options = new Options();

        Option variable = Option.builder("v").argName("variable")
                .required()
                .longOpt("variable")
                .hasArg()
                .desc("output variable")
                .build();

        options.addOption(variable);

        CommandLineParser parser = new DefaultParser();

        String[] args = new String[]{"-variable=12", "-v=14", "-v=13"};
        CommandLine commandLine = parser.parse(options, args);

        Assert.assertEquals(3, commandLine.getOptionValues('v').length);
    }

    /**
     * Number of arguments option is not what you expect.
     */
    @Test
    public void testRestrictNumberOfValues() throws ParseException {
        Options options = new Options();

        Option interval = Option.builder("i").argName("interval")
                .required()
                .longOpt("interval")
                .hasArg()
                .numberOfArgs(1)
                .desc("interval")
                .build();

        options.addOption(interval);

        CommandLineParser parser = new DefaultParser();

        String[] args = new String[]{"-i=12", "-i=13"};
        CommandLine commandLine = parser.parse(options, args);
        Assert.assertNotEquals(1, commandLine.getOptionValues('i').length);
    }

    /**
     * Numeric types should be typed as {@link Number}s.
     *
     * @throws ParseException never thrown
     */
    @Test
    public void testArgumentType() throws ParseException {
        Options options = new Options();

        Option interval = Option.builder("i").argName("interval")
                .required()
                .longOpt("interval")
                .hasArg()
                .type(Number.class)
                .desc("interval")
                .build();

        options.addOption(interval);

        CommandLineParser parser = new DefaultParser();

        String[] args = new String[]{"-i=12"};
        CommandLine commandLine = parser.parse(options, args);
        Assert.assertEquals(12L, commandLine.getParsedOptionValue("interval"));
    }

    @Test
    public void testRestOfArguments() throws ParseException {
        Options options = new Options();

        Option interval = Option.builder("i").argName("interval")
                .required()
                .longOpt("interval")
                .hasArg()
                .type(Number.class)
                .desc("interval")
                .build();

        options.addOption(interval);

        CommandLineParser parser = new DefaultParser();

        String[] args = new String[]{"-i=12", "yaba", "daba", "doo"};
        CommandLine commandLine = parser.parse(options, args);
        Assert.assertArrayEquals(new String[]{"yaba", "daba", "doo"}, commandLine.getArgs());
    }

    @Test(expected = MissingOptionException.class)
    public void testRequiredArguments() throws ParseException {
        Options options = new Options();

        Option interval = Option.builder("i").argName("interval")
                .required()
                .longOpt("interval")
                .hasArg()
                .type(Number.class)
                .desc("interval")
                .build();

        options.addOption(interval);

        CommandLineParser parser = new DefaultParser();

        String[] args = new String[]{"yaba", "daba", "doo"};
        parser.parse(options, args);
    }
}
