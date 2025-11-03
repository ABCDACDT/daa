#include <iostream>
#include <vector>
#include <algorithm>

const int INF = 99999; // A large value to represent infinity

// Function to run the Floyd-Warshall algorithm
void floydWarshall(std::vector<std::vector<int>>& dist, int V) {
    // k is the intermediate vertex
    for (int k = 0; k < V; k++) {
        // i is the source vertex
        for (int i = 0; i < V; i++) {
            // j is the destination vertex
            for (int j = 0; j < V; j++) {
                if (dist[i][k] != INF && dist[k][j] != INF) {
                    dist[i][j] = std::min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
    }

    // Print the shortest distance matrix
    std::cout << "Shortest distances between every pair of vertices:\n";
    for (int i = 0; i < V; i++) {
        for (int j = 0; j < V; j++) {
            if (dist[i][j] == INF)
                std::cout << "INF" << "\t";
            else
                std::cout << dist[i][j] << "\t";
        }
        std::cout << std::endl;
    }
}

int main() {
    int V = 4;
    std::vector<std::vector<int>> graph = {
        {0, 5, INF, 10},
        {INF, 0, 3, INF},
        {INF, INF, 0, 1},
        {INF, INF, INF, 0}
    };
    floydWarshall(graph, V);
    return 0;
}