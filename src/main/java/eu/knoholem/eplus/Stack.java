package eu.knoholem.eplus;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public interface Stack {

    public Token peek();

    public Token pop();

    public void push(Token token);

    public Memo getMemo();

    public int size();
}
