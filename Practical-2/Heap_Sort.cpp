#include <bits/stdc++.h>
using namespace std;
void siftDown(vector<int>& a, int i, int heapSize) {
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
void heapSort(vector<int>& a) {
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
int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    int n;
    if (!(cin >> n)) return 0;
    vector<int> a(n);
    for (int i = 0; i < n; ++i) cin >> a[i];
    heapSort(a);
    for (int x : a) cout << x << ' ';
    cout << '\n';
    return 0;
}