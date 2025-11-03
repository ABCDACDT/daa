import java.util.*;

public class knapsack_backtracking {
    // Recursive backtracking function for 0/1 Knapsack
    static int knapsackBacktrack(int W, int[] wt, int[] val, int i) {
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
            return Math.max(included, notIncluded);
        }
    }

    public static void main(String[] args) {
        int[] val = {60, 100, 120};
        int[] wt = {10, 20, 30};
        int W = 50;
        int n = val.length;
        System.out.println("Maximum value in knapsack: " + knapsackBacktrack(W, wt, val, n));
    }
}
