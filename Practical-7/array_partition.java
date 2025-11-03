import java.util.*;

public class array_partition {
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
    int arrayPairSum(ArrayList<Integer>& nums) {
        ArrayList<Integer> hashtable(20001, 0);
        for (size_t i = 0; i < nums.size(); i++) {
            hashtable[nums[i] + 10000]++;
        }
        int ret = 0;
        int flag = 0;
        for (size_t i = 0; i < 20001;) {
            if ((hashtable[i] > 0) && (flag == 0)) {
                ret = ret + i - 10000;
                flag = 1;
                hashtable[i]--;
            } else if ((hashtable[i] > 0) && (flag == 1)) {
                hashtable[i]--;
                flag = 0;
            } else
                i++;
        }
        return ret;
    }
};
}