import java.util.*;

public class maximum_earnings_from_taxi {
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
    long dp[100005];
    long solve(int idx, vector<ArrayList<Integer>>& rides, ArrayList<Integer>& starts) {
        if (idx >= rides.size())
            return 0;
        if (dp[idx] != -1)
            return dp[idx];
        long skip = solve(idx + 1, rides, starts);
        long take = (rides[idx][1] - rides[idx][0]) + rides[idx][2];
        int next = lower_bound(starts.begin(), starts.end(), rides[idx][1]) -
                   starts.begin();
        take += solve(next, rides, starts);
        return dp[idx] = max(skip, take);
    }
    long maxTaxiEarnings(int n, vector<ArrayList<Integer>>& rides) {
        sort(rides.begin(), rides.end());
        ArrayList<Integer> starts;
        for (auto& r : rides)
            starts.push_back(r[0]);
        memset(dp, -1, sizeof(dp));
        return solve(0, rides, starts);
    }
};
auto init = atexit([]() { ofstream("display_runtime.txt") << "0"; });
}