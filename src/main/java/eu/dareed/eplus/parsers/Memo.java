package eu.dareed.eplus.parsers;

import java.util.*;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class Memo implements Iterable<Token> {

    protected List<Token> tokens;

    public Memo() {
        this.tokens = new ArrayList<>();
    }

    public Collection<Token> recall() {
        return Collections.unmodifiableCollection(tokens);
    }

    public void memoize(Token token) {
        erase();
        tokens.add(token);
    }

    public void erase() {
        tokens.clear();
    }

    public Iterator<Token> iterator() {
        return tokens.iterator();
    }
}
