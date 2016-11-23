package eu.dareed.eplus.parsers.eso.tokens;

import eu.dareed.eplus.parsers.Context;
import eu.dareed.eplus.parsers.Stack;
import eu.dareed.eplus.parsers.Token;

import java.util.LinkedList;
import java.util.List;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class ESO extends Token {
    protected final List<Line> dataDictionary;
    protected final List<Line> data;

    protected final OutputsStack outputsStack;

    protected Line versionStatement;

    protected boolean inDataDictionary = false;
    protected boolean inData = false;

    public ESO(Context context) {
        super(context);

        this.dataDictionary = new LinkedList<>();

        this.outputsStack = new OutputsStack();
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

    public OutputsStack getOutputsStack() {
        return outputsStack;
    }

    public List<Line> getDataDictionary() {
        return dataDictionary;
    }

    public List<Line> getData() {
        return data;
    }

    public Line getVersionStatement() {
        return versionStatement;
    }

    private void processControlStatement(Line line) {
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
        if (outputsStack.place(line)) {
            Outputs currentOutput = outputsStack.getCurrentOutput();

            line.setScheduledOutput(currentOutput);
            currentOutput.addItem(line);
        }

        this.data.add(line);
    }
}
