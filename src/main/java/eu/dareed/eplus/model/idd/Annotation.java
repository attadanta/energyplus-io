package eu.dareed.eplus.model.idd;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public interface Annotation {
    String name();
    boolean isParameter();

    Parameter asParameter();
}
