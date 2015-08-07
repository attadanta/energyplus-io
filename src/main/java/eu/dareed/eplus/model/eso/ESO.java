package eu.dareed.eplus.model.eso;

import eu.dareed.eplus.model.Item;

import java.util.List;

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
     * Returns a list of output groups.
     *
     * @return
     */
    List<DataPoints> getDataPoints();
}
