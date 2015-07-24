package eu.dareed.eplus.validation;

import eu.dareed.eplus.model.Field;
import eu.dareed.eplus.model.idd.IDDField;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
class IntegerCheck implements ValidityCheck {
    protected final IDDField iddField;
    protected final Field field;

    public IntegerCheck(IDDField iddField, Field field) {
        this.iddField = iddField;
        this.field = field;
    }

    @Override
    public boolean performCheck() {
        return checkNumericalValue() || checkAutocalculable();
    }

    protected boolean checkNumericalValue() {
        try {
            field.integerValue();
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    protected boolean checkAutocalculable() {
        return field.stringValue().equals("autocalculate") && iddField.isSet("autocalculatable");
    }

    @Override
    public String renderOffence() {
        return "Field " + iddField.getName() + " should be an integer. It is " + field.getRawValue() + " instead.";
    }
}
