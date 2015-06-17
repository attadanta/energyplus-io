package eu.dareed.eplus.model;

import java.util.List;

/**
 * An item is an object which contains multiple fields.
 *
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public interface Item {

    List<? extends Field> getFields();
}
