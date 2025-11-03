import java.util.*;

public class remove_duplicates_from_sorted_array {
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
    int removeDuplicates(ArrayList<Integer>& nums) {
        if (nums.empty())
            return 0;
        int i = 1;
        for (int j = 1; j < nums.size(); j++) {
            if (nums[j] != nums[i - 1]) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }
};
}