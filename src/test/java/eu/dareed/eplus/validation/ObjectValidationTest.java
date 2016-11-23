package eu.dareed.eplus.validation;

import eu.dareed.eplus.IDFWriter;
import eu.dareed.eplus.model.idd.IDD;
import eu.dareed.eplus.model.idd.IDDObject;
import eu.dareed.eplus.model.idf.IDFObject;
import eu.dareed.eplus.parsers.idd.IDDParser;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class ObjectValidationTest {
    private static IDD dataDictionary;

    @BeforeClass
    public static void setup() throws IOException {
        InputStream resource = ObjectValidationTest.class.getResourceAsStream("/fixtures/data_dictionary.idd");
        ObjectValidationTest.dataDictionary = new IDDParser().parseFile(resource);
    }

    @Test
    public void testDistributeFieldChecks() {
        IDDObject building = dataDictionary.findObject("Building");
        IDFWriter writer = new IDFWriter();
        IDFObject object = writer.createObject("Building", new String[]{"way:308064632",
                "0.0000000E+00",
                "Suburbs",
                ".04",
                ".004",
                "MinimalShadowing",
                "30"});
        ObjectValidation objectValidation = new ObjectValidation(writer.getIDF(), building, object);
        List<FieldValidation> pairs = objectValidation.initializeFieldValidations();

        Assert.assertEquals(7, pairs.size());

        FieldValidation firstPair = pairs.get(0);
        Assert.assertEquals("A1", firstPair.dictionaryField.getName());
        Assert.assertEquals("way:308064632", firstPair.field.stringValue());

        FieldValidation lastPair = pairs.get(6);
        Assert.assertEquals("N4", lastPair.dictionaryField.getName());
        Assert.assertEquals("30", lastPair.field.stringValue());
    }

    @Test
    public void testInitializeRealFieldChecker() {
        IDDObject building = dataDictionary.findObject("Building");
        IDFWriter writer = new IDFWriter();
        IDFObject object = writer.createObject("Building", new String[]{"way:308064632",
                "0.0000000E+00",
                "Suburbs",
                ".04",
                ".004",
                "MinimalShadowing",
                "30"});
        ObjectValidation objectValidation = new ObjectValidation(writer.getIDF(), building, object);
        List<FieldValidation> pairs = objectValidation.initializeFieldValidations();

        List<ObjectLevelCheck> northFieldChecks = pairs.get(1).gatherValidityChecks();
        Assert.assertEquals(1, northFieldChecks.size());
        Assert.assertEquals(RealNumberCheck.class, northFieldChecks.get(0).getClass());
        Assert.assertTrue(northFieldChecks.get(0).performCheck());
    }

    @Test
    public void testInitializeChoiceFieldChecker() {
        IDDObject building = dataDictionary.findObject("Building");
        IDFWriter writer = new IDFWriter();
        IDFObject object = writer.createObject("Building", new String[]{"way:308064632",
                "0.0000000E+00",
                "Suburbs",
                ".04",
                ".004",
                "MinimalShadowing",
                "30"});
        ObjectValidation objectValidation = new ObjectValidation(writer.getIDF(), building, object);
        List<FieldValidation> pairs = objectValidation.initializeFieldValidations();

        List<ObjectLevelCheck> terrainChecks = pairs.get(2).gatherValidityChecks();
        Assert.assertEquals(1, terrainChecks.size());
        Assert.assertEquals(Choice.class, terrainChecks.get(0).getClass());
        Assert.assertTrue(terrainChecks.get(0).performCheck());
    }

    @Test
    public void testInitializeRangeFieldCheckers() {
        IDDObject building = dataDictionary.findObject("Building");
        IDFWriter writer = new IDFWriter();
        IDFObject object = writer.createObject("Building", new String[]{"way:308064632",
                "0.0000000E+00",
                "Suburbs",
                ".04",
                ".004",
                "MinimalShadowing",
                "30"});
        ObjectValidation objectValidation = new ObjectValidation(writer.getIDF(), building, object);
        List<FieldValidation> pairs = objectValidation.initializeFieldValidations();

        List<ObjectLevelCheck> convergenceChecks = pairs.get(3).gatherValidityChecks();
        Assert.assertEquals(3, convergenceChecks.size());
        Assert.assertEquals(RealNumberCheck.class, convergenceChecks.get(0).getClass());
        Assert.assertTrue(convergenceChecks.get(0).performCheck());

        Assert.assertEquals(Minimum.class, convergenceChecks.get(1).getClass());
        Assert.assertTrue(convergenceChecks.get(1).performCheck());

        Assert.assertEquals(MaximumInclusive.class, convergenceChecks.get(2).getClass());
        Assert.assertTrue(convergenceChecks.get(2).performCheck());
    }

    @Test
    public void testObjectLevelCheckers() {
        IDDObject building = dataDictionary.findObject("Building");
        IDFWriter writer = new IDFWriter();
        IDFObject object = writer.createObject("Building", new String[]{"way:308064632",
                "0.0000000E+00",
                "Suburbs",
                ".04",
                ".004",
                "MinimalShadowing",
                "30"});
        ObjectValidation objectValidation = new ObjectValidation(writer.getIDF(), building, object);
        List<ValidityCheck> objectLevelChecks = objectValidation.initializeObjectLevelChecks();

        Assert.assertEquals(2, objectLevelChecks.size());
        Assert.assertEquals(UniqueObject.class, objectLevelChecks.get(0).getClass());
        Assert.assertTrue(objectLevelChecks.get(0).performCheck());
        Assert.assertEquals(MinimumFields.class, objectLevelChecks.get(1).getClass());
        Assert.assertFalse(objectLevelChecks.get(1).performCheck());
    }
}
