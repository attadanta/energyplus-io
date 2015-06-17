package eu.dareed.eplus.parsers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public abstract class FileParser<T extends Token> {

    protected final T rootToken;

    public FileParser(T rootToken) {
        this.rootToken = rootToken;
    }

    public T parse(InputStream inputStream) throws IOException {
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
}
