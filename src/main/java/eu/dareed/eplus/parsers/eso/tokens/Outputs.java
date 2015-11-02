package eu.dareed.eplus.parsers.eso.tokens;

import eu.dareed.eplus.parsers.Token;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class represents a collection of data points. Each object may be linked with its parents (in the case of
 * environments), siblings and children.
 *
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class Outputs {
    protected final List<Outputs> children;
    protected final List<Line> items;
    protected Line line;
    protected Outputs parent;
    protected Outputs sibling;

    public Outputs() {
        this.children = new ArrayList<>();
        this.items = new ArrayList<>();
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    /**
     * Returns the outputs group which appears immediately after this group.
     *
     * @return the consequent outputs group.
     */
    public Outputs getSibling() {
        return sibling;
    }

    /**
     * Sets the outputs group which appears immediately after this group.
     *
     * @param sibling the consequent outputs group.
     */
    public void setSibling(Outputs sibling) {
        this.sibling = sibling;
    }

    public Outputs getParent() {
        return parent;
    }

    public void setParent(Outputs parent) {
        this.parent = parent;
        if (parent != null) {
            parent.children.add(this);
        }
    }

    public int controlNumber() {
        Token token = line.getChildren().get(0);
        return Integer.parseInt(token.getContents());
    }

    public List<Outputs> getChildren() {
        return Collections.unmodifiableList(children);
    }

    public List<Line> getItems() {
        return Collections.unmodifiableList(items);
    }

    public boolean addItem(Line line) {
        return this.items.add(line);
    }

    @Override
    public String toString() {
        return "Outputs{ " + controlNumber() + ", " + line.getChildren().get(1).getContents() + " }";
    }
}
