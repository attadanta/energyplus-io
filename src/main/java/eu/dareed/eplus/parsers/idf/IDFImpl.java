package eu.dareed.eplus.parsers.idf;

import eu.dareed.eplus.model.Field;
import eu.dareed.eplus.model.idf.IDF;
import eu.dareed.eplus.model.idf.IDFObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class IDFImpl implements IDF {
    protected List<IDFObject> objects;

    public IDFImpl() {
        this.objects = new ArrayList<>();
    }

    public IDFImpl(List<IDFObject> objects) {
        this();
        this.objects.addAll(objects);
    }

    @Override
    public List<IDFObject> findInstances(String typeName) {
        List<IDFObject> foundObjects = new ArrayList<>();
        for (IDFObject o : objects) {
            if (o.getType().equals(typeName)) {
                foundObjects.add(o);
            }
        }
        return foundObjects;
    }

    @Override
    public IDFObject findObject(String name) {
        for (IDFObject idfObject : objects) {
            List<? extends Field> fields = idfObject.getFields();
            if (fields.size() > 0 && fields.get(0).getRawValue().equals(name)) {
                return idfObject;
            }
        }

        return null;
    }

    @Override
    public List<IDFObject> getObjects() {
        return Collections.unmodifiableList(objects);
    }
}
