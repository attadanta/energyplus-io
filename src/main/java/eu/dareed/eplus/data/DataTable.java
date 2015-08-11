package eu.dareed.eplus.data;

import eu.dareed.eplus.model.Item;
import eu.dareed.eplus.model.eso.DataPoints;
import eu.dareed.eplus.model.eso.ESO;
import eu.dareed.eplus.model.eso.ESOItem;

import java.util.*;

/**
 * Extracts time series of variable outputs in an {@link ESO} file.
 *
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class DataTable {
    protected final ESO eso;
    protected final Map<Integer, Item> variables;

    public DataTable(ESO eso) {
        this.eso = eso;
        this.variables = initialize(eso);
    }

    private Map<Integer, Item> initialize(ESO eso) {
        Map<Integer, Item> variables = new LinkedHashMap<>();
        for (Item item : eso.getDataDictionary()) {
            int reportCode = item.getField(0).integerValue();
            if (reportCode > 5) {
                variables.put(reportCode, item);
            }
        }
        return variables;
    }

    public List<TimeSeries> getTimeSeries(int variable) {
        if (!variables.containsKey(variable)) {
            throw new IllegalArgumentException("Variable not found: " + variable);
        }

        List<TimeSeries> result = new ArrayList<>();
        Map<String, TimeSeries> index = new HashMap<>();

        for (DataPoints dataPoints : eso.getDataPoints()) {
            Item pointsDescriptor = dataPoints.getItem();
            if (pointsDescriptor.getField(0).integerValue() == 2) {
                ESOItem environment = dataPoints.getEnvironment().get(0);
                String environmentName = environment.getField(1).stringValue();

                TimeSeries series;
                if (index.containsKey(environmentName)) {
                    series = index.get(environmentName);
                } else {
                    series = new TimeSeries(environment, variables.get(variable));
                    index.put(environmentName, series);
                    result.add(series);
                }

                ESOItem value = dataPoints.getValue(variable);
                if (value != null) {
                    Calendar time = Calendar.getInstance();
                    int year = time.get(Calendar.YEAR);
                    time.clear();
                    time.set(Calendar.MONTH, pointsDescriptor.getField(2).integerValue() - 1);
                    time.set(Calendar.DAY_OF_MONTH, pointsDescriptor.getField(3).integerValue());
                    time.set(Calendar.YEAR, year);
                    time.set(Calendar.HOUR_OF_DAY, pointsDescriptor.getField(5).integerValue());
                    time.set(Calendar.MINUTE, (int) (pointsDescriptor.getField(6).doubleValue()));

                    Measurement measurement = new Measurement(time, value.getField(1).doubleValue());
                    series.addMeasurement(measurement);
                }
            }
        }

        return result;
    }
}
