import java.util.*;

public class Bubble_sort {
void bubbleSort(ArrayList<Integer>& a) {
    int n = (int)a.size();
    for (int i = 0; i < n - 1; ++i) {
        boolean swapped = false;
        for (int j = 0; j < n - 1 - i; ++j) {
            if (a[j] > a[j + 1]) {
                swap(a[j], a[j + 1]);
                swapped = true;
            }
        }
        if (!swapped) break;
    }
}
public static void main(String[] args) {
    ios::sync_with_stdio(false);
    cin.tie(null);
    int n;
    if (!(scanner.nextInt() n)) return 0;
    ArrayList<Integer> a(n);
    for (int i = 0; i < n; ++i) scanner.nextInt() a[i];
    bubbleSort(a);
    for (int x : a) System.out.print( x << ' ';
    System.out.print( '\n';
    return 0;
}
}