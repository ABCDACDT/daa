import java.util.*;

public class reachable_nodes_with_restriction {
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
public:
    void dfs(int u, ArrayList<Integer> adj[], int& cnt, ArrayList<Boolean>& vis) {
        vis[u] = true;
        cnt++;
        for (auto v : adj[u]) {
            if (vis[v] == false) {
                dfs(v, adj, cnt, vis);
            }
        }
    }
    int reachableNodes(int n, vector<ArrayList<Integer>>& edges,
                       ArrayList<Integer>& restricted) {
        ArrayList<Boolean> visited(n, false);
        ArrayList<Integer> adj[n];
        for (int i = 0; i < edges.size(); i++) {
            adj[edges[i][0]].push_back(edges[i][1]);
            adj[edges[i][1]].push_back(edges[i][0]);
        }
        for (auto a : restricted) {
            visited[a] = true;
        }
        int count = 0;
        dfs(0, adj, count, visited);
        return count;
    }
};
}