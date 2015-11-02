package eu.dareed.eplus.validation;

import eu.dareed.eplus.model.idd.IDDField;
import eu.dareed.eplus.model.idf.IDFField;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
class Choice implements ObjectLevelCheck {
    protected final IDDField iddField;
    protected final IDFField field;
    protected final Set<String> choices;

    Choice(IDDField iddField, IDFField field, Collection<String> choices) {
        this.iddField = iddField;
        this.field = field;
        this.choices = new HashSet<>(choices.size());
        this.choices.addAll(choices);
    }

    @Override
    public boolean performCheck() {
        if (field.getRawValue().trim().isEmpty()) {
            return !iddField.isSet("required-field");
        }

        String stringValue = field.stringValue();
        if (iddField.isSet("retaincase")) {
            return choices.contains(stringValue);
        } else {
            return choices.contains(stringValue.toLowerCase());
        }
    }

    @Override
    public String renderOffence() {
        return "Field " + iddField.getName() + " should be one of: " + StringUtils.join(choices, ", ")
                + ". It is " + (field.getRawValue().trim().isEmpty() ? "blank" : field.getRawValue()) + " instead.";
    }

    @Override
    public int getLineNumber() {
        return field.getLineNumber();
    }
}
