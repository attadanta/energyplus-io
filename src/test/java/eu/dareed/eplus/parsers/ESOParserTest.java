package eu.dareed.eplus.parsers;

import eu.dareed.eplus.model.Field;
import eu.dareed.eplus.model.Item;
import eu.dareed.eplus.model.eso.ESO;
import eu.dareed.eplus.model.eso.ESOItem;
import eu.dareed.eplus.parsers.eso.ESOParser;
import eu.dareed.eplus.parsers.eso.tokens.Outputs;
import eu.dareed.eplus.parsers.eso.tokens.OutputsStack;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class ESOParserTest {

    private InputStream in;

    @Before
    public void setup() throws IOException {
        List<String> lines = Arrays.asList("Program Version,EnergyPlus, Version 8.3.0-6d97d074ea, YMD=2015.06.08 13:40",
                "1,5,Environment Title[],Latitude[deg],Longitude[deg],Time Zone[],Elevation[m]",
                "2,6,Day of Simulation[],Month[],Day of Month[],DST Indicator[1=yes 0=no],Hour[],StartMinute[],EndMinute[],DayType",
                "3,3,Cumulative Day of Simulation[],Month[],Day of Month[],DST Indicator[1=yes 0=no],DayType  ! When Daily Report Variables Requested",
                "4,2,Cumulative Days of Simulation[],Month[]  ! When Monthly Report Variables Requested",
                "5,1,Cumulative Days of Simulation[] ! When Run Period Report Variables Requested",
                "496,11,ZONE001,Zone Thermal Comfort ASHRAE 55 Simple Model Summer or Winter Clothes Not Comfortable Time [hr] !RunPeriod [Value,Min,Month,Day,Hour,Minute,Max,Month,Day,Hour,Minute]",
                "End of Data Dictionary",
                "1,CHICAGO_IL_USA HEATING 99% CONDITIONS,  37.42,  -5.90,   1.00,  31.00",
                "2,1, 1,21, 0, 1, 0.00,60.00,WinterDesignDay",
                "6,-18.1",
                "7,0.0",
                "1,CHICAGO_IL_USA HEATING 99% CONDITIONS,  37.42,  -5.90,   1.00,  31.00",
                "2,1, 1,21, 0, 1, 0.00,60.00,WinterDesignDay",
                "6,-18.1",
                "7,0.0",
                "2,1, 1,21, 0, 1, 0.00,60.00,WinterDesignDay",
                "6,-18.1",
                "7,0.0",
                "End of Data",
                " Number of Records Written=        2352");

        String fileContents = StringUtils.join(lines, "\n");
        in = IOUtils.toInputStream(fileContents);
    }

    @Test
    public void testParseData() throws IOException {
        ESO eso = new ESOParser().parseFile(in);
        Assert.assertEquals("Program Version", eso.getVersionStatement().getField(0).stringValue());
        Assert.assertEquals(6, eso.getDataDictionary().size());
        Assert.assertEquals(11, eso.getData().size());

        Assert.assertEquals(1, eso.getData().get(0).getFields().get(0).integerValue());
        Assert.assertEquals(2, eso.getData().get(1).getFields().get(0).integerValue());
    }

    @Test
    public void testFieldsInDataDictionary() throws IOException {
        ESO eso = new ESOParser().parseFile(in);
        List<Item> dataDictionary = eso.getDataDictionary();

        Item item = dataDictionary.get(dataDictionary.size() - 1);

        List<? extends Field> fields = item.getFields();
        Assert.assertEquals(4, fields.size());
        Assert.assertEquals("Zone Thermal Comfort ASHRAE 55 Simple Model Summer or Winter Clothes Not Comfortable Time [hr] !RunPeriod [Value,Min,Month,Day,Hour,Minute,Max,Month,Day,Hour,Minute]", fields.get(fields.size() - 1).stringValue());
    }

    @Test
    public void testOutputRoots() throws IOException {
        eu.dareed.eplus.parsers.eso.tokens.ESO rootToken = new ESOParser().parse(in);
        OutputsStack queue = rootToken.getOutputsStack();

        List<Outputs> roots = queue.getRoots();
        Assert.assertEquals(2, roots.size());

        Outputs output = roots.get(0);
        Assert.assertEquals(1, output.controlNumber());
        Assert.assertNotNull(output.getSibling());
        Assert.assertNull(output.getParent());
        Assert.assertEquals(1, output.getChildren().size());
        Assert.assertNotNull(output.getChildren().get(0).getParent());
    }

    @Test
    public void testOutputLeaves() throws IOException {
        eu.dareed.eplus.parsers.eso.tokens.ESO rootToken = new ESOParser().parse(in);
        OutputsStack queue = rootToken.getOutputsStack();

        List<Outputs> leaves = queue.leaves();
        Assert.assertEquals(3, leaves.size());

        Outputs firstLeaf = leaves.get(0);
        Assert.assertEquals(2, firstLeaf.controlNumber());
        Assert.assertEquals(0, firstLeaf.getChildren().size());
        Assert.assertNotNull(firstLeaf.getParent());
        Assert.assertNull(firstLeaf.getSibling());

        Outputs secondLeaf = leaves.get(1);
        Assert.assertNotNull(secondLeaf.getSibling());
        Assert.assertEquals(2, secondLeaf.controlNumber());
    }

    @Test
    public void testDataDictionaryItem() throws IOException {
        ESO eso = new ESOParser().parseFile(in);
        ESOItem esoItem = eso.getData().get(0);
        Item dictionaryItem = esoItem.getDictionaryItem();
        Assert.assertNotNull(dictionaryItem);
        Assert.assertEquals(esoItem.getField(0).integerValue(), dictionaryItem.getField(0).integerValue());
    }
}
