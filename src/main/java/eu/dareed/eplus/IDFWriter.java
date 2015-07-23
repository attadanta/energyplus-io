package eu.dareed.eplus;

import eu.dareed.eplus.model.Field;
import eu.dareed.eplus.model.idf.IDF;
import eu.dareed.eplus.model.idf.IDFObject;
import eu.dareed.eplus.parsers.idf.IDFImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class IDFWriter {
    protected final List<IDFObject> objects;

    public IDFWriter() {
        objects = new ArrayList<>();
    }

    public ObjectBuilder buildObject(String type) {
        ObjectBuilder objectBuilder = new ObjectBuilder(type);
        this.objects.add(objectBuilder.object);
        return objectBuilder;
    }

    public IDF getIDF() {
        return new IDFImpl(objects);
    }

    public IDFObject createObject(String type, String[] values) {
        ObjectBuilder objectBuilder = new ObjectBuilder(type);
        for (String value : values) {
            objectBuilder.addValue(value);
        }
        this.objects.add(objectBuilder.object);
        return objectBuilder.getObject();
    }

    public void write(PrintWriter writer) throws IOException {
        for (IDFObject o : objects) {
            writer.write(o.getType());

            List<? extends Field> fields = o.getFields();
            for (int i = 0; i < fields.size() - 1; i++) {
                writer.write(",\n  ");
                writer.write(fields.get(i).getRawValue());
            }
            if (fields.size() > 0) {
                writer.write(",\n  ");
                writer.write(fields.get(fields.size() - 1).getRawValue());
            }
            writer.write(";\n\n");
        }
    }
}
