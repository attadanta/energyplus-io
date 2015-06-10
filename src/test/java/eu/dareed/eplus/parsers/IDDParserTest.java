package eu.dareed.eplus.parsers;

import eu.dareed.eplus.parsers.idd.IDDParser;
import eu.dareed.eplus.parsers.idd.tokens.IDD;
import eu.dareed.eplus.parsers.idd.tokens.IDDObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class IDDParserTest {

    @Test
    public void testOneLine() {
        String line = "Site:Location,\n";
        int lineNumber = 1;
        IDD idd = new IDD(null);

        Parser parser = new Parser(idd);
        parser.parseLine(line, lineNumber);

        Assert.assertEquals(2, parser.stack.size());
        Assert.assertTrue(parser.stack.peek() instanceof IDDObject);
        Assert.assertEquals(1, parser.stack.peek().getChildren().size());
        Assert.assertEquals("Site:Location", parser.stack.peek().getChildren().get(0).getContents());
    }

    @Test
    public void testReadFile() throws IOException {
        InputStream resource = IDDParserTest.class.getResourceAsStream("/location.idd");
        IDD idd = new IDDParser().parse(resource);

        Assert.assertEquals(1, idd.getChildren().size());

        IDDObject object = (IDDObject) idd.getChildren().get(0);
        Assert.assertEquals(6, object.getChildren().size());
    }
}
