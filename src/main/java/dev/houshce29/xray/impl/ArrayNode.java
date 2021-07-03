package dev.houshce29.xray.impl;

import dev.houshce29.xray.core.AbstractNode;

import java.util.Collection;
import java.util.Iterator;

/**
 * Node for arrays/collections.
 */
public class ArrayNode extends AbstractNode {
    private final int index;

    ArrayNode(String pathSegment, int index) {
        super(pathSegment);
        this.index = index;
    }

    @Override
    public Object visit(Object source) {
        if (source.getClass().isArray()) {
            return visitArray((Object[]) source);
        }
        else if (source instanceof Collection) {
            return visitCollection((Collection) source);
        }
        return null;
    }

    /**
     * @return The target index in the array/collection.
     */
    public int getIndex() {
        return index;
    }

    private Object visitArray(Object[] array) {
        if (array.length <= index || index < 0) {
            return null;
        }
        return array[index];
    }

    private Object visitCollection(Collection collection) {
        if (collection.size() <= index || index < 0) {
            return null;
        }
        Iterator iterator = collection.iterator();
        int current = 0;
        Object next = null;
        while (current <= index) {
            next = iterator.next();
            current++;
        }
        return next;
    }
}
