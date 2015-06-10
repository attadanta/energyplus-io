package eu.dareed.eplus.parsers.idd.tokens;

import eu.dareed.eplus.parsers.Context;
import eu.dareed.eplus.parsers.Stack;
import eu.dareed.eplus.parsers.Token;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class IDD extends Token {

    public IDD(Context context) {
        super(context);
    }

    public void insertCharacter(Character character, Stack stack, Context context) {
        switch (character) {
            case '\\':
                Comment comment = new Comment(context);
                stack.push(comment);

                if (context.isIndented()) {
                    for (Token token : stack.getMemo().recall()) {
                        if (token instanceof Field) {
                            token.addChild(comment);
                        }
                    }
                } else {
                    this.addChild(comment);
                }
                break;
            case '\n':
                stack.getMemo().erase();
                break;
            case ' ':
                break;
            default:
                IDDObject object = new IDDObject(context);
                this.addChild(object);

                stack.push(object);

                object.insertCharacter(character, stack, context);
                break;
        }
    }
}
