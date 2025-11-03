import java.util.*;

public class fibo {
// Function to calculate the nth Fibonacci number
int fibonacci(int n) {
    if (n <= 1) return n;
    
    ArrayList<Integer> f(n + 1);
    f[0] = 0;
    f[1] = 1;
    
    // Fill the DP table
    for (int i = 2; i <= n; i++) {
        f[i] = f[i - 1] + f[i - 2];
    }
    return f[n];
}

public static void main(String[] args) {
    int n = 9;
    std::System.out.print( "The " << n << "th Fibonacci number is " << fibonacci(n) );
    return 0;
}
}