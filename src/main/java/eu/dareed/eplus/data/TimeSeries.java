package eu.dareed.eplus.data;

import eu.dareed.eplus.model.Item;
import eu.dareed.eplus.model.eso.ESOItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class TimeSeries {
    protected final ESOItem environment;
    protected final Item variable;
    protected final List<Measurement> measurements;

    public TimeSeries(ESOItem environment, Item variable) {
        this.environment = environment;
        this.variable = variable;
        this.measurements = new ArrayList<>();
    }

    public Item getVariable() {
        return variable;
    }

    public String getEnvironmentName() {
        return environment.getField(1).stringValue();
    }

    public ESOItem getEnvironment() {
        return environment;
    }

    public List<Measurement> getMeasurements() {
        return Collections.unmodifiableList(measurements);
    }

    protected void addMeasurement(Measurement measurement) {
        this.measurements.add(measurement);
    }
}
