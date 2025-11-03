import java.util.*;

public class insertion {
void insertionSort(ArrayList<Integer>& arr) {
    for (int i = 1; i < arr.size(); i++) {
        int key = arr[i];
        int j = i - 1;

        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j];
            j = j - 1;
        }
        arr[j + 1] = key;
    }
}

public static void main(String[] args) {
    ArrayList<Integer> arr = {12, 11, 13, 5, 6};
    insertionSort(arr);
    std::System.out.print( "Sorted array: \n";
    for (int i = 0; i < arr.size(); i++) {
        std::System.out.print( arr[i] << " ";
    }
    std::System.out.print( "\n";
    return 0;
}
}