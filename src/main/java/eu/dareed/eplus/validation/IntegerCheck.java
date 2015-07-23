package eu.dareed.eplus.validation;

import eu.dareed.eplus.model.Field;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
class IntegerCheck implements ValidityCheck {
    protected final Field field;

    public IntegerCheck(Field field) {
        this.field = field;
    }

    @Override
    public boolean performCheck() {
        try {
            field.integerValue();
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
