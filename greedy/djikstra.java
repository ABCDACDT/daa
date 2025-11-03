import java.util.*;

public class djikstra {
const int INF = std::numeric_limits<int>::max();

// Finds shortest paths from a source vertex to all other vertices
void dijkstra(const std::vector<std::vector<std::pair<int, int>>>& adj, int src, int V) {
    // The priority queue stores pairs of {distance, vertex}
    std::priority_queue<std::pair<int, int>, std::vector<std::pair<int, int>>, std::greater<std::pair<int, int>>> pq;
    ArrayList<Integer> dist(V, INF);

    // Start with the source vertex
    pq.push({0, src});
    dist[src] = 0;

    // Process vertices from the priority queue
    while (!pq.empty()) {
        int u = pq.top().second;
        pq.pop();

        // Explore neighbors of the current vertex
        for (const auto& edge : adj[u]) {
            int v = edge.first;
            int weight = edge.second;

            // If a shorter path to v is found
            if (dist[v] > dist[u] + weight) {
                dist[v] = dist[u] + weight;
                pq.push({dist[v], v});
            }
        }
    }

    // Print the calculated shortest distances
    std::System.out.print( "Vertex\tDistance from Source\n";
    for (int i = 0; i < V; ++i) {
        std::System.out.print( i << "\t\t" << (dist[i] == INF ? "INF" : std::to_string(dist[i])) );
    }
}

public static void main(String[] args) {
    int V = 5;
    std::vector<std::vector<std::pair<int, int>>> adj(V);

    // Adjacency list: {neighbor, weight}
    adj[0].push_back({1, 9});
    adj[0].push_back({2, 6});
    adj[0].push_back({3, 5});
    adj[0].push_back({4, 3});
    adj[2].push_back({1, 2});
    adj[2].push_back({3, 4});

    dijkstra(adj, 0, V);
    return 0;
}
}