#include <bits/stdc++.h>
using namespace std;
int main()
{
    ios::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    int t;
    cin >> t;
    while (t--)
    {
        
    }
    return 0;
}
class Solution {
public:
    void dfs(int u, vector<int> adj[], int& cnt, vector<bool>& vis) {
        vis[u] = true;
        cnt++;
        for (auto v : adj[u]) {
            if (vis[v] == false) {
                dfs(v, adj, cnt, vis);
            }
        }
    }
    int reachableNodes(int n, vector<vector<int>>& edges,
                       vector<int>& restricted) {
        vector<bool> visited(n, false);
        vector<int> adj[n];
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