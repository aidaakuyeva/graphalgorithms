import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class MazeSolverTest {
    
    @Test
    public void testMazeNoSolution() {        
        int[][] maze = new int[][]{
            {0, 0, 0},
            {1, 1, 1},
            {0, 0, 0}
        };
        
        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(0, 2);
        
        List<Coordinate> path = MazeSolver.getShortestPath(maze, start, end);
        assertTrue(path.isEmpty());
    }
    
    @Test
    public void testSmallMaze() {
        int[][] maze = new int[][] {
            {0, 0},
            {1, 0}
        };
        
        List<Coordinate> expectedPath = new ArrayList<Coordinate>();
        expectedPath.add(new Coordinate(0, 0));
        expectedPath.add(new Coordinate(1, 0));
        expectedPath.add(new Coordinate(1, 1));
        
        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(1, 1);
        
        List<Coordinate> path = MazeSolver.getShortestPath(maze, start, end);
        
        for (int i = 0; i < 3; i++) {
            assertEquals(expectedPath.get(i), path.get(i));
        } 
    }
    
    @Test
    public void testMazeSameStartAndEnd() {
        int[][] maze = new int[][] {
            {0, 0, 0},
            {1, 0, 0},
            {1, 1, 1}
        };
        
        Coordinate start = new Coordinate(2, 0);
        Coordinate end = new Coordinate(2, 0);
        
        List<Coordinate> path = MazeSolver.getShortestPath(maze, start, end);
        
        assertEquals(1, path.size());
        assertEquals(new Coordinate(2, 0), path.get(0));
    }
    
    @Test
    public void testMazeReturnsShortestPath() {
        int[][] maze = new int[][] {
            {0, 0, 0, 0},
            {0, 0, 1, 0},
            {1, 0, 0, 0},
            {0, 0, 0, 0}
        };
        
        List<Coordinate> expectedPath = new ArrayList<Coordinate>();
        expectedPath.add(new Coordinate(0, 0));
        expectedPath.add(new Coordinate(1, 0));
        expectedPath.add(new Coordinate(1, 1));
        expectedPath.add(new Coordinate(1, 2));
        expectedPath.add(new Coordinate(1, 3));
        expectedPath.add(new Coordinate(0, 3));
        
        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(0, 3);
        
        List<Coordinate> path = MazeSolver.getShortestPath(maze, start, end);
        
        for (int i = 0; i < path.size(); i++) {
            assertEquals(expectedPath.get(i), path.get(i));
        }
        assertEquals(expectedPath.size(), path.size());
    }
    
    @Test
    public void testBigMaze() {
        int[][] maze = new int[][]{
            {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 1, 1, 1, 0, 1, 1, 0},
            {0, 0, 0, 1, 0, 1, 0, 1, 1, 0},
            {1, 1, 0, 1, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 0, 0, 0, 1, 0},
            {0, 1, 0, 0, 1, 0, 0, 0, 1, 0},
            {0, 1, 1, 0, 0, 1, 1, 0, 1, 0},
            {0, 0, 1, 1, 0, 0, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };
        
        List<Coordinate> expectedPath = new ArrayList<Coordinate>();
        expectedPath.add(new Coordinate(2, 0));
        expectedPath.add(new Coordinate(1, 0));
        expectedPath.add(new Coordinate(0, 0));
        expectedPath.add(new Coordinate(0, 1));
        expectedPath.add(new Coordinate(0, 2));
        expectedPath.add(new Coordinate(1, 2));
        expectedPath.add(new Coordinate(2, 2));
        expectedPath.add(new Coordinate(2, 3));
        expectedPath.add(new Coordinate(2, 4));
        expectedPath.add(new Coordinate(2, 5));
        expectedPath.add(new Coordinate(3, 5));
        expectedPath.add(new Coordinate(3, 6));
        expectedPath.add(new Coordinate(4, 6));
        expectedPath.add(new Coordinate(4, 7));
        expectedPath.add(new Coordinate(5, 7));
        expectedPath.add(new Coordinate(6, 7));
        expectedPath.add(new Coordinate(7, 7));
        expectedPath.add(new Coordinate(8, 7));
        expectedPath.add(new Coordinate(9, 7));
        expectedPath.add(new Coordinate(9, 6));
        expectedPath.add(new Coordinate(9, 5));
        expectedPath.add(new Coordinate(9, 4));
        expectedPath.add(new Coordinate(9, 3));
        expectedPath.add(new Coordinate(9, 2));
        expectedPath.add(new Coordinate(9, 1));
        expectedPath.add(new Coordinate(9, 0));
        expectedPath.add(new Coordinate(8, 0));
        expectedPath.add(new Coordinate(7, 0));
        expectedPath.add(new Coordinate(6, 0));
        expectedPath.add(new Coordinate(5, 0));
        expectedPath.add(new Coordinate(4, 0));
        
        Coordinate start = new Coordinate(2, 0);
        Coordinate end = new Coordinate(4, 0);
        
        List<Coordinate> path = MazeSolver.getShortestPath(maze, start, end);
        
        for (int i = 0; i < expectedPath.size(); i++) {
            assertEquals(expectedPath.get(i), path.get(i));
        }
    }
    
    @Test
    public void testIsValidXNeg() {
        int[][] maze = new int[][] {
            {0, 0},
            {1, 0}
        };
        
        boolean[][] visited = new boolean[2][2];
        Coordinate c = new Coordinate(-1, 0);
        assertFalse(MazeSolver.isValid(maze, visited, c));
    }
    
    @Test
    public void testIsValidXOutOfBounds() {
        int[][] maze = new int[][] {
            {0, 0},
            {1, 0}
        };
        
        boolean[][] visited = new boolean[2][2];
        Coordinate c = new Coordinate(2, 0);
        assertFalse(MazeSolver.isValid(maze, visited, c));
    }
    
    @Test
    public void testIsValidYNeg() {
        int[][] maze = new int[][] {
            {0, 0},
            {1, 0}
        };
        
        boolean[][] visited = new boolean[2][2];
        Coordinate c = new Coordinate(0, -2);
        assertFalse(MazeSolver.isValid(maze, visited, c));
    }
    
    @Test
    public void testIsValidYOutOfBounds() {
        int[][] maze = new int[][] {
            {0, 0},
            {1, 0}
        };
        
        boolean[][] visited = new boolean[2][2];
        Coordinate c = new Coordinate(1, 3);
        assertFalse(MazeSolver.isValid(maze, visited, c));
    }
    
    @Test
    public void testIsValidBlockedCell() {
        int[][] maze = new int[][] {
            {0, 0},
            {1, 0}
        };
        
        boolean[][] visited = new boolean[2][2];
        Coordinate c = new Coordinate(0, 1);
        assertFalse(MazeSolver.isValid(maze, visited, c));
    }
    
    @Test
    public void testIsValidAlreadyVisited() {
        int[][] maze = new int[][] {
            {0, 0},
            {1, 0}
        };
        
        boolean[][] visited = new boolean[][] {
            {true, false},
            {false, false}
        };
        
        Coordinate c = new Coordinate(0, 0);
        assertFalse(MazeSolver.isValid(maze, visited, c));
    }
    
    @Test
    public void test2x2Right() {
        int[][] maze = new int[][] {
            {0, 0},
            {0, 0}
        };
        
        List<Coordinate> expectedPath = new ArrayList<Coordinate>();
        expectedPath.add(new Coordinate(0, 0));
        expectedPath.add(new Coordinate(1, 0));
        
        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(1, 0);
        
        List<Coordinate> path = MazeSolver.getShortestPath(maze, start, end);
        
        for (int i = 0; i < expectedPath.size(); i++) {
            assertEquals(expectedPath.get(i), path.get(i));
        }
    }
    
    @Test
    public void test2x2Down() {
        int[][] maze = new int[][] {
            {0, 0},
            {0, 0}
        };
        
        List<Coordinate> expectedPath = new ArrayList<Coordinate>();
        expectedPath.add(new Coordinate(1, 0));
        expectedPath.add(new Coordinate(1, 1));
        
        Coordinate start = new Coordinate(1, 0);
        Coordinate end = new Coordinate(1, 1);
        
        List<Coordinate> path = MazeSolver.getShortestPath(maze, start, end);
        
        for (int i = 0; i < expectedPath.size(); i++) {
            assertEquals(expectedPath.get(i), path.get(i));
        }
    }
    
    @Test
    public void test2x2Left() {
        int[][] maze = new int[][] {
            {0, 0},
            {0, 0}
        };
        
        List<Coordinate> expectedPath = new ArrayList<Coordinate>();
        expectedPath.add(new Coordinate(1, 1));
        expectedPath.add(new Coordinate(0, 1));
        
        Coordinate start = new Coordinate(1, 1);
        Coordinate end = new Coordinate(0, 1);
        
        List<Coordinate> path = MazeSolver.getShortestPath(maze, start, end);
        
        for (int i = 0; i < expectedPath.size(); i++) {
            assertEquals(expectedPath.get(i), path.get(i));
        }
    }
    
    @Test
    public void test2x2Up() {
        int[][] maze = new int[][] {
            {0, 0},
            {0, 0}
        };
        
        List<Coordinate> expectedPath = new ArrayList<Coordinate>();
        expectedPath.add(new Coordinate(1, 0));
        expectedPath.add(new Coordinate(0, 0));
        
        Coordinate start = new Coordinate(1, 0);
        Coordinate end = new Coordinate(0, 0);
        
        List<Coordinate> path = MazeSolver.getShortestPath(maze, start, end);
        
        for (int i = 0; i < expectedPath.size(); i++) {
            assertEquals(expectedPath.get(i), path.get(i));
        }
    }
}
