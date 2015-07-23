package eu.dareed.eplus.model.idf;

import java.util.List;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public interface IDF {
    List<IDFObject> findObjects(String typeName);
    List<IDFObject> getObjects();
}
