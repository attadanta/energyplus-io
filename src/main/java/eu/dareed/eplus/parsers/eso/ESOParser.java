package eu.dareed.eplus.parsers.eso;

import eu.dareed.eplus.model.Item;
import eu.dareed.eplus.parsers.AbstractItemImpl;
import eu.dareed.eplus.parsers.FileParser;
import eu.dareed.eplus.parsers.Token;
import eu.dareed.eplus.parsers.eso.tokens.ESO;

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

        for (Token token : rootToken.getDataDictionary()) {
            AbstractItemImpl item = new AbstractItemImpl();
            item.addAllFields(asFields(token.getChildren()));
            eso.addDataDictionaryItem(item);
        }

        for (Token token : rootToken.getData()) {
            AbstractItemImpl item = new AbstractItemImpl();
            item.addAllFields(asFields(token.getChildren()));
            eso.addDataItem(item);
        }

        return eso;
    }

    protected Item processVersionStatement(Token versionStatement) {
        AbstractItemImpl item = new AbstractItemImpl();
        item.addAllFields(asFields(versionStatement.getChildren()));
        return item;
    }
}
