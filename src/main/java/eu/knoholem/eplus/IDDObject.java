package eu.knoholem.eplus;

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
            case ' ':
                break;
            default:
                Field field = new Field(context);
                this.addChild(field);

                stack.push(field);
                break;
        }
    }
}
