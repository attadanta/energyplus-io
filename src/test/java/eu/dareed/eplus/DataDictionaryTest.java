package eu.dareed.eplus;

import eu.dareed.eplus.model.idd.IDD;
import eu.dareed.eplus.model.idd.IDDObject;
import eu.dareed.eplus.parsers.idd.IDDParser;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class DataDictionaryTest {
    private static IDD idd;

    @BeforeClass
    public static void setup() throws IOException {
        InputStream resource = DataDictionaryTest.class.getResourceAsStream("/Energy+.idd");
        idd = new IDDParser().parseFile(resource);
    }

    @Test
    public void testParseWholeDictionary() {
        Assert.assertNotNull(idd);
    }

    @Test
    public void testIgnoreComment() {
        IDDObject iddObject = idd.findObject("GroundHeatTransfer:Basement:EquivSlab");
        Assert.assertNotNull(iddObject);
        System.out.println(iddObject.getMemo());
        Assert.assertEquals("N1", iddObject.getFields().get(0).getName());
    }
}
