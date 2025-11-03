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
class Solution {
public:
    int longestConsecutive(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int cnt = 1;
        set<int> a;
        if (int(nums.size()) == 0) {
            return 0;
        }
        for (int i = 0; i < int(nums.size()) - 1; i++) {
            if (nums.at(i) == nums.at(i + 1)) {
                continue;
            } else if (nums.at(i) + 1 == nums.at(i + 1)) {
                cnt++;
            } else {
                a.insert(cnt);
                cnt = 1;
            }
        }
        a.insert(cnt);
        int max = *max_element(a.begin(), a.end());
        return max;
    }
};