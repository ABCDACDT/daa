#include <iostream>
#include <vector>
#include <algorithm>

// Recursive backtracking function for 0/1 Knapsack
int knapsackBacktrack(int W, const std::vector<int>& wt, const std::vector<int>& val, int i) {
    // Base case: no more items or knapsack is full
    if (i == 0 || W == 0) {
        return 0;
    }

    // If weight of the ith item is more than Knapsack capacity W, it cannot be included
    if (wt[i - 1] > W) {
        return knapsackBacktrack(W, wt, val, i - 1);
    } else {
        // Return the maximum of two cases: (1) ith item included (2) not included
        int included = val[i - 1] + knapsackBacktrack(W - wt[i - 1], wt, val, i - 1);
        int notIncluded = knapsackBacktrack(W, wt, val, i - 1);
        return std::max(included, notIncluded);
    }
}

int main() {
    std::vector<int> val = {60, 100, 120};
    std::vector<int> wt = {10, 20, 30};
    int W = 50;
    int n = val.size();
    std::cout << "Maximum value in knapsack: " << knapsackBacktrack(W, wt, val, n) << std::endl;
    return 0;
}