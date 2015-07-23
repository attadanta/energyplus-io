package eu.dareed.eplus.validation;

import eu.dareed.eplus.model.Field;
import eu.dareed.eplus.model.idd.Annotation;
import eu.dareed.eplus.model.idd.IDDField;
import eu.dareed.eplus.model.idd.IDDObject;
import eu.dareed.eplus.model.idf.IDF;
import eu.dareed.eplus.model.idf.IDFObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
class ObjectValidation {
    protected final IDF idf;
    protected final IDDObject dataDictionaryObject;
    protected final IDFObject idfObject;

    public ObjectValidation(IDF idf, IDDObject dataDictionaryObject, IDFObject idfObject) {
        this.idf = idf;
        this.dataDictionaryObject = dataDictionaryObject;
        this.idfObject = idfObject;
    }

    protected List<FieldValidation> initializeFieldValidations() {
        List<IDDField> dataDictionaryObjectFields = dataDictionaryObject.getFields();
        List<? extends Field> idfObjectFields = idfObject.getFields();

        int commonFields = Math.min(dataDictionaryObjectFields.size(), idfObjectFields.size());
        List<FieldValidation> result = new ArrayList<>(commonFields);
        for (int i = 0; i < commonFields; i++) {
            result.add(new FieldValidation(dataDictionaryObjectFields.get(i), idfObjectFields.get(i)));
        }

        return result;
    }

    protected List<ValidityCheck> initializeObjectLevelChecks() {
        List<ValidityCheck> checks = new ArrayList<>();

        for (Annotation annotation : dataDictionaryObject.getAnnotations()) {
            switch (annotation.name()) {
                case "unique-object":
                    checks.add(new UniqueObject(idf, idfObject));
                    break;
                case "min-fields":
                    checks.add(new MinimumFields(Integer.parseInt(annotation.asParameter().value()), idfObject));
                    break;
            }
        }

        return checks;
    }
}
