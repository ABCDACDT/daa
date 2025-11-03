import java.util.*;

public class course_schedule {
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
    boolean isCycle = false;

    void dfs(int course, ArrayList<Integer>& vis, vector<ArrayList<Integer>>& g) {
        vis[course] = 1;
        for (int x : g[course]) {
            if (vis[x] == 0)
                dfs(x, vis, g);
            else if (vis[x] == 1)
                isCycle = true;
        }
        vis[course] = 2;
    }

    boolean canFinish(int numCourses, vector<ArrayList<Integer>>& prerequisites) {
        vector<ArrayList<Integer>> g(numCourses);
        int n = prerequisites.size();
        for (int i = 0; i < n; i++) {
            g[prerequisites[i][1]].push_back(prerequisites[i][0]);
        }
        ArrayList<Integer> vis(numCourses, 0);
        for (int i = 0; i < numCourses; i++) {
            if (vis[i] == 0) {
                dfs(i, vis, g);
            }
        }
        return !isCycle;
    }
};
}