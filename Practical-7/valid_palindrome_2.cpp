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
    bool res1(string& s, int i, int j) {
        while (i < j) {
            if (s[i] != s[j])
                return false;
            else {
                i++;
                j--;
            }
        }
        return true;
    }
    bool validPalindrome(string s) {
        int n = s.size();
        int i = 0, j = n - 1;
        while (i < j) {
            if (s[i] != s[j]) {
                return res1(s, i + 1, j) || res1(s, i, j - 1);
            } else {
                i++;
                j--;
            }
        }
        return true;
    }
};