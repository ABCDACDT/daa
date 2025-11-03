import java.util.*;

public class longcomsubseq {
// Function to find the length of the Longest Common Subsequence
int lcs(const String& X, const String& Y) {
    int m = X.length();
    int n = Y.length();
    std::vector<ArrayList<Integer>> L(m + 1, ArrayList<Integer>(n + 1));

    // Build L[m+1][n+1] in bottom up fashion
    for (int i = 0; i <= m; i++) {
        for (int j = 0; j <= n; j++) {
            if (i == 0 || j == 0) {
                L[i][j] = 0;
            } else if (X[i - 1] == Y[j - 1]) {
                L[i][j] = L[i - 1][j - 1] + 1;
            } else {
                L[i][j] = Math.max(L[i - 1][j], L[i][j - 1]);
            }
        }
    }
    return L[m][n];
}

public static void main(String[] args) {
    String S1 = "AGGTAB";
    String S2 = "GXTXAYB";
    std::System.out.print( "Length of LCS is " << lcs(S1, S2) );
    return 0;
}
}