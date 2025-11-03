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
   
    int solve(int node, unordered_map<int, vector<int>>& mp, vector<int>& subtreeSize) {
        int size = 1;
        for (int child : mp[node]) {
            size += solve(child, mp, subtreeSize);
        }
        subtreeSize[node] = size;
        return size;
    }

    int countHighestScoreNodes(vector<int>& parents) {
        int n = parents.size();
        unordered_map<int, vector<int>> mp;
        for (int i = 0; i < n; i++) {
            if (parents[i] != -1) {
                mp[parents[i]].push_back(i);
            }
        }

        vector<int> subtreeSize(n, 0);
        solve(0, mp, subtreeSize); 

        long long maxScore = 0;
        int count = 0;

        for (int i = 0; i < n; i++) {
            long long score = 1;
            int remaining = n - subtreeSize[i];

            for (int child : mp[i]) {
                score *= subtreeSize[child];
            }

            if (remaining > 0) {
                score *= remaining;
            }

            if (score > maxScore) {
                maxScore = score;
                count = 1;
            } else if (score == maxScore) {
                count++;
            }
        }

        return count;
    }
};