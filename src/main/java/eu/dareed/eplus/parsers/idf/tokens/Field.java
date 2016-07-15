package eu.dareed.eplus.parsers.idf.tokens;

import eu.dareed.eplus.parsers.Context;
import eu.dareed.eplus.parsers.Stack;
import eu.dareed.eplus.parsers.Token;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class Field extends Token {
    public Field(Context context) {
        super(context);
    }

    @Override
    public void insertCharacter(Character character, Stack stack, Context context) {
        switch (character) {
            case ',':
                stack.pop();
                break;
            case ';':
                stack.pop();
                stack.pop();
                break;
            default:
                this.contents.append(character);
                break;
        }
    }
}
