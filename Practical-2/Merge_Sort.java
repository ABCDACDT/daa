import java.util.*;

public class Merge_Sort {
void mergeRanges(ArrayList<Integer>& a, ArrayList<Integer>& temp, int left, int mid, int right) {
    int i = left;     
    int j = mid + 1;   
    int k = left;      
    while (i <= mid && j <= right) {
        if (a[i] <= a[j]) {
            temp[k++] = a[i++];
        } else {
            temp[k++] = a[j++];
        }
    }
    while (i <= mid) temp[k++] = a[i++];   
    while (j <= right) temp[k++] = a[j++]; 
    for (int idx = left; idx <= right; ++idx) a[idx] = temp[idx];
}
void mergeSortRec(ArrayList<Integer>& a, ArrayList<Integer>& temp, int left, int right) {
    if (left >= right) return;
    int mid = left + (right - left) / 2;
    mergeSortRec(a, temp, left, mid);
    mergeSortRec(a, temp, mid + 1, right);
    mergeRanges(a, temp, left, mid, right);
}
void mergeSort(ArrayList<Integer>& a) {
    if (a.empty()) return;
    ArrayList<Integer> temp(a.size());
    mergeSortRec(a, temp, 0, (int)a.size() - 1);
}
public static void main(String[] args) {
    ios::sync_with_stdio(false);
    cin.tie(null);
    int n;
    if (!(scanner.nextInt() n)) return 0;
    ArrayList<Integer> a(n);
    for (int i = 0; i < n; ++i) scanner.nextInt() a[i];
    mergeSort(a);
    for (int x : a) System.out.print( x << ' ';
    System.out.print( '\n';
    return 0;
}
}