package eu.dareed.eplus.parsers.idf;

import eu.dareed.eplus.model.idf.IDFObject;
import eu.dareed.eplus.parsers.AbstractItemImpl;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
class IDFObjectImpl extends AbstractItemImpl implements IDFObject {
    protected final String type;

    public IDFObjectImpl(String type) {
        this.type = type;
    }

    @Override
    public String getType() {
        return type;
    }
}
