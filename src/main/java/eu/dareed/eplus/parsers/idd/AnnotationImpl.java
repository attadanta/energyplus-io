package eu.dareed.eplus.parsers.idd;

import eu.dareed.eplus.model.idd.Annotation;
import eu.dareed.eplus.model.idd.Parameter;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
class AnnotationImpl implements Annotation {
    protected final String name;

    public AnnotationImpl(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public boolean isParameter() {
        return false;
    }

    @Override
    public Parameter asParameter() {
        throw new UnsupportedOperationException("Annotation `" + name + "' is not a parameter");
    }
}
