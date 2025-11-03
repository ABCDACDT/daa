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
//   Definition for singly-linked list.
struct ListNode
{
    int val;
    ListNode *next;
    ListNode() : val(0), next(nullptr) {}
    ListNode(int x) : val(x), next(nullptr) {}
    ListNode(int x, ListNode *next) : val(x), next(next) {}
};

class Solution
{
public:
    ListNode *middleNode(ListNode *head)
    {
        int count = 0;
        ListNode *temp = head;
        while (temp)
        {
            count++;
            temp = temp->next;
        }
        for (int i = 0; i < count / 2; i++)
        {
            head = head->next;
        }
        return head;
    }
};