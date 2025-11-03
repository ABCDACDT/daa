#include <iostream>
#include <vector>
#include <algorithm>

struct Job {
    char id;
    int dead;
    int profit;
};

// Comparison function to sort jobs by profit
bool compareJobs(Job a, Job b) {
    return (a.profit > b.profit);
}

// Returns minimum of two values.
void jobScheduling(std::vector<Job>& arr, int n) {
    std::sort(arr.begin(), arr.end(), compareJobs);

    int maxDeadline = 0;
    for (int i = 0; i < n; i++) {
        if (arr[i].dead > maxDeadline) {
            maxDeadline = arr[i].dead;
        }
    }

    std::vector<char> result(maxDeadline, ' ');
    std::vector<bool> slot(maxDeadline, false);

    // Iterate through all given jobs
    for (int i = 0; i < n; i++) {
        // Find a free slot for this job (starting from the last possible slot)
        for (int j = std::min(maxDeadline, arr[i].dead) - 1; j >= 0; j--) {
            if (slot[j] == false) {
                result[j] = arr[i].id;
                slot[j] = true;
                break;
            }
        }
    }

    std::cout << "Scheduled jobs: ";
    for (int i = 0; i < maxDeadline; i++) {
        if (slot[i]) {
            std::cout << result[i] << " ";
        }
    }
    std::cout << std::endl;
}

int main() {
    std::vector<Job> arr = {{'a', 2, 100}, {'b', 1, 19}, {'c', 2, 27}, {'d', 1, 25}, {'e', 3, 15}};
    jobScheduling(arr, arr.size());
    return 0;
}