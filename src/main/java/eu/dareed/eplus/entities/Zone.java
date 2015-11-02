package eu.dareed.eplus.entities;

import eu.dareed.eplus.model.idf.IDF;
import eu.dareed.eplus.model.idf.IDFField;
import eu.dareed.eplus.model.idf.IDFObject;

import java.util.List;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class Zone {
    protected final IDF root;
    protected final IDFObject object;

    public Zone(IDF root, IDFObject object) {
        this.root = root;
        this.object = object;
    }

    public String getName() {
        return object.getField(0).stringValue();
    }

    public ZoneEquipment getZoneEquipment() {
        ZoneEquipment equipment = new ZoneEquipment(this);

        for (IDFObject connections : root.findInstances("ZoneHVAC:EquipmentConnections")) {
            if (connections.getField(0).stringValue().equalsIgnoreCase(getName())) {
                String listName = connections.getField(1).stringValue();

                for (IDFObject list : root.findInstances("ZoneHVAC:EquipmentList")) {
                    if (list.getField(0).stringValue().equalsIgnoreCase(listName)) {
                        List<IDFField> fields = list.getFields();
                        for (int i = 1; i < fields.size(); i = i + 4) {
                            String equipmentName = fields.get(i + 1).stringValue();
                            IDFObject object = root.findObject(equipmentName);
                            if (object != null) {
                                equipment.addEquipment(new Equipment(object));
                            }
                        }
                    }
                }
            }
        }

        return equipment;
    }
}
