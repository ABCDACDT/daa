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
    int maxSubArray(vector<int>& nums) {
        int sum = 0, maximumsum = nums[0];
        for (int i = 0; i < int(nums.size()); i++) {
            sum += nums[i];
            maximumsum = max(maximumsum, sum);
            if (sum < 0) {
                sum = 0;
            }
        }
        return maximumsum;
    }
};