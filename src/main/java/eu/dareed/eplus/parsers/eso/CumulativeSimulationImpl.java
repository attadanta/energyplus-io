package eu.dareed.eplus.parsers.eso;

import eu.dareed.eplus.model.Item;
import eu.dareed.eplus.model.eso.CumulativeSimulation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
class CumulativeSimulationImpl implements CumulativeSimulation {

    protected final Item item;
    protected final List<Item> data;
    protected final List<CumulativeSimulation> children;
    protected CumulativeSimulation parent;

    public CumulativeSimulationImpl(Item item) {
        this.item = item;
        this.data = new ArrayList<>();
        this.children = new ArrayList<>();
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
    public CumulativeSimulation getParent() {
        return parent;
    }

    @Override
    public List<CumulativeSimulation> getChildren() {
        return Collections.unmodifiableList(children);
    }

    protected boolean addDataItem(Item item) {
        return this.data.add(item);
    }
}
