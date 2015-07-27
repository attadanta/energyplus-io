package eu.dareed.eplus.validation;

import eu.dareed.eplus.model.idd.IDDField;
import eu.dareed.eplus.model.idf.IDFField;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
class RequiredField implements ObjectLevelCheck {
    protected final IDDField iddField;
    protected final IDFField field;

    public RequiredField(IDDField iddField, IDFField field) {
        this.iddField = iddField;
        this.field = field;
    }

    @Override
    public boolean performCheck() {
        return !field.getRawValue().trim().isEmpty();
    }

    @Override
    public String renderOffence() {
        return "Field " + iddField.getName() + " is required and should not be blank.";
    }

    @Override
    public int getLineNumber() {
        return field.getLineNumber();
    }
}
