#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
	int n;
	cout << "Enter number of elements: ";
	if (!(cin >> n) || n <= 0) return 0;

	vector<int> a(n);
	cout << "Enter elements separated by space: ";
	for (int i = 0; i < n; ++i) cin >> a[i];

	for (int i = 0; i < n - 1; ++i) {
		bool swapped = false;
		for (int j = 0; j < n - 1 - i; ++j) {
			if (a[j] > a[j + 1]) {
				swap(a[j], a[j + 1]);
				swapped = true;
			}
		}
		if (!swapped) break;
	}

	cout << "Sorted array: ";
	for (int x : a) cout << x << " ";
	cout << '\n';
	return 0;
}

