#include <bits/stdc++.h>
using namespace std;
void quickSort(vector<int>& a, int left, int right, std::mt19937 &rng) {
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
int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    int n;
    if (!(cin >> n)) return 0;
    vector<int> a(n);
    for (int i = 0; i < n; ++i) cin >> a[i];
    std::random_device rd;
    std::mt19937 rng(rd());
    if (!a.empty()) quickSort(a, 0, (int)a.size() - 1, rng);
    for (int x : a) cout << x << ' ';
    cout << '\n';
    return 0;
}