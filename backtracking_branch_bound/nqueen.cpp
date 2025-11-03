#include <iostream>
#include <vector>

// Helper function to check if a queen can be placed on board[row][col]
bool isSafe(const std::vector<std::vector<int>>& board, int row, int col, int N) {
    // Check this row on left side
    for (int i = 0; i < col; i++)
        if (board[row][i]) return false;

    // Check upper diagonal on left side
    for (int i = row, j = col; i >= 0 && j >= 0; i--, j--)
        if (board[i][j]) return false;

    // Check lower diagonal on left side
    for (int i = row, j = col; j >= 0 && i < N; i++, j--)
        if (board[i][j]) return false;

    return true;
}

// Recursive utility function to solve N Queen problem
bool solveNQUtil(std::vector<std::vector<int>>& board, int col, int N) {
    // Base case: If all queens are placed
    if (col >= N) return true;

    // Consider this column and try placing this queen in all rows one by one
    for (int i = 0; i < N; i++) {
        if (isSafe(board, i, col, N)) {
            // Place this queen in board[i][col]
            board[i][col] = 1;

            // Recur to place rest of the queens
            if (solveNQUtil(board, col + 1, N)) return true;

            // If placing queen in board[i][col] doesn't lead to a solution, then backtrack
            board[i][col] = 0;
        }
    }
    return false; // If queen can not be placed in any row in this col
}

void solveNQueens(int N) {
    std::vector<std::vector<int>> board(N, std::vector<int>(N, 0));

    if (!solveNQUtil(board, 0, N)) {
        std::cout << "Solution does not exist" << std::endl;
        return;
    }

    // Print the solution
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++)
            std::cout << " " << (board[i][j] ? "Q" : ".") << " ";
        std::cout << std::endl;
    }
}

int main() {
    int N = 4;
    solveNQueens(N);
    return 0;
}