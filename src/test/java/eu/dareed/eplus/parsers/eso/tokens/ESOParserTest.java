package eu.dareed.eplus.parsers.eso.tokens;

import eu.dareed.eplus.parsers.Parser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class ESOParserTest {

    private ESO eso;
    private Parser parser;

    @Before
    public void setup() {
        this.eso = new ESO(null);
        this.parser = new Parser(eso);
    }

    @Test
    public void testParse() {
        List<String> file = Arrays.asList("Program Version,EnergyPlus, Version 8.3.0-6d97d074ea, YMD=2015.06.08 13:40\n",
                "1,5,Environment Title[],Latitude[deg],Longitude[deg],Time Zone[],Elevation[m]\n",
                "2,6,Day of Simulation[],Month[],Day of Month[],DST Indicator[1=yes 0=no],Hour[],StartMinute[],EndMinute[],DayType\n",
                "3,3,Cumulative Day of Simulation[],Month[],Day of Month[],DST Indicator[1=yes 0=no],DayType  ! When Daily Report Variables Requested\n",
                "4,2,Cumulative Days of Simulation[],Month[]  ! When Monthly Report Variables Requested\n",
                "5,1,Cumulative Days of Simulation[] ! When Run Period Report Variables Requested\n",
                "End of Data Dictionary\n",
                "1,CHICAGO_IL_USA HEATING 99% CONDITIONS,  37.42,  -5.90,   1.00,  31.00\n",
                "2,1, 1,21, 0, 1, 0.00,60.00,WinterDesignDay\n",
                "6,-18.1\n",
                "7,0.0\n",
                "End of Data\n",
                " Number of Records Written=        2352\n");

        for (int i = 0; i < file.size(); i++) {
            parser.parseLine(file.get(i), i + 1);
        }

        Assert.assertNotNull(eso.versionStatement);
        Assert.assertEquals(4, eso.data.size());
        Assert.assertEquals(5, eso.dataDictionary.size());
    }
}
