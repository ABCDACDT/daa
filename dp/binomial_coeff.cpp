#include <iostream>
#include <vector>
#include <algorithm>

// Function to calculate Binomial Coefficient
int binomialCoefficient(int n, int k) {
    std::vector<std::vector<int>> C(n + 1, std::vector<int>(k + 1, 0));

    // Calculate value of Binomial Coefficient in bottom up manner
    for (int i = 0; i <= n; i++) {
        for (int j = 0; j <= std::min(i, k); j++) {
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

int main() {
    int n = 5, k = 2;
    std::cout << "Value of C(" << n << ", " << k << ") is " << binomialCoefficient(n, k) << std::endl;
    return 0;
}