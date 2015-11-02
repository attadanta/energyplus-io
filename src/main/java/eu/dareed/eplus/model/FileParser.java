package eu.dareed.eplus.model;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public interface FileParser<T> {
    T parseFile(InputStream inputStream) throws IOException;
}
