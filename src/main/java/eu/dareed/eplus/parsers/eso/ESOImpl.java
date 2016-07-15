package eu.dareed.eplus.parsers.eso;

import eu.dareed.eplus.model.Item;
import eu.dareed.eplus.model.eso.DataPoints;
import eu.dareed.eplus.model.eso.ESO;
import eu.dareed.eplus.model.eso.ESOItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
class ESOImpl implements ESO {

    protected final List<Item> dataDictionary;
    protected final List<ESOItem> data;
    protected final List<DataPoints> dataPointsList;
    protected Item versionStatement;


    public ESOImpl(Item versionStatement) {
        this.versionStatement = versionStatement;

        this.data = new LinkedList<>();
        this.dataDictionary = new ArrayList<>();
        this.dataPointsList = new ArrayList<>();
    }

    @Override
    public Item getVersionStatement() {
        return versionStatement;
    }

    protected void setVersionStatement(Item versionStatement) {
        this.versionStatement = versionStatement;
    }

    @Override
    public List<Item> getDataDictionary() {
        return Collections.unmodifiableList(dataDictionary);
    }

    public List<DataPoints> getDataPoints() {
        return Collections.unmodifiableList(dataPointsList);
    }

    @Override
    public List<ESOItem> getData() {
        return Collections.unmodifiableList(data);
    }

    protected boolean addDataDictionaryItem(Item item) {
        return dataDictionary.add(item);
    }

    protected void addDataPoints(DataPoints dataPoints) {
        this.dataPointsList.add(dataPoints);
    }

    protected boolean addDataItem(ESOItem item) {
        return data.add(item);
    }
}
