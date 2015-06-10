package eu.knoholem.eplus;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class Comment extends Token {
    public Comment(Context context) {
        super(context);
    }

    @Override
    public void insertCharacter(Character character, Stack stack, Context context) {
        switch (character) {
            case '\n':
                stack.pop();
                break;
            default:
                contents.append(character);
                break;
        }
    }
}
