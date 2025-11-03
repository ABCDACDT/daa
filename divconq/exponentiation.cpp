#include <iostream>

// Function to calculate power in O(log n)
long long power(long long base, int exp) {
    long long res = 1;
    // Reduce the base modulo if needed for very large numbers
    // base %= some_mod; 
    while (exp > 0) {
        // If exponent is odd, multiply base with result
        if (exp % 2 == 1) {
            res = res * base;
        }
        // Exponent must be even now, so halve it
        base = base * base;
        exp /= 2;
    }
    return res;
}

int main() {
    long long base = 3;
    int exp = 5;
    std::cout << "Result of " << base << "^" << exp << " is " << power(base, exp) << std::endl;
    return 0;
}