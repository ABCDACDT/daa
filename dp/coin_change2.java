import java.util.*;

public class coin_change2 {
// Function to find the number of ways to make change
int countWays(const ArrayList<Integer>& coins, int n, int sum) {
    ArrayList<Integer> dp(sum + 1, 0);
    dp[0] = 1;

    // Pick all coins one by one and update dp[] values
    for (int i = 0; i < n; i++) {
        for (int j = coins[i]; j <= sum; j++) {
            dp[j] += dp[j - coins[i]];
        }
    }
    return dp[sum];
}

public static void main(String[] args) {
    ArrayList<Integer> coins = {1, 2, 3};
    int n = coins.size();
    int sum = 4;
    std::System.out.print( "Number of ways to make change: " << countWays(coins, n, sum) );
    return 0;
}
}