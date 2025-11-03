#include <iostream>
#include <vector>
#include <algorithm>

// Greedy algorithm to find the minimum coins for a given amount
void findMinCoins(std::vector<int>& coins, int amount) {
    std::sort(coins.rbegin(), coins.rend()); // Sort in descending order

    std::vector<int> result;

    // Iterate through coins
    for (int coin : coins) {
        while (amount >= coin) {
            amount -= coin;
            result.push_back(coin);
        }
    }

    std::cout << "Coins used to make change: ";
    for (int coin : result) {
        std::cout << coin << " ";
    }
    std::cout << std::endl;
}

int main() {
    std::vector<int> coins = {1, 2, 5, 10, 20, 50, 100, 500, 2000};
    int amount = 93;
    findMinCoins(coins, amount);
    return 0;
}