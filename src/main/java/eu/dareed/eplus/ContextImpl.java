package eu.dareed.eplus;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
class ContextImpl implements Context {
    protected final String line;
    protected final int lineNumber;
    protected final int column;

    public ContextImpl(String line, int lineNumber, int column) {
        this.line = line;
        this.lineNumber = lineNumber;
        this.column = column;
    }

    public String getLine() {
        return line;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public int getColumn() {
        return column;
    }

    public boolean isIndented() {
        return line.startsWith(" ") || line.startsWith("\t");
    }

    @Override
    public String toString() {
        return "Context{" +
                "line='" + line + '\'' +
                ", lineNumber=" + lineNumber +
                '}';
    }
}
