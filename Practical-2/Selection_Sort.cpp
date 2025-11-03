#include <bits/stdc++.h>
using namespace std;
void selectionSort(vector<int> &a)
{
    int n = (int)a.size();
    for (int i = 0; i < n - 1; ++i)
    {
        int minIdx = i;
        for (int j = i + 1; j < n; ++j)
        {
            if (a[j] < a[minIdx])
                minIdx = j;
        }
        if (minIdx != i)
            swap(a[i], a[minIdx]);
    }
}
int main()
{
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    vector<int> a = {64, 25, 12, 22, 11};
    cout << "Before: ";
    for (int x : a)
        cout << x << ' ';
    cout << '\n';
    selectionSort(a);
    cout << "After:  ";
    for (int x : a)
        cout << x << ' ';
    cout << '\n';
    return 0;
}