package eu.dareed.eplus.validation;

import eu.dareed.eplus.model.idd.IDDField;
import eu.dareed.eplus.model.idf.IDF;
import eu.dareed.eplus.model.idf.IDFField;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
class Reference implements ObjectLevelCheck {
    protected final IDDField iddField;
    protected final IDFField field;
    protected final IDF idf;

    public Reference(IDDField dictionaryField, IDFField field, IDF idf) {
        this.iddField = dictionaryField;
        this.field = field;
        this.idf = idf;
    }

    @Override
    public boolean performCheck() {
        return idf.findObject(field.getRawValue()) != null;
    }

    @Override
    public String renderOffence() {
        return "Field " + iddField.getName()
                + " should contain a reference to an existing object. "
                + (field.getRawValue().trim().isEmpty() ? "It was blank instead." : "No object with name "
                + field.getRawValue() + " was found in the IDF file.");
    }

    @Override
    public int getLineNumber() {
        return field.getLineNumber();
    }
}
