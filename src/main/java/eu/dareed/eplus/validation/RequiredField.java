package eu.dareed.eplus.validation;

import eu.dareed.eplus.model.Field;
import eu.dareed.eplus.model.idd.IDDField;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
class RequiredField implements ValidityCheck {
    protected final IDDField iddField;
    protected final Field field;

    public RequiredField(IDDField iddField, Field field) {
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
}
