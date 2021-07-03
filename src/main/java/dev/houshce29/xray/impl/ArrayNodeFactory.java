package dev.houshce29.xray.impl;

import dev.houshce29.xray.core.Node;
import dev.houshce29.xray.core.NodeFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Factory for producing array-related nodes.
 */
public class ArrayNodeFactory implements NodeFactory {
    private static final Pattern ARRAY_PATTERN = Pattern.compile("[a-zA-Z_$][a-zA-Z0-9_$]*(\\[*[0-9]+\\])+");
    private static final Pattern ARRAY_CAPTURE_PATTERN = Pattern.compile("([a-zA-Z_$][a-zA-Z0-9_$]*)((\\[[0-9]+\\])+)");
    private static final int FIELD_SEGMENT_GROUP = 1;
    private static final int INDEX_CLUSTER_GROUP = 2;

    @Override
    public boolean isSupported(String pathSegment) {
        return ARRAY_PATTERN.matcher(pathSegment).matches();
    }

    @Override
    public List<Node> create(String pathSegment) {
        Matcher matcher = ARRAY_CAPTURE_PATTERN.matcher(pathSegment);
        if (!matcher.matches()) {
            throw new UnsupportedOperationException("Cannot create array node(s) for path: " + pathSegment);
        }
        return create(matcher.group(FIELD_SEGMENT_GROUP), matcher.group(INDEX_CLUSTER_GROUP));
    }

    private static List<Node> create(String fieldSegment, String indexCluster) {
        final List<Node> nodes = new ArrayList<>();
        nodes.add(new FieldNode(fieldSegment));
        nodes.addAll(parseIndexes(fieldSegment, indexCluster));
        return nodes;
    }

    private static List<Node> parseIndexes(final String baseField, String indexCluster) {
        // Remove the square brackets.
        final String[] indexes = indexCluster.replaceAll("\\[", " ")
                .replaceAll("\\]", " ")
                .split("[ ]+");
        StringBuilder path = new StringBuilder(baseField);
        List<Node> nodes = new ArrayList<>();
        for (String index : indexes) {
            parseToInt(index.trim()).ifPresent(intIndex -> {
                path.append('[').append(intIndex).append(']');
                nodes.add(new ArrayNode(path.toString(), intIndex));
            });
        }
        return nodes;
    }

    private static Optional<Integer> parseToInt(String input) {
        if (input.isEmpty()) {
            return Optional.empty();
        }
        try {
            return Optional.of(Integer.parseInt(input));
        }
        catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Could not parse array index: " + input, ex);
        }
    }
}
