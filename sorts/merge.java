import java.util.*;

public class merge {
// Function to merge two subarrays of arr[]
void merge(ArrayList<Integer>& arr, int l, int m, int r) {
    int n1 = m - l + 1;
    int n2 = r - m;

    ArrayList<Integer> L(n1), R(n2);

    for (int i = 0; i < n1; i++)
        L[i] = arr[l + i];
    for (int j = 0; j < n2; j++)
        R[j] = arr[m + 1 + j];

    int i = 0;
    int j = 0;
    int k = l;
    while (i < n1 && j < n2) {
        if (L[i] <= R[j]) {
            arr[k] = L[i];
            i++;
        } else {
            arr[k] = R[j];
            j++;
        }
        k++;
    }

    while (i < n1) {
        arr[k] = L[i];
        i++;
        k++;
    }

    while (j < n2) {
        arr[k] = R[j];
        j++;
        k++;
    }
}

// Main function that sorts arr[l..r] using merge()
void mergeSort(ArrayList<Integer>& arr, int l, int r) {
    if (l < r) {
        int m = l + (r - l) / 2;

        mergeSort(arr, l, m);
        mergeSort(arr, m + 1, r);

        merge(arr, l, m, r);
    }
}

public static void main(String[] args) {
    ArrayList<Integer> arr = {12, 11, 13, 5, 6, 7};
    mergeSort(arr, 0, arr.size() - 1);
    std::System.out.print( "Sorted array: \n";
    for (int i = 0; i < arr.size(); i++) {
        std::System.out.print( arr[i] << " ";
    }
    std::System.out.print( "\n";
    return 0;
}
}