package eu.dareed.eplus;

import eu.dareed.eplus.model.idd.IDD;
import eu.dareed.eplus.model.idd.IDDField;
import eu.dareed.eplus.model.idd.IDDObject;
import eu.dareed.eplus.parsers.idd.IDDParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class IDDExport {

    public static final String COL_SEPARATOR = ",";
    public static final String LINE_SEPARATOR = "\n";

    public static void main(String[] args) throws IOException {
        FileInputStream input = new FileInputStream(new File(args[0]));
        IDD idd = new IDDParser().parseFile(input);

        StringBuilder output = new StringBuilder();

        for (String group : idd.getGroupNames()) {
            output.append(group);
            output.append(LINE_SEPARATOR);
            for (IDDObject object : idd.getGroupMembers(group)) {
                // Header section
                output.append(COL_SEPARATOR);
                output.append(object.getType());
                output.append(COL_SEPARATOR);
                output.append(object.getMemo());
                output.append(LINE_SEPARATOR);

                // Fields
                for (IDDField field : object.getFields()) {
                    if (field.getAnnotations().size() > 0) {
                        output.append(COL_SEPARATOR);
                        output.append(COL_SEPARATOR);
                        output.append(field.getAnnotations().get(0).asParameter().value());
                        output.append(LINE_SEPARATOR);
                    }
                }
            }
        }

        System.out.println(output);
    }
}
