#include <bits/stdc++.h>
using namespace std;
void insertionSort(vector<int>& a) {
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
int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    int n;
    if (!(cin >> n)) return 0;             
    vector<int> a(n);
    for (int i = 0; i < n; ++i) cin >> a[i];
    insertionSort(a);
    for (int x : a) cout << x << ' ';
    cout << '\n';
    return 0;
}