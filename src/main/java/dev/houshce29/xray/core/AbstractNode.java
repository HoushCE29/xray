package dev.houshce29.xray.core;

/**
 * Abstraction for nodes to handle boiler-plate code.
 */
public abstract class AbstractNode implements Node {
    private final String pathSegment;

    public AbstractNode(String pathSegment) {
        this.pathSegment = pathSegment;
    }

    @Override
    public final String getPathSegment() {
        return pathSegment;
    }

    @Override
    public String toString() {
        return pathSegment;
    }
}
