package eu.dareed.eplus.parsers.idd.tokens;

import eu.dareed.eplus.parsers.Context;
import eu.dareed.eplus.parsers.Stack;
import eu.dareed.eplus.parsers.Token;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class IDD extends Token {

    protected final List<Comment> comments;

    public IDD(Context context) {
        super(context);
        this.comments = new LinkedList<>();
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
                    this.comments.add(comment);
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

    protected List<Comment> gatherGroups() {
        List<Comment> groups = new ArrayList<>();
        for (Comment comment : comments) {
            if (comment.getContents().startsWith("group")) {
                groups.add(comment);
            }
        }
        return groups;
    }

    public List<GroupRange> collectGroupRanges() {
        List<Comment> groups = gatherGroups();
        LinkedList<GroupRange> groupRanges = new LinkedList<>();
        for (int i = 0; i < groups.size() - 1; i++) {
            String groupName = groupName(groups.get(i));
            int lineStart = groups.get(i).getContext().getLineNumber();
            int lineEnd = groups.get(i + 1).getContext().getLineNumber();
            groupRanges.push(new GroupRange(groupName, lineStart, lineEnd));
        }

        if (groups.size() > 0) {
            Comment lastGroup = groups.get(groups.size() - 1);
            int lineStart = lastGroup.getContext().getLineNumber();
            groupRanges.push(new GroupRange(groupName(lastGroup), lineStart, Integer.MAX_VALUE));
        }

        return groupRanges;
    }

    private String groupName(Comment group) {
        String value = group.getContents();
        return value.substring(6, value.length());
    }
}
