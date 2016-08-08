package eu.dareed.eplus;

import eu.dareed.eplus.model.idf.IDF;
import eu.dareed.eplus.model.idf.IDFObject;
import eu.dareed.eplus.parsers.idf.IDFParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Provides an overview of the objects in an IDF file.
 *
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class Glossary {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: Glossary [input.idf]");
            System.exit(1);
        }

        IDF idf = null;
        try (InputStream input = new FileInputStream(new File(args[0]))) {
            idf = new IDFParser().parseFile(input);
        } catch (IOException e) {
            System.err.printf("Could not read input file: %s\n", e.getMessage());
            System.exit(1);
        }

        List<IDFObject> idfObjects = idf.getObjects();
        Set<String> objects = new LinkedHashSet<>(idf.getObjects().size());
        Map<String, Integer> occurrences = new HashMap<>();

        for (IDFObject idfObject : idfObjects) {
            String objectType = idfObject.getType();

            int numOfOccurrences = occurrences.containsKey(objectType) ? occurrences.get(objectType) + 1 : 1;

            objects.add(objectType);
            occurrences.put(objectType, numOfOccurrences);
        }

        for (String objectType : objects) {
            System.out.printf("%s, %d\n", objectType, occurrences.get(objectType));
        }
    }
}
