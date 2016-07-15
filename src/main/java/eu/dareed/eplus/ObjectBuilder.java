package eu.dareed.eplus;

import eu.dareed.eplus.model.idf.IDFObject;
import eu.dareed.eplus.parsers.AbstractIDFObjectImplementation;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class ObjectBuilder {
    protected final AbstractIDFObjectImplementation object;

    public ObjectBuilder(int lineNumber, String type) {
        this.object = new AbstractIDFObjectImplementation(lineNumber, type);
    }

    public void addValue(String rawValue) {
        object.addField(rawValue);
    }

    public IDFObject getObject() {
        return object;
    }
}
