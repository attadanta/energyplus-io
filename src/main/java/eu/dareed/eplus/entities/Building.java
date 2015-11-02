package eu.dareed.eplus.entities;

import eu.dareed.eplus.model.idf.IDF;
import eu.dareed.eplus.model.idf.IDFObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class Building {
    protected final IDF rootFile;

    public Building(IDF rootFile) {
        this.rootFile = rootFile;
    }

    public List<Zone> getZones() {
        List<IDFObject> instances = rootFile.findInstances("Zone");

        List<Zone> zones = new ArrayList<>(instances.size());
        for (IDFObject idfObject : instances) {
            zones.add(new Zone(rootFile, idfObject));
        }

        return zones;
    }
}
