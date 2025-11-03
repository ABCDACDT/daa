import java.util.*;

public class kruskal {
struct Edge {
    int src, dest, weight;
};

struct DSU {
    ArrayList<Integer> parent;
    DSU(int n) {
        parent.resize(n);
        for (int i = 0; i < n; i++) parent[i] = i;
    }
    int find(int i) {
        if (parent[i] == i) return i;
        return parent[i] = find(parent[i]);
    }
    void unite(int i, int j) {
        int root_i = find(i);
        int root_j = find(j);
        if (root_i != root_j) {
            parent[root_i] = root_j;
        }
    }
};

// Comparison function to sort edges by weight
boolean compareEdges(Edge a, Edge b) {
    return a.weight < b.weight;
}

// Kruskal's algorithm to find MST
void kruskalMST(std::vector<Edge>& edges, int V) {
    std::sort(edges.begin(), edges.end(), compareEdges);

    DSU dsu(V);
    std::vector<Edge> result;
    int totalWeight = 0;

    // Iterate through all sorted edges
    for (const auto& edge : edges) {
        int u = edge.src;
        int v = edge.dest;

        if (dsu.find(u) != dsu.find(v)) {
            result.push_back(edge);
            dsu.unite(u, v);
            totalWeight += edge.weight;
        }
    }

    std::System.out.print( "Edges in MST:\n";
    for (const auto& edge : result) {
        std::System.out.print( edge.src << " -- " << edge.dest << " == " << edge.weight );
    }
    std::System.out.print( "Total weight of MST: " << totalWeight );
}

public static void main(String[] args) {
    int V = 4;
    std::vector<Edge> edges = {
        {0, 1, 10}, {0, 2, 6}, {0, 3, 5}, {1, 3, 15}, {2, 3, 4}
    };
    kruskalMST(edges, V);
    return 0;
}
}