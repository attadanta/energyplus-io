package eu.dareed.eplus.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class ZoneEquipment {
    protected final Zone zone;
    protected final List<Equipment> equipment;

    public ZoneEquipment(Zone zone) {
        this.zone = zone;
        this.equipment = new ArrayList<>();
    }

    public List<Equipment> getEquipment() {
        return Collections.unmodifiableList(equipment);
    }

    public Zone getZone() {
        return zone;
    }

    protected boolean addEquipment(Equipment equipment) {
        return this.equipment.add(equipment);
    }
}
