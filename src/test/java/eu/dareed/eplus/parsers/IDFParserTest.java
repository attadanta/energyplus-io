package eu.dareed.eplus.parsers;

import eu.dareed.eplus.parsers.idf.IDFParser;
import eu.dareed.eplus.parsers.idf.tokens.IDF;
import eu.dareed.eplus.parsers.idf.tokens.IDFObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class IDFParserTest {

    @Test
    public void testParseLine() {
        IDF idf = new IDF(null);
        Parser parser = new Parser(idf);
        parser.parseLine("  WindowMaterial:Shade,", 0);

        Assert.assertEquals(2, parser.stack.size());
        Assert.assertEquals(IDFObject.class, parser.stack.peek().getClass());
    }

    @Test
    public void testParseObject() {
        List<String> lines = Arrays.asList(
                "  WindowMaterial:Shade,",
                "MEDIUM REFLECT - MEDIUM TRANS SHADE,  !- Name",
                "0.4,                     !- Solar Transmittance {dimensionless}",
                "0.5,                     !- Solar Reflectance {dimensionless}",
                "0.4,                     !- Visible Transmittance {dimensionless}",
                "0.5;                     !- Visible Reflectance {dimensionless}"
        );

        IDF idf = new IDF(null);
        Parser parser = new Parser(idf);

        for (int i = 0; i < lines.size(); i++) {
            parser.parseLine(lines.get(0), i + 1);
        }

        Assert.assertEquals(1, idf.getChildren().size());
        Assert.assertEquals(6, idf.getChildren().get(0).getChildren().size());
        Assert.assertEquals("WindowMaterial:Shade", idf.getChildren().get(0).getChildren().get(0).getContents());
    }

    @Test
    public void parseFile() throws IOException {
        InputStream resource = IDDParserTest.class.getResourceAsStream("/forum.idf");
        eu.dareed.eplus.model.idf.IDF idf = new IDFParser().parseFile(resource);

        List<eu.dareed.eplus.model.idf.IDFObject> actualItems = idf.getObjects();
        Assert.assertEquals(2, actualItems.size());
        Assert.assertEquals("Version", actualItems.get(0).getType());
        Assert.assertEquals("SimulationControl", actualItems.get(1).getType());
        Assert.assertEquals(9, actualItems.get(0).getLineNumber());
        Assert.assertEquals(13, actualItems.get(1).getLineNumber());
    }
}
