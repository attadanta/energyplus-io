package eu.knoholem.eplus;

import java.util.LinkedList;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
class StackImpl implements Stack {

    protected final LinkedList<Token> stack;
    protected final Memo memo;

    protected StackImpl() {
        this.stack = new LinkedList<Token>();
        this.memo = new Memo();
    }

    public Token peek() {
        return stack.peek();
    }

    public Token pop() {
        return stack.pop();
    }

    public void push(Token token) {
        stack.push(token);
    }

    public int size() {
        return stack.size();
    }

    public Memo getMemo() {
        return memo;
    }
}
