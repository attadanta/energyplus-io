package eu.knoholem.eplus;

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

                Field field = new Field(context);
                object.addChild(field);

                stack.push(object);
                stack.push(field);

                field.insertCharacter(character, stack, context);
        }
    }
}
