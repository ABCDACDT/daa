import java.util.*;

public class reverse_pairs {
using namespace __gnu_pbds;
typedef pair<long, long> pll;
typedef tree<pll, null_type, less<pll>, rb_tree_tag,
             tree_order_statistics_node_update>
    ordered_multiset;
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
    int reversePairs(ArrayList<Integer>& nums) {
        long ans = 0, cnt = 0;
        ordered_multiset om;
        int n = nums.size();
        for (int i = 0; i < n; i++) {
            om.insert({2LL * nums[i], cnt++});
        }
        cnt = 0;
        for (int i = 0; i < n; i++) {
            auto it = om.lower_bound({2LL * nums[i], 0});
            if (it != om.end())
                om.erase(it);
            ans += om.order_of_key({nums[i], 0});
        }
        return ans;
    }
};
}