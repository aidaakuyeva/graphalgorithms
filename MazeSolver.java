import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.Set;

final public class MazeSolver {
    static int numRow;
    static int numCol;
    static Graph graph;
    static boolean solved;
    
    private MazeSolver() {
    }
    
    // Returns true if coordinate is valid to move to, false otherwise
    static boolean isValid(int[][] maze, boolean[][] visited, Coordinate c) {
        int x = c.getX();
        int y = c.getY();
        
        if (x < 0 || x >= numCol) {
            return false;
        }
      
        if (y < 0 || y >= numRow) {
            return false;
        }
        
        if (maze[y][x] == 1 || visited[y][x]) {
            return false;
        }
        
        return true;
    }
    
    // Returns the number of the node the coordinate in the maze corresponds to
    static int getNode(Coordinate c) {
        int x = c.getX();
        int y = c.getY();
        int factor = y * numRow;
        return x + factor;
    }
    
    // Returns the coordinate a given node in the graph corresponds to
    static Coordinate getCoord(Integer node) {
        int x = node % numCol;
        int y = node / numCol;
        return new Coordinate(x, y);
    }
   
    
    // Helper function that uses bfs to traverse the graph to find shortest path
    static List<Coordinate> bfs(int[][] maze, boolean[][] visited, Coordinate src, Coordinate tgt) {
        
        List<Coordinate> path = new ArrayList<Coordinate>();
        Coordinate curr = src;
        visited[src.getY()][src.getX()] = true;
        
        Queue<Coordinate> q = new ArrayDeque<Coordinate>();
        q.add(src);
        
        while (!q.isEmpty()) {
            curr = q.remove();
            
            if (curr.equals(tgt)) {
                solved = true;
                break;
            }
            
            // Go right
            Coordinate right = new Coordinate(curr.getX() + 1, curr.getY());
            if (isValid(maze, visited, right)) {
                q.add(right);
                visited[right.getY()][right.getX()] = true;
                graph.addEdge(getNode(right), getNode(curr), 1);
            }
            
            // Go left
            Coordinate left = new Coordinate(curr.getX() - 1, curr.getY());
            if (isValid(maze, visited, left)) {
                q.add(left);
                visited[left.getY()][left.getX()] = true;
                graph.addEdge(getNode(left), getNode(curr), 1);
            }
            
            // Go up
            Coordinate up = new Coordinate(curr.getX(), curr.getY() - 1);
            if (isValid(maze, visited, up)) {
                q.add(up);
                visited[up.getY()][up.getX()] = true;
                graph.addEdge(getNode(up), getNode(curr), 1);
            }
            
            // Go down
            Coordinate down = new Coordinate(curr.getX(), curr.getY() + 1);
            if (isValid(maze, visited, down)) {
                q.add(down);
                visited[down.getY()][down.getX()] = true;
                graph.addEdge(getNode(down), getNode(curr), 1);
            }
            
        }
        
        if (!solved) {
            return path;
        }
        
        path.add(tgt);
        Coordinate current = tgt;
        for (int i = 0; i < numCol * numRow; i++) {
            
            if (current.equals(src)) {
                break;
            }
            
            Integer last = getNode(current);
            Set<Integer> parentSet = graph.outNeighbors(last);
            Integer parent = parentSet.iterator().next();
            current = getCoord(parent);
            path.add(current);
            
        }
        
        Collections.reverse(path);
        
        return path;
    }

    /**
     * Returns a list of coordinates on the shortest path from {@code src} to {@code tgt}
     * by executing a breadth-first search on the graph represented by a 2D-array of size m x n.
     * <p>
     * Input {@code maze} guaranteed to be a non-empty and valid matrix.
     * Input {@code src} and {@code tgt} are guaranteed to be valid, in-bounds, and not blocked.
     * <p>
     * Do NOT modify this method header.
     *
     * @param maze the input maze, which is a 2D-array of size m x n
     * @param src The starting Coordinate of the path on the matrix
     * @param tgt The target Coordinate of the path on the matrix
     * @return an empty list if there is no path from {@param src} to {@param tgt}, otherwise an
     * ordered list of vertices in the shortest path from {@param src} to {@param tgt},
     * with the first element being {@param src} and the last element being {@param tgt}.
     * If {@param src} = {@param tgt}, you should return a SINGLETON list.
     * @implSpec This method should run in worst-case O(m x n) time.
     */
    public static List<Coordinate> getShortestPath(int[][] maze, Coordinate src, Coordinate tgt) {
        MazeSolver.numRow = maze.length;
        MazeSolver.numCol = maze[0].length;
        MazeSolver.graph = new Graph(maze.length * maze[0].length);
        MazeSolver.solved = false;
        
        List<Coordinate> path = new ArrayList<Coordinate>();
        if (src.equals(tgt)) {
            path.add(src);
            return path;
        } else {
            
            boolean[][] visited = new boolean[numRow][numCol];
            
            return bfs(maze, visited, src, tgt);
        }
    }
}