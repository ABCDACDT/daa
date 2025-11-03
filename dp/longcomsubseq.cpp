#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

// Function to find the length of the Longest Common Subsequence
int lcs(const std::string& X, const std::string& Y) {
    int m = X.length();
    int n = Y.length();
    std::vector<std::vector<int>> L(m + 1, std::vector<int>(n + 1));

    // Build L[m+1][n+1] in bottom up fashion
    for (int i = 0; i <= m; i++) {
        for (int j = 0; j <= n; j++) {
            if (i == 0 || j == 0) {
                L[i][j] = 0;
            } else if (X[i - 1] == Y[j - 1]) {
                L[i][j] = L[i - 1][j - 1] + 1;
            } else {
                L[i][j] = std::max(L[i - 1][j], L[i][j - 1]);
            }
        }
    }
    return L[m][n];
}

int main() {
    std::string S1 = "AGGTAB";
    std::string S2 = "GXTXAYB";
    std::cout << "Length of LCS is " << lcs(S1, S2) << std::endl;
    return 0;
}