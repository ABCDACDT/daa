import java.util.*;

public class convert_sorted_list_to_binary_search_tree {
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
// Definition for singly-linked list.
struct ListNode {
     int val;
    ListNode *next;
     ListNode() : val(0), next(null) {}
     ListNode(int x) : val(x), next(null) {}
     ListNode(int x, ListNode *next) : val(x), next(next) {}
};

// Definition for a binary tree node.
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
    TreeNode* sortedListToBST(ListNode* head) {
        if (head == null)
            return null;
        if (head.next == null)
            return new TreeNode(head.val);
        ListNode* middle = getMiddle(head);
        TreeNode* root = new TreeNode(middle.val);
        root.right = sortedListToBST(middle.next);
        middle.next = null;
        root.left = sortedListToBST(head);
        return root;
    }
    ListNode* getMiddle(ListNode* head) {
        ListNode* fast = head;
        ListNode* slow = head;
        ListNode* prev = null;
        while (fast != null && fast.next != null) {
            fast = fast.next->next;
            prev = slow;
            slow = slow.next;
        }
        if (prev != null)
            prev.next = null;
        return slow;
    }
};
}