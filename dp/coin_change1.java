import java.util.*;

public class coin_change1 {
// Function to find minimum coins required to make a change
int minCoins(const ArrayList<Integer>& coins, int V) {
    int M = coins.size();
    ArrayList<Integer> dp(V + 1, std::numeric_limits<int>::max());

    dp[0] = 0;

    // Compute minimum coins required for all values from 1 to V
    for (int i = 1; i <= V; i++) {
        for (int j = 0; j < M; j++) {
            if (coins[j] <= i) {
                int sub_res = dp[i - coins[j]];
                if (sub_res != std::numeric_limits<int>::max() && sub_res + 1 < dp[i]) {
                    dp[i] = sub_res + 1;
                }
            }
        }
    }
    return dp[V];
}

public static void main(String[] args) {
    ArrayList<Integer> coins = {9, 6, 5, 1};
    int V = 11;
    std::System.out.print( "Minimum coins required is " << minCoins(coins, V) );
    return 0;
}
}