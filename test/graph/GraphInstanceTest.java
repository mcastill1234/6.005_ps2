/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;

/**
 * Tests for instance methods of Graph.
 * 
 * <p>PS2 instructions: you MUST NOT add constructors, fields, or non-@Test
 * methods to this class, or change the spec of {@link #emptyInstance()}.
 * Your tests MUST only obtain Graph instances by calling emptyInstance().
 * Your tests MUST NOT refer to specific concrete implementations.
 */
public abstract class GraphInstanceTest {

    /*
     * Testing strategy for each operation of Graph:
     *
     * add():
     *   true/false if added new/existing vertex
     *   test for graph sizes: 0, n
     * set():
     *   source equal target: yes, no
     *   source is in graph: yes, no
     *   target is in graph: yes, no
     *   weight: 0, 1, n
     *   source and target was connected: yes, no
     *   initial graph size: 0, 1, n
     * remove():
     *   graph had this vertex : yes, no
     *   -- might need edge verification.
     *   test for graph sizes : 0, 1, n
     * sources():
     *   target vertex is in graph: yes, no
     *   source vertexes number: 0, 1, n
     *   initial graph size: 0, 1, n
     * targets():
     *   source vertex is in graph: yes, no
     *   target vertexes number: 0, 1, n
     *   graph size: 0, 1, n
     * vertices():
     *   graph size: 0,1, n
     */

    private static final String vertex1 = "V1";
    private static final String vertex2 = "V2";
    private static final String vertex3 = "V3";
    private static final String vertex4 = "V4";

    private static final int weight0 = 0;
    private static final int weight1 = 1;
    private static final int weight2 = 2;


    /**
     * Overridden by implementation-specific test classes.
     * 
     * @return a new empty graph of the particular implementation being tested
     */
    public abstract Graph<String> emptyInstance();
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testInitialVerticesEmpty() {
        assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), emptyInstance().vertices());
    }


    /**
     * Tests for add() method
     */

    // Add new vertex to empty graph, test true return.
    @Test
    public void testAddNewVertexEmptyGraph() {
        Graph<String> testGraph = emptyInstance();
        assertTrue(testGraph.add(vertex1));
        assertTrue(testGraph.vertices().contains(vertex1));
    }

    // Add n vertices to a graph, test true return.
    @Test
    public void testAddVertexesToEmptyGraph() {
        Graph<String> testGraph = emptyInstance();
        assertTrue(testGraph.add(vertex1));
        assertTrue(testGraph.add(vertex2));
        assertTrue(testGraph.add(vertex3));
        assertTrue(testGraph.vertices().contains(vertex1));
        assertTrue(testGraph.vertices().contains(vertex2));
        assertTrue(testGraph.vertices().contains(vertex3));
    }

    // Add existing vertex to graph, test false return.
    @Test
    public void testAddExistingVertexToGraph() {
        Graph<String> testGraph = emptyInstance();
        assertTrue(testGraph.add(vertex1));
        assertTrue(testGraph.add(vertex2));
        assertFalse(testGraph.add(vertex2));
    }

    /**
     * Tests for set() method
     */

    // Test set weighted directed (WD) edge in empty graph.
    @Test
    public void testSetEdgeEmptyGraph() {
        Graph<String> testGraph = emptyInstance();
        int previousWeight = testGraph.set(vertex1, vertex2, weight1);
        assertEquals(0, previousWeight);
        assertTrue(testGraph.targets(vertex1).get(vertex2) == weight1);
        assertEquals(2, testGraph.vertices().size());
    }

    // Test set new WD edge and vertex to non empty graph, existing target.
    @Test
    public void testSetEdgeWithNewSource() {
        Graph<String> testGraph = emptyInstance();
        testGraph.add(vertex2);
        int previousWeight = testGraph.set(vertex1, vertex2, weight2);
        assertEquals(0, previousWeight);
        assertTrue(testGraph.targets(vertex1).get(vertex2) == weight2);
    }

    // Test set new WD edge with 0 weight. No edge/vertex should be added.
    @Test
    public void testSetEdgeNewTargetWeight0() {
        Graph<String> testGraph = emptyInstance();
        testGraph.add(vertex1);
        int previousWeight = testGraph.set(vertex1, vertex2, weight0);
        assertEquals(0, previousWeight);
        assertFalse(testGraph.vertices().contains(vertex2));
        assertEquals(1, testGraph.vertices().size());
    }

    // Test set WD edge to same existing vertex, source = target.
    @Test
    public void testSetEdgeSourceEqualTarget() {
        Graph<String> testGraph = emptyInstance();
        testGraph.add(vertex1);
        int previousWeight = testGraph.set(vertex1, vertex1, weight1);
        assertEquals(0, previousWeight);
        assertTrue(testGraph.targets(vertex1).get(vertex1) == weight1);
        assertEquals(1, testGraph.vertices().size());
    }

    // Test set existing WD edge weight to a new value.
    @Test
    public void testSetEdgeModifier() {
        Graph<String> testGraph = emptyInstance();
        testGraph.add(vertex1);
        testGraph.add(vertex2);
        testGraph.set(vertex1, vertex2, weight1);
        int previousWeight = testGraph.set(vertex1, vertex2, weight2);
        assertEquals(weight1 = previousWeight);
        assertTrue(testGraph.targets(vertex1).get(vertex2) == weight2);
        assertEquals(2, testGraph.vertices().size());
    }

    // test set existing WD edge weight to 0 deleting the edge.
    @Test
    public void testSetEdgeDelete() {
        Graph<String> testGraph = emptyInstance();
        testGraph.add(vertex1);
        testGraph.add(vertex2);
        testGraph.set(vertex1, vertex2, weight1);
        int previousWeight = testGraph.set(vertex1, vertex2, weight0);
        assertEquals(weight1, previousWeight);
        assertFalse(testGraph.targets(vertex1).containsKey(vertex2));
        assertEquals(2, testGraph.vertices().size());
    }

    /**
     * Tests for remove() method
     */



}
