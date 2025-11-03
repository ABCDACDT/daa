import java.util.*;

public class Quick_sort {
void quickSort(ArrayList<Integer>& a, int left, int right, std::mt19937 &rng) {
    if (left >= right) return;
    std::uniform_int_distribution<int> dist(left, right);
    int pivot = a[dist(rng)];
    int i = left - 1;
    int j = right + 1;
    while (true) {       
        do { ++i; } while (a[i] < pivot);
        do { --j; } while (a[j] > pivot);
        if (i >= j) break;
        swap(a[i], a[j]);
    }
    quickSort(a, left, j, rng);
    quickSort(a, j + 1, right, rng);
}
public static void main(String[] args) {
    ios::sync_with_stdio(false);
    cin.tie(null);
    int n;
    if (!(scanner.nextInt() n)) return 0;
    ArrayList<Integer> a(n);
    for (int i = 0; i < n; ++i) scanner.nextInt() a[i];
    std::random_device rd;
    std::mt19937 rng(rd());
    if (!a.empty()) quickSort(a, 0, (int)a.size() - 1, rng);
    for (int x : a) System.out.print( x << ' ';
    System.out.print( '\n';
    return 0;
}
}