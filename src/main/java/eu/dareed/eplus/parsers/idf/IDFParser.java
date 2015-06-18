package eu.dareed.eplus.parsers.idf;

import eu.dareed.eplus.model.Item;
import eu.dareed.eplus.parsers.FileParser;
import eu.dareed.eplus.parsers.idf.tokens.IDF;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class IDFParser extends FileParser<IDF, Item> {
    public IDFParser() {
        super(new IDF(null));
    }

    @Override
    protected Item processParseTree() {
        return null;
    }
}
