#include <iostream>
#include <vector>
#include <algorithm>

#define NUM_STATIONS 4

// Function to find the minimum time to traverse the assembly lines
int assemblyLineScheduling(const std::vector<std::vector<int>>& a, const std::vector<std::vector<int>>& t, const std::vector<int>& e, const std::vector<int>& x) {
    std::vector<int> T1(NUM_STATIONS), T2(NUM_STATIONS);

    // Time taken to leave first station in line 1
    T1[0] = e[0] + a[0][0];
    
    // Time taken to leave first station in line 2
    T2[0] = e[1] + a[1][0];

    // Fill tables T1[] and T2[] using the recursive relations
    for (int i = 1; i < NUM_STATIONS; ++i) {
        T1[i] = std::min(T1[i - 1] + a[0][i], T2[i - 1] + t[1][i - 1] + a[0][i]);
        T2[i] = std::min(T2[i - 1] + a[1][i], T1[i - 1] + t[0][i - 1] + a[1][i]);
    }

    // Consider exit times and find overall minimum
    return std::min(T1[NUM_STATIONS - 1] + x[0], T2[NUM_STATIONS - 1] + x[1]);
}

int main() {
    // Station processing times
    std::vector<std::vector<int>> a = {{4, 5, 3, 2},
                                       {2, 10, 1, 4}};
    // Transfer times between lines
    std::vector<std::vector<int>> t = {{0, 7, 4, 5},
                                       {0, 9, 2, 8}};
    // Entry times
    std::vector<int> e = {10, 12};
    // Exit times
    std::vector<int> x = {18, 7};

    std::cout << "Minimum time for assembly: " << assemblyLineScheduling(a, t, e, x) << std::endl;

    return 0;
}