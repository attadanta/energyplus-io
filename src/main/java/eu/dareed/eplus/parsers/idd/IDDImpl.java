package eu.dareed.eplus.parsers.idd;

import eu.dareed.eplus.model.idd.IDD;
import eu.dareed.eplus.model.idd.IDDObject;
import eu.dareed.eplus.parsers.idd.tokens.GroupRange;

import java.util.*;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
class IDDImpl implements IDD {

    protected final Map<GroupRange, List<IDDObject>> groupsMap;
    protected final List<IDDObject> objects;

    public IDDImpl() {
        this.objects = new LinkedList<>();
        this.groupsMap = new LinkedHashMap<>();
    }

    @Override
    public List<IDDObject> getAllObjects() {
        return Collections.unmodifiableList(objects);
    }

    @Override
    public IDDObject findObject(String typeName) {
        for (IDDObject o : objects) {
            if (o.getType().equals(typeName))
                return o;
        }

        return null;
    }

    @Override
    public List<String> getGroupNames() {
        List<String> groupNames = new ArrayList<>(groupsMap.keySet().size());
        for (GroupRange range : groupsMap.keySet()) {
            groupNames.add(range.groupName);
        }
        return groupNames;
    }

    @Override
    public List<IDDObject> getGroupMembers(String groupName) {
        for (GroupRange groupRange : groupsMap.keySet()) {
            if (groupRange.groupName.equals(groupName)) {
                return Collections.unmodifiableList(groupsMap.get(groupRange));
            }
        }
        return Collections.emptyList();
    }

    protected boolean addObject(IDDObject object) {
        return this.objects.add(object);
    }

    protected boolean addObject(IDDObject object, GroupRange groupRange) {
        addObject(object);
        List<IDDObject> group;
        if (groupsMap.containsKey(groupRange)) {
            group = groupsMap.get(groupRange);
        } else {
            group = new ArrayList<>();
            groupsMap.put(groupRange, group);
        }
        return group.add(object);
    }
}
