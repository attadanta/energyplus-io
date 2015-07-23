package eu.dareed.eplus.validation;

import eu.dareed.eplus.IDFWriter;
import eu.dareed.eplus.model.idd.IDD;
import eu.dareed.eplus.model.idf.IDF;
import eu.dareed.eplus.parsers.idd.IDDParser;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class IDFValidationTest {
    private static IDD dataDictionary;
    private static IDF idf;

    @BeforeClass
    public static void setup() throws IOException {
        InputStream resource = ObjectValidationTest.class.getResourceAsStream("/Energy+.idd");
        IDFValidationTest.dataDictionary = new IDDParser().parseFile(resource);

        IDFWriter writer = new IDFWriter();
        writer.createObject("Building", new String[]{"way:308064632",
                "0.0000000E+00",
                "Suburbs",
                ".04",
                ".004",
                "MinimalShadowing",
                "30"});
        IDFValidationTest.idf = writer.getIDF();
    }

    @Test
    public void testGatherIDFChecks() throws Exception {
        IDFValidation idfValidation = new IDFValidation(idf, dataDictionary);
        Assert.assertFalse(idfValidation.initializeGlobalValidators().isEmpty());
    }
}
