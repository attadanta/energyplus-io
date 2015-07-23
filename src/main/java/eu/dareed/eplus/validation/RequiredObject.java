package eu.dareed.eplus.validation;

import eu.dareed.eplus.model.idf.IDF;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
class RequiredObject implements ValidityCheck {
    protected final String requiredType;
    protected final IDF idf;

    public RequiredObject(String requiredType, IDF idf) {
        this.requiredType = requiredType;
        this.idf = idf;
    }

    @Override
    public boolean performCheck() {
        return !idf.findObjects(requiredType).isEmpty();
    }
}
