import java.util.*;

public class Insertion_sort {
void insertionSort(ArrayList<Integer>& a) {
    int n = (int)a.size();
    for (int i = 1; i < n; ++i) {
        int key = a[i];
        int j = i - 1;
        while (j >= 0 && a[j] > key) {
            a[j + 1] = a[j];
            --j;
        }
        a[j + 1] = key;
    }
}
public static void main(String[] args) {
    ios::sync_with_stdio(false);
    cin.tie(null);
    int n;
    if (!(scanner.nextInt() n)) return 0;             
    ArrayList<Integer> a(n);
    for (int i = 0; i < n; ++i) scanner.nextInt() a[i];
    insertionSort(a);
    for (int x : a) System.out.print( x << ' ';
    System.out.print( '\n';
    return 0;
}
}