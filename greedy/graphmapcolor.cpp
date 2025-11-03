#include <iostream>
#include <vector>
#include <map>

// Function to assign colors to vertices of a graph
void graphColoring(const std::vector<std::vector<int>>& adj, int V) {
    std::map<int, int> result; // Stores color of each vertex
    std::vector<bool> available(V, true); // Stores available colors

    // Assign the first color to the first vertex
    result[0] = 0;

    // Assign colors to the remaining V-1 vertices
    for (int u = 1; u < V; u++) {
        // Mark colors of adjacent vertices as unavailable
        for (int v : adj[u]) {
            if (result.count(v)) {
                available[result[v]] = false;
            }
        }

        // Find the first available color
        int cr;
        for (cr = 0; cr < V; cr++) {
            if (available[cr]) {
                break;
            }
        }
        result[u] = cr; // Assign the found color

        // Reset the available colors for the next iteration
        std::fill(available.begin(), available.end(), true);
    }

    std::cout << "Vertex colors:\n";
    for (int u = 0; u < V; u++) {
        std::cout << "Vertex " << u << " ---> Color " << result[u] << std::endl;
    }
}

int main() {
    int V = 5;
    std::vector<std::vector<int>> adj(V);

    adj[0] = {1, 2, 3};
    adj[1] = {0, 2};
    adj[2] = {0, 1, 3};
    adj[3] = {0, 2, 4};
    adj[4] = {3};

    graphColoring(adj, V);
    return 0;
}