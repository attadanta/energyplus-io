package eu.dareed.eplus.parsers.idd;

import eu.dareed.eplus.model.idd.IDD;
import eu.dareed.eplus.model.idd.IDDObject;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
class IDDImpl implements IDD {

    protected final List<IDDObject> objects;

    public IDDImpl() {
        this.objects = new LinkedList<>();
    }

    @Override
    public List<IDDObject> getAllObjects() {
        return Collections.unmodifiableList(objects);
    }

    @Override
    public IDDObject findObject(String typeName) {
        return null;
    }

    @Override
    public List<String> getGroupNames() {
        return Collections.emptyList();
    }

    @Override
    public List<IDDObject> getGroupMembers(String groupName) {
        return Collections.emptyList();
    }

    protected boolean addObject(IDDObject object) {
        return this.objects.add(object);
    }
}
