package eu.dareed.eplus.validation;

import eu.dareed.eplus.model.idd.Annotation;
import eu.dareed.eplus.model.idd.IDD;
import eu.dareed.eplus.model.idd.IDDObject;
import eu.dareed.eplus.model.idf.IDF;

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
