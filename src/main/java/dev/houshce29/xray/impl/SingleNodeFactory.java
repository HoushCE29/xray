package dev.houshce29.xray.impl;

import dev.houshce29.xray.core.Node;
import dev.houshce29.xray.core.NodeFactory;

import java.util.Collections;
import java.util.List;

/**
 * Node factory that produces only a single node at a time.
 */
public abstract class SingleNodeFactory implements NodeFactory {

    @Override
    public List<Node> create(String pathSegment) {
        return Collections.singletonList(createNode(pathSegment));
    }

    /**
     * Creates a single node.
     * @param pathSegment Path segment to create from.
     * @return New node instance.
     */
    public abstract Node createNode(String pathSegment);
}
