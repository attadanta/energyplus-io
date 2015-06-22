package eu.dareed.eplus.parsers.idd;

import eu.dareed.eplus.model.idd.Annotation;
import eu.dareed.eplus.model.idd.IDDField;
import eu.dareed.eplus.model.idd.IDDObject;
import eu.dareed.eplus.parsers.FileParser;
import eu.dareed.eplus.parsers.Token;
import eu.dareed.eplus.parsers.idd.tokens.Comment;
import eu.dareed.eplus.parsers.idd.tokens.Field;
import eu.dareed.eplus.parsers.idd.tokens.GroupRange;
import eu.dareed.eplus.parsers.idd.tokens.IDD;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
public class IDDParser extends FileParser<IDD, eu.dareed.eplus.model.idd.IDD> {

    public IDDParser() {
        super(new IDD(null));
    }

    @Override
    protected eu.dareed.eplus.model.idd.IDD processParseTree() {
        IDDImpl idd = new IDDImpl();

        IDD rootToken = this.rootToken;
        List<GroupRange> groupRanges = rootToken.collectGroupRanges();
        for (Token token : rootToken.getChildren()) {
            if (token instanceof eu.dareed.eplus.parsers.idd.tokens.IDDObject) {
                IDDObject iddObject = processIDDObject((eu.dareed.eplus.parsers.idd.tokens.IDDObject) token);
                GroupRange groupRange = null;
                for (GroupRange candidateGroup : groupRanges) {
                    int lineNumber = token.getContext().getLineNumber();
                    if (lineNumber >= candidateGroup.startLine && lineNumber < candidateGroup.endLine) {
                        groupRange = candidateGroup;
                        break;
                    }
                }
                if (groupRange == null) {
                    idd.addObject(iddObject);
                } else {
                    idd.addObject(iddObject, groupRange);
                }
            }
        }

        return idd;
    }

    private IDDObject processIDDObject(eu.dareed.eplus.parsers.idd.tokens.IDDObject token) {
        List<Token> children = token.getChildren();

        IDDObjectImpl iddObject = new IDDObjectImpl(children.get(0).getContents());

        for (Token child : children.get(0).getChildren()) {
            if (child instanceof Comment) {
                iddObject.addAnnotation(processComment((Comment) child));
            }
        }

        for (int i = 1; i < children.size(); i++) {
            Token child = children.get(i);
            if (child instanceof Field) {
                iddObject.addField(processField((Field) child));
            }
        }

        return iddObject;
    }

    private IDDField processField(Field field) {
        FieldImpl iddField = new FieldImpl(field.getContents());

        for (Token token : field.getChildren()) {
            if (token instanceof Comment) {
                iddField.addAnnotation(processComment((Comment) token));
            }
        }

        return iddField;
    }

    private Annotation processComment(Comment comment) {
        Annotation result;

        List<String> tokens = Arrays.asList(comment.getContents().split(" "));
        if (tokens.size() > 1) {
            String value = StringUtils.join(tokens.subList(1, tokens.size()), " ");
            result = new ParameterImpl(tokens.get(0), value);
        } else {
            result = new AnnotationImpl(tokens.get(0));
        }

        return result;
    }
}
