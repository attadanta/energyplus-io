package eu.dareed.eplus;

import eu.dareed.eplus.model.idf.IDFObject;
import eu.dareed.eplus.parsers.AbstractFieldImplementation;
import eu.dareed.eplus.parsers.AbstractItemImpl;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class ObjectBuilder {
    protected final IDFWriter source;
    protected final WritableIDFObject object;

    public ObjectBuilder(IDFWriter source, String type) {
        this.source = source;
        this.object = new WritableIDFObject(type);
    }

    public void addValue(String rawValue) {
        object.addField(new AbstractFieldImplementation(rawValue));
    }

    public IDFObject build() {
        source.objects.add(object);
        return object;
    }

    protected final class WritableIDFObject extends AbstractItemImpl implements IDFObject {
        protected final String type;

        public WritableIDFObject(String type) {
            this.type = type;
        }

        @Override
        public String getType() {
            return type;
        }
    }
}