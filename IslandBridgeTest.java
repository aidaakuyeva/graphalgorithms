import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class IslandBridgeTest {
    
    @Test
    public void testSimpleFalse() {
        Graph g = new Graph(4);
        g.addEdge(0, 1, 1);
        g.addEdge(1, 2, 1);
        g.addEdge(2, 3, 1);
        g.addEdge(3, 1, 1);
        
        assertFalse(IslandBridge.allNavigable(g, 0));
    }
    
    @Test
    public void testSimpleTrue() {
        Graph g = new Graph(4);
        g.addEdge(0, 1, 1);
        g.addEdge(1, 2, 1);
        g.addEdge(2, 3, 1);
        g.addEdge(3, 1, 1);
        g.addEdge(2, 0, 1);

        assertTrue(IslandBridge.allNavigable(g, 0));
    }
    
    @Test
    public void testNotConnected() {
        Graph g = new Graph(4);
        g.addEdge(1, 2, 1);
        g.addEdge(2, 3, 1);
        g.addEdge(3, 1, 1);
        
        assertTrue(IslandBridge.allNavigable(g, 0));
        assertTrue(IslandBridge.allNavigable(g,  2));
    }
    
    @Test
    public void testSingleton() {
        Graph g = new Graph(1);
        
        assertTrue(IslandBridge.allNavigable(g, 0));
    }
    
    @Test
    public void testSimpleTwoWayBridges1() {
        Graph g = new Graph(4);
        g.addEdge(0, 1, 1);
        g.addEdge(1, 0, 1);
        g.addEdge(1, 2, 1);
        g.addEdge(1, 3, 1);
        
        assertFalse(IslandBridge.allNavigable(g, 0));

    }
    
    @Test
    public void testSimpleTwoWayBridges2() {
        Graph g = new Graph(4);
        g.addEdge(0, 1, 1);
        g.addEdge(1, 0, 1);
        g.addEdge(2, 1, 1);
        g.addEdge(3, 1, 1);
        
        assertTrue(IslandBridge.allNavigable(g, 1));
        assertTrue(IslandBridge.allNavigable(g, 0));
        assertFalse(IslandBridge.allNavigable(g, 2));
        assertFalse(IslandBridge.allNavigable(g, 3));
    }
    
    @Test
    public void testSimpleCycle() {
        Graph g = new Graph(3);
        g.addEdge(0, 1, 1);
        g.addEdge(1, 2, 1);
        g.addEdge(2, 0, 1);
        
        assertTrue(IslandBridge.allNavigable(g, 0));
    }
    
    @Test
    public void testUndirected() {
        Graph g = new Graph(4);
        g.addEdge(0, 3, 1);
        g.addEdge(3, 0, 1);
        g.addEdge(1, 2, 1);
        g.addEdge(2, 1, 1);
        g.addEdge(1, 3, 1);
        g.addEdge(3, 1, 1);
        g.addEdge(2, 3, 1);
        g.addEdge(3, 2, 1);
        
        assertTrue(IslandBridge.allNavigable(g, 0));
        assertTrue(IslandBridge.allNavigable(g, 1));
        assertTrue(IslandBridge.allNavigable(g, 2));
        assertTrue(IslandBridge.allNavigable(g, 3));
    }
    
    @Test
    public void test2SCC() {
        Graph g = new Graph(6);
        g.addEdge(0, 1, 1);
        g.addEdge(1, 2, 1);
        g.addEdge(2, 0, 1);
        g.addEdge(2, 3, 1);
        g.addEdge(3, 4, 1);
        g.addEdge(4, 5, 1);
        g.addEdge(5, 3, 1);
        
        assertTrue(IslandBridge.allNavigable(g, 5));
        assertFalse(IslandBridge.allNavigable(g, 0));
    }
    
    @Test
    public void test2Nodes() {
        Graph g = new Graph(2);
        g.addEdge(0, 1, 1);
        
        assertFalse(IslandBridge.allNavigable(g, 0));
        assertTrue(IslandBridge.allNavigable(g, 1));
        
        g.addEdge(1, 0, 1);
        
        assertTrue(IslandBridge.allNavigable(g, 0));
    }
    
    @Test
    public void test2NodesUndirected() {
        Graph g = new Graph(2);
        g.addEdge(0, 1, 1);
        g.addEdge(1, 0, 1);
        
        assertTrue(IslandBridge.allNavigable(g, 0));
        assertTrue(IslandBridge.allNavigable(g, 1));
    }
    
}
