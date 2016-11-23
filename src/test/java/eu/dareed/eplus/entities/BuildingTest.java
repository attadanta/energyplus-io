package eu.dareed.eplus.entities;

import eu.dareed.eplus.model.idf.IDF;
import eu.dareed.eplus.parsers.idf.IDFParser;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class BuildingTest {
    @Test
    public void testBuilding() throws IOException {
        IDF idf = new IDFParser().parseFile(BuildingTest.class.getResourceAsStream("/fixtures/building.idf"));
        Building building = new Building(idf);

        List<Zone> zones = building.getZones();
        Assert.assertEquals(1, zones.size());

        List<Equipment> equipment = zones.get(0).getZoneEquipment().getEquipment();
        Assert.assertEquals(2, equipment.size());
    }
}
