package eu.knoholem.eplus;

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
        String line = "Site:Location,";
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
    }
}
