package eu.dareed.eplus.parsers.idd;

import eu.dareed.eplus.parsers.FileParser;
import eu.dareed.eplus.parsers.Parser;
import eu.dareed.eplus.parsers.idd.tokens.IDD;

import java.io.*;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class IDDParser extends FileParser<IDD> {

    public IDDParser() {
        super(new IDD(null));
    }
}
