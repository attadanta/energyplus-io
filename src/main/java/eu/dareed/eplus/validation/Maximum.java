package eu.dareed.eplus.validation;

import eu.dareed.eplus.model.idd.IDDField;
import eu.dareed.eplus.model.idf.IDFField;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
class Maximum implements ObjectLevelCheck {
    protected final double referenceValue;
    protected final IDDField iddField;
    protected final IDFField field;

    Maximum(double referenceValue, IDDField iddField, IDFField field) {
        this.referenceValue = referenceValue;
        this.iddField = iddField;
        this.field = field;
    }

    @Override
    public boolean performCheck() {
        try {
            return checkBlankField() || field.doubleValue() < referenceValue;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean checkBlankField() {
        return field.getRawValue().trim().isEmpty() && !iddField.isSet("required-field");
    }

    @Override
    public String renderOffence() {
        return "Field " + iddField.getName() + " should be less than " + Double.toString(referenceValue)
                + ". It is " + (field.getRawValue().trim().isEmpty() ? "blank" : field.getRawValue()) + " instead.";
    }

    @Override
    public int getLineNumber() {
        return field.getLineNumber();
    }
}
