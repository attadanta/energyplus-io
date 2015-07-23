package eu.dareed.eplus.validation;

import eu.dareed.eplus.model.Field;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
class RealNumberCheck implements ValidityCheck {
    protected final Field field;

    public RealNumberCheck(Field field) {
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
}
