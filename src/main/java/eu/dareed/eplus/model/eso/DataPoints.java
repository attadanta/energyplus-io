package eu.dareed.eplus.model.eso;

import eu.dareed.eplus.model.Item;

import java.util.List;

/**
 * Represents a set of data points belonging to a simulation output.
 *
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public interface DataPoints {
    /**
     * Returns the item describing this simulation output.
     *
     * @return simulation output metadata.
     */
    Item getItem();

    /**
     * Returns the list of items belonging to this output.
     *
     * @return a list of data items.
     */
    List<ESOItem> getData();

    /**
     * Returns the value of a simulation output.
     *
     * @param reportCode the report code of the output variable.
     * @return an item.
     */
    ESOItem getValue(int reportCode);

    /**
     * Returns the lines representing this output's environment.
     *
     * @return this output's environment metadata. This list should always be of size one.
     */
    List<ESOItem> getEnvironment();
}
