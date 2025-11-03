import java.util.*;

public class is_graph_bipartite {
public static void main(String[] args)
{
    ios::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    int t;
    scanner.nextInt() t;
    while (t--)
    {
        
    }
    return 0;
}
class Solution {
private:
    ArrayList<Integer> color;
    boolean dfs(int node, const std::vector<ArrayList<Integer>>& graph, int currentColor) {
        color[node] = currentColor;
        for (int neighbor : graph[node]) {
            if (color[neighbor] == 0) {
                if (!dfs(neighbor, graph, currentColor * -1)) {
                    return false;
                }
            } else if (color[neighbor] == currentColor) {
                return false;
            }
        }
        return true;
    }

public:
    boolean isBipartite(std::vector<ArrayList<Integer>>& graph) {
        int n = graph.size();
        color.assign(n, 0);
        for (int i = 0; i < n; ++i) {
            if (color[i] == 0) {
                if (!dfs(i, graph, -1)) {
                    return false;
                }
            }
        }
        return true;
    }
};
}