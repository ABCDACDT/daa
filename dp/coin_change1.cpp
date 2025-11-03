#include <iostream>
#include <vector>
#include <algorithm>
#include <limits>

// Function to find minimum coins required to make a change
int minCoins(const std::vector<int>& coins, int V) {
    int M = coins.size();
    std::vector<int> dp(V + 1, std::numeric_limits<int>::max());

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

int main() {
    std::vector<int> coins = {9, 6, 5, 1};
    int V = 11;
    std::cout << "Minimum coins required is " << minCoins(coins, V) << std::endl;
    return 0;
}