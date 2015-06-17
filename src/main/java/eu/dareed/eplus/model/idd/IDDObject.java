package eu.dareed.eplus.model.idd;

import eu.dareed.eplus.model.Item;

import java.util.List;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public interface IDDObject extends Item, AnnotatedObject {
    String getType();

    String getMemo();

    @Override
    List<IDDField> getFields();
}
