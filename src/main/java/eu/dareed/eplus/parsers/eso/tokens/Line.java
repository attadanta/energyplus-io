package eu.dareed.eplus.parsers.eso.tokens;

import eu.dareed.eplus.parsers.Context;
import eu.dareed.eplus.parsers.Stack;
import eu.dareed.eplus.parsers.Token;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class Line extends Token {

    protected final ESO eso;
    protected Outputs scheduledOutput;

    public Line(Context context, ESO eso) {
        super(context);
        this.eso = eso;
    }

    public Outputs getScheduledOutput() {
        return scheduledOutput;
    }

    public void setScheduledOutput(Outputs scheduledOutput) {
        this.scheduledOutput = scheduledOutput;
    }

    @Override
    public void insertCharacter(Character character, Stack stack, Context context) {
        switch (character) {
            case '\n':
                eso.addChild(this);
                stack.pop();
                break;
            case ' ':
                break;
            default:
                Field field = new Field(context);
                stack.push(field);
                addChild(field);
                field.insertCharacter(character, stack, context);
                break;
        }
    }

    @Override
    public String toString() {
        return "eso.Line{ \"" +
                context.getLine() +
                "\" }";
    }
}
