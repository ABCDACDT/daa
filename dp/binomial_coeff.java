import java.util.*;

public class binomial_coeff {
// Function to calculate Binomial Coefficient
int binomialCoefficient(int n, int k) {
    std::vector<ArrayList<Integer>> C(n + 1, ArrayList<Integer>(k + 1, 0));

    // Calculate value of Binomial Coefficient in bottom up manner
    for (int i = 0; i <= n; i++) {
        for (int j = 0; j <= Math.min(i, k); j++) {
            // Base Cases
            if (j == 0 || j == i) {
                C[i][j] = 1;
            } else {
                C[i][j] = C[i - 1][j - 1] + C[i - 1][j];
            }
        }
    }
    return C[n][k];
}

public static void main(String[] args) {
    int n = 5, k = 2;
    std::System.out.print( "Value of C(" << n << ", " << k << ") is " << binomialCoefficient(n, k) );
    return 0;
}
}