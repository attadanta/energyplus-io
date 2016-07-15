package eu.dareed.eplus.validation;

import eu.dareed.eplus.model.idf.IDFObject;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
class MinimumFields implements ObjectLevelCheck {
    protected final int minimumFields;
    protected final IDFObject object;

    public MinimumFields(int minimumFields, IDFObject object) {
        this.minimumFields = minimumFields;
        this.object = object;
    }

    @Override
    public boolean performCheck() {
        return object.getFields().size() >= minimumFields;
    }

    @Override
    public String renderOffence() {
        return "Object " + object.getType() + " should have at least " + minimumFields + " fields. It has " + object.getFields().size() + " instead.";
    }

    @Override
    public int getLineNumber() {
        return object.getLineNumber();
    }
}
