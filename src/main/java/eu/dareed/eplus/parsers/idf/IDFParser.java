package eu.dareed.eplus.parsers.idf;

import eu.dareed.eplus.parsers.FileParser;
import eu.dareed.eplus.parsers.Token;
import eu.dareed.eplus.parsers.idf.tokens.IDF;

import java.util.List;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class IDFParser extends FileParser<IDF, eu.dareed.eplus.model.idf.IDF> {
    public IDFParser() {
        super(new IDF(null));
    }

    @Override
    protected eu.dareed.eplus.model.idf.IDF processParseTree() {
        IDFImpl idf = new IDFImpl();

        List<Token> objects = rootToken.getChildren();
        for (Token object : objects) {
            String type = object.getChildren().get(0).getContents();
            IDFObjectImpl item = new IDFObjectImpl(type);
            item.addAllFields(asFields(object.getChildren(), 1));
            idf.objects.add(item);
        }

        return idf;
    }
}
