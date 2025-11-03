import java.util.*;

public class BestTimeToBuyAndSellStock {
    public static int maxProfit(int[] prices) {
        if (prices.length <= 1)
            return 0;
        int mi = prices[0];
        int mx = 0;
        for (int i = 1; i < prices.length; i++) {
            mx = Math.max(mx, prices[i] - mi);
            mi = Math.min(mi, prices[i]);
        }
        return mx;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = 1;
        if (sc.hasNextInt())
            t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++)
                arr[i] = sc.nextInt();
            System.out.println(maxProfit(arr));
        }
        sc.close();
    }
}
