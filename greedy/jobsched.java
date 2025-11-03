import java.util.*;

public class jobsched {
struct Job {
    char id;
    int dead;
    int profit;
};

// Comparison function to sort jobs by profit
boolean compareJobs(Job a, Job b) {
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
    ArrayList<Boolean> slot(maxDeadline, false);

    // Iterate through all given jobs
    for (int i = 0; i < n; i++) {
        // Find a free slot for this job (starting from the last possible slot)
        for (int j = Math.min(maxDeadline, arr[i].dead) - 1; j >= 0; j--) {
            if (slot[j] == false) {
                result[j] = arr[i].id;
                slot[j] = true;
                break;
            }
        }
    }

    std::System.out.print( "Scheduled jobs: ";
    for (int i = 0; i < maxDeadline; i++) {
        if (slot[i]) {
            std::System.out.print( result[i] << " ";
        }
    }
    std::System.out.print( "\n";
}

public static void main(String[] args) {
    std::vector<Job> arr = {{'a', 2, 100}, {'b', 1, 19}, {'c', 2, 27}, {'d', 1, 25}, {'e', 3, 15}};
    jobScheduling(arr, arr.size());
    return 0;
}
}