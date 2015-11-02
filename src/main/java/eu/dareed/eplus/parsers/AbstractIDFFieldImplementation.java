package eu.dareed.eplus.parsers;

import eu.dareed.eplus.model.idf.IDFField;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class AbstractIDFFieldImplementation extends AbstractFieldImplementation implements IDFField {
    protected final int lineNumber;

    public AbstractIDFFieldImplementation(int lineNumber, String value) {
        super(value);
        this.lineNumber = lineNumber;
    }

    @Override
    public int getLineNumber() {
        return lineNumber;
    }
}
