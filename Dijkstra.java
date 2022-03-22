import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


/**
 * Provides access to Dijkstra's algorithm for a weighted graph.
 */
final public class Dijkstra {
    private Dijkstra() {}

    /**
     * Computes the shortest path between two nodes in a weighted graph.
     * Input graph is guaranteed to be valid and have no negative-weighted edges.
     *
     * @param g   the weighted graph to compute the shortest path on
     * @param src the source node
     * @param tgt the target node
     * @return an empty list if there is no path from {@param src} to {@param tgt}, otherwise an
     * ordered list of vertices in the shortest path from {@param src} to {@param tgt},
     * with the first element being {@param src} and the last element being {@param tgt}.
     * If {@param src} = {@param tgt}, you should return a SINGLETON list.
     */
    public static List<Integer> getShortestPath(Graph g, int src, int tgt) {
        List<Integer> shortestPath = new ArrayList<Integer>();
        int size = g.getSize();
        
        boolean[] visited = new boolean[size];
        int[] parent = new int[size];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = -2;
        }
        
        if (src == tgt) {
            shortestPath.add(src);
            return shortestPath;
        } else {
            
            if (g.outNeighbors(src).isEmpty()) {
                return shortestPath;
            }
            
            BinaryMinHeapImpl<Integer, Integer> minHeap = new BinaryMinHeapImpl<>();
            
            for (int i = 0; i < size; i++) {
                if (i == src) {
                    minHeap.add(0, i);
                    parent[i] = -1;
                } else {
                    minHeap.add(Integer.MAX_VALUE, i);
                }
            }
            
            while (!minHeap.isEmpty()) {
                
                BinaryMinHeap.Entry<Integer, Integer> min = minHeap.extractMin();
                int dist = min.key;
                int node = min.value;
                visited[node] = true;
                
                Set<Integer> neighbors = g.outNeighbors(node);
                Iterator<Integer> iter = neighbors.iterator();
                while (iter.hasNext()) {
                    int v = iter.next();
                    if (!visited[v]) {
                        int vDist = g.getWeight(node, v);
                        int currDist = dist + vDist;
                        
                        try {
                            minHeap.decreaseKey(v, currDist);
                            parent[v] = node;
                        } catch (Exception IllegalArgumentException) { }
                    }
                }
            }
            
            int currentNode = tgt;

            
            while (currentNode != -1) {
                shortestPath.add(currentNode);
                currentNode = parent[currentNode];
                if (currentNode == -2) {
                    List<Integer> emptyPath = new ArrayList<Integer>();
                    return emptyPath;
                }
            }
            
            Collections.reverse(shortestPath);
            return shortestPath;
        }
    }
}
