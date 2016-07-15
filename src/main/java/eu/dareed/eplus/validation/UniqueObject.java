package eu.dareed.eplus.validation;

import eu.dareed.eplus.model.idf.IDF;
import eu.dareed.eplus.model.idf.IDFObject;

import java.util.List;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
class UniqueObject implements ObjectLevelCheck {
    protected final IDF idf;
    protected final IDFObject object;

    public UniqueObject(IDF idf, IDFObject dataDictionaryObject) {
        this.idf = idf;
        this.object = dataDictionaryObject;
    }

    @Override
    public boolean performCheck() {
        List<IDFObject> objects = idf.findInstances(object.getType());
        return objects.size() == 1 && objects.get(0) == object;
    }

    @Override
    public String renderOffence() {
        return "Object " + object.getType() + " should be unique in the IDF file. Multiple files of the same type were found.";
    }

    @Override
    public int getLineNumber() {
        return object.getLineNumber();
    }
}
