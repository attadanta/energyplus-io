package eu.dareed.eplus.parsers.eso.tokens;

import java.util.*;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class OutputsStack {
    protected final Deque<ScheduledOutput> stack;
    protected final List<ScheduledOutput> roots;
    protected ScheduledOutput currentOutput;
    protected Line lastLine;

    public OutputsStack() {
        this.stack = new LinkedList<>();
        this.roots = new ArrayList<>();
    }

    public List<ScheduledOutput> getRoots() {
        return Collections.unmodifiableList(roots);
    }

    public boolean offer(Line line) {
        boolean dataItem = false;

        if (lastLine == null) {
            if (!isScheduledOutput(line)) {
                throw new RuntimeException("Expected a scheduled output line: " + line.getContext().getLineNumber());
            } else {
                ScheduledOutput scheduledOutput = new ScheduledOutput();
                scheduledOutput.setLine(line);
                enqueue(scheduledOutput);
            }
        } else {
            if (isScheduledOutput(line)) {
                ScheduledOutput scheduledOutput = new ScheduledOutput();
                scheduledOutput.setLine(line);
                enqueue(scheduledOutput);
            } else {
                dataItem = true;
            }
        }

        this.lastLine = line;
        return dataItem;
    }

    public ScheduledOutput getCurrentOutput() {
        return currentOutput;
    }

    public boolean isScheduledOutput(Line line) {
        int controlNumber = Integer.parseInt(line.getChildren().get(0).getContents());
        return controlNumber >= 1 && controlNumber <= 5;
    }

    /**
     * Places the given element in the appropriate place in the stack. It adjusts the current output accordingly.
     *
     * @param scheduledOutput
     */
    protected void enqueue(ScheduledOutput scheduledOutput) {
        if (stack.isEmpty()) {
            stack.addFirst(scheduledOutput);
            roots.add(scheduledOutput);
            return;
        }

        if (scheduledOutput.controlNumber() < stack.peekFirst().controlNumber()) {
            do {
                stack.removeFirst();
            } while (!stack.isEmpty() && scheduledOutput.controlNumber() < stack.peekFirst().controlNumber());
            if (!stack.isEmpty()) {
                stack.removeFirst().setSibling(scheduledOutput);
            }
            if (stack.isEmpty()) {
                roots.add(scheduledOutput);
            }
            stack.addFirst(scheduledOutput);
        } else if (scheduledOutput.controlNumber() == stack.peekFirst().controlNumber()) {
            stack.removeFirst().setSibling(currentOutput);
            stack.addFirst(scheduledOutput);
        } else {
            scheduledOutput.setParent(stack.peekFirst());
            stack.addFirst(scheduledOutput);
        }

        this.currentOutput = scheduledOutput;
    }
}
