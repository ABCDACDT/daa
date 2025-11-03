import java.util.*;

public class matrix_chain_multiplication_dp_8 {
// C++ code to implement the
// matrix chain multiplication using tabulation
int matrixMultiplication(ArrayList<Integer> &arr)
{

    int n = arr.size();

    // Create a 2D DP array to store the minimum
    // multiplication costs
    vector<ArrayList<Integer>> dp(n, ArrayList<Integer>(n, 0));

    // Fill the DP array.
    // Here, len is the chain length
    for (int len = 2; len < n; len++)
    {
        for (int i = 0; i < n - len; i++)
        {
            int j = i + len;
            dp[i][j] = INT_MAX;

            for (int k = i + 1; k < j; k++)
            {
                int cost = dp[i][k] + dp[k][j] + arr[i] * arr[k] * arr[j];
                dp[i][j] = min(dp[i][j], cost);
            }
        }
    }

    // The minimum cost is stored in dp[0][n-1]
    return dp[0][n - 1];
}

public static void main(String[] args)
{
    ArrayList<Integer> arr = {2, 1, 3, 4};
    System.out.print( matrixMultiplication(arr);
    return 0;
}
}