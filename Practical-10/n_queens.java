import java.util.*;

public class n_queens {
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
    boolean canPlace(ArrayList<String>& board, int row, int col, int n) {
        int r = row, c = col;
        while (r >= 0 && c >= 0) {
            if (board[r][c] == 'Q')
                return false;
            r--;
            c--;
        }
        r = row;
        c = col;
        while (c >= 0) {
            if (board[r][c] == 'Q')
                return false;
            c--;
        }
        r = row;
        c = col;
        while (r < n && c >= 0) {
            if (board[r][c] == 'Q')
                return false;
            r++;
            c--;
        }
        return true;
    }

    void placements(int col, ArrayList<String>& board, vector<ArrayList<String>>& ans,
                    int n) {
        if (col == n) {
            ans.push_back(board);
            return;
        }
        for (int row = 0; row < n; row++) {
            if (canPlace(board, row, col, n)) {
                board[row][col] = 'Q';
                placements(col + 1, board, ans, n);
                board[row][col] = '.';
            }
        }
    }
    vector<ArrayList<String>> solveNQueens(int n) {
        vector<ArrayList<String>> ans;
        ArrayList<String> board(n, string(n, '.'));
        placements(0, board, ans, n);
        return ans;
    }
};
}