import java.util.*;

public class quick {
#include <algorithm> // For swap

// Function to partition the array on the basis of pivot element
int partition(ArrayList<Integer>& arr, int low, int high) {
    int pivot = arr[high];
    int i = (low - 1);

    for (int j = low; j <= high - 1; j++) {
        if (arr[j] < pivot) {
            i++;
            swap(arr[i], arr[j]);
        }
    }
    swap(arr[i + 1], arr[high]);
    return (i + 1);
}

// Function to implement quick sort
void quickSort(ArrayList<Integer>& arr, int low, int high) {
    if (low < high) {
        int pi = partition(arr, low, high);

        quickSort(arr, low, pi - 1);
        quickSort(arr, pi + 1, high);
    }
}

public static void main(String[] args) {
    ArrayList<Integer> arr = {10, 7, 8, 9, 1, 5};
    quickSort(arr, 0, arr.size() - 1);
    std::System.out.print( "Sorted array: \n";
    for (int i = 0; i < arr.size(); i++) {
        std::System.out.print( arr[i] << " ";
    }
    std::System.out.print( "\n";
    return 0;
}
}