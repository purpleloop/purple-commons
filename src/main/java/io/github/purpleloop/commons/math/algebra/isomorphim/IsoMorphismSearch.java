package io.github.purpleloop.commons.math.algebra.isomorphim;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import io.github.purpleloop.commons.math.algebra.Int2DMatrix;

/**
 * This class implements the J.R. Ullman’s Algorithm for Subgraph Isomorphism.
 */
public class IsoMorphismSearch {

    /** Class logger. */
    private static final Log LOG = LogFactory.getLog(IsoMorphismSearch.class);

    /** Private constructor to prevent instantiation. */
    private IsoMorphismSearch() {
        // Nothing to do here
    }

    /**
     * @param mappingMatrix the proposed morphism of query graph in data graph
     * @param queryGraph the query sub graph being matched
     * @param dataGraph the data graph
     */
    public static void pruneOptions(Int2DMatrix mappingMatrix, Int2DMatrix queryGraph,
            Int2DMatrix dataGraph) {

        // For each row of the mapping matrix (node index of query graph)
        for (int row = 0; row < mappingMatrix.getHeight(); row++) {

            // For each column of the mapping matrix (node index of data graph)
            for (int col = 0; col < mappingMatrix.getWidth(); col++) {

                // If nodes 'row' and 'col' are mapped
                if (mappingMatrix.get(col, row) == 1) {

                    // For all neighbors x of node 'row' in the query graph
                    for (int x = 0; x < queryGraph.getWidth(); x++) {

                        if (queryGraph.get(x, row) == 1) {

                            // x is a node in query graph that is adjacent to
                            // 'row'

                            // check if there is no neighbor y of node 'col' in
                            // the data graph such that
                            // M[x][y] == 1

                            boolean hasNeighbourY = false;
                            for (int y = 0; y < dataGraph.getWidth(); y++) {
                                if (dataGraph.get(col, y) == 1) {
                                    hasNeighbourY = true;
                                    break;
                                }
                            }

                            if (!hasNeighbourY) {

                                // 'row' and 'col' are not mapped anymore
                                mappingMatrix.set(row, col, 0);
                            }

                        }
                    }
                }
            }
        }

    }

    /**
     * Determines if the nodes in query graph (P[p]) and in data graph (G[g])
     * are similar enough to be candidates for an isomorphic mapping.
     *
     * This is the default implementation relies on the node connectivity. It
     * uses the degree, number of (outgoing) links of the nodes to determine
     * similarity.
     *
     * @param queryGraph Adjacency matrix of subgraph to search for
     * @param dataGraph Adjacency matrix of the host/mother graph in which to
     *            search for a match.
     * @param queryIndex index of a node in the query graph
     * @param dataIndex index of a node in the data graph
     * @returns a function that returns true if nodes are similar enough, false
     *          otherwise.
     */
    public static final SimilarityMappingCriteria DEGREE_CRITERIA = (queryGraph, dataGraph,
            queryIndex, dataIndex) -> {
        int queryNodeDegree = queryGraph.arraySumRow(queryIndex);
        int dataNodeDegree = dataGraph.arraySumRow(dataIndex);

        // The connectivity (degree) of the node in the query graph is lesser or
        // equals than the one of the mapping candidate node in the data graph
        return queryNodeDegree <= dataNodeDegree;
    };

    /**
     * @param testedMappingMatrix the matrix to test is it is an isomorphism
     * @return a mapping function for each node (row) from a source graph to
     *         another target graph for a given mapping matrix.
     */
    public static MappingFunction mapQueryToDataGraph(Int2DMatrix testedMappingMatrix) {

        // For a given row (index of a node in the source graph), the mapping
        // function returns (first, should be unique) the corresponding column
        // (index of the matched node in the target graph)
        return row -> {
            int width = testedMappingMatrix.getWidth();
            for (int col = 0; col < width; col++) {
                if (testedMappingMatrix.get(col, row) == 1) {
                    return col;
                }
            }

            // added for completeness, to check
            return 0;
        };
    }

    /**
     * Check if a given matrix is an isomorphism of the query graph matrix in
     * data graph matrix.
     * 
     * @param testedMappingMatrix the matrix to test is it is an isomorphism
     * @param dataGraphMatrix Adjacency matrix of the host/mother graph in which
     *            to search for a match.
     * @param queryGraphMatrix Adjacency matrix of subgraph to search for
     * 
     * @return true if tested mapping matrix is an isomprphism, false otherwise
     */
    public static boolean isIsoMorphism(Int2DMatrix testedMappingMatrix,
            Int2DMatrix dataGraphMatrix, Int2DMatrix queryGraphMatrix) {

        int rows = queryGraphMatrix.getHeight();

        MappingFunction mappingFunction = mapQueryToDataGraph(testedMappingMatrix);

        for (int r1 = 0; r1 < rows; r1++) {

            for (int r2 = 0; r2 < rows; r2++) {

                // If nodes are adjacent in query graph matrix P
                if (queryGraphMatrix.get(r1, r2) == 1) {

                    // We find the mapped nodes in data graph matrix G
                    int c1 = mappingFunction.map(r1);
                    int c2 = mappingFunction.map(r2);

                    // are they adjacent in data graph G?
                    if (dataGraphMatrix.get(c1, c2) != 1) {
                        // no - not isomorphism
                        return false;
                    }

                }
            }

        }

        // Ok no failed mapping encountered, this is an isomorphism
        return true;

    }

    /**
     * A recursive method that searches for isomorphisms of a query graph in a
     * data graph.
     *
     * @param boundedDataNodes array of nodes that are bounded (used) in the
     *            data graph
     * @param currentRow the current row (index of the node to bind in the query
     *            graph)
     * @param dataGraph Adjacency matrix of the host/mother graph in which to
     *            search for a match.
     * @param queryGraph Adjacency matrix of subgraph to search for
     * @param mappingMatrix mapping matrix candidate
     * @param collectedIsomorphisms The list of collected isomorphisms
     * @param maxNumIsoMorph the maximum number isomorphisms to find, may return
     *            fewer if fewer are matched, is specified.
     * @param prune true if pruning of the matrix mapping is enabled, false
     *            otherwise
     */
    public static void recurseMorphismSearch(boolean[] boundedDataNodes, int currentRow,
            Int2DMatrix dataGraph, Int2DMatrix queryGraph, Int2DMatrix mappingMatrix,
            List<Int2DMatrix> collectedIsomorphisms, Integer maxNumIsoMorph, boolean prune) {

        if (currentRow == mappingMatrix.getHeight()) {

            // Recursive search reached the last row of the mappingMatrix.
            // Check mapping matrix is an isomorphism.

            if (isIsoMorphism(mappingMatrix, dataGraph, queryGraph)) {

                // The matrix is an isomorphism
                // Collects a copy of the current mapping matrix as a solution
                collectedIsomorphisms.add(new Int2DMatrix(mappingMatrix));
            }

        } else {
            // There are remaining nodes to explore (rows in the mapping matrix)

            // Derives by copy a new mapping matrix
            Int2DMatrix newMappingMatrix = new Int2DMatrix(mappingMatrix);

            // Prune the proposed morphism to remove mappings candidates/options
            // that are obviously not possible.
            if (prune) {
                pruneOptions(newMappingMatrix, queryGraph, dataGraph);
            }

            int numMappingCols = mappingMatrix.getWidth();

            // Tries a testedCol considering for all unused columns
            for (int testedCol = 0; testedCol < numMappingCols; testedCol++) {

                // only explore if the nodes are candidates for matching and the
                // column has not been set yet.
                if (!boundedDataNodes[testedCol] && mappingMatrix.get(testedCol, currentRow) == 1) {

                    // set column testedCol in newMappingMatrix to 1 and other
                    // columns to 0
                    for (int colToUpdate = 0; colToUpdate < numMappingCols; colToUpdate++) {
                        newMappingMatrix.set(colToUpdate, currentRow,
                                (colToUpdate == testedCol) ? 1 : 0);
                    }

                    // Marks the data node at index testedCol as bounded
                    boundedDataNodes[testedCol] = true;

                    // If one want to find more isomorphisms.
                    if (maxNumIsoMorph == null || collectedIsomorphisms.size() < maxNumIsoMorph) {

                        // Recurse, considering a next node (current row) to
                        // match
                        recurseMorphismSearch(boundedDataNodes, currentRow + 1, dataGraph,
                                queryGraph, newMappingMatrix, collectedIsomorphisms, maxNumIsoMorph,
                                prune);
                    }

                    // Release the data node at index testedCol (backtracking)
                    boundedDataNodes[testedCol] = false;

                } // if -- Is testedCol unused and candidate ?

            } // for -- Tries testedCol

        }

    }

    /**
     * Initializes a mapping matrix (base for a graph morphism) according to a
     * given criteria.
     * 
     * @param dataGraph Adjacency matrix of the data (host/mother) graph in
     *            which to search for a match.
     * @param queryGraph Adjacency matrix of query (subgraph) to search for
     * @param mappingCriteria the criteria to use for mapping (or else
     *            {@link IsoMorphismSearch#DEGREE_CRITERIA} will be used if this
     *            parameter is null)
     * 
     * @return a mapping matrix to be use as a search base
     */
    public static Int2DMatrix initMorphism(Int2DMatrix dataGraph, Int2DMatrix queryGraph,
            SimilarityMappingCriteria mappingCriteria) {

        int dataGraphSize = dataGraph.getWidth();
        int queryGraphSize = queryGraph.getWidth();

        SimilarityMappingCriteria usedMappingCriteria;
        if (mappingCriteria == null) {
            usedMappingCriteria = DEGREE_CRITERIA;
        } else {
            usedMappingCriteria = mappingCriteria;
        }

        // Creates a morphismMatrix M is |V_queryGraphSize| X |V_dataGraphSize|
        // matrix (queryGraphSize rows, dataGraphSize cols).
        Int2DMatrix morphismMatrix = new Int2DMatrix(dataGraphSize, queryGraphSize);

        for (int queryIndex = 0; queryIndex < queryGraphSize; queryIndex++) {
            for (int dataIndex = 0; dataIndex < dataGraphSize; dataIndex++) {

                if (usedMappingCriteria.verified(queryGraph, dataGraph, queryIndex, dataIndex)) {
                    morphismMatrix.set(dataIndex, queryIndex, 1);
                }
            }
        }

        return morphismMatrix;
    }

    /**
     * Finds isomorphisms (mappings) of a subgraph in a host/mother graph.
     *
     * @param dataGraph Adjacency matrix of the host/mother graph in which to
     *            search for a match.
     * @param queryGraph Adjacency matrix of subgraph to search for
     * @param maxNumIsoMorph the maximum number isomorphisms to find, may return
     *            fewer if fewer are matched, is specified.
     * @param similarityCriteria ] a function used to determine if two nodes are
     *            similar enough to be candidates for matching in the resulting
     *            morphism (degree criteria by default)
     * 
     * @param prune true if pruning of the matrix mapping is enabled, false
     *            otherwise.
     *
     * @return the list of morphism matrix arrays found
     */
    public static List<Int2DMatrix> getIsomorphicSubgraphs(Int2DMatrix dataGraph,
            Int2DMatrix queryGraph, Integer maxNumIsoMorph,
            SimilarityMappingCriteria similarityCriteria, boolean prune) {

        LOG.debug("Search for isomorphisms - Preconditions checks");

        // Expectation check
        if (maxNumIsoMorph != null && maxNumIsoMorph <= 0) {
            throw new IllegalArgumentException(
                    "Invalid upper limit of results, should be null or strictly positive");
        }

        // Checking given adjacency matrices
        if (!dataGraph.isSquare()) {
            throw new IllegalArgumentException("Data graph adjacency matrix is not square");
        }

        if (!queryGraph.isSquare()) {
            throw new IllegalArgumentException("Query graph adjacency matrix is not square");
        }

        // Easy results

        int dataGraphSize = dataGraph.getWidth();
        int queryGraphSize = queryGraph.getWidth();

        List<Int2DMatrix> collectedIsomorphisms = new ArrayList<>();

        // No match is possible if query graph is bigger than data graph
        if (dataGraphSize < queryGraphSize) {
            return collectedIsomorphisms;
        }

        // Real search

        LOG.debug("Search for isomorphisms - Initialises first mapping matrix");
        Int2DMatrix morphismMatrix = initMorphism(dataGraph, queryGraph, similarityCriteria);

        if (LOG.isDebugEnabled()) {
            LOG.debug(morphismMatrix.toPrettyString());
        }

        boolean[] boundedDataNodes = new boolean[dataGraphSize];

        recurseMorphismSearch(boundedDataNodes, 0, dataGraph, queryGraph, morphismMatrix,
                collectedIsomorphisms, maxNumIsoMorph, prune);

        LOG.debug("Search for isomorphisms - End of search, " + collectedIsomorphisms.size()
                + " isomorphisms found.");

        return collectedIsomorphisms;
    }

}
