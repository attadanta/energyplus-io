package eu.dareed.eplus.parsers;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public interface Stack {

    Token peek();

    Token pop();

    void push(Token token);

    Memo getMemo();

    int size();
}
