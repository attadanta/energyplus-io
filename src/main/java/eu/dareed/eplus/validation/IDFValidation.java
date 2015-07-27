package eu.dareed.eplus.validation;

import eu.dareed.eplus.model.idd.Annotation;
import eu.dareed.eplus.model.idd.IDD;
import eu.dareed.eplus.model.idd.IDDObject;
import eu.dareed.eplus.model.idf.IDF;
import eu.dareed.eplus.model.idf.IDFObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class IDFValidation {
    protected final IDF idf;
    protected final IDD dataDictionary;

    public IDFValidation(IDF idf, IDD dataDictionary) {
        this.idf = idf;
        this.dataDictionary = dataDictionary;
    }

    public List<ValidityCheck> collectValidators() {
        List<ValidityCheck> result = new ArrayList<>();

        result.addAll(initializeGlobalValidators());
        for (IDFObject idfObject : idf.getObjects()) {
            IDDObject dataDictionaryObject = dataDictionary.findObject(idfObject.getType());

            ObjectValidation validation;
            if (dataDictionaryObject == null) {
                validation = new FailingValidation(idf, idfObject, new UnknownObjectType(idfObject));
            } else {
                validation = new ObjectValidation(idf, dataDictionaryObject, idfObject);
            }

            result.addAll(validation.initializeObjectLevelChecks());
            for (FieldValidation fieldValidation : validation.initializeFieldValidations()) {
                result.addAll(fieldValidation.gatherValidityChecks());
            }
        }

        return result;
    }

    public List<ValidityCheck> collectOffences() {
        List<ValidityCheck> result = new ArrayList<>();

        for (ValidityCheck check : collectValidators()) {
            if (!check.performCheck()) {
                result.add(check);
            }
        }

        return result;
    }

    protected List<ValidityCheck> initializeGlobalValidators() {
        List<ValidityCheck> globalValidators = new ArrayList<>();
        for (IDDObject object : dataDictionary.getAllObjects()) {
            for (Annotation annotation : object.getAnnotations()) {
                if (annotation.name().equals("required-object")) {
                    globalValidators.add(new RequiredObject(object.getType(), idf));
                }
            }
        }
        return globalValidators;
    }
}
