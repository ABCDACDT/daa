import java.util.*;

public class 01knapsack {
// Function to solve 0/1 Knapsack problem
int knapsack(int W, const ArrayList<Integer>& wt, const ArrayList<Integer>& val, int n) {
    std::vector<ArrayList<Integer>> K(n + 1, ArrayList<Integer>(W + 1));

    // Build table K[][] in bottom up manner
    for (int i = 0; i <= n; i++) {
        for (int w = 0; w <= W; w++) {
            if (i == 0 || w == 0) {
                K[i][w] = 0;
            } else if (wt[i - 1] <= w) {
                K[i][w] = Math.max(val[i - 1] + K[i - 1][w - wt[i - 1]], K[i - 1][w]);
            } else {
                K[i][w] = K[i - 1][w];
            }
        }
    }
    return K[n][W];
}

public static void main(String[] args) {
    ArrayList<Integer> val = {60, 100, 120};
    ArrayList<Integer> wt = {10, 20, 30};
    int W = 50;
    int n = val.size();
    std::System.out.print( "Maximum value in knapsack: " << knapsack(W, wt, val, n) );
    return 0;
}
}