package eu.dareed.eplus.parsers;

import eu.dareed.eplus.model.eso.DataPoints;
import eu.dareed.eplus.model.eso.ESO;
import eu.dareed.eplus.model.eso.ESOItem;
import eu.dareed.eplus.parsers.eso.ESOParser;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class ESOIndexingTest {

    public static ESO eso;

    @BeforeClass
    public static void setup() throws IOException {
        InputStream inputStream = ESOIndexingTest.class.getResourceAsStream("/environments.eso");
        eso = new ESOParser().parseFile(inputStream);
    }

    @Test
    public void testEnvironmentTitles() {
        HashSet<String> expectedTitles = new HashSet<>();
        expectedTitles.add("SUMMER DESIGN DAY");
        expectedTitles.add("WINTER DESIGN DAY");
        expectedTitles.add("RESIDENTIAL");

        Set<String> actualTitles = eso.getEnvironments().keySet();

        Assert.assertEquals(expectedTitles, actualTitles);
    }

    @Test
    public void testEnumerateTitles() {
        List<String> expected = Arrays.asList("SUMMER DESIGN DAY", "WINTER DESIGN DAY", "RESIDENTIAL");
        Assert.assertEquals(expected, eso.enumerateEnvironments());
    }

    @Test
    public void testEnvironmentMetadata() {
        Map<String, ESOItem> actualEnvironments = eso.getEnvironments();
        Assert.assertTrue(actualEnvironments.containsKey("RESIDENTIAL"));
        double delta = 0.01;

        ESOItem residential = actualEnvironments.get("RESIDENTIAL");

        Assert.assertEquals(27.97, residential.getField(2).doubleValue(), delta);
        Assert.assertEquals(-82.53, residential.getField(3).doubleValue(), delta);
        Assert.assertEquals(-5.00, residential.getField(4).doubleValue(), delta);
        Assert.assertEquals(6.00, residential.getField(5).doubleValue(), delta);
    }

    @Test
    public void testEnvironmentOutputs() {
        List<DataPoints> residential = eso.getDataPoints("RESIDENTIAL");
        Assert.assertFalse(residential.isEmpty());

        Assert.assertEquals(8761, residential.size());
    }

    @Test
    public void testDataPointsSequence() {
        List<DataPoints> residential = eso.getDataPoints("RESIDENTIAL");

        DataPoints first = residential.get(0);
        Assert.assertEquals(1, first.getItem().getField(1).integerValue());
        Assert.assertEquals(1, first.getItem().getField(5).integerValue());

        DataPoints second = residential.get(1);
        Assert.assertEquals(1, second.getItem().getField(1).integerValue());
        Assert.assertEquals(2, second.getItem().getField(5).integerValue());

        DataPoints third = residential.get(2);
        Assert.assertEquals(1, third.getItem().getField(1).integerValue());
        Assert.assertEquals(3, third.getItem().getField(5).integerValue());

        DataPoints twentyFifth = residential.get(24);
        Assert.assertEquals(2, twentyFifth.getItem().getField(1).integerValue());
        Assert.assertEquals(1, twentyFifth.getItem().getField(5).integerValue());
    }
}
