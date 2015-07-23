package eu.dareed.eplus.validation;

import eu.dareed.eplus.model.Field;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
class RequiredField implements ValidityCheck {
    protected final Field field;

    public RequiredField(Field field) {
        this.field = field;
    }

    @Override
    public boolean performCheck() {
        return !field.getRawValue().trim().isEmpty();
    }
}
