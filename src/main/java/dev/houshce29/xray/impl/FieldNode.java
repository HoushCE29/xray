package dev.houshce29.xray.impl;

import dev.houshce29.xray.core.AbstractNode;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

/**
 * Node that is for targeting fields.
 */
public class FieldNode extends AbstractNode {

    /**
     * Creates a new FieldNode.
     * @param pathSegment Path segment, which is the name of the field.
     */
    FieldNode(String pathSegment) {
        super(pathSegment);
    }

    @Override
    public Object visit(Object source) {
        // If the source object is a map, use the path
        // segment as a key.
        if (source instanceof Map) {
            return ((Map) source).get(getPathSegment());
        }
        return visit(source.getClass(), source);
    }

    private Object visit(Class<?> clazz, Object object) {
        Optional<Field> target = Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> getPathSegment().equals(field.getName()))
                .findFirst();
        // If present, perfect -- return the value.
        if (target.isPresent()) {
            return getValue(target.get(), object);
        }
        // Next, check the superclass -- the field may
        // actually be there instead.
        Class<?> superClass = clazz.getSuperclass();
        // If base object, just return null.
        if (superClass.equals(Object.class)) {
            return null;
        }
        // Recurse up the hierarchy
        return visit(superClass, object);
    }

    private static Object getValue(Field field, Object object) {
        field.setAccessible(true);
        try {
            return field.get(object);
        }
        catch (IllegalAccessException ex) {
            throw new IllegalStateException("Failed to get field value.", ex);
        }
    }
}
