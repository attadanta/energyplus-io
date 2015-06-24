package eu.dareed.eplus.model.eso;

import eu.dareed.eplus.model.Item;

import java.util.List;

/**
 * Represents a set of data points belonging to a simulation output.
 *
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public interface CumulativeSimulation {
    Item getItem();

    List<Item> getData();

    CumulativeSimulation getParent();

    List<CumulativeSimulation> getChildren();
}
