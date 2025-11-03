import java.util.*;

public class kth_smallest_number_in_multiplication_table {
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
    int count(int m, int n, int x) {
        int ans = 0;
        for (int i = 1; i <= m; i++)
            ans += min(x / i, n);
        return ans;
    }
    int findKthNumber(int m, int n, int k) {
        int L = 1, R = m * n, mid, ans;
        while (L <= R) {
            mid = (L + R) >> 1;
            if (count(m, n, mid) < k)
                L = mid + 1;
            else
                R = mid - 1, ans = mid;
        }
        return ans;
    }
};
}