package eu.dareed.eplus.validation;

import eu.dareed.eplus.model.Field;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
class MaximumInclusive implements ValidityCheck {
    protected final double referenceValue;
    protected final Field field;

    MaximumInclusive(double referenceValue, Field field) {
        this.referenceValue = referenceValue;
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
}
