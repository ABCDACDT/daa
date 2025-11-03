import java.util.*;

public class fibonacci_number {
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
    int ans(int n) {
        if (n <= 1)
            return n;
        return ans(n - 1) + ans(n - 2);
    }
    int fib(int n) { return ans(n); }
};
}