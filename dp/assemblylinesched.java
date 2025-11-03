import java.util.*;

public class assemblylinesched {
#define NUM_STATIONS 4

// Function to find the minimum time to traverse the assembly lines
int assemblyLineScheduling(const std::vector<ArrayList<Integer>>& a, const std::vector<ArrayList<Integer>>& t, const ArrayList<Integer>& e, const ArrayList<Integer>& x) {
    ArrayList<Integer> T1(NUM_STATIONS), T2(NUM_STATIONS);

    // Time taken to leave first station in line 1
    T1[0] = e[0] + a[0][0];
    
    // Time taken to leave first station in line 2
    T2[0] = e[1] + a[1][0];

    // Fill tables T1[] and T2[] using the recursive relations
    for (int i = 1; i < NUM_STATIONS; ++i) {
        T1[i] = Math.min(T1[i - 1] + a[0][i], T2[i - 1] + t[1][i - 1] + a[0][i]);
        T2[i] = Math.min(T2[i - 1] + a[1][i], T1[i - 1] + t[0][i - 1] + a[1][i]);
    }

    // Consider exit times and find overall minimum
    return Math.min(T1[NUM_STATIONS - 1] + x[0], T2[NUM_STATIONS - 1] + x[1]);
}

public static void main(String[] args) {
    // Station processing times
    std::vector<ArrayList<Integer>> a = {{4, 5, 3, 2},
                                       {2, 10, 1, 4}};
    // Transfer times between lines
    std::vector<ArrayList<Integer>> t = {{0, 7, 4, 5},
                                       {0, 9, 2, 8}};
    // Entry times
    ArrayList<Integer> e = {10, 12};
    // Exit times
    ArrayList<Integer> x = {18, 7};

    std::System.out.print( "Minimum time for assembly: " << assemblyLineScheduling(a, t, e, x) );

    return 0;
}
}