package eu.dareed.eplus.parsers.eso.tokens;

import java.util.*;

/**
 * A state machine keeping track of the data line memberships in an output file.
 *
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class OutputsStack {
    protected final Deque<Outputs> stack;
    protected final List<Outputs> roots;
    protected Outputs currentOutput;
    protected Line lastLine;

    public OutputsStack() {
        this.stack = new LinkedList<>();
        this.roots = new ArrayList<>();
    }

    public List<Outputs> getRoots() {
        return Collections.unmodifiableList(roots);
    }

    public List<Outputs> leaves() {
        List<Outputs> result = new ArrayList<>();
        Deque<Outputs> queue = new LinkedList<>();

        for (Outputs root : getRoots()) {
            queue.offer(root);
        }
        while (!queue.isEmpty()) {
            Outputs outputs = queue.poll();
            List<Outputs> children = outputs.getChildren();
            if (children.isEmpty()) {
                result.add(outputs);
            } else {
                for (Outputs member : children) {
                    queue.offer(member);
                }
            }
        }

        return result;
    }

    public boolean place(Line line) {
        boolean dataItem = false;

        if (lastLine == null) {
            if (!isControlStatements(line)) {
                throw new RuntimeException("Expected a scheduled output line: " + line.getContext().getLineNumber());
            } else {
                Outputs scheduledOutput = new Outputs();
                scheduledOutput.setLine(line);
                enqueue(scheduledOutput);
            }
        } else {
            if (isControlStatements(line)) {
                Outputs scheduledOutput = new Outputs();
                scheduledOutput.setLine(line);
                enqueue(scheduledOutput);
            } else {
                dataItem = true;
            }
        }

        this.lastLine = line;
        return dataItem;
    }

    public Outputs getCurrentOutput() {
        return currentOutput;
    }

    public boolean isControlStatements(Line line) {
        int controlNumber = Integer.parseInt(line.getChildren().get(0).getContents());
        return controlNumber >= 1 && controlNumber <= 5;
    }

    /**
     * Places the given element in the appropriate place in the stack. It adjusts the current output accordingly.
     *
     * @param output a group of output values.
     */
    protected void enqueue(Outputs output) {
        if (stack.isEmpty()) {
            assert output.controlNumber() == 1;

            stack.addFirst(output);
            roots.add(output);
            return;
        }

        if (output.controlNumber() == 1) {
            do {
                stack.removeFirst();
            } while (!stack.isEmpty() && stack.peekFirst().controlNumber() > 1);
            if (!stack.isEmpty()) {
                Outputs lastOutput = stack.removeFirst();
                lastOutput.setSibling(output);
            }
            if (stack.isEmpty()) {
                roots.add(output);
            }

            stack.addFirst(output);
        } else {
            if (stack.peekFirst().controlNumber() == 1) {
                output.setParent(stack.peekFirst());
            } else {
                Outputs sibling = stack.removeFirst();
                sibling.setSibling(output);

                assert !stack.isEmpty() && stack.peekFirst().controlNumber() == 1;
                output.setParent(stack.peekFirst());
            }

            stack.addFirst(output);
        }

        this.currentOutput = output;
    }
}
