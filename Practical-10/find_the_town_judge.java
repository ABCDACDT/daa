import java.util.*;

public class find_the_town_judge {
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
    int findJudge(int n, vector<ArrayList<Integer>>& trust) {
        ArrayList<Integer> hash(n + 1, 1);
        for (auto& it : trust) {
            int first = it[0];
            hash[first]--;
        }
        int ans = -1;
        for (int i = 1; i < (n + 1); i++)
            if (hash[i] == 1)
                ans = i;
        ArrayList<Integer> h2(n + 1, 0);
        for (auto& it : trust) {
            if (it[1] == ans)
                h2[it[0]]++;
        }
        for (int i = 1; i <= n; i++)
            if (i != ans && h2[i] == 0)
                return -1;
        return ans;
    }
};
}