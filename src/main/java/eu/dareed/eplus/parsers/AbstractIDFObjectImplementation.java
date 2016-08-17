package eu.dareed.eplus.parsers;

import eu.dareed.eplus.model.Field;
import eu.dareed.eplus.model.idf.IDFField;
import eu.dareed.eplus.model.idf.IDFObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class AbstractIDFObjectImplementation implements IDFObject {
    protected final String type;
    protected final List<IDFField> fields;

    private final int lineNumber;

    public AbstractIDFObjectImplementation(int lineNumber, String type) {
        this.lineNumber = lineNumber;
        this.type = type;
        this.fields = new ArrayList<>();
    }

    @Override
    public Field firstField() {
        if (fields.isEmpty()) {
            throw new NullPointerException("No fields in item.");
        }

        return fields.get(0);
    }

    @Override
    public Field lastField() {
        if (fields.isEmpty()) {
            throw new NullPointerException("No fields in item.");
        }

        return fields.get(fields.size() - 1);
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public int getLineNumber() {
        return lineNumber;
    }

    @Override
    public IDFField getField(int index) {
        return fields.get(index);
    }

    @Override
    public List<IDFField> getFields() {
        return Collections.unmodifiableList(fields);
    }

    public boolean addField(String value) {
        int lineNumber = this.lineNumber + fields.size() + 1;
        return this.fields.add(new AbstractIDFFieldImplementation(lineNumber, value));
    }
}
