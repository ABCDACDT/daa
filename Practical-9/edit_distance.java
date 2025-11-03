import java.util.*;

public class edit_distance {
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
    int minDistance(string word1, string word2) {
        int m = word1.size();
        int n = word2.size();
        vector<ArrayList<Integer>> cache(m + 1, ArrayList<Integer>(n + 1, -1));
        return dp(word1, word2, m, n, cache);
    }
private:
    int dp(const string& word1, const string& word2, int i, int j, vector<ArrayList<Integer>>& cache) {
        if (i == 0) return j;
        if (j == 0) return i;
        if (cache[i][j] != -1) return cache[i][j];
        if (word1[i - 1] == word2[j - 1]) {
            cache[i][j] = dp(word1, word2, i - 1, j - 1, cache);
        } else {
            cache[i][j] = 1 + min({
                dp(word1, word2, i - 1, j, cache),     
                dp(word1, word2, i, j - 1, cache),     
                dp(word1, word2, i - 1, j - 1, cache)  
            });
        }
        return cache[i][j];
    }
};
}