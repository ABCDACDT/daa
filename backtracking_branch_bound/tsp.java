public class tsp {
    static final int INF = Integer.MAX_VALUE;

    // Function to solve TSP using backtracking
    static void tspUtil(int[][] graph, boolean[] visited, int currPos, int n, int count, int cost, int[] ans) {
        // Base case: if all cities have been visited
        if (count == n && graph[currPos][0] != 0) {
            ans[0] = Math.min(ans[0], cost + graph[currPos][0]);
            return;
        }

        // Backtracking step
        for (int i = 0; i < n; i++) {
            if (!visited[i] && graph[currPos][i] != 0) {
                visited[i] = true;
                tspUtil(graph, visited, i, n, count + 1, cost + graph[currPos][i], ans);
                
                // Backtrack
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        int n = 4;
        int[][] graph = {
            {0, 10, 15, 20},
            {10, 0, 35, 25},
            {15, 35, 0, 30},
            {20, 25, 30, 0}
        };
        
        boolean[] visited = new boolean[n];
        visited[0] = true; // Start from the first city
        int[] ans = {INF};

        tspUtil(graph, visited, 0, n, 1, 0, ans);

        System.out.println("Minimum cost for TSP: " + ans[0]);
    }
}
