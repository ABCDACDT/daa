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
    bool isCycle = false;

    void dfs(int course, vector<int>& vis, vector<vector<int>>& g) {
        vis[course] = 1;
        for (int x : g[course]) {
            if (vis[x] == 0)
                dfs(x, vis, g);
            else if (vis[x] == 1)
                isCycle = true;
        }
        vis[course] = 2;
    }

    bool canFinish(int numCourses, vector<vector<int>>& prerequisites) {
        vector<vector<int>> g(numCourses);
        int n = prerequisites.size();
        for (int i = 0; i < n; i++) {
            g[prerequisites[i][1]].push_back(prerequisites[i][0]);
        }
        vector<int> vis(numCourses, 0);
        for (int i = 0; i < numCourses; i++) {
            if (vis[i] == 0) {
                dfs(i, vis, g);
            }
        }
        return !isCycle;
    }
};