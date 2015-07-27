package eu.dareed.eplus.validation;

import eu.dareed.eplus.model.idf.IDFObject;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
class UnknownObjectType implements ObjectLevelCheck {
    protected final IDFObject object;

    public UnknownObjectType(IDFObject object) {
        this.object = object;
    }

    @Override
    public boolean performCheck() {
        return false;
    }

    @Override
    public String renderOffence() {
        return "Unknown object type: " + object.getType() + ".";
    }

    @Override
    public int getLineNumber() {
        return object.getLineNumber();
    }
}
