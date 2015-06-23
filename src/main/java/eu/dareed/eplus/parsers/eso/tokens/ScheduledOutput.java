package eu.dareed.eplus.parsers.eso.tokens;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class ScheduledOutput {
    protected final List<Line> head;

    int startIndex;
    int endIndex;

    public ScheduledOutput() {
        this.head = new ArrayList<>();
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    public int incrementEndIndex() {
        endIndex++;
        return endIndex;
    }

    public int[] dataIndizes() {
        return new int[]{startIndex, endIndex};
    }

    public int[] headIndizes() {
        return new int[]{head.get(0).getContext().getLineNumber(), head.get(head.size() - 1).getContext().getLineNumber()};
    }

    public boolean addHeader(Line line) {
        return head.add(line);
    }
}
