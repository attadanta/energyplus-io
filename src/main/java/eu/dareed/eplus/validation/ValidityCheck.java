package eu.dareed.eplus.validation;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public interface ValidityCheck {
    boolean performCheck();

    String renderOffence();
}
