import java.util.*;

public class Heap_Sort {
void siftDown(ArrayList<Integer>& a, int i, int heapSize) {
    while (true) {
        int left = 2 * i + 1;
        int right = left + 1;
        int largest = i;
        if (left < heapSize && a[left] > a[largest]) largest = left;
        if (right < heapSize && a[right] > a[largest]) largest = right;
        if (largest == i) break;
        swap(a[i], a[largest]);
        i = largest;
    }
}
void heapSort(ArrayList<Integer>& a) {
    int n = (int)a.size();
    if (n <= 1) return;
    for (int i = (n / 2) - 1; i >= 0; --i) {
        siftDown(a, i, n);
    }
    for (int end = n - 1; end > 0; --end) {
        swap(a[0], a[end]);       
        siftDown(a, 0, end);     
    }
}
public static void main(String[] args) {
    ios::sync_with_stdio(false);
    cin.tie(null);
    int n;
    if (!(scanner.nextInt() n)) return 0;
    ArrayList<Integer> a(n);
    for (int i = 0; i < n; ++i) scanner.nextInt() a[i];
    heapSort(a);
    for (int x : a) System.out.print( x << ' ';
    System.out.print( '\n';
    return 0;
}
}