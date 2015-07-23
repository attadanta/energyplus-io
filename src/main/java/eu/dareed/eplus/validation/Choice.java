package eu.dareed.eplus.validation;

import eu.dareed.eplus.model.Field;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
class Choice implements ValidityCheck {
    protected final Field field;
    protected final Set<String> choices;

    Choice(Field field, Collection<String> choices) {
        this.field = field;
        this.choices = new HashSet<>(choices.size());
        this.choices.addAll(choices);
    }

    @Override
    public boolean performCheck() {
        return choices.contains(field.stringValue());
    }
}
