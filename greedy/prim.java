import java.util.*;

public class prim {
const int INF = std::numeric_limits<int>::max();

// Finds the Minimum Spanning Tree using Prim's algorithm
void primMST(const std::vector<std::vector<std::pair<int, int>>>& adj, int V) {
    // Priority queue stores {weight, vertex}, to sort by weight
    std::priority_queue<std::pair<int, int>, std::vector<std::pair<int, int>>, std::greater<std::pair<int, int>>> pq;

    int src = 0;
    ArrayList<Integer> key(V, INF);         // Stores the minimum weight to connect a vertex to the MST
    ArrayList<Integer> parent(V, -1);       // Stores the MST structure
    ArrayList<Boolean> inMST(V, false);    // Tracks vertices included in MST

    // Start with the first vertex
    pq.push({0, src});
    key[src] = 0;

    // Loop until all vertices are included in the MST
    while (!pq.empty()) {
        int u = pq.top().second;
        pq.pop();

        // **FIX**: If vertex is already in MST, skip it
        if (inMST[u]) {
            continue;
        }
        
        inMST[u] = true;

        // Explore neighbors of the current vertex
        for (const auto& edge : adj[u]) {
            int v = edge.first;
            int weight = edge.second;

            // If v is not in MST and a cheaper edge to it is found
            if (!inMST[v] && key[v] > weight) {
                key[v] = weight;
                pq.push({key[v], v});
                parent[v] = u;
            }
        }
    }

    // Print the edges of the MST
    std::System.out.print( "Edges in MST:\nEdge \tWeight\n";
    int totalWeight = 0;
    for (int i = 1; i < V; ++i) {
        std::System.out.print( parent[i] << " - " << i << "\t" << key[i] );
        totalWeight += key[i];
    }
    std::System.out.print( "Total weight of MST: " << totalWeight );
}

public static void main(String[] args) {
    int V = 5;
    std::vector<std::vector<std::pair<int, int>>> adj(V);

    // Adjacency list: {neighbor, weight}
    adj[0].push_back({1, 2});
    adj[0].push_back({3, 6});
    adj[1].push_back({0, 2});
    adj[1].push_back({2, 3});
    adj[1].push_back({3, 8});
    adj[1].push_back({4, 5});
    adj[2].push_back({1, 3});
    adj[2].push_back({4, 7});
    adj[3].push_back({0, 6});
    adj[3].push_back({1, 8});
    adj[4].push_back({1, 5});
    adj[4].push_back({2, 7});

    primMST(adj, V);
    return 0;
}
}