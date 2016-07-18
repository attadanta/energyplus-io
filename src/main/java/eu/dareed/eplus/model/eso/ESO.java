package eu.dareed.eplus.model.eso;

import eu.dareed.eplus.model.Item;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public interface ESO {
    /**
     * Returns the first line of the output file.
     *
     * @return an item representing the version statement line.
     */
    Item getVersionStatement();

    /**
     * Returns all lines in the Data Dictionary section of the output file.
     *
     * @return a list of items in the data dictionary.
     */
    List<Item> getDataDictionary();

    /**
     * Returns all lines in the Data section of the output file.
     *
     * @return a list of lines appearing after the "End of Data Dictionary" statement of the output.
     */
    List<ESOItem> getData();

    /**
     * Obtains an index of this output's environments.
     *
     * @return a mapping indicating the environment's metadata for each environment title.
     */
    Map<String, ESOItem> getEnvironments();

    /**
     * Obtains a list of environment titles in the sequence they have occurred in the output.
     *
     * @return a list of environment titles.
     */
    List<String> enumerateEnvironments();

    /**
     * Obtains a list of data points in an environment.
     *
     * @param environmentTitle the title of the environment.
     * @return a list of {@link DataPoints}.
     * @see ESO#enumerateEnvironments() enumerateEnvironments
     * @see ESO#getEnvironments() getEnvironments
     */
    List<DataPoints> getDataPoints(String environmentTitle);

    /**
     * Returns all output intervals.
     *
     * @return a list of {@link DataPoints} representing the output intervals in the simulation output.
     */
    List<DataPoints> getDataPoints();
}
