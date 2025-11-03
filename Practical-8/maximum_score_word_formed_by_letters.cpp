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
    int maxScoreWords(vector<string>& words, vector<char>& letters,
                      vector<int>& score) {
        unordered_map<char, int> lettersCounter;
        for (char letter : letters) {
            lettersCounter[letter]++;
        }

        int totalScore = 0;

        function<void(int, unordered_map<char, int>, int)> explore =
            [&](int index, unordered_map<char, int> letterCounter,
                int currScore) {
                totalScore = max(totalScore, currScore);
                if (index == words.size())
                    return;

                for (int i = index; i < words.size(); ++i) {
                    unordered_map<char, int> tmpCounter = letterCounter;
                    string word = words[i];
                    int wordScore = 0;
                    bool isValid = true;

                    for (char ch : word) {
                        if (tmpCounter[ch] > 0) {
                            tmpCounter[ch]--;
                            wordScore += score[ch - 'a'];
                        } else {
                            isValid = false;
                            break;
                        }
                    }

                    if (isValid) {
                        explore(i + 1, tmpCounter, currScore + wordScore);
                    }
                }
            };

        explore(0, lettersCounter, 0);
        return totalScore;
    }
};