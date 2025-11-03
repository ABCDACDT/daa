import java.util.*;

public class two_sum {
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
    ArrayList<Integer> twoSum(ArrayList<Integer>& nums, int target) {
        int ans, i1, i2;
        unordered_map<int, int> umap;
        for (int i = 0; i < int(nums.size()); i++) {
            ans = target - nums[i];
            if (umap.find(ans) != umap.end()) {
                i1 = umap[ans];
                i2 = i;
                break;
            }
            else {
                umap[nums[i]] = i;
            }
        }
        return {i1 , i2};
    }
};
}