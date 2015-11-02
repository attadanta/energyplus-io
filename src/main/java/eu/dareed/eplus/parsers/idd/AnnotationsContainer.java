package eu.dareed.eplus.parsers.idd;

import eu.dareed.eplus.model.idd.AnnotatedObject;
import eu.dareed.eplus.model.idd.Annotation;
import eu.dareed.eplus.model.idd.Parameter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class AnnotationsContainer implements AnnotatedObject {
    protected final List<Annotation> annotations;

    public AnnotationsContainer() {
        this.annotations = new ArrayList<>();
    }

    @Override
    public List<Annotation> getAnnotations() {
        return Collections.unmodifiableList(annotations);
    }

    @Override
    public List<Parameter> getParameters() {
        List<Parameter> result = new ArrayList<>();

        for (Annotation annotation : annotations) {
            if (annotation.isParameter()) {
                result.add((Parameter) annotation);
            }
        }

        return result;
    }

    @Override
    public List<Parameter> getParameters(String name) {
        List<Parameter> result = new ArrayList<>();

        for (Annotation annotation : annotations) {
            if (annotation.isParameter() && annotation.name().equals(name)) {
                result.add((Parameter) annotation);
            }
        }

        return result;
    }

    @Override
    public boolean isSet(String annotationName) {
        for (Annotation annotation : this.annotations) {
            if (annotation.name().equals(annotationName)) {
                return true;
            }
        }

        return false;
    }

    protected boolean addAnnotation(Annotation annotation) {
        return annotations.add(annotation);
    }
}
