import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

final public class IslandBridge {

    static boolean[] gVisited;
    static boolean[] tVisited;

    private IslandBridge() {
    }

    /**
     * See question details in the write-up. Input is guaranteed to be valid.
     *
     * @param g
     *            the input graph representing the islands as vertices and bridges
     *            as directed edges
     * @param x
     *            the starting node
     * @return true, if no matter how you navigate through the one-way bridges from
     *         node x, there is always a way to drive back to node x, and false
     *         otherwise.
     * @implSpec This method should run in worst-case O(n + m) time.
     */
    public static boolean allNavigable(Graph g, int x) {
        if (g.outNeighbors(x).isEmpty() || g.getSize() == 1) {
            return true;
        } else {
            gVisited = new boolean[g.getSize()];
            tVisited = new boolean[g.getSize()];
            Graph transpose = dfs(g, x);
            dfsTranspose(transpose, x);
            
            for (int i = 0; i < gVisited.length; i++) {
                if (gVisited[i] != tVisited[i]) {
                    return false;
                }
            }
            
            return true;
            
        }
        
    }

    static Graph dfs(Graph g, int x) {
        Graph transpose = new Graph(g.getSize());
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(x);
        gVisited[x] = true;
        
        while (!stack.isEmpty()) {
            int curr = stack.pop();
            Set<Integer> neighbors = g.outNeighbors(curr);
            Iterator<Integer> iter = neighbors.iterator();
            
            while (iter.hasNext()) {
                int v = iter.next();
                transpose.addEdge(v, curr, 1);
                if (!gVisited[v]) {
                    stack.push(v);
                    gVisited[v] = true;
                }
            }
        }
        
        return transpose;
    }
    
    static void dfsTranspose(Graph transpose, int x) {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(x);
        tVisited[x] = true;
        
        while (!stack.isEmpty()) {
            int curr = stack.pop();
            Set<Integer> neighbors = transpose.outNeighbors(curr);
            Iterator<Integer> iter = neighbors.iterator();
            
            while (iter.hasNext()) {
                int v = iter.next();
                if (!tVisited[v]) {
                    stack.push(v);
                    tVisited[v] = true;
                }
            }
        }
    }
  
}
