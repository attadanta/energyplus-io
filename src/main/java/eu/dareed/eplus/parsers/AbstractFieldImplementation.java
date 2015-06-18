package eu.dareed.eplus.parsers;

import eu.dareed.eplus.model.Field;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class AbstractFieldImplementation implements Field {

    protected final String value;

    public AbstractFieldImplementation(String value) {
        this.value = value;
    }

    @Override
    public String getRawValue() {
        return value;
    }

    @Override
    public int integerValue() {
        return Integer.parseInt(value);
    }

    @Override
    public double doubleValue() {
        return Double.parseDouble(value);
    }

    @Override
    public String stringValue() {
        return value.trim();
    }
}
