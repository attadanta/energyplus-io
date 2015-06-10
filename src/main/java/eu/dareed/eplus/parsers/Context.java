package eu.dareed.eplus.parsers;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public interface Context {
    public String getLine();
    public int getLineNumber();
    public int getColumn();
    public boolean isIndented();
}
