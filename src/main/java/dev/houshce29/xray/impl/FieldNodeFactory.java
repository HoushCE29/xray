package dev.houshce29.xray.impl;

import dev.houshce29.xray.core.Node;

import java.util.regex.Pattern;

/**
 * Node factory that produces Field nodes.
 */
public class FieldNodeFactory extends SingleNodeFactory {
    private static final Pattern FIELD_PATTERN = Pattern.compile("[a-zA-Z_$][a-zA-Z0-9_$]*");

    @Override
    public boolean isSupported(String pathSegment) {
        return FIELD_PATTERN.matcher(pathSegment).matches();
    }

    @Override
    public Node createNode(String pathSegment) {
        return new FieldNode(pathSegment);
    }
}
