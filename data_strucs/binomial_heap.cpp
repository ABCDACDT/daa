#include <iostream>
#include <vector>
#include <algorithm>
#include <limits>

// Node structure for the Binomial Heap
struct Node {
    int key;
    int degree;
    Node *parent, *child, *sibling;

    Node(int k) : key(k), degree(0), parent(nullptr), child(nullptr), sibling(nullptr) {}
};

class BinomialHeap {
public:
    Node* head; // Points to the head of the root list

    // Link two binomial trees of the same degree
    void linkTrees(Node* y, Node* z) {
        y->parent = z;
        y->sibling = z->child;
        z->child = y;
        z->degree++;
    }

    // Merge the root lists of two heaps into a single sorted list by degree
    Node* mergeHeaps(Node* h1, Node* h2) {
        if (!h1) return h2;
        if (!h2) return h1;

        Node* newHead = nullptr;
        Node* tail = nullptr;

        Node* curr1 = h1;
        Node* curr2 = h2;

        if (curr1->degree <= curr2->degree) {
            newHead = curr1;
            curr1 = curr1->sibling;
        } else {
            newHead = curr2;
            curr2 = curr2->sibling;
        }
        tail = newHead;

        while (curr1 && curr2) {
            if (curr1->degree <= curr2->degree) {
                tail->sibling = curr1;
                curr1 = curr1->sibling;
            } else {
                tail->sibling = curr2;
                curr2 = curr2->sibling;
            }
            tail = tail->sibling;
        }

        // Append the remaining list
        if (curr1) tail->sibling = curr1;
        else tail->sibling = curr2;
        
        return newHead;
    }

public:
    // Constructor
    BinomialHeap() : head(nullptr) {}

    // Function to check if the heap is empty
    bool isEmpty() {
        return head == nullptr;
    }

    // Find the node with the minimum key in the root list
    Node* findMinimum() {
        if (!head) return nullptr;

        Node* minNode = head;
        Node* current = head->sibling;
        while (current) {
            if (current->key < minNode->key) {
                minNode = current;
            }
            current = current->sibling;
        }
        return minNode;
    }

    // Unite two binomial heaps
    Node* unionHeaps(Node* h1, Node* h2) {
        Node* newHead = mergeHeaps(h1, h2);
        if (!newHead) return nullptr;

        Node* prev = nullptr;
        Node* x = newHead;
        Node* next = x->sibling;

        while (next) {
            // Case 1 & 2: Degrees are different or three consecutive trees have same degree
            if ((x->degree != next->degree) || (next->sibling && next->sibling->degree == x->degree)) {
                prev = x;
                x = next;
            } else {
                // Case 3 & 4: Two consecutive trees have same degree
                if (x->key <= next->key) {
                    x->sibling = next->sibling;
                    linkTrees(next, x);
                } else {
                    if (!prev) newHead = next;
                    else prev->sibling = next;
                    linkTrees(x, next);
                    x = next;
                }
            }
            next = x->sibling;
        }
        return newHead;
    }

    // Insert a key into the heap
    void insert(int key) {
        Node* newNode = new Node(key);
        BinomialHeap tempHeap;
        tempHeap.head = newNode;
        head = unionHeaps(this->head, tempHeap.head);
    }

    // Extract the node with the minimum key
    int extractMin() {
        if (!head) return -1; // Or throw exception

        Node* minNode = findMinimum();
        int minKey = minNode->key;

        // Remove minNode from the root list
        Node* prev = nullptr;
        Node* current = head;
        while (current != minNode) {
            prev = current;
            current = current->sibling;
        }
        if (!prev) head = minNode->sibling;
        else prev->sibling = minNode->sibling;

        // Create a new heap from the children of the minNode
        Node* childHead = nullptr;
        Node* child = minNode->child;
        while (child) {
            Node* nextChild = child->sibling;
            child->sibling = childHead;
            child->parent = nullptr;
            childHead = child;
            child = nextChild;
        }

        // Union the original heap with the new heap
        BinomialHeap tempHeap;
        tempHeap.head = childHead;
        head = unionHeaps(this->head, tempHeap.head);

        delete minNode;
        return minKey;
    }
    
    // Decrease the key of a given node
    void decreaseKey(Node* node, int newKey) {
        if (!node || newKey > node->key) return;

        node->key = newKey;
        Node* y = node;
        Node* z = y->parent;

        // Bubble the key up to maintain heap property
        while (z && y->key < z->key) {
            std::swap(y->key, z->key);
            // If we need to track nodes, we'd swap more than just the key
            y = z;
            z = y->parent;
        }
    }

    // Delete a given node from the heap
    void deleteNode(Node* node) {
        if (!node) return;
        
        // Decrease key to negative infinity and extract it
        decreaseKey(node, std::numeric_limits<int>::min());
        extractMin();
    }
    
    // Simple print function to show the keys in the root list
    void printRootList() {
        if (!head) {
            std::cout << "Heap is empty." << std::endl;
            return;
        }
        Node* current = head;
        std::cout << "Root list: ";
        while(current) {
            std::cout << current->key << "(B" << current->degree << ") ";
            current = current->sibling;
        }
        std::cout << std::endl;
    }
};

int main() {
    BinomialHeap heap;

    // --- Insert Demo ---
    std::cout << "--- Insertion Demo ---" << std::endl;
    heap.insert(10);
    heap.insert(20);
    heap.insert(5); // New minimum
    heap.insert(30);
    heap.insert(3); // New minimum
    heap.printRootList();

    // --- Find Minimum Demo ---
    std::cout << "\n--- Find Minimum ---" << std::endl;
    Node* minNode = heap.findMinimum();
    if (minNode) {
        std::cout << "Minimum key is: " << minNode->key << std::endl;
    }

    // --- Extract Minimum Demo ---
    std::cout << "\n--- Extract Minimum ---" << std::endl;
    std::cout << "Extracted min: " << heap.extractMin() << std::endl;
    heap.printRootList();
    minNode = heap.findMinimum();
    if (minNode) {
        std::cout << "New minimum key is: " << minNode->key << std::endl;
    }
    
    // --- Union Demo ---
    std::cout << "\n--- Union Demo ---" << std::endl;
    BinomialHeap heap2;
    heap2.insert(7);
    heap2.insert(18);
    heap2.insert(22);
    std::cout << "Heap 1: "; heap.printRootList();
    std::cout << "Heap 2: "; heap2.printRootList();
    
    BinomialHeap combinedHeap;
    // HACK: For simplicity, we directly access heads. A proper API would be cleaner.
    combinedHeap.head = combinedHeap.unionHeaps(heap.head, heap2.head);
    std::cout << "Combined Heap: "; combinedHeap.printRootList();
    minNode = combinedHeap.findMinimum();
    if (minNode) {
        std::cout << "Minimum key of combined heap is: " << minNode->key << std::endl;
    }

    // --- Decrease Key and Delete Demo ---
    std::cout << "\n--- Decrease Key & Delete ---" << std::endl;
    // In a real application, you would need a way to get a pointer to a node.
    // For this demo, we'll find a node to work with. Let's find node with key 30.
    Node* nodeToModify = combinedHeap.head; // Should be the B3 tree root (30)
    while (nodeToModify && nodeToModify->key != 30) {
        nodeToModify = nodeToModify->sibling;
    }

    if (nodeToModify) {
        std::cout << "Decreasing key of node 30 to 1." << std::endl;
        combinedHeap.decreaseKey(nodeToModify, 1);
        minNode = combinedHeap.findMinimum();
        std::cout << "New minimum key is: " << minNode->key << std::endl;
        combinedHeap.printRootList();

        std::cout << "\nDeleting node with key 22." << std::endl;
        Node* nodeToDelete = combinedHeap.head; // Should be B0 tree root (22)
         while (nodeToDelete && nodeToDelete->key != 22) {
            nodeToDelete = nodeToDelete->sibling;
        }
        if(nodeToDelete) {
            combinedHeap.deleteNode(nodeToDelete);
            std::cout << "Heap after deleting 22: "; combinedHeap.printRootList();
        } else {
             std::cout << "Node 22 not found in root list." << std::endl;
        }

    } else {
        std::cout << "Node with key 30 not found in root list for demo." << std::endl;
    }

    return 0;
}