import java.util.*;

public class valid_palindrome_2 {
public static void main(String[] args)
{
    ios::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    int t;
    scanner.nextInt() t;
    while (t--)
    {
        
    }
    return 0;
}
class Solution {
public:
    boolean res1(string& s, int i, int j) {
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
    boolean validPalindrome(string s) {
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
}