package eu.dareed.eplus.parsers.idf.tokens;

import eu.dareed.eplus.parsers.Context;
import eu.dareed.eplus.parsers.Stack;
import eu.dareed.eplus.parsers.Token;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class IDF extends Token {
    public IDF(Context context) {
        super(context);
    }

    @Override
    public void insertCharacter(Character character, Stack stack, Context context) {
        switch (character) {
            case '!':
                Comment comment = new Comment(context);
                stack.push(comment);
                break;
            case '\n':
                break;
            case ' ':
                break;
            default:
                IDFObject object = new IDFObject(context);
                this.addChild(object);

                stack.push(object);

                object.insertCharacter(character, stack, context);
                break;
        }

    }
}
