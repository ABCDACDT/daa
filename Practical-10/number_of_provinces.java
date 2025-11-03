import java.util.*;

public class number_of_provinces {
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
    void dfs(const vector<ArrayList<Integer>>& isConnected, int node, ArrayList<Boolean>& visited) {
        int n = isConnected.size();
        visited[node] = true;
        for (int neighbor = 0; neighbor < n; ++neighbor) {
            if (isConnected[node][neighbor] == 1 && !visited[neighbor]) {
                dfs(isConnected, neighbor, visited);
            }
        }
    }
public:
    int findCircleNum(std::vector<ArrayList<Integer>>& isConnected) {
        int n = isConnected.size();
        ArrayList<Boolean> visited(n, false);
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
}