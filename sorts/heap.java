import java.util.*;

public class heap {
#include <algorithm> // For swap

// To heapify a subtree rooted with node i which is an index in arr[]
void heapify(ArrayList<Integer>& arr, int n, int i) {
    int largest = i;
    int l = 2 * i + 1;
    int r = 2 * i + 2;

    if (l < n && arr[l] > arr[largest])
        largest = l;

    if (r < n && arr[r] > arr[largest])
        largest = r;

    if (largest != i) {
        swap(arr[i], arr[largest]);
        heapify(arr, n, largest);
    }
}

// Main function to do heap sort
void heapSort(ArrayList<Integer>& arr) {
    int n = arr.size();

    for (int i = n / 2 - 1; i >= 0; i--)
        heapify(arr, n, i);

    for (int i = n - 1; i > 0; i--) {
        swap(arr[0], arr[i]);
        heapify(arr, i, 0);
    }
}

public static void main(String[] args) {
    ArrayList<Integer> arr = {12, 11, 13, 5, 6, 7};
    heapSort(arr);
    std::System.out.print( "Sorted array: \n";
    for (int i = 0; i < arr.size(); i++) {
        std::System.out.print( arr[i] << " ";
    }
    std::System.out.print( "\n";
    return 0;
}
}