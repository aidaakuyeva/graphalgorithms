import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Set;

import org.junit.Test;

public class GraphTest {
    
    @Test (expected = IllegalArgumentException.class)
    public void testConstructor0() {
        new Graph(0);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testConstructorNegative() {
        new Graph(-10);
    }
    
    @Test
    public void testConstructorSingleton() {
        Graph g = new Graph(1);
        assertEquals(1, g.nodes.size());
    }
    
    @Test
    public void testConstructor() {
        Graph g = new Graph(100);
        assertEquals(100, g.nodes.size());
    }
    
    @Test
    public void testGetSize() {
        Graph g = new Graph(100);
        assertEquals(100, g.getSize());
    }
    
    @Test
    public void testGetSizeSingleton() {
        Graph g = new Graph(1);
        assertEquals(1, g.getSize());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testHasEdgeUNegative() {
        Graph g = new Graph(100);
        g.hasEdge(-10, 19);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testHasEdgeUNotInGraph() {
        Graph g = new Graph(100);
        g.hasEdge(100, 19);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testHasEdgeVNegative() {
        Graph g = new Graph(100);
        g.hasEdge(0, -99);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testHasEdgeVNotInGraph() {
        Graph g = new Graph(100);
        g.hasEdge(0, 100);
    }
    
    @Test
    public void testHasEdgeSingleton() {
        Graph g = new Graph(1);
        assertFalse(g.hasEdge(0, 0));
    }
    
    @Test
    public void testHasEdge2() {
        Graph g = new Graph(2);
        g.addEdge(0, 1, 2);
        g.addEdge(1, 0, 2);
        assertTrue(g.hasEdge(0, 1));
        assertTrue(g.hasEdge(1, 0));
    }
    
    @Test
    public void testHasEdgeSimpleFalse() {
        Graph g = new Graph(10);
        g.addEdge(9, 3, 1);
        g.addEdge(5, 8, 1);
        assertFalse(g.hasEdge(0, 2));
        assertFalse(g.hasEdge(3, 9));
    }
    
    @Test
    public void testHasEdgeSimpleTrue() {
        Graph g = new Graph(10);
        g.addEdge(1, 4, 4);
        g.addEdge(8, 9, 19);
        assertTrue(g.hasEdge(1, 4));
        assertTrue(g.hasEdge(8, 9));
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testGetWeightUNegative() {
        Graph g = new Graph(10);
        g.getWeight(-1, 3);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testGetWeightUNotInGraph() {
        Graph g = new Graph(10);
        g.getWeight(10, 2);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testGetWEightVNegative() {
        Graph g = new Graph(10);
        g.getWeight(8, -3);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testGetWeightVNotInGraph() {
        Graph g = new Graph(10);
        g.getWeight(8, 20);
    }
    
    @Test (expected = NoSuchElementException.class)
    public void testGetWeightEdgeDoesntExist() {
        Graph g = new Graph(10);
        g.getWeight(1, 2);
    }
    
    @Test
    public void testGetWeightSimple() {
        Graph g = new Graph(5);
        g.addEdge(1, 2, 3);
        g.addEdge(4, 1, 4);
        assertEquals(3, g.getWeight(1, 2));
        assertEquals(4, g.getWeight(4, 1));
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testAddEdgeUNegative() {
        Graph g = new Graph(5);
        g.addEdge(-99, 2, 3);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testAddEdgeUNotInGraph() {
        Graph g = new Graph(5);
        g.addEdge(5, 2, 3);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testAddEdgeVNegative() {
        Graph g = new Graph(5);
        g.addEdge(0,-2, 3);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testAddEdgeVNotInGraph() {
        Graph g = new Graph(5);
        g.addEdge(3, 9, 3);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testAddSelfEdge() {
        Graph g = new Graph(2);
        g.addEdge(1, 1, 2);
    }
    
    @Test
    public void testAddEdgeSimple() {
        Graph g = new Graph(5);
        assertTrue(g.addEdge(1, 3, 3));
        assertTrue(g.addEdge(2, 4, 4));
        
        HashMap<Integer, Integer> map1 = g.nodes.get(1);
        assertTrue(map1.containsKey(3));
        assertTrue(map1.containsValue(3));
        
        HashMap<Integer, Integer> map2 = g.nodes.get(2);
        assertTrue(map2.containsKey(4));
        assertTrue(map2.containsValue(4));
    }
    
    @Test
    public void testAddVertexHasMultOutEdges() {
        Graph g = new Graph(5);
        assertTrue(g.addEdge(1, 2, 2));
        assertTrue(g.addEdge(1, 3, 3));
        assertTrue(g.addEdge(1, 0, 0));
        assertTrue(g.addEdge(1, 4, 4));
        
        HashMap<Integer, Integer> map1 = g.nodes.get(1);
        assertTrue(map1.containsKey(0));
        assertTrue(map1.containsKey(2));
        assertTrue(map1.containsKey(3));
        assertTrue(map1.containsKey(4));
    }
    
    @Test
    public void testAddVertexHasMultInEdges() {
        Graph g = new Graph(5);
        assertTrue(g.addEdge(0, 1, 0));
        assertTrue(g.addEdge(2, 1, 2));
        
        HashMap<Integer, Integer> map0 = g.nodes.get(0);
        assertTrue(map0.containsKey(1));
        
        HashMap<Integer, Integer> map1 = g.nodes.get(1);
        assertEquals(0, map1.keySet().size());
        
        HashMap<Integer, Integer> map2 = g.nodes.get(2);
        assertTrue(map2.containsKey(1));
    }
    
    @Test
    public void testAddDoubleEdge() {
        Graph g = new Graph(3);
        assertTrue(g.addEdge(0, 1, 1));
        assertTrue(g.addEdge(1, 0, 0));
        
        HashMap<Integer, Integer> map0 = g.nodes.get(0);
        assertTrue(map0.containsKey(1));
        
        HashMap<Integer, Integer> map1 = g.nodes.get(1);
        assertTrue(map1.containsKey(0));
    }
    
    @Test
    public void testAddEdgeAlreadyExists() {
        Graph g = new Graph(3);
        assertTrue(g.addEdge(0, 1, 1));
        assertFalse(g.addEdge(0, 1, 1));
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testOutNeighborsVNegative() {
        Graph g = new Graph(4);
        g.outNeighbors(-1);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testOutNeighborsVNotInGraph() {
        Graph g = new Graph(4);
        g.outNeighbors(4);
    }
    
    @Test
    public void testOutNeighborsSingleton() {
        Graph g = new Graph(1);
        assertTrue(g.outNeighbors(0).isEmpty());
    }
    
    @Test
    public void testOutNeighborsNone() {
        Graph g = new Graph(4);
        g.addEdge(1, 2, 2);
        assertTrue(g.outNeighbors(2).isEmpty());
    }
    
    @Test
    public void testOutNeighborsSimple() {
        Graph g = new Graph(4);
        g.addEdge(0, 1, 1); 
        g.addEdge(0, 2, 2);
        g.addEdge(0, 3, 3);
        
        Set<Integer> neighbors = g.outNeighbors(0);
        assertTrue(neighbors.contains(1));
        assertTrue(neighbors.contains(2));
        assertTrue(neighbors.contains(3));
        assertEquals(3, neighbors.size());
    }
    
}
