#include <iostream>
#include <vector>
#include <numeric>

// Class to represent a Disjoint Set Union (DSU) data structure
class DSU {
private:
    std::vector<int> parent;
    std::vector<int> rank;

public:
    // Constructor to create and initialize sets
    DSU(int n) {
        parent.resize(n);
        rank.resize(n);
        makeSet(n);
    }

    // Creates n sets with single elements
    void makeSet(int n) {
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0; // The rank (or height) of each tree is initially 0
        }
    }

    // Finds the representative of the set that element i belongs to (with path compression)
    int find(int i) {
        // If i is not the parent of itself, then it is not the representative
        if (parent[i] != i) {
            // Recursively find the root and make it the direct parent of i (path compression)
            parent[i] = find(parent[i]);
        }
        return parent[i];
    }

    // Unites the two sets that x and y belong to (by rank)
    void unite(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        // If they are already in the same set, do nothing
        if (rootX != rootY) {
            // Attach the smaller rank tree under the root of the higher rank tree
            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootY] < rank[rootX]) {
                parent[rootY] = rootX;
            } else {
                // If ranks are the same, make one as root and increment its rank
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }

    // Helper to print the parent array for visualization
    void printSets() {
        std::cout << "Element:\t";
        for (int i = 0; i < parent.size(); ++i) {
            std::cout << i << "\t";
        }
        std::cout << "\nParent:\t\t";
        for (int i = 0; i < parent.size(); ++i) {
            std::cout << parent[i] << "\t";
        }
        std::cout << std::endl;
    }
};

int main() {
    // Create a DSU with 5 elements (0 to 4)
    int n = 5;
    DSU dsu(n);
    std::cout << "Initial state (each element is its own set):" << std::endl;
    dsu.printSets();
    std::cout << "\n";

    // --- Union Operations ---
    std::cout << "Performing unite(0, 2)..." << std::endl;
    dsu.unite(0, 2);
    dsu.printSets();
    std::cout << "\n";
    
    std::cout << "Performing unite(4, 2)..." << std::endl;
    dsu.unite(4, 2);
    dsu.printSets();
    std::cout << "\n";

    std::cout << "Performing unite(3, 1)..." << std::endl;
    dsu.unite(3, 1);
    dsu.printSets();
    std::cout << "\n";
    
    // --- Find Operations ---
    std::cout << "--- Find Operations ---" << std::endl;
    
    // Check if 4 and 0 are in the same set
    if (dsu.find(4) == dsu.find(0)) {
        std::cout << "Elements 4 and 0 are in the same set." << std::endl;
    } else {
        std::cout << "Elements 4 and 0 are not in the same set." << std::endl;
    }

    // Check if 1 and 0 are in the same set
    if (dsu.find(1) == dsu.find(0)) {
        std::cout << "Elements 1 and 0 are in the same set." << std::endl;
    } else {
        std::cout << "Elements 1 and 0 are not in the same set." << std::endl;
    }
    
    std::cout << "\nFinal state after all operations:" << std::endl;
    dsu.printSets();

    return 0;
}