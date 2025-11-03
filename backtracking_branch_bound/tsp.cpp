#include <iostream>
#include <vector>
#include <algorithm>
#include <limits>

const int INF = std::numeric_limits<int>::max();

// Function to solve TSP using backtracking
void tsp(const std::vector<std::vector<int>>& graph, std::vector<bool>& visited, int currPos, int n, int count, int cost, int& ans) {
    // Base case: if all cities have been visited
    if (count == n && graph[currPos][0]) {
        ans = std::min(ans, cost + graph[currPos][0]);
        return;
    }

    // Backtracking step
    for (int i = 0; i < n; i++) {
        if (!visited[i] && graph[currPos][i]) {
            visited[i] = true;
            tsp(graph, visited, i, n, count + 1, cost + graph[currPos][i], ans);
            
            // Backtrack
            visited[i] = false;
        }
    }
}

int main() {
    int n = 4;
    std::vector<std::vector<int>> graph = {
        {0, 10, 15, 20},
        {10, 0, 35, 25},
        {15, 35, 0, 30},
        {20, 25, 30, 0}
    };
    
    std::vector<bool> visited(n, false);
    visited[0] = true; // Start from the first city
    int ans = INF;

    tsp(graph, visited, 0, n, 1, 0, ans);

    std::cout << "Minimum cost for TSP: " << ans << std::endl;

    return 0;
}