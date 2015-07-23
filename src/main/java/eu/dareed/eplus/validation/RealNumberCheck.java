package eu.dareed.eplus.validation;

import eu.dareed.eplus.model.Field;
import eu.dareed.eplus.model.idd.IDDField;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
class RealNumberCheck implements ValidityCheck {
    protected final IDDField iddField;
    protected final Field field;

    public RealNumberCheck(IDDField iddField, Field field) {
        this.iddField = iddField;
        this.field = field;
    }

    @Override
    public boolean performCheck() {
        try {
            field.doubleValue();
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String renderOffence() {
        return "Field " + iddField.getName() + " should be a real number. It is " + field.getRawValue() + " instead.";
    }
}
