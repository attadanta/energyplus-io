package eu.dareed.eplus.parsers.eso.tokens;

import eu.dareed.eplus.parsers.Token;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class ScheduledOutput {
    protected final List<ScheduledOutput> children;
    protected final List<Line> items;
    protected Line line;
    protected ScheduledOutput parent;
    protected ScheduledOutput sibling;

    public ScheduledOutput() {
        this.children = new ArrayList<>();
        this.items = new ArrayList<>();
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public ScheduledOutput getSibling() {
        return sibling;
    }

    public void setSibling(ScheduledOutput sibling) {
        this.sibling = sibling;
    }

    public ScheduledOutput getParent() {
        return parent;
    }

    public void setParent(ScheduledOutput parent) {
        this.parent = parent;
        parent.children.add(this);
    }

    public int controlNumber() {
        Token token = line.getChildren().get(0);
        return Integer.parseInt(token.getContents());
    }

    public List<ScheduledOutput> getChildren() {
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
        return "ScheduledOutput{ " + controlNumber() + ", " + line.getChildren().get(1).getContents() + " }";
    }
}
