package eu.dareed.eplus.parsers.eso;

import eu.dareed.eplus.model.Item;
import eu.dareed.eplus.model.eso.DataPoints;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
class DataPointsImpl implements DataPoints {

    protected final Item item;
    protected final List<Item> data;
    protected List<Item> environment;

    public DataPointsImpl(Item item) {
        this.item = item;
        this.data = new ArrayList<>();
    }

    @Override
    public Item getItem() {
        return item;
    }

    @Override
    public List<Item> getData() {
        return Collections.unmodifiableList(data);
    }

    @Override
    public List<Item> getEnvironment() {
        return environment;
    }

    public void setEnvironment(List<Item> environment) {
        this.environment = environment;
    }

    protected boolean addDataItem(Item item) {
        return this.data.add(item);
    }
}
