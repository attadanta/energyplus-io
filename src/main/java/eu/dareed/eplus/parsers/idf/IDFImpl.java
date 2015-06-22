package eu.dareed.eplus.parsers.idf;

import eu.dareed.eplus.model.idf.IDF;
import eu.dareed.eplus.model.idf.IDFObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
class IDFImpl implements IDF {

    protected List<IDFObject> objects;

    public IDFImpl() {
        this.objects = new ArrayList<>();
    }

    @Override
    public List<IDFObject> getObjects() {
        return Collections.unmodifiableList(objects);
    }
}
