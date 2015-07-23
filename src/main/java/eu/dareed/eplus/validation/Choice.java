package eu.dareed.eplus.validation;

import eu.dareed.eplus.model.Field;
import eu.dareed.eplus.model.idd.IDDField;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
class Choice implements ValidityCheck {
    protected final IDDField iddField;
    protected final Field field;
    protected final Set<String> choices;

    Choice(IDDField iddField, Field field, Collection<String> choices) {
        this.iddField = iddField;
        this.field = field;
        this.choices = new HashSet<>(choices.size());
        this.choices.addAll(choices);
    }

    @Override
    public boolean performCheck() {
        return choices.contains(field.stringValue());
    }

    @Override
    public String renderOffence() {
        return "Field " + iddField.getName() + " should be one of: " + StringUtils.join(choices, ", ") + ". It is " + field.getRawValue() + " instead.";
    }
}
