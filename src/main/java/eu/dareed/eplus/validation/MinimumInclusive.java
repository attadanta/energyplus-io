package eu.dareed.eplus.validation;

import eu.dareed.eplus.model.idd.IDDField;
import eu.dareed.eplus.model.idf.IDFField;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
class MinimumInclusive implements ObjectLevelCheck {
    protected final double referenceValue;
    protected final IDDField iddField;
    protected final IDFField field;

    MinimumInclusive(double referenceValue, IDDField iddField, IDFField field) {
        this.referenceValue = referenceValue;
        this.iddField = iddField;
        this.field = field;
    }

    @Override
    public boolean performCheck() {
        try {
            return checkBlankField() || field.doubleValue() >= referenceValue;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String renderOffence() {
        return "Field " + iddField.getName()
                + " should be greater than "
                + Double.toString(referenceValue)
                + ". It is " + (field.getRawValue().trim().isEmpty() ? "blank" : field.getRawValue()) + " instead.";
    }

    @Override
    public int getLineNumber() {
        return field.getLineNumber();
    }

    private boolean checkBlankField() {
        return field.getRawValue().trim().isEmpty() && !iddField.isSet("required-field");
    }
}
