package eu.dareed.eplus;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class IDFWriterTest {
    @Test
    public void testSingleValue() throws IOException {
        IDFWriter idfWriter = new IDFWriter();
        ObjectBuilder building = idfWriter.createObject("Building");
        building.build();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(out);
        idfWriter.write(writer);
        writer.flush();
        Assert.assertEquals("Building;\n\n", out.toString("UTF-8"));
    }

    @Test
    public void testMultipleValues() throws IOException {
        IDFWriter idfWriter = new IDFWriter();
        ObjectBuilder building = idfWriter.createObject("Building");
        building.addValue("x");
        building.addValue("y");
        building.build();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(out);
        idfWriter.write(writer);
        writer.flush();
        Assert.assertEquals("Building,\n  x,\n  y;\n\n", out.toString("UTF-8"));
    }
}
