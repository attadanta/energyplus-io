package eu.dareed.eplus.validation;

import eu.dareed.eplus.model.Field;
import eu.dareed.eplus.model.idd.Annotation;
import eu.dareed.eplus.model.idd.IDDField;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
class FieldValidation {
    protected final IDDField dictionaryField;
    protected final Field field;

    /**
     * Default constructor.
     *
     * @param dictionaryField the field in the data dictionary containing the value constraints.
     * @param objectField     the value to test.
     */
    public FieldValidation(IDDField dictionaryField, Field objectField) {
        this.dictionaryField = dictionaryField;
        this.field = objectField;
    }

    public List<ValidityCheck> gatherValidityChecks() {
        List<ValidityCheck> checks = new ArrayList<>();
        for (Annotation annotation : dictionaryField.getAnnotations()) {
            switch (annotation.name()) {
                case "required-field":
                    checks.add(new RequiredField(dictionaryField, field));
                    break;
                case "maximum":
                    checks.add(new MaximumInclusive(Double.parseDouble(annotation.asParameter().value()), dictionaryField, field));
                    break;
                case "maximum<":
                    checks.add(new Maximum(Double.parseDouble(annotation.asParameter().value()), dictionaryField, field));
                    break;
                case "minimum":
                    checks.add(new MinimumInclusive(Double.parseDouble(annotation.asParameter().value()), dictionaryField, field));
                    break;
                case "minimum>":
                    checks.add(new Minimum(Double.parseDouble(annotation.asParameter().value()), dictionaryField, field));
                    break;
                case "type":
                    String value = annotation.asParameter().value();
                    switch (value) {
                        case "integer":
                            checks.add(new IntegerCheck(dictionaryField, field));
                            break;
                        case "real":
                            checks.add(new RealNumberCheck(dictionaryField, field));
                            break;
                        case "choice":
                            checks.add(new Choice(dictionaryField, field, collectKeys(dictionaryField.getAnnotations())));
                            break;
                    }
            }
        }
        return checks;
    }

    private Collection<String> collectKeys(List<Annotation> annotations) {
        List<String> result = new ArrayList<>();
        for (Annotation annotation : annotations) {
            if (annotation.name().equals("key")) {
                result.add(annotation.asParameter().value());
            }
        }
        return result;
    }
}
