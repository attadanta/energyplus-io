package eu.dareed.eplus.validation;

import eu.dareed.eplus.model.idd.Annotation;
import eu.dareed.eplus.model.idd.IDDField;
import eu.dareed.eplus.model.idf.IDF;
import eu.dareed.eplus.model.idf.IDFField;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
class FieldValidation {
    protected final IDF idf;
    protected final IDDField dictionaryField;
    protected final IDFField field;

    /**
     * Default constructor.
     *
     * @param idf             the input IDF data.
     * @param dictionaryField the field in the data dictionary containing the value constraints.
     * @param objectField     the value to test.
     */
    public FieldValidation(IDF idf, IDDField dictionaryField, IDFField objectField) {
        this.idf = idf;
        this.dictionaryField = dictionaryField;
        this.field = objectField;
    }

    public List<ObjectLevelCheck> gatherValidityChecks() {
        List<ObjectLevelCheck> checks = new ArrayList<>();
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
                        case "object-list":
                            checks.add(new Reference(dictionaryField, field, idf));
                            break;
                        case "real":
                            checks.add(new RealNumberCheck(dictionaryField, field));
                            break;
                        case "choice":
                            checks.add(new Choice(dictionaryField, field, collectKeys(dictionaryField)));
                            break;
                    }
            }
        }
        return checks;
    }

    private Collection<String> collectKeys(IDDField dictionaryField) {
        List<String> result = new ArrayList<>();
        boolean retainCase = dictionaryField.isSet("retaincase");

        for (Annotation annotation : dictionaryField.getAnnotations()) {
            if (annotation.name().equals("key")) {
                String value = annotation.asParameter().value();
                if (retainCase) {
                    result.add(value);
                } else {
                    result.add(value.toLowerCase());
                }
            }
        }
        return result;
    }
}
