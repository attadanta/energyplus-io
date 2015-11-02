package eu.dareed.eplus.model.idd;

import java.util.List;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public interface IDD {
    List<IDDObject> getAllObjects();

    IDDObject findObject(String typeName);

    List<String> getGroupNames();

    List<IDDObject> getGroupMembers(String groupName);
}
