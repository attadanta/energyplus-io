package eu.dareed.eplus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public abstract class Token {

    protected final Context context;
    protected final StringBuilder contents;
    protected final List<Token> children;

    protected Token parent;

    protected Token(Context context) {
        this.context = context;
        this.contents = new StringBuilder();
        this.children = new ArrayList<Token>();
    }

    public abstract void insertCharacter(Character character, Stack stack, Context context);

    public Context getContext() {
        return context;
    }

    public String getContents() {
        return contents.toString();
    }

    public void addChild(Token token) {
        this.children.add(token);
        token.setParent(this);
    }

    public List<Token> getChildren() {
        return Collections.unmodifiableList(children);
    }

    public Token getParent() {
        return parent;
    }

    public void setParent(Token parent) {
        this.parent = parent;
    }
}
