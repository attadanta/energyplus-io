package eu.dareed.eplus.parsers.eso;

import eu.dareed.eplus.model.Item;
import eu.dareed.eplus.parsers.AbstractItemImpl;
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
        Map<Integer, Item> dataMap = new HashMap<>();

        for (Token token : rootToken.getDataDictionary()) {
            AbstractItemImpl item = new AbstractItemImpl();
            item.addAllFields(asFields(token.getChildren()));
            eso.addDataDictionaryItem(item);
        }

        for (Token token : rootToken.getData()) {
            AbstractItemImpl item = new AbstractItemImpl();
            item.addAllFields(asFields(token.getChildren()));
            eso.addDataItem(item);
            dataMap.put(token.getContext().getLineNumber(), item);
        }

        for (Outputs outputs : rootToken.getOutputsStack().leaves()) {
            int containerLineNumber = outputs.getLine().getContext().getLineNumber();
            DataPointsImpl dataPoints = new DataPointsImpl(dataMap.get(containerLineNumber));

            for (Line line : outputs.getItems()) {
                dataPoints.addDataItem(dataMap.get(line.getContext().getLineNumber()));
            }

            List<Item> environment = new ArrayList<>();
            Outputs parent = outputs.getParent();
            while (parent != null) {
                environment.add(dataMap.get(parent.getLine().getContext().getLineNumber()));
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
