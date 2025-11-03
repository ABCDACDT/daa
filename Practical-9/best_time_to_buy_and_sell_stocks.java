import java.util.*;

public class best_time_to_buy_and_sell_stocks {
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
    int maxProfit(ArrayList<Integer>& prices) {
        if (int(prices.size()) == 1)
            return 0;
        int mi = prices[0], mx = INT_MIN;
        for (int i = 1; i < int(prices.size()); i++) {
            if (prices[i] - mi >= 0)
                mx = max(mx, prices[i] - mi);
            else
                mx = max(mx, 0);
            mi = min(mi, prices[i]);
        }
        return mx;
    }
};
}