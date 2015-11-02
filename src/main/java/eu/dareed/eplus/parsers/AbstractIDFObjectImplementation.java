package eu.dareed.eplus.parsers;

import eu.dareed.eplus.model.idf.IDFField;
import eu.dareed.eplus.model.idf.IDFObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class AbstractIDFObjectImplementation implements IDFObject {
    protected final int lineNumber;
    protected final String type;
    protected final List<IDFField> fields;

    public AbstractIDFObjectImplementation(int lineNumber, String type) {
        this.lineNumber = lineNumber;
        this.type = type;
        this.fields = new ArrayList<>();
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
