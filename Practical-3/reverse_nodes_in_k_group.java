import java.util.*;

public class reverse_nodes_in_k_group {
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
//  Definition for singly-linked list.
struct ListNode
{
    int val;
    ListNode *next;
    ListNode() : val(0), next(null) {}
    ListNode(int x) : val(x), next(null) {}
    ListNode(int x, ListNode *next) : val(x), next(next) {}
};
class Solution
{
public:
    ListNode *reverseKGroup(ListNode *head, int k)
    {
        if (head == null)
        {
            return null;
        }
        ListNode *prev = null;
        ListNode *next = null;
        ListNode *curr = head;
        ListNode *temp = head;
        int len = 0;
        while (temp != null)
        {
            len++;
            temp = temp.next;
        }
        if (len < k)
        {
            return head;
        }
        int count = 0;
        while (curr != null && count < k)
        {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            count++;
        }
        if (next != null)
        {
            head.next = reverseKGroup(next, k);
        }
        return prev;
    }
};
}