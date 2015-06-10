package eu.dareed.eplus.parsers.idd;

import eu.dareed.eplus.parsers.Parser;
import eu.dareed.eplus.parsers.idd.tokens.IDD;

import java.io.*;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class IDDParser {

    public IDD parse(InputStream inputStream) throws IOException {
        BufferedReader stream = new BufferedReader(new InputStreamReader(inputStream));

        IDD idd = new IDD(null);

        Parser parser = new Parser(idd);
        int lineNumber = 0;
        for (String line = stream.readLine(); line != null; ) {
            lineNumber++;
            line = line + "\n";
            parser.parseLine(line, lineNumber);
            line = stream.readLine();
        }

        return idd;
    }
}
