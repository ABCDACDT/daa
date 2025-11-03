import java.util.*;

public class combination_sum {
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
    vector<ArrayList<Integer>> combinationSum(ArrayList<Integer>& candidates, int target) {
        vector<ArrayList<Integer>> res;
        ArrayList<Integer> comb;
        makeCombination(candidates, target, 0, comb, 0, res);
        return res;
    }

private:
    void makeCombination(ArrayList<Integer>& candidates, int target, int idx,
                         ArrayList<Integer>& comb, int total,
                         vector<ArrayList<Integer>>& res) {
        if (total == target) {
            res.push_back(comb);
            return;
        }

        if (total > target || idx >= candidates.size()) {
            return;
        }

        comb.push_back(candidates[idx]);
        makeCombination(candidates, target, idx, comb, total + candidates[idx],
                        res);
        comb.pop_back();
        makeCombination(candidates, target, idx + 1, comb, total, res);
    }
};
}