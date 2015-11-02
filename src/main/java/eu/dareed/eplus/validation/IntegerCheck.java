package eu.dareed.eplus.validation;

import eu.dareed.eplus.model.idd.IDDField;
import eu.dareed.eplus.model.idf.IDFField;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
class IntegerCheck implements ObjectLevelCheck {
    protected final IDDField iddField;
    protected final IDFField field;

    public IntegerCheck(IDDField iddField, IDFField field) {
        this.iddField = iddField;
        this.field = field;
    }

    @Override
    public boolean performCheck() {
        return checkBlankField() || checkNumericalValue() || checkAutocalculable();
    }

    private boolean checkBlankField() {
        return field.getRawValue().trim().isEmpty() && !iddField.isSet("required-field");
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
        return "Field " + iddField.getName() + " should be an integer. It is " + (field.getRawValue().isEmpty() ? "blank" : field.getRawValue()) + " instead.";
    }

    @Override
    public int getLineNumber() {
        return field.getLineNumber();
    }
}
