package eu.dareed.eplus.parsers;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public interface Context {
    String getLine();

    int getLineNumber();

    int getColumn();

    boolean isIndented();
}
