package eu.dareed.eplus.parsers.idd;

import eu.dareed.eplus.model.idd.Parameter;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
class ParameterImpl extends AnnotationImpl implements Parameter {

    protected final String value;

    public ParameterImpl(String name, String value) {
        super(name);
        this.value = value;
    }

    @Override
    public String value() {
        return value;
    }

    @Override
    public boolean isParameter() {
        return true;
    }

    @Override
    public Parameter asParameter() {
        return this;
    }
}
