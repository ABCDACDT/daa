#include <iostream>
#include <vector>

// Function to calculate the nth Fibonacci number
int fibonacci(int n) {
    if (n <= 1) return n;
    
    std::vector<int> f(n + 1);
    f[0] = 0;
    f[1] = 1;
    
    // Fill the DP table
    for (int i = 2; i <= n; i++) {
        f[i] = f[i - 1] + f[i - 2];
    }
    return f[n];
}

int main() {
    int n = 9;
    std::cout << "The " << n << "th Fibonacci number is " << fibonacci(n) << std::endl;
    return 0;
}