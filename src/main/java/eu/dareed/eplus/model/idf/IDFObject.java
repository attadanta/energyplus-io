package eu.dareed.eplus.model.idf;

import eu.dareed.eplus.model.Item;

import java.util.List;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public interface IDFObject extends Item {
    String getType();

    int getLineNumber();

    @Override
    List<IDFField> getFields();
}
