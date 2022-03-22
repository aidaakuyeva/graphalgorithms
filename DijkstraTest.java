import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class DijkstraTest {
    @Test
    public void testSimple() {
        Graph g = new Graph(2);
        g.addEdge(0, 1, 4);
        
        List<Integer> expectedPath = new ArrayList<Integer>();
        expectedPath.add(0);
        expectedPath.add(1);
        
        List<Integer> actualPath = Dijkstra.getShortestPath(g, 0, 1);
        
        for (int i = 0; i < actualPath.size(); i++) {
            assertEquals(expectedPath.get(i), actualPath.get(i));
        }
    }
    
    @Test
    public void testMoreNodesShorterPath() {
        Graph g = new Graph(4);
        g.addEdge(0, 1, 2);
        g.addEdge(1, 2, 20);
        g.addEdge(1, 3, 2);
        g.addEdge(3, 2, 4);
        
        List<Integer> expectedPath = new ArrayList<Integer>();
        expectedPath.add(1);
        expectedPath.add(3);
        expectedPath.add(2);

        List<Integer> actualPath = Dijkstra.getShortestPath(g, 1, 2);
        
        for (int i = 0; i < actualPath.size(); i++) {
            assertEquals(expectedPath.get(i), actualPath.get(i));
        }
    }
    
    @Test
    public void testMoreNodesShorterPath2() {
        Graph g = new Graph(4);
        g.addEdge(0, 1, 2);
        g.addEdge(1, 2, 20);
        g.addEdge(1, 3, 2);
        g.addEdge(3, 2, 4);
        g.addEdge(2, 3, 1);
        
        List<Integer> expectedPath = new ArrayList<Integer>();
        expectedPath.add(1);
        expectedPath.add(3);
        expectedPath.add(2);

        List<Integer> actualPath = Dijkstra.getShortestPath(g, 1, 2);
        
        for (int i = 0; i < actualPath.size(); i++) {
            assertEquals(expectedPath.get(i), actualPath.get(i));
        }
    }
    
    
    @Test
    public void testSingleton() {
        Graph g = new Graph(1);
        
        List<Integer> expectedPath = new ArrayList<Integer>();
        expectedPath.add(0);
        
        List<Integer> actualPath = Dijkstra.getShortestPath(g, 0, 0);
        
        assertEquals(expectedPath.get(0), actualPath.get(0));
        assertEquals(expectedPath.size(), actualPath.size());
    }
    
    @Test
    public void testSameSrcAndTgt() {
        Graph g = new Graph(4);
        g.addEdge(0, 1, 2);
        g.addEdge(1, 2, 20);
        g.addEdge(1, 3, 2);
        g.addEdge(3, 2, 4);
        g.addEdge(2, 3, 1);
        
        List<Integer> expectedPath = new ArrayList<Integer>();
        expectedPath.add(0);
        
        List<Integer> actualPath = Dijkstra.getShortestPath(g, 0, 0);
        
        assertEquals(expectedPath.get(0), actualPath.get(0));
        assertEquals(expectedPath.size(), actualPath.size());
    }
    
    @Test
    public void testUndirectedGraph() {
        Graph g = new Graph(5);
        g.addEdge(0, 1, 7);
        g.addEdge(1, 0, 7);
        
        g.addEdge(0, 2, 3);
        g.addEdge(2, 0, 3);
        
        g.addEdge(2, 1, 1);
        g.addEdge(1, 2, 1);
        
        g.addEdge(2, 3, 2);
        g.addEdge(3, 2, 2);
        
        g.addEdge(1, 3, 2);
        g.addEdge(3, 1, 2);
        
        g.addEdge(1, 4, 6);
        g.addEdge(4, 1, 6);
        
        g.addEdge(3, 4, 4);
        g.addEdge(4, 3, 4);
        
        List<Integer> expectedPath = new ArrayList<Integer>();
        expectedPath.add(0);
        expectedPath.add(2);
        expectedPath.add(3);
        expectedPath.add(4);
        
        List<Integer> actualPath = Dijkstra.getShortestPath(g, 0, 4);
        
        for (int i = 0; i < actualPath.size(); i++) {
            assertEquals(expectedPath.get(i), actualPath.get(i));
        }
    }
    
    @Test
    public void testUndirected2Nodes() {
        Graph g = new Graph(2);
        g.addEdge(1, 0, 1);
        g.addEdge(0, 1, 1);
        
        List<Integer> actualPath = Dijkstra.getShortestPath(g, 0, 1);
        
        assertEquals(2, actualPath.size());
        assertEquals(0, (int) actualPath.get(0));
        assertEquals(1, (int) actualPath.get(1));
    }
    
    @Test
    public void testLargerGraph() {
        Graph g = new Graph(9);
        
        g.addEdge(0, 1, 4);
        g.addEdge(1, 0, 4);
        
        g.addEdge(0, 7, 8);
        
        g.addEdge(1, 7, 11);
        g.addEdge(7, 1, 11);
        
        g.addEdge(2, 1, 4);
        g.addEdge(1, 2, 8);
        
        g.addEdge(7, 8, 7);
        g.addEdge(8, 7, 7);
        
        g.addEdge(7, 6, 1);
        g.addEdge(6, 7, 1);
        
        g.addEdge(2, 8, 2);
        g.addEdge(8, 2, 2);
        
        g.addEdge(8, 6, 6);
        g.addEdge(6, 8, 6);
        
        g.addEdge(2, 3, 7);
        g.addEdge(3, 2, 7);
        
        g.addEdge(2, 5, 4);
        g.addEdge(5, 2, 4);
        
        g.addEdge(6, 5, 2);
        g.addEdge(5, 6, 2);
        
        g.addEdge(3, 5, 14);
        g.addEdge(5, 3, 14);
        
        g.addEdge(3, 4, 9);
        g.addEdge(4, 3, 9);
        
        g.addEdge(5, 4, 10);
        g.addEdge(4, 5, 2);
        
        List<Integer> expectedPath = new ArrayList<Integer>();
        int[] nodes = new int[] {0, 7, 6, 5, 4};
        for (int i = 0; i < nodes.length; i++) {
            expectedPath.add(nodes[i]);
        }
        
        List<Integer> actualPath = Dijkstra.getShortestPath(g, 0, 4);
        
        for (int i = 0; i < actualPath.size(); i++) {
            assertEquals(expectedPath.get(i), actualPath.get(i));
        }
    }
    
    @Test
    public void testNoPath() {
        Graph g = new Graph(3);
        
        g.addEdge(2, 0, 1);
        g.addEdge(2, 1, 5);
        
        assertTrue(Dijkstra.getShortestPath(g, 0, 1).isEmpty());
    }
    
    @Test
    public void testNoPath2() {
        Graph g = new Graph(4);
        
        g.addEdge(0, 1, 1);
        g.addEdge(2, 1, 2);
        g.addEdge(3, 2, 1);
        
        assertTrue(Dijkstra.getShortestPath(g, 0, 2).isEmpty());
    }
}
