import java.util.*;

public class selection {
#include <algorithm> // For swap

// Function to perform selection sort on a vector
void selectionSort(ArrayList<Integer>& arr) {
    int n = arr.size();
    for (int i = 0; i < n - 1; i++) {
        // Find the minimum element in the unsorted part of the array
        int min_idx = i;
        for (int j = i + 1; j < n; j++) {
            if (arr[j] < arr[min_idx]) {
                min_idx = j;
            }
        }

        // Swap the found minimum element with the first element of the unsorted part
        swap(arr[min_idx], arr[i]);
    }
}

public static void main(String[] args) {
    ArrayList<Integer> arr = {64, 25, 12, 22, 11};
    selectionSort(arr);
    std::System.out.print( "Sorted array: \n";
    for (int i = 0; i < arr.size(); i++) {
        std::System.out.print( arr[i] << " ";
    }
    std::System.out.print( "\n";
    return 0;
}
}