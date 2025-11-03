import java.util.*;

public class majority_element {
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
    int majorityElement(ArrayList<Integer>& nums) {
        int n = nums.size();
        unordered_map<int, int> m;
        for(int i = 0; i < n; i++){
            m[nums[i]]++;
        }
        n = n/2;
        for(auto x: m){
            if(x.second > n){
                return x.first;
            }
        }
        return 0;
    }
};
}