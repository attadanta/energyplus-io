package eu.dareed.eplus.validation;

import eu.dareed.eplus.model.Field;
import eu.dareed.eplus.model.idd.IDDField;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
class Minimum implements ValidityCheck {
    protected final double referenceValue;
    protected final IDDField iddField;
    protected final Field field;

    Minimum(double referenceValue, IDDField iddField, Field field) {
        this.referenceValue = referenceValue;
        this.iddField = iddField;
        this.field = field;
    }

    @Override
    public boolean performCheck() {
        try {
            return field.doubleValue() > referenceValue;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String renderOffence() {
        return "Field " + iddField.getName()
                + " should be greater than or equal "
                + Double.toString(referenceValue)
                + ". It is " + field.getRawValue() + " instead.";
    }
}
