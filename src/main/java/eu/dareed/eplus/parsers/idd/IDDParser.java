package eu.dareed.eplus.parsers.idd;

import eu.dareed.eplus.parsers.FileParser;
import eu.dareed.eplus.parsers.idd.tokens.IDD;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class IDDParser extends FileParser<IDD, eu.dareed.eplus.model.idd.IDD> {

    public IDDParser() {
        super(new IDD(null));
    }

    @Override
    protected eu.dareed.eplus.model.idd.IDD processParseTree() {
        return null;
    }
}
