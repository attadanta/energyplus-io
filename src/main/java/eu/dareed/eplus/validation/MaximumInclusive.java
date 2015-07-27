package eu.dareed.eplus.validation;

import eu.dareed.eplus.model.idd.IDDField;
import eu.dareed.eplus.model.idf.IDFField;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
class MaximumInclusive implements ObjectLevelCheck {
    protected final double referenceValue;
    protected final IDDField iddField;
    protected final IDFField field;

    MaximumInclusive(double referenceValue, IDDField dataDictionaryField, IDFField field) {
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

    @Override
    public int getLineNumber() {
        return field.getLineNumber();
    }
}
