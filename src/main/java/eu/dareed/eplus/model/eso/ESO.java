package eu.dareed.eplus.model.eso;

import eu.dareed.eplus.model.Item;

import java.util.List;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public interface ESO {
    public List<Item> getDataDictionary();

    public List<Item> getData();
}
