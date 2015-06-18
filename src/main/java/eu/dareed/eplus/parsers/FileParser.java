package eu.dareed.eplus.parsers;

import eu.dareed.eplus.model.Field;
import eu.dareed.eplus.model.Item;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public abstract class FileParser<I extends Token, O> implements eu.dareed.eplus.model.FileParser<O> {

    protected final I rootToken;

    protected FileParser(I rootToken) {
        this.rootToken = rootToken;
    }

    protected I parse(InputStream inputStream) throws IOException {
        BufferedReader stream = new BufferedReader(new InputStreamReader(inputStream));

        Parser parser = new Parser(rootToken);
        int lineNumber = 0;
        for (String line = stream.readLine(); line != null; ) {
            lineNumber++;
            line = line + "\n";
            parser.parseLine(line, lineNumber);
            line = stream.readLine();
        }

        return rootToken;
    }

    protected abstract O processParseTree();

    @Override
    public O parseFile(InputStream inputStream) throws IOException {
        parse(inputStream);
        return processParseTree();
    }

    protected List<Field> asFields(List<? extends Token> tokens) {
        return asFields(tokens, 0);
    }

    protected List<Field> asFields(List<? extends Token> tokens, int beginIndex) {
        List<Field> result = new ArrayList<>(tokens.size());

        for (int i = beginIndex; i < tokens.size(); i++) {
            result.add(new AbstractFieldImplementation(tokens.get(i).getContents()));
        }

        return result;
    }
}
