package eu.dareed.eplus.parsers;

import eu.dareed.eplus.model.Field;
import eu.dareed.eplus.model.Item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * An item implementation based on array list.
 *
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class AbstractItemImpl implements Item {

    protected final List<Field> fields;

    public AbstractItemImpl() {
        this.fields = new ArrayList<>();
    }

    @Override
    public Field getField(int index) {
        return fields.get(index);
    }

    @Override
    public List<? extends Field> getFields() {
        return Collections.unmodifiableList(fields);
    }

    public boolean addItem(Field field) {
        return fields.add(field);
    }

    public void addAllItems(Collection<? extends Field> fields) {
        this.fields.addAll(fields);
    }
}
