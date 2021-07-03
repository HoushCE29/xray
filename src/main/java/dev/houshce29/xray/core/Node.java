package dev.houshce29.xray.core;

/**
 * Contract for supported path segments.
 */
public interface Node {

    /**
     * Visits this node with the given source object.
     * @param source Source object. This will be the result of a
     *               visit to the previous node, or will be the
     *               original object in the chain.
     * @return The object resolved from this visit.
     */
    Object visit(Object source);

    /**
     * Returns the path segment that this node was built for.
     * @return The source path segment for this node.
     */
    String getPathSegment();
}
