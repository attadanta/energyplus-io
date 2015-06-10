package eu.dareed.eplus.parsers.idd.tokens;

import eu.dareed.eplus.parsers.Context;
import eu.dareed.eplus.parsers.Stack;
import eu.dareed.eplus.parsers.Token;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class IDDObject extends Token {

    public IDDObject(Context context) {
        super(context);
    }

    @Override
    public void insertCharacter(Character character, Stack stack, Context context) {
        switch (character) {
            case '\\':
                Comment comment = new Comment(context);
                for (Token token : stack.getMemo().recall()) {
                    token.addChild(comment);
                }
                stack.push(comment);
                break;
            case '\t':
                break;
            case '\n':
                break;
            case ' ':
                break;
            default:
                Field field = new Field(context);
                this.addChild(field);

                stack.push(field);
                field.insertCharacter(character, stack, context);
                break;
        }
    }
}
