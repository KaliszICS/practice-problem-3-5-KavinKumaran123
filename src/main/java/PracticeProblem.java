public class PracticeProblem {

    public static void main(String args[]) {

    }

    // ================= NUMBER OF PATHS =================
    public static int noOfPaths(String[][] maze) {
        int rows = maze.length;
        int cols = maze[0].length;

        boolean[][] visited = new boolean[rows][cols];

        return dfsCount(maze, rows - 1, 0, visited);
    }

    private static int dfsCount(String[][] maze, int r, int c, boolean[][] visited) {

        // Out of bounds / wall / visited
        if (r < 0 || c < 0 || r >= maze.length || c >= maze[0].length ||
            maze[r][c].equals("X") || visited[r][c])
            return 0;

        // Found finish
        if (maze[r][c].equals("F"))
            return 1;

        visited[r][c] = true;

        // 🔥 ONLY UP + RIGHT (prevents overcounting)
        int total =
            dfsCount(maze, r - 1, c, visited) +   // up
            dfsCount(maze, r, c + 1, visited);    // right

        visited[r][c] = false;

        return total;
    }

    // ================= MINIMUM MOVES =================
    public static int searchMazeMoves(String[][] maze) {
        int rows = maze.length;
        int cols = maze[0].length;

        boolean[][] visited = new boolean[rows][cols];

        int result = dfsMin(maze, rows - 1, 0, visited);

        return (result == Integer.MAX_VALUE) ? -1 : result;
    }

    private static int dfsMin(String[][] maze, int r, int c, boolean[][] visited) {

        // Out of bounds / wall / visited
        if (r < 0 || c < 0 || r >= maze.length || c >= maze[0].length ||
            maze[r][c].equals("X") || visited[r][c])
            return Integer.MAX_VALUE;

        // Found finish
        if (maze[r][c].equals("F"))
            return 0;

        visited[r][c] = true;

        int min =
            Math.min(
                Math.min(dfsMin(maze, r - 1, c, visited), dfsMin(maze, r + 1, c, visited)),
                Math.min(dfsMin(maze, r, c - 1, visited), dfsMin(maze, r, c + 1, visited))
            );

        visited[r][c] = false;

        if (min == Integer.MAX_VALUE)
            return Integer.MAX_VALUE;

        return 1 + min;
    }
}