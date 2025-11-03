import java.util.*;

public class strassen_matmul {
// Function to add two matrices
std::vector<ArrayList<Integer>> add(const std::vector<ArrayList<Integer>>& A, const std::vector<ArrayList<Integer>>& B) {
    int n = A.size();
    std::vector<ArrayList<Integer>> C(n, ArrayList<Integer>(n));
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            C[i][j] = A[i][j] + B[i][j];
        }
    }
    return C;
}

// Function to subtract two matrices
std::vector<ArrayList<Integer>> subtract(const std::vector<ArrayList<Integer>>& A, const std::vector<ArrayList<Integer>>& B) {
    int n = A.size();
    std::vector<ArrayList<Integer>> C(n, ArrayList<Integer>(n));
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            C[i][j] = A[i][j] - B[i][j];
        }
    }
    return C;
}

// Function to perform Strassen's matrix multiplication
std::vector<ArrayList<Integer>> strassen(const std::vector<ArrayList<Integer>>& A, const std::vector<ArrayList<Integer>>& B) {
    int n = A.size();

    // Base case
    if (n == 1) {
        std::vector<ArrayList<Integer>> C(1, ArrayList<Integer>(1));
        C[0][0] = A[0][0] * B[0][0];
        return C;
    }

    // New size of submatrices
    int newSize = n / 2;
    std::vector<ArrayList<Integer>> A11(newSize, ArrayList<Integer>(newSize));
    std::vector<ArrayList<Integer>> A12(newSize, ArrayList<Integer>(newSize));
    std::vector<ArrayList<Integer>> A21(newSize, ArrayList<Integer>(newSize));
    std::vector<ArrayList<Integer>> A22(newSize, ArrayList<Integer>(newSize));
    std::vector<ArrayList<Integer>> B11(newSize, ArrayList<Integer>(newSize));
    std::vector<ArrayList<Integer>> B12(newSize, ArrayList<Integer>(newSize));
    std::vector<ArrayList<Integer>> B21(newSize, ArrayList<Integer>(newSize));
    std::vector<ArrayList<Integer>> B22(newSize, ArrayList<Integer>(newSize));

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
    std::vector<ArrayList<Integer>> P1 = strassen(add(A11, A22), add(B11, B22));
    std::vector<ArrayList<Integer>> P2 = strassen(add(A21, A22), B11);
    std::vector<ArrayList<Integer>> P3 = strassen(A11, subtract(B12, B22));
    std::vector<ArrayList<Integer>> P4 = strassen(A22, subtract(B21, B11));
    std::vector<ArrayList<Integer>> P5 = strassen(add(A11, A12), B22);
    std::vector<ArrayList<Integer>> P6 = strassen(subtract(A21, A11), add(B11, B12));
    std::vector<ArrayList<Integer>> P7 = strassen(subtract(A12, A22), add(B21, B22));

    // Calculating the 4 quadrants of the result matrix
    std::vector<ArrayList<Integer>> C11 = subtract(add(add(P1, P4), P7), P5);
    std::vector<ArrayList<Integer>> C12 = add(P3, P5);
    std::vector<ArrayList<Integer>> C21 = add(P2, P4);
    std::vector<ArrayList<Integer>> C22 = subtract(add(add(P1, P3), P6), P2);

    // Combining the 4 quadrants into a single result matrix
    std::vector<ArrayList<Integer>> C(n, ArrayList<Integer>(n));
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

public static void main(String[] args) {
    std::vector<ArrayList<Integer>> A = {{1, 2}, {3, 4}};
    std::vector<ArrayList<Integer>> B = {{5, 6}, {7, 8}};

    std::vector<ArrayList<Integer>> C = strassen(A, B);

    std::System.out.print( "Result matrix:\n";
    for (int i = 0; i < C.size(); i++) {
        for (int j = 0; j < C.size(); j++) {
            std::System.out.print( C[i][j] << " ";
        }
        std::System.out.print( "\n";
    }

    return 0;
}
}