package eu.dareed.eplus.parsers;

import eu.dareed.eplus.model.idd.Annotation;
import eu.dareed.eplus.parsers.idd.IDDParser;
import eu.dareed.eplus.parsers.idd.tokens.IDD;
import eu.dareed.eplus.parsers.idd.tokens.IDDObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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
        InputStream resource = IDDParserTest.class.getResourceAsStream("/fixtures/location.idd");
        IDD idd = new IDDParser().parse(resource);

        Assert.assertEquals(1, idd.getChildren().size());

        IDDObject object = (IDDObject) idd.getChildren().get(0);
        Assert.assertEquals(6, object.getChildren().size());
    }

    @Test
    public void testIDDInterface() throws IOException {
        InputStream resource = IDDParserTest.class.getResourceAsStream("/fixtures/location.idd");
        eu.dareed.eplus.model.idd.IDD idd = new IDDParser().parseFile(resource);

        Assert.assertEquals(1, idd.getAllObjects().size());

        List<Annotation> objectLevelComments = idd.getAllObjects().get(0).getAnnotations();
        Assert.assertEquals(4, objectLevelComments.size());
        Assert.assertTrue(objectLevelComments.get(0).isParameter());
        Assert.assertTrue(objectLevelComments.get(1).isParameter());

        Assert.assertEquals("unique-object", objectLevelComments.get(2).name());
        Assert.assertEquals("min-fields", objectLevelComments.get(3).name());
        Assert.assertEquals("5", objectLevelComments.get(3).asParameter().value());
    }

    @Test
    public void testGroups() throws IOException {
        InputStream resource = IDDParserTest.class.getResourceAsStream("/fixtures/groups.idd");
        eu.dareed.eplus.model.idd.IDD idd = new IDDParser().parseFile(resource);

        Assert.assertFalse(idd.getGroupNames().isEmpty());
        Assert.assertTrue(idd.getGroupMembers("locations").isEmpty());
        Assert.assertFalse(idd.getGroupMembers("Simulation Parameters").isEmpty());
        Assert.assertEquals(3, idd.getGroupMembers("Simulation Parameters").size());
        Assert.assertEquals(5, idd.getAllObjects().size());
    }
}
