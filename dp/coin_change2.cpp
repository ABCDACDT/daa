#include <iostream>
#include <vector>

// Function to find the number of ways to make change
int countWays(const std::vector<int>& coins, int n, int sum) {
    std::vector<int> dp(sum + 1, 0);
    dp[0] = 1;

    // Pick all coins one by one and update dp[] values
    for (int i = 0; i < n; i++) {
        for (int j = coins[i]; j <= sum; j++) {
            dp[j] += dp[j - coins[i]];
        }
    }
    return dp[sum];
}

int main() {
    std::vector<int> coins = {1, 2, 3};
    int n = coins.size();
    int sum = 4;
    std::cout << "Number of ways to make change: " << countWays(coins, n, sum) << std::endl;
    return 0;
}