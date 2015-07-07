package eu.dareed.eplus;

import eu.dareed.eplus.model.idd.IDD;
import eu.dareed.eplus.parsers.idd.IDDParser;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class DataDictionaryTest {
    @Test
    public void testParseWholeDictionary() throws IOException {
        InputStream resource = DataDictionaryTest.class.getResourceAsStream("/Energy+.idd");
        IDD idd = new IDDParser().parseFile(resource);
        Assert.assertNotNull(idd);
    }
}
