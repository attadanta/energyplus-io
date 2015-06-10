package eu.dareed.eplus;

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
                stack.getMemo().memoize(this);
                stack.pop();
                break;
            case ';':
                stack.getMemo().memoize(this);
                stack.pop(); // Remove this
                stack.pop(); // Remove parent object
                break;
            default:
                this.contents.append(character);
                break;
        }
    }
}
