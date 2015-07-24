package eu.dareed.eplus.validation;

import eu.dareed.eplus.model.idf.IDF;
import eu.dareed.eplus.model.idf.IDFObject;

import java.util.Collections;
import java.util.List;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
class FailingValidation extends ObjectValidation {
    protected final ValidityCheck failingCheck;

    public FailingValidation(IDF idf, IDFObject idfObject, ValidityCheck failingCheck) {
        super(idf, null, idfObject);
        this.failingCheck = failingCheck;
    }

    @Override
    protected List<ValidityCheck> initializeObjectLevelChecks() {
        return Collections.singletonList(failingCheck);
    }

    @Override
    protected List<FieldValidation> initializeFieldValidations() {
        return Collections.emptyList();
    }
}
