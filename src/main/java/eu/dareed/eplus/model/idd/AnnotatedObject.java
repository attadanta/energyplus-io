package eu.dareed.eplus.model.idd;

import java.util.List;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public interface AnnotatedObject {

    List<Annotation> getAnnotations();

    List<Parameter> getParameters();
    List<Parameter> getParameters(String name);

    boolean isSet(String commentName);
}
