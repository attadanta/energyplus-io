package eu.dareed.eplus.data;

import java.util.Calendar;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class Measurement {
    public final Calendar time;
    public final double value;

    public Measurement(Calendar time, double value) {
        this.time = time;
        this.value = value;
    }
}
