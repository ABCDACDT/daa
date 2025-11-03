#include <bits/stdc++.h>
using namespace std;
void mergeRanges(vector<int>& a, vector<int>& temp, int left, int mid, int right) {
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
void mergeSortRec(vector<int>& a, vector<int>& temp, int left, int right) {
    if (left >= right) return;
    int mid = left + (right - left) / 2;
    mergeSortRec(a, temp, left, mid);
    mergeSortRec(a, temp, mid + 1, right);
    mergeRanges(a, temp, left, mid, right);
}
void mergeSort(vector<int>& a) {
    if (a.empty()) return;
    vector<int> temp(a.size());
    mergeSortRec(a, temp, 0, (int)a.size() - 1);
}
int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    int n;
    if (!(cin >> n)) return 0;
    vector<int> a(n);
    for (int i = 0; i < n; ++i) cin >> a[i];
    mergeSort(a);
    for (int x : a) cout << x << ' ';
    cout << '\n';
    return 0;
}