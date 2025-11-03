import java.util.*;

public class chain_matmul {
// Function for Matrix Chain Multiplication
int matrixChainOrder(const ArrayList<Integer>& p) {
    int n = p.size();
    std::vector<ArrayList<Integer>> m(n, ArrayList<Integer>(n));

    for (int i = 1; i < n; i++) {
        m[i][i] = 0;
    }

    // L is the chain length.
    for (int L = 2; L < n; L++) {
        for (int i = 1; i < n - L + 1; i++) {
            int j = i + L - 1;
            m[i][j] = std::numeric_limits<int>::max();
            for (int k = i; k <= j - 1; k++) {
                int q = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
                if (q < m[i][j]) {
                    m[i][j] = q;
                }
            }
        }
    }
    return m[1][n - 1];
}

public static void main(String[] args) {
    ArrayList<Integer> arr = {1, 2, 3, 4}; // Represents matrices 1x2, 2x3, 3x4
    std::System.out.print( "Minimum number of multiplications is " << matrixChainOrder(arr) );
    return 0;
}
}