package eu.dareed.eplus.parsers.eso;

import eu.dareed.eplus.model.Item;
import eu.dareed.eplus.model.eso.DataPoints;
import eu.dareed.eplus.model.eso.ESOItem;

import java.util.*;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
class DataPointsImpl implements DataPoints {

    protected final Item item;
    protected final Map<Integer, ESOItem> data;
    protected List<ESOItem> environment;

    public DataPointsImpl(Item item) {
        this.item = item;
        this.data = new LinkedHashMap<>();
    }

    @Override
    public Item getItem() {
        return item;
    }

    @Override
    public List<ESOItem> getData() {
        Collection<ESOItem> data = this.data.values();

        List<ESOItem> result = new ArrayList<>(data.size());
        for (ESOItem item : data) {
            result.add(item);
        }

        return result;
    }

    @Override
    public ESOItem getValue(int reportCode) {
        return data.get(reportCode);
    }

    @Override
    public List<ESOItem> getEnvironment() {
        return environment;
    }

    public void setEnvironment(List<ESOItem> environment) {
        this.environment = environment;
    }

    protected ESOItem addDataItem(ESOItem item) {
        return this.data.put(item.getField(0).integerValue(), item);
    }
}
