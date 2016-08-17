package eu.dareed.eplus.parsers.idd;

import eu.dareed.eplus.model.Field;
import eu.dareed.eplus.model.idd.Annotation;
import eu.dareed.eplus.model.idd.IDDField;
import eu.dareed.eplus.model.idd.IDDObject;
import eu.dareed.eplus.model.idd.Parameter;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author <a href="mailto:kiril.tonev@kit.edu">Kiril Tonev</a>
 */
final class IDDObjectImpl implements IDDObject {

    private final String type;
    private final List<IDDField> fields;
    private final AnnotationsContainer annotations;

    IDDObjectImpl(String type) {
        this.type = type;

        this.fields = new ArrayList<>();
        this.annotations = new AnnotationsContainer();
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getMemo() {
        List<String> memo = new LinkedList<>();

        for (Parameter parameter : annotations.getParameters("memo")) {
            memo.add(parameter.value());
        }

        return StringUtils.join(memo, " ");
    }

    @Override
    public Field firstField() {
        if (fields.isEmpty()) {
            throw new NullPointerException("No fields in item.");
        }

        return fields.get(0);
    }

    @Override
    public Field lastField() {
        if (fields.isEmpty()) {
            throw new NullPointerException("No fields in item.");
        }

        return fields.get(fields.size() - 1);
    }

    @Override
    public IDDField getField(int index) {
        return fields.get(index);
    }

    @Override
    public List<IDDField> getFields() {
        return Collections.unmodifiableList(fields);
    }

    @Override
    public List<Annotation> getAnnotations() {
        return annotations.getAnnotations();
    }

    @Override
    public List<Parameter> getParameters() {
        return annotations.getParameters();
    }

    @Override
    public List<Parameter> getParameters(String name) {
        return annotations.getParameters(name);
    }

    @Override
    public Parameter getParameter(String name) {
        return annotations.getParameter(name);
    }

    @Override
    public boolean isSet(String annotationName) {
        return annotations.isSet(annotationName);
    }

    boolean addAnnotation(Annotation annotation) {
        return annotations.addAnnotation(annotation);
    }

    boolean addField(IDDField field) {
        return this.fields.add(field);
    }
}
