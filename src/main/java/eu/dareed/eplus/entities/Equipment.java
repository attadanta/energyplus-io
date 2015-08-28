package eu.dareed.eplus.entities;

import eu.dareed.eplus.model.idf.IDFObject;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class Equipment {
    protected final IDFObject object;

    public Equipment(IDFObject object) {
        this.object = object;
    }

    public IDFObject getObject() {
        return object;
    }
}
