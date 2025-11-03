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
private:
    void dfs(const vector<vector<int>>& isConnected, int node, vector<bool>& visited) {
        int n = isConnected.size();
        visited[node] = true;
        for (int neighbor = 0; neighbor < n; ++neighbor) {
            if (isConnected[node][neighbor] == 1 && !visited[neighbor]) {
                dfs(isConnected, neighbor, visited);
            }
        }
    }
public:
    int findCircleNum(std::vector<std::vector<int>>& isConnected) {
        int n = isConnected.size();
        std::vector<bool> visited(n, false);
        int numProvinces = 0;
        for (int i = 0; i < n; ++i) {
            if (!visited[i]) {
                dfs(isConnected, i, visited);
                numProvinces++;
            }
        }
        return numProvinces;
    }
};