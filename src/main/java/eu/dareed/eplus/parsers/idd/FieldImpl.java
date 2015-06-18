package eu.dareed.eplus.parsers.idd;

import eu.dareed.eplus.model.idd.Annotation;
import eu.dareed.eplus.model.idd.IDDField;
import eu.dareed.eplus.model.idd.Parameter;

import java.util.List;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
class FieldImpl implements IDDField {

    protected final String name;
    protected final AnnotationsContainer annotations;

    public FieldImpl(String name) {
        this.name = name;
        this.annotations = new AnnotationsContainer();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Annotation> getAnnotations() {
        return annotations.getAnnotations();
    }

    @Override
    public List<Parameter> getParameters() {
        return annotations.getParameters();
    }

    @Override
    public List<Parameter> getParameters(String name) {
        return annotations.getParameters(name);
    }

    @Override
    public boolean isSet(String commentName) {
        return annotations.isSet(commentName);
    }

    @Override
    public String getRawValue() {
        return name;
    }

    @Override
    public int integerValue() {
        throw new UnsupportedOperationException();
    }

    @Override
    public double doubleValue() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String stringValue() {
        return name.trim();
    }

    protected boolean addAnnotation(Annotation annotation) {
        return this.annotations.addAnnotation(annotation);
    }
}
