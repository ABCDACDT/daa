public class exponentiation {
// Function to calculate power in O(log n)
long power(long base, int exp) {
    long res = 1;
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

public static void main(String[] args) {
    long base = 3;
    int exp = 5;
    std::System.out.print( "Result of " << base << "^" << exp << " is " << power(base, exp) );
    return 0;
}
}