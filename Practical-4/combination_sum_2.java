import java.util.*;

public class combination_sum_2 {
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
    vector<ArrayList<Integer>> combinationSum2(ArrayList<Integer>& candidates, int target) {
        sort(candidates.begin(), candidates.end());
        vector<ArrayList<Integer>> ans;
        ArrayList<Integer> ds;
        findCombination(0, target, candidates, ans, ds);
        return ans;
    }
    void findCombination(int ind, int target, ArrayList<Integer>& arr,
                         vector<ArrayList<Integer>>& ans, ArrayList<Integer>& ds) {
        if (target == 0) {
            ans.push_back(ds);
            return;
        }
        for (int i = ind; i < arr.size(); i++) {
            if (i > ind && arr[i] == arr[i - 1])
                continue;
            if (arr[i] > target)
                break;
            ds.push_back(arr[i]);
            findCombination(i + 1, target - arr[i], arr, ans, ds);
            ds.pop_back();
        }
    }
};
}