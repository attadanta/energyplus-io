package eu.dareed.eplus.parsers.eso.tokens;

import eu.dareed.eplus.parsers.Context;
import eu.dareed.eplus.parsers.Stack;
import eu.dareed.eplus.parsers.Token;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class ESO extends Token {

    protected final List<Line> dataDictionary;
    protected final List<Line> data;

    protected final List<ScheduledOutput> scheduledOutputs;
    protected ScheduledOutput currentOutput;
    protected Line lastLine;

    protected Line versionStatement;

    protected boolean inDataDictionary = false;
    protected boolean inData = false;

    public ESO(Context context) {
        super(context);

        this.dataDictionary = new LinkedList<>();

        this.scheduledOutputs = new ArrayList<>();
        this.data = new LinkedList<>();
    }

    @Override
    public void insertCharacter(Character character, Stack stack, Context context) {
        Line line = new Line(context, this);
        stack.push(line);
        line.insertCharacter(character, stack, context);
    }

    @Override
    public void addChild(Token token) {
        Line line = (Line) token;

        if (line.getChildren().size() == 1) {
            processControlStatement(line);
        } else if (inDataDictionary) {
            dataDictionary.add(line);
        } else if (inData) {
            processDataItem(line);
        } else if (line.getContext().getLineNumber() == 1) {
            this.versionStatement = (Line) token;
            inDataDictionary = true;
        }

        super.addChild(token);
    }

    public List<Line> getDataDictionary() {
        return dataDictionary;
    }

    public List<ScheduledOutput> getScheduledOutputs() {
        return scheduledOutputs;
    }

    public List<Line> getData() {
        return data;
    }

    public Line getVersionStatement() {
        return versionStatement;
    }

    protected void processControlStatement(Line line) {
        String statement = line.getChildren().get(0).getContents();

        if (statement.equalsIgnoreCase("End of Data Dictionary")) {
            inDataDictionary = false;
            inData = true;
        }

        if (statement.equalsIgnoreCase("End of Data")) {
            inData = false;
        }
    }

    private void processDataItem(Line line) {
        if (updateScheduledOutput(line)) {
            scheduledOutputs.add(currentOutput);
        }

        this.lastLine = line;
        this.data.add(line);
    }

    private boolean updateScheduledOutput(Line line) {
        // This should check for errors.
        ScheduledOutput output;
        boolean newOutput = false;
        if (lastLine != null) {
            if (isScheduledOutputHeader(lastLine)) {
                output = currentOutput;
                if (isScheduledOutputHeader(line)) {
                    output.addHeader(line);
                } else {
                    output.setStartIndex(line.getContext().getLineNumber());
                    output.setEndIndex(line.getContext().getLineNumber());
                }
            } else {
                if (isScheduledOutputHeader(line)) {
                    output = new ScheduledOutput();
                    this.currentOutput = output;
                    output.addHeader(line);
                    newOutput = true;
                } else {
                    output = currentOutput;
                    output.incrementEndIndex();
                }
            }
        } else {
            output = new ScheduledOutput();
            this.currentOutput = output;
            output.addHeader(line);
            newOutput = true;
        }
        return newOutput;
    }

    private boolean isScheduledOutputHeader(Line line) {
        int controlNumber = Integer.parseInt(line.getChildren().get(0).getContents());
        return controlNumber >= 1 && controlNumber <= 5;
    }
}
