package io.github.purpleloop.commons.math.algebra.isomorphim;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import io.github.purpleloop.commons.math.algebra.Int2DMatrix;

/** Isomorphism search tests. */
class IsoMorphismSearchTest {

    // @formatter:off

    /**  A data graph matrix. */
    private static final int[][] DATA_GRAPH_1 = { 
            { 0, 1, 0, 0 }, 
            { 0, 0, 1, 1 },
            { 0, 0, 0, 1 },
            { 0, 0, 0, 0 }
          };

    /**  A query graph matrix. */
    private static final int[][] QUERY_GRAPH_1 = { 
            { 0, 1, 1 },
            { 0, 0, 1 },
            { 0, 0, 0 } 
          };

    /** A mapping matrix. */
    private static final int[][] MAPPING_1 = { 
            { 0, 1, 0, 0 }, 
            { 0, 0, 1, 0 },
            { 0, 0, 0, 1 }
          };
    

    /**  A data graph matrix. */
    private static final int[][] DATA_GRAPH_2 = { 
            { 0, 1, 1, 0 }, 
            { 0, 0, 1, 1 },
            { 0, 0, 0, 0 },
            { 0, 0, 0, 0 }
          };

    /**  A query graph matrix. */
    private static final int[][] QUERY_GRAPH_2 = { 
            { 0, 1, 1 },
            { 0, 0, 0 },
            { 0, 0, 0 } 
          };

    /** A mapping matrix - 1st solution of mapping search 2. */
    private static final int[][] MAPPING_2_1 = {
      {1, 0, 0, 0},
      {0, 1, 0, 0},
      {0, 0, 1, 0}
    };

    
    /** A mapping matrix - 2nd solution of mapping search 2. */    
    private static final int[][] MAPPING_2_2 = {
      {1, 0, 0, 0},
      {0, 0, 1, 0},
      {0, 1, 0, 0}
    };
    
    /** A mapping matrix - 3rd solution of mapping search 2. */    
    private static final int[][] MAPPING_2_3 = {
      {0, 1, 0, 0},
      {0, 0, 1, 0},
      {0, 0, 0, 1}
    };
    
    /** A mapping matrix - 4th solution of mapping search 2. */    
    private static final int[][] MAPPING_2_4 = {
      {0, 1, 0, 0},
      {0, 0, 0, 1},
      {0, 0, 1, 0}
    }; 
    
    // @formatter:on

    /**
     * Tests the isomorphisms check. Case for simple case for the search of
     * QUERY_1 in DATA_1, expected MAPPING_1.
     */
    @Test
    void testIsIsoMorphism() {
        Int2DMatrix morph = new Int2DMatrix(MAPPING_1);
        Int2DMatrix query = new Int2DMatrix(QUERY_GRAPH_1);
        Int2DMatrix data = new Int2DMatrix(DATA_GRAPH_1);
        assertTrue(IsoMorphismSearch.isIsoMorphism(morph, data, query));
    }

    /**
     * Tests the search for isomorphisms.
     * 
     * Search of QUERY_1 in DATA_1, expected an unique result MAPPING_1.
     */
    @Test
    void testSearchoMorphismUnique() {
        Int2DMatrix query = new Int2DMatrix(QUERY_GRAPH_1);
        Int2DMatrix data = new Int2DMatrix(DATA_GRAPH_1);

        List<Int2DMatrix> results = IsoMorphismSearch.getIsomorphicSubgraphs(data, query, null,
                IsoMorphismSearch.DEGREE_CRITERIA, false);

        assertEquals(1, results.size());

        assertEquals(new Int2DMatrix(MAPPING_1).toPrettyString(), results.get(0).toPrettyString());

    }

    /**
     * Tests the search for isomorphisms.
     * 
     * Search of QUERY_2 in DATA_2, expected two results.
     */
    @Test
    void testSearchoMorphismMultiple() {
        Int2DMatrix query = new Int2DMatrix(QUERY_GRAPH_2);
        Int2DMatrix data = new Int2DMatrix(DATA_GRAPH_2);

        List<Int2DMatrix> results = IsoMorphismSearch.getIsomorphicSubgraphs(data, query, null,
                IsoMorphismSearch.DEGREE_CRITERIA, false);
        assertEquals(4, results.size());

        assertEquals(new Int2DMatrix(MAPPING_2_1).toPrettyString(),
                results.get(0).toPrettyString());

        assertEquals(new Int2DMatrix(MAPPING_2_2).toPrettyString(),
                results.get(1).toPrettyString());

        assertEquals(new Int2DMatrix(MAPPING_2_3).toPrettyString(),
                results.get(2).toPrettyString());

        assertEquals(new Int2DMatrix(MAPPING_2_4).toPrettyString(),
                results.get(3).toPrettyString());

    }

}
