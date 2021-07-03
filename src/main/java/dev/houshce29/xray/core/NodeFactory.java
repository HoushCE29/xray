package dev.houshce29.xray.core;

import java.util.List;

/**
 * Factory that produces nodes given that the node type is appropriate.
 */
public interface NodeFactory {

    /**
     * Returns true if this factory can produce valid nodes for the given path segment.
     * @param pathSegment Path segment to check.
     * @return True if this factory can produce a node for the segment.
     */
    boolean isSupported(String pathSegment);

    /**
     * Creates new node instances for the path segment. Given that
     * path segments are the source path split by the '.' delimiter,
     * it's possible that multiple path segments still exist such that
     * more than one node is produced at the given point in the full path.
     * One such example of the path segment representing multiple paths is
     * nested-array notation, such as foo[0][1].
     * @param pathSegment Segment to create nodes for.
     * @return List of created nodes.
     */
    List<Node> create(String pathSegment);
}
