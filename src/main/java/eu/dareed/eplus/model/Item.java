package eu.dareed.eplus.model;

import java.util.List;

/**
 * An item is an object that contains multiple fields.
 *
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public interface Item {

    Field getField(int index);

    List<? extends Field> getFields();
}
