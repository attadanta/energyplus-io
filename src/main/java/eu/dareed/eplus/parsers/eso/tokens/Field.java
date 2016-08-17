package eu.dareed.eplus.parsers.eso.tokens;

import eu.dareed.eplus.parsers.Context;
import eu.dareed.eplus.parsers.Stack;
import eu.dareed.eplus.parsers.Token;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class Field extends Token {
    private boolean inBracket;

    public Field(Context context) {
        super(context);
        this.inBracket = false;
    }

    @Override
    public void insertCharacter(Character character, Stack stack, Context context) {
        switch (character) {
            case '[':
                inBracket = true;
                this.contents.append(character);
                break;
            case ']':
                inBracket = false;
                this.contents.append(character);
                break;
            case ',':
                if (inBracket) {
                    this.contents.append(character);
                } else {
                    stack.pop();
                }
                break;
            case '\n':
                stack.pop();
                stack.peek().insertCharacter(character, stack, context);
                break;
            default:
                this.contents.append(character);
                break;
        }
    }
}
