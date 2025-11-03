#include <iostream>
#include <vector>
#include <cmath>
#include <limits>

// Node structure for the Fibonacci Heap
struct Node {
    int key;
    int degree;
    bool mark; // Mark for cascading cuts
    Node *parent, *child, *left, *right;

    Node(int k) : key(k), degree(0), mark(false), parent(nullptr), child(nullptr) {
        left = right = this; // Initialize as a circular doubly-linked list of one
    }
};

class FibonacciHeap {
public:
    Node* minNode;
    int nodeCount;

    // Constructor to create an empty heap
    FibonacciHeap() : minNode(nullptr), nodeCount(0) {}
    
    // Checks if the heap is empty
    bool isEmpty() {
        return minNode == nullptr;
    }

    // Inserts a new node and returns a pointer to it
    Node* insert(int key) {
        Node* newNode = new Node(key);
        if (minNode == nullptr) {
            minNode = newNode;
        } else {
            // Add the new node to the root list (circularly linked)
            newNode->left = minNode;
            newNode->right = minNode->right;
            minNode->right->left = newNode;
            minNode->right = newNode;
            // Update the minimum pointer if necessary
            if (key < minNode->key) {
                minNode = newNode;
            }
        }
        nodeCount++;
        return newNode;
    }
    
    // Links tree y to be a child of tree x
    void link(Node* y, Node* x) {
        // Remove y from the root list
        y->left->right = y->right;
        y->right->left = y->left;

        // Make y a child of x
        y->parent = x;
        if (x->child == nullptr) {
            x->child = y;
            y->right = y;
            y->left = y;
        } else {
            y->left = x->child;
            y->right = x->child->right;
            x->child->right->left = y;
            x->child->right = y;
        }
        x->degree++;
        y->mark = false;
    }

    // Consolidates the root list to ensure no two trees have the same degree
    void consolidate() {
        if (isEmpty()) return;
        int maxDegree = static_cast<int>(log2(nodeCount)) + 2;
        std::vector<Node*> degreeTable(maxDegree, nullptr);

        std::vector<Node*> rootNodes;
        Node* current = minNode;
        do {
            rootNodes.push_back(current);
            current = current->right;
        } while (current != minNode);

        for (Node* x : rootNodes) {
            int d = x->degree;
            while (degreeTable[d] != nullptr) {
                Node* y = degreeTable[d];
                if (x->key > y->key) {
                    std::swap(x, y);
                }
                link(y, x);
                degreeTable[d] = nullptr;
                d++;
            }
            degreeTable[d] = x;
        }

        minNode = nullptr;
        for (int i = 0; i < maxDegree; i++) {
            if (degreeTable[i] != nullptr) {
                // Rebuild the root list from the degree table
                degreeTable[i]->left = degreeTable[i];
                degreeTable[i]->right = degreeTable[i];
                if (minNode == nullptr) {
                    minNode = degreeTable[i];
                } else {
                    degreeTable[i]->left = minNode;
                    degreeTable[i]->right = minNode->right;
                    minNode->right->left = degreeTable[i];
                    minNode->right = degreeTable[i];
                    if (degreeTable[i]->key < minNode->key) {
                        minNode = degreeTable[i];
                    }
                }
            }
        }
    }
    
    // Extracts the node with the minimum key
    int deleteMin() {
        if (isEmpty()) return -1;

        Node* z = minNode;
        int minKey = z->key;
        
        // Add children of minNode to the root list
        if (z->child != nullptr) {
            Node* child = z->child;
            do {
                Node* nextChild = child->right;
                child->parent = nullptr;
                // Add child to root list
                child->left = minNode;
                child->right = minNode->right;
                minNode->right->left = child;
                minNode->right = child;
                child = nextChild;
            } while (child != z->child);
        }

        // Remove minNode from the root list
        z->left->right = z->right;
        z->right->left = z->left;

        if (z == z->right) {
            minNode = nullptr;
        } else {
            minNode = z->right;
            consolidate();
        }
        nodeCount--;
        delete z;
        return minKey;
    }

    // Merges another Fibonacci heap into this one
    void unionHeaps(FibonacciHeap& otherHeap) {
        if (otherHeap.isEmpty()) return;
        if (this->isEmpty()) {
            this->minNode = otherHeap.minNode;
            this->nodeCount = otherHeap.nodeCount;
        } else {
            // Concatenate the root lists
            Node* thisLast = this->minNode->left;
            Node* otherLast = otherHeap.minNode->left;

            thisLast->right = otherHeap.minNode;
            otherHeap.minNode->left = thisLast;
            this->minNode->left = otherLast;
            otherLast->right = this->minNode;

            if (otherHeap.minNode->key < this->minNode->key) {
                this->minNode = otherHeap.minNode;
            }
            this->nodeCount += otherHeap.nodeCount;
        }
        // Clear the other heap
        otherHeap.minNode = nullptr;
        otherHeap.nodeCount = 0;
    }
    
    // Cuts node x from its parent y
    void cut(Node* x, Node* y) {
        // Remove x from child list of y
        if (x == x->right) {
            y->child = nullptr;
        } else {
            x->right->left = x->left;
            x->left->right = x->right;
            if (y->child == x) {
                y->child = x->right;
            }
        }
        y->degree--;

        // Add x to the root list
        x->left = minNode;
        x->right = minNode->right;
        minNode->right->left = x;
        minNode->right = x;
        
        x->parent = nullptr;
        x->mark = false;
    }
    
    // Performs cascading cuts up the tree
    void cascadingCut(Node* y) {
        Node* z = y->parent;
        if (z != nullptr) {
            if (!y->mark) {
                y->mark = true;
            } else {
                cut(y, z);
                cascadingCut(z);
            }
        }
    }
    
    // Decreases the key of a given node
    void decreaseKey(Node* node, int newKey) {
        if (newKey > node->key) return; // Cannot increase key

        node->key = newKey;
        Node* y = node->parent;

        if (y != nullptr && node->key < y->key) {
            cut(node, y);
            cascadingCut(y);
        }
        
        if (node->key < minNode->key) {
            minNode = node;
        }
    }
    
    // Deletes a given node
    void deleteNode(Node* node) {
        // Decrease the key to negative infinity to make it the minimum
        decreaseKey(node, std::numeric_limits<int>::min());
        // Now extract the minimum element
        deleteMin();
    }
    
    // Helper to print the root list for visualization
    void printRootList() {
        if (isEmpty()) {
            std::cout << "Heap is empty." << std::endl;
            return;
        }
        Node* temp = minNode;
        std::cout << "Root list: ";
        do {
            std::cout << temp->key << "(D" << temp->degree << ") ";
            temp = temp->right;
        } while (temp != minNode);
        std::cout << std::endl;
    }
};

int main() {
    FibonacciHeap fh;
    std::cout << "--- Insert & Get Minimum Demo ---" << std::endl;
    fh.insert(23);
    fh.insert(7);
    fh.insert(21);
    fh.insert(3);
    fh.insert(17);
    fh.printRootList();
    std::cout << "Minimum key: " << fh.minNode->key << std::endl;

    std::cout << "\n--- Delete Minimum Demo ---" << std::endl;
    std::cout << "Extracted min: " << fh.deleteMin() << std::endl;
    fh.printRootList();
    std::cout << "New minimum key: " << fh.minNode->key << std::endl;

    std::cout << "\n--- Union Demo ---" << std::endl;
    FibonacciHeap fh2;
    fh2.insert(11);
    fh2.insert(2); // This will be the new min
    std::cout << "Heap 1: "; fh.printRootList();
    std::cout << "Heap 2: "; fh2.printRootList();
    fh.unionHeaps(fh2);
    std::cout << "Combined Heap: "; fh.printRootList();
    std::cout << "Minimum of combined heap: " << fh.minNode->key << std::endl;

    std::cout << "\n--- Decrease Key & Delete Demo ---" << std::endl;
    FibonacciHeap demoHeap;
    std::vector<Node*> nodes;
    nodes.push_back(demoHeap.insert(10));
    nodes.push_back(demoHeap.insert(20));
    nodes.push_back(demoHeap.insert(30));
    nodes.push_back(demoHeap.insert(40));
    
    std::cout << "Initial demo heap: "; demoHeap.printRootList();
    
    // Decrease Key
    std::cout << "Decreasing key of node 30 to 5." << std::endl;
    demoHeap.decreaseKey(nodes[2], 5); // nodes[2] is the node with key 30
    std::cout << "Heap after decrease key: "; demoHeap.printRootList();
    std::cout << "New minimum: " << demoHeap.minNode->key << std::endl;
    
    // Delete Node
    std::cout << "Deleting node with original key 20." << std::endl;
    demoHeap.deleteNode(nodes[1]); // nodes[1] is the node with key 20
    std::cout << "Heap after deleting node: "; demoHeap.printRootList();
    std::cout << "New minimum: " << demoHeap.minNode->key << std::endl;

    return 0;
}