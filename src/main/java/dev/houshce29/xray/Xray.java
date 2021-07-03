package dev.houshce29.xray;

import dev.houshce29.xray.core.Node;
import dev.houshce29.xray.core.NodeFactory;
import dev.houshce29.xray.impl.ArrayNodeFactory;
import dev.houshce29.xray.impl.FieldNodeFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Object that xrays an object through a path.
 */
public final class Xray {
    private static final Xray INSTANCE = new Xray();
    private final List<NodeFactory> factories;

    private Xray() {
        this.factories = Collections.unmodifiableList(Arrays.asList(
                new FieldNodeFactory(),
                new ArrayNodeFactory()
        ));
    }

    private Object doXray(Object source, String path) {
        List<Node> nodes = parse(path);
        Object value = source;
        for (Node node : nodes) {
            value = node.visit(value);
            if (value == null) {
                break;
            }
        }
        return value;
    }

    private List<Node> parse(String path) {
        return Arrays.stream(path.split("\\."))
                .flatMap(segment -> parseNodes(segment.trim()).stream())
                .collect(Collectors.toList());
    }

    private List<Node> parseNodes(String segment) {
        for (NodeFactory factory : factories) {
            if (factory.isSupported(segment)) {
                return factory.create(segment);
            }
        }
        throw new IllegalArgumentException("Could not parse path segment: '" + segment + "'");
    }

    /**
     * Xrays the source object all the way through the given path.
     * @param source Source object to xray.
     * @param path Path to xray the given object through.
     * @return The value at the given path.
     */
    public static Object xray(Object source, String path) {
        return INSTANCE.doXray(source, path);
    }
}
