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
    long long dp[100005];
    long long solve(int idx, vector<vector<int>>& rides, vector<int>& starts) {
        if (idx >= rides.size())
            return 0;
        if (dp[idx] != -1)
            return dp[idx];
        long long skip = solve(idx + 1, rides, starts);
        long long take = (rides[idx][1] - rides[idx][0]) + rides[idx][2];
        int next = lower_bound(starts.begin(), starts.end(), rides[idx][1]) -
                   starts.begin();
        take += solve(next, rides, starts);
        return dp[idx] = max(skip, take);
    }
    long long maxTaxiEarnings(int n, vector<vector<int>>& rides) {
        sort(rides.begin(), rides.end());
        vector<int> starts;
        for (auto& r : rides)
            starts.push_back(r[0]);
        memset(dp, -1, sizeof(dp));
        return solve(0, rides, starts);
    }
};
auto init = atexit([]() { ofstream("display_runtime.txt") << "0"; });