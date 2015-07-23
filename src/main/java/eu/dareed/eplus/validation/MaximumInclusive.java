package eu.dareed.eplus.validation;

import eu.dareed.eplus.model.Field;
import eu.dareed.eplus.model.idd.IDDField;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
class MaximumInclusive implements ValidityCheck {
    protected final double referenceValue;
    protected final IDDField iddField;
    protected final Field field;

    MaximumInclusive(double referenceValue, IDDField dataDictionaryField, Field field) {
        this.referenceValue = referenceValue;
        this.iddField = dataDictionaryField;
        this.field = field;
    }

    @Override
    public boolean performCheck() {
        try {
            return field.doubleValue() <= referenceValue;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String renderOffence() {
        return "Field " + iddField.getName()
                + " should be less than or equal to "
                + Double.toString(referenceValue)
                + ". It is " + field.getRawValue() + " instead.";
    }
}
