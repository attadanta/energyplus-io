package eu.dareed.eplus.validation;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class UnknownObjectType implements ValidityCheck {
    protected final String objectType;

    public UnknownObjectType(String objectType) {
        this.objectType = objectType;
    }

    @Override
    public boolean performCheck() {
        return false;
    }

    @Override
    public String renderOffence() {
        return "Unknown object type: " + objectType + ".";
    }
}
