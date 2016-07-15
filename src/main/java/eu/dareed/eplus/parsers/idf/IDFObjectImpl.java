package eu.dareed.eplus.parsers.idf;

import eu.dareed.eplus.model.Field;
import eu.dareed.eplus.model.idf.IDFField;
import eu.dareed.eplus.model.idf.IDFObject;
import eu.dareed.eplus.parsers.AbstractItemImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
class IDFObjectImpl extends AbstractItemImpl implements IDFObject {

    protected final List<IDFField> fields;
    protected final int lineNumber;
    protected final String type;

    public IDFObjectImpl(int lineNumber, String type) {
        this.lineNumber = lineNumber;
        this.type = type;
        this.fields = new ArrayList<>();
    }

    @Override
    public int getLineNumber() {
        return lineNumber;
    }

    @Override
    public Field getField(int index) {
        return fields.get(index);
    }

    @Override
    public List<IDFField> getFields() {
        return Collections.unmodifiableList(fields);
    }

    public boolean addField(IDFField field) {
        return fields.add(field);
    }

    @Override
    public String getType() {
        return type;
    }
}
