#include <iostream>
#include <vector>

// Function to add two matrices
std::vector<std::vector<int>> add(const std::vector<std::vector<int>>& A, const std::vector<std::vector<int>>& B) {
    int n = A.size();
    std::vector<std::vector<int>> C(n, std::vector<int>(n));
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            C[i][j] = A[i][j] + B[i][j];
        }
    }
    return C;
}

// Function to subtract two matrices
std::vector<std::vector<int>> subtract(const std::vector<std::vector<int>>& A, const std::vector<std::vector<int>>& B) {
    int n = A.size();
    std::vector<std::vector<int>> C(n, std::vector<int>(n));
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            C[i][j] = A[i][j] - B[i][j];
        }
    }
    return C;
}

// Function to perform Strassen's matrix multiplication
std::vector<std::vector<int>> strassen(const std::vector<std::vector<int>>& A, const std::vector<std::vector<int>>& B) {
    int n = A.size();

    // Base case
    if (n == 1) {
        std::vector<std::vector<int>> C(1, std::vector<int>(1));
        C[0][0] = A[0][0] * B[0][0];
        return C;
    }

    // New size of submatrices
    int newSize = n / 2;
    std::vector<std::vector<int>> A11(newSize, std::vector<int>(newSize));
    std::vector<std::vector<int>> A12(newSize, std::vector<int>(newSize));
    std::vector<std::vector<int>> A21(newSize, std::vector<int>(newSize));
    std::vector<std::vector<int>> A22(newSize, std::vector<int>(newSize));
    std::vector<std::vector<int>> B11(newSize, std::vector<int>(newSize));
    std::vector<std::vector<int>> B12(newSize, std::vector<int>(newSize));
    std::vector<std::vector<int>> B21(newSize, std::vector<int>(newSize));
    std::vector<std::vector<int>> B22(newSize, std::vector<int>(newSize));

    // Dividing the matrices into 4 sub-matrices
    for (int i = 0; i < newSize; i++) {
        for (int j = 0; j < newSize; j++) {
            A11[i][j] = A[i][j];
            A12[i][j] = A[i][j + newSize];
            A21[i][j] = A[i + newSize][j];
            A22[i][j] = A[i + newSize][j + newSize];
            B11[i][j] = B[i][j];
            B12[i][j] = B[i][j + newSize];
            B21[i][j] = B[i + newSize][j];
            B22[i][j] = B[i + newSize][j + newSize];
        }
    }

    // Calculating the 7 products
    std::vector<std::vector<int>> P1 = strassen(add(A11, A22), add(B11, B22));
    std::vector<std::vector<int>> P2 = strassen(add(A21, A22), B11);
    std::vector<std::vector<int>> P3 = strassen(A11, subtract(B12, B22));
    std::vector<std::vector<int>> P4 = strassen(A22, subtract(B21, B11));
    std::vector<std::vector<int>> P5 = strassen(add(A11, A12), B22);
    std::vector<std::vector<int>> P6 = strassen(subtract(A21, A11), add(B11, B12));
    std::vector<std::vector<int>> P7 = strassen(subtract(A12, A22), add(B21, B22));

    // Calculating the 4 quadrants of the result matrix
    std::vector<std::vector<int>> C11 = subtract(add(add(P1, P4), P7), P5);
    std::vector<std::vector<int>> C12 = add(P3, P5);
    std::vector<std::vector<int>> C21 = add(P2, P4);
    std::vector<std::vector<int>> C22 = subtract(add(add(P1, P3), P6), P2);

    // Combining the 4 quadrants into a single result matrix
    std::vector<std::vector<int>> C(n, std::vector<int>(n));
    for (int i = 0; i < newSize; i++) {
        for (int j = 0; j < newSize; j++) {
            C[i][j] = C11[i][j];
            C[i][j + newSize] = C12[i][j];
            C[i + newSize][j] = C21[i][j];
            C[i + newSize][j + newSize] = C22[i][j];
        }
    }
    return C;
}

int main() {
    std::vector<std::vector<int>> A = {{1, 2}, {3, 4}};
    std::vector<std::vector<int>> B = {{5, 6}, {7, 8}};

    std::vector<std::vector<int>> C = strassen(A, B);

    std::cout << "Result matrix:\n";
    for (int i = 0; i < C.size(); i++) {
        for (int j = 0; j < C.size(); j++) {
            std::cout << C[i][j] << " ";
        }
        std::cout << "\n";
    }

    return 0;
}