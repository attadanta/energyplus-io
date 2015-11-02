package eu.dareed.eplus.parsers;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class Parser {
    protected final Stack stack;
    protected final Token root;

    public Parser(Token root) {
        stack = new StackImpl();
        stack.push(root);
        this.root = root;
    }

    public void parseLine(String line, int lineNumber) {
        for (int column = 0; column < line.length(); column++) {
            Token token = stack.peek();
            Character character = line.charAt(column);

            token.insertCharacter(character, stack, new ContextImpl(line, lineNumber, column));
        }
    }
}
