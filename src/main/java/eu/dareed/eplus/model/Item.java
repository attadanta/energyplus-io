package eu.dareed.eplus.model;

import java.util.List;

/**
 * An item is an object that contains an ordered collection of {@link Field}s.
 *
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public interface Item {
    Field firstField();

    Field lastField();

    Field getField(int index);

    List<? extends Field> getFields();
}
