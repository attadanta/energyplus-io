package eu.dareed.eplus.parsers.eso;

import eu.dareed.eplus.model.Item;
import eu.dareed.eplus.model.eso.DataPoints;
import eu.dareed.eplus.model.eso.ESO;
import eu.dareed.eplus.model.eso.ESOItem;

import java.util.*;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
class ESOImpl implements ESO {

    protected final List<Item> dataDictionary;
    protected final List<ESOItem> data;
    protected final List<DataPoints> dataPointsList;
    protected Item versionStatement;

    protected Map<String, ESOItem> environmentsMap;
    protected Map<String, List<DataPoints>> dataPointsMap;


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

    @Override
    public Map<String, ESOItem> getEnvironments() {
        return Collections.unmodifiableMap(environmentsMap);
    }

    @Override
    public List<String> enumerateEnvironments() {
        return new ArrayList<>(environmentsMap.keySet());
    }

    @Override
    public List<DataPoints> getDataPoints(String environmentTitle) {
        if (!environmentsMap.containsKey(environmentTitle)) {
            throw new IllegalArgumentException("No environment with title `" + environmentTitle + "' reported in the output.");
        }

        return Collections.unmodifiableList(dataPointsMap.get(environmentTitle));
    }

    protected void setEnvironmentsMap(Map<String, ESOItem> environmentsMap) {
        this.environmentsMap = environmentsMap;
    }

    protected void setDataPointsMap(Map<String, List<DataPoints>> dataPointsMap) {
        this.dataPointsMap = dataPointsMap;
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
