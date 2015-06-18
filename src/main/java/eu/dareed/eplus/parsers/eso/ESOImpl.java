package eu.dareed.eplus.parsers.eso;

import eu.dareed.eplus.model.Item;
import eu.dareed.eplus.model.eso.ESO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
class ESOImpl implements ESO {

    protected Item versionStatement;

    protected final List<Item> dataDictionary;
    protected final List<Item> data;

    public ESOImpl(Item versionStatement) {
        this.versionStatement = versionStatement;

        this.data = new LinkedList<>();
        this.dataDictionary = new ArrayList<>();
    }

    @Override
    public Item getVersionStatement() {
        return versionStatement;
    }

    @Override
    public List<Item> getDataDictionary() {
        return Collections.unmodifiableList(dataDictionary);
    }

    @Override
    public List<Item> getData() {
        return Collections.unmodifiableList(data);
    }

    protected void setVersionStatement(Item versionStatement) {
        this.versionStatement = versionStatement;
    }

    protected boolean addDataDictionaryItem(Item item) {
        return dataDictionary.add(item);
    }

    protected boolean addDataItem(Item item) {
        return data.add(item);
    }
}
