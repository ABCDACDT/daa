import java.util.*;

public class binary_tree_maximum_path_sum {
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
//  Definition for a binary tree node.
 struct TreeNode {
     int val;
     TreeNode *left;
     TreeNode *right;
     TreeNode() : val(0), left(null), right(null) {}
     TreeNode(int x) : val(x), left(null), right(null) {}
     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left),
 right(right) {}
 };

class Solution {
public:
    int maxPathSum(TreeNode* root) {
        int res = root.val;
        dfs(root, res);
        return res;
    }

private:
    int dfs(TreeNode* node, int& res) {
        if (!node) {
            return 0;
        }
        int leftSum = max(0, dfs(node.left, res));
        int rightSum = max(0, dfs(node.right, res));
        res = max(res, leftSum + rightSum + node.val);
        return max(leftSum, rightSum) + node.val;
    }
};
}