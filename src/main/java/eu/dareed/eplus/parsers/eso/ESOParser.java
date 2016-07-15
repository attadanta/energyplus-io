package eu.dareed.eplus.parsers.eso;

import eu.dareed.eplus.model.Item;
import eu.dareed.eplus.model.eso.ESOItem;
import eu.dareed.eplus.parsers.AbstractItemImpl;
import eu.dareed.eplus.parsers.ESOItemImpl;
import eu.dareed.eplus.parsers.FileParser;
import eu.dareed.eplus.parsers.Token;
import eu.dareed.eplus.parsers.eso.tokens.ESO;
import eu.dareed.eplus.parsers.eso.tokens.Line;
import eu.dareed.eplus.parsers.eso.tokens.Outputs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class ESOParser extends FileParser<ESO, eu.dareed.eplus.model.eso.ESO> {

    public ESOParser() {
        super(new ESO(null));
    }

    @Override
    protected eu.dareed.eplus.model.eso.ESO processParseTree() {
        ESO rootToken = this.rootToken;
        ESOImpl eso = new ESOImpl(processVersionStatement(rootToken.getVersionStatement()));
        Map<Integer, ESOItem> dataMap = new HashMap<>();
        Map<Integer, Item> dictionaryMap = new HashMap<>();

        for (Token token : rootToken.getDataDictionary()) {
            AbstractItemImpl dataDictionaryItem = new AbstractItemImpl();
            dataDictionaryItem.addAllFields(asFields(token.getChildren()));
            eso.addDataDictionaryItem(dataDictionaryItem);
            dictionaryMap.put(dataDictionaryItem.getField(0).integerValue(), dataDictionaryItem);
        }

        for (Token token : rootToken.getData()) {
            ESOItemImpl item = new ESOItemImpl();
            item.addAllFields(asFields(token.getChildren()));
            item.setDataDictionaryItem(dictionaryMap.get(item.getField(0).integerValue()));
            eso.addDataItem(item);
            dataMap.put(token.getContext().getLineNumber(), item);
        }

        for (Outputs outputs : rootToken.getOutputsStack().leaves()) {
            int containerLineNumber = outputs.getLine().getContext().getLineNumber();
            DataPointsImpl dataPoints = new DataPointsImpl(dataMap.get(containerLineNumber));

            for (Line line : outputs.getItems()) {
                dataPoints.addDataItem(dataMap.get(line.getContext().getLineNumber()));
            }

            List<ESOItem> environment = new ArrayList<>();
            Outputs parent = outputs.getParent();
            while (parent != null) {
                ESOItem item = dataMap.get(parent.getLine().getContext().getLineNumber());
                environment.add(item);
                parent = parent.getParent();
            }

            dataPoints.setEnvironment(environment);
            eso.addDataPoints(dataPoints);
        }

        return eso;
    }

    protected Item processVersionStatement(Token versionStatement) {
        AbstractItemImpl item = new AbstractItemImpl();
        item.addAllFields(asFields(versionStatement.getChildren()));
        return item;
    }
}
