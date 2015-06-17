package eu.dareed.eplus.model;

/**
 * A field represents a value.
 *
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 * @see Item
 */
public interface Field {
    String getRawValue();

    int integerValue();
    double doubleValue();
    String stringValue();
}
