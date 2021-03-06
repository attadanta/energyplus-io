package eu.dareed.eplus.parsers;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class Parser {
    protected final Stack stack;

    public Parser(Token root) {
        stack = new StackImpl();
        stack.push(root);
    }

    /**
     * Passes the characters in the line through the tokens on the stack.
     *
     * @param line       a newline-terminated string.
     * @param lineNumber the line number currently being processed.
     */
    public void parseLine(String line, int lineNumber) {
        for (int column = 0; column < line.length(); column++) {
            Token token = stack.peek();
            Character character = line.charAt(column);

            token.insertCharacter(character, stack, new ContextImpl(line, lineNumber, column));
        }
    }
}
