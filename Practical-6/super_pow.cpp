#include <bits/stdc++.h>
using namespace std;
int main()
{
    ios::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    int t;
    cin >> t;
    while (t--)
    {
        
    }
    return 0;
}
class Solution {
public:
    int find(int a, int b) {
        a %= 1337;
        int res = 1;
        for (int i = 0; i < b; i++) {
            res *= a;
            res %= 1337;
        }
        return res;
    }
    int superPow(int a, vector<int>& b) {
        int res = 1, x, f;
        for (int i = 0; i < b.size(); i++) {
            x = find(a, b[i]);
            x *= res;
            x %= 1337;
            f = x;
            x = find(x, 10);
            res = x;
        }
        return f;
    }
};