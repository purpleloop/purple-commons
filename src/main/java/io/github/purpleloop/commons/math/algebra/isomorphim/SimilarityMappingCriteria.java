package io.github.purpleloop.commons.math.algebra.isomorphim;

import io.github.purpleloop.commons.math.algebra.Int2DMatrix;

/** The interface for a similarity criteria for graph mappings. */
public interface SimilarityMappingCriteria {

    /**
     * Decide if the nodes in query graph (P[p]) and in data graph (G[g]) are
     * similar enough to be candidates for an isomorphic mapping.
     * 
     * @param queryGraph Adjacency matrix of subgraph to search for
     * @param dataGraph Adjacency matrix of the host/mother graph in which to
     *            search for a match.
     * @param queryIndex index of a node in the query graph
     * @param dataIndex index of a node in the data graph
     * @return a function that returns true if nodes are similar enough, false
     *          otherwise.
     */
    boolean verified(Int2DMatrix queryGraph, Int2DMatrix dataGraph, int queryIndex, int dataIndex);

}
