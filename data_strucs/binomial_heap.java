import java.util.*;

public class binomial_heap {
// Node structure for the Binomial Heap
struct Node {
    int key;
    int degree;
    Node *parent, *child, *sibling;

    Node(int k) : key(k), degree(0), parent(null), child(null), sibling(null) {}
};

class BinomialHeap {
public:
    Node* head; // Points to the head of the root list

    // Link two binomial trees of the same degree
    void linkTrees(Node* y, Node* z) {
        y.parent = z;
        y.sibling = z.child;
        z.child = y;
        z.degree++;
    }

    // Merge the root lists of two heaps into a single sorted list by degree
    Node* mergeHeaps(Node* h1, Node* h2) {
        if (!h1) return h2;
        if (!h2) return h1;

        Node* newHead = null;
        Node* tail = null;

        Node* curr1 = h1;
        Node* curr2 = h2;

        if (curr1.degree <= curr2.degree) {
            newHead = curr1;
            curr1 = curr1.sibling;
        } else {
            newHead = curr2;
            curr2 = curr2.sibling;
        }
        tail = newHead;

        while (curr1 && curr2) {
            if (curr1.degree <= curr2.degree) {
                tail.sibling = curr1;
                curr1 = curr1.sibling;
            } else {
                tail.sibling = curr2;
                curr2 = curr2.sibling;
            }
            tail = tail.sibling;
        }

        // Append the remaining list
        if (curr1) tail.sibling = curr1;
        else tail.sibling = curr2;
        
        return newHead;
    }

public:
    // Constructor
    BinomialHeap() : head(null) {}

    // Function to check if the heap is empty
    boolean isEmpty() {
        return head == null;
    }

    // Find the node with the minimum key in the root list
    Node* findMinimum() {
        if (!head) return null;

        Node* minNode = head;
        Node* current = head.sibling;
        while (current) {
            if (current.key < minNode.key) {
                minNode = current;
            }
            current = current.sibling;
        }
        return minNode;
    }

    // Unite two binomial heaps
    Node* unionHeaps(Node* h1, Node* h2) {
        Node* newHead = mergeHeaps(h1, h2);
        if (!newHead) return null;

        Node* prev = null;
        Node* x = newHead;
        Node* next = x.sibling;

        while (next) {
            // Case 1 & 2: Degrees are different or three consecutive trees have same degree
            if ((x.degree != next.degree) || (next.sibling && next.sibling->degree == x.degree)) {
                prev = x;
                x = next;
            } else {
                // Case 3 & 4: Two consecutive trees have same degree
                if (x.key <= next.key) {
                    x.sibling = next.sibling;
                    linkTrees(next, x);
                } else {
                    if (!prev) newHead = next;
                    else prev.sibling = next;
                    linkTrees(x, next);
                    x = next;
                }
            }
            next = x.sibling;
        }
        return newHead;
    }

    // Insert a key into the heap
    void insert(int key) {
        Node* newNode = new Node(key);
        BinomialHeap tempHeap;
        tempHeap.head = newNode;
        head = unionHeaps(this.head, tempHeap.head);
    }

    // Extract the node with the minimum key
    int extractMin() {
        if (!head) return -1; // Or throw exception

        Node* minNode = findMinimum();
        int minKey = minNode.key;

        // Remove minNode from the root list
        Node* prev = null;
        Node* current = head;
        while (current != minNode) {
            prev = current;
            current = current.sibling;
        }
        if (!prev) head = minNode.sibling;
        else prev.sibling = minNode.sibling;

        // Create a new heap from the children of the minNode
        Node* childHead = null;
        Node* child = minNode.child;
        while (child) {
            Node* nextChild = child.sibling;
            child.sibling = childHead;
            child.parent = null;
            childHead = child;
            child = nextChild;
        }

        // Union the original heap with the new heap
        BinomialHeap tempHeap;
        tempHeap.head = childHead;
        head = unionHeaps(this.head, tempHeap.head);

        delete minNode;
        return minKey;
    }
    
    // Decrease the key of a given node
    void decreaseKey(Node* node, int newKey) {
        if (!node || newKey > node.key) return;

        node.key = newKey;
        Node* y = node;
        Node* z = y.parent;

        // Bubble the key up to maintain heap property
        while (z && y.key < z.key) {
            swap(y.key, z.key);
            // If we need to track nodes, we'd swap more than just the key
            y = z;
            z = y.parent;
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
            std::System.out.print( "Heap is empty." );
            return;
        }
        Node* current = head;
        std::System.out.print( "Root list: ";
        while(current) {
            std::System.out.print( current.key << "(B" << current.degree << ") ";
            current = current.sibling;
        }
        std::System.out.print( "\n";
    }
};

public static void main(String[] args) {
    BinomialHeap heap;

    // --- Insert Demo ---
    std::System.out.print( "--- Insertion Demo ---" );
    heap.insert(10);
    heap.insert(20);
    heap.insert(5); // New minimum
    heap.insert(30);
    heap.insert(3); // New minimum
    heap.printRootList();

    // --- Find Minimum Demo ---
    std::System.out.print( "\n--- Find Minimum ---" );
    Node* minNode = heap.findMinimum();
    if (minNode) {
        std::System.out.print( "Minimum key is: " << minNode.key );
    }

    // --- Extract Minimum Demo ---
    std::System.out.print( "\n--- Extract Minimum ---" );
    std::System.out.print( "Extracted min: " << heap.extractMin() );
    heap.printRootList();
    minNode = heap.findMinimum();
    if (minNode) {
        std::System.out.print( "New minimum key is: " << minNode.key );
    }
    
    // --- Union Demo ---
    std::System.out.print( "\n--- Union Demo ---" );
    BinomialHeap heap2;
    heap2.insert(7);
    heap2.insert(18);
    heap2.insert(22);
    std::System.out.print( "Heap 1: "; heap.printRootList();
    std::System.out.print( "Heap 2: "; heap2.printRootList();
    
    BinomialHeap combinedHeap;
    // HACK: For simplicity, we directly access heads. A proper API would be cleaner.
    combinedHeap.head = combinedHeap.unionHeaps(heap.head, heap2.head);
    std::System.out.print( "Combined Heap: "; combinedHeap.printRootList();
    minNode = combinedHeap.findMinimum();
    if (minNode) {
        std::System.out.print( "Minimum key of combined heap is: " << minNode.key );
    }

    // --- Decrease Key and Delete Demo ---
    std::System.out.print( "\n--- Decrease Key & Delete ---" );
    // In a real application, you would need a way to get a pointer to a node.
    // For this demo, we'll find a node to work with. Let's find node with key 30.
    Node* nodeToModify = combinedHeap.head; // Should be the B3 tree root (30)
    while (nodeToModify && nodeToModify.key != 30) {
        nodeToModify = nodeToModify.sibling;
    }

    if (nodeToModify) {
        std::System.out.print( "Decreasing key of node 30 to 1." );
        combinedHeap.decreaseKey(nodeToModify, 1);
        minNode = combinedHeap.findMinimum();
        std::System.out.print( "New minimum key is: " << minNode.key );
        combinedHeap.printRootList();

        std::System.out.print( "\nDeleting node with key 22." );
        Node* nodeToDelete = combinedHeap.head; // Should be B0 tree root (22)
         while (nodeToDelete && nodeToDelete.key != 22) {
            nodeToDelete = nodeToDelete.sibling;
        }
        if(nodeToDelete) {
            combinedHeap.deleteNode(nodeToDelete);
            std::System.out.print( "Heap after deleting 22: "; combinedHeap.printRootList();
        } else {
             std::System.out.print( "Node 22 not found in root list." );
        }

    } else {
        std::System.out.print( "Node with key 30 not found in root list for demo." );
    }

    return 0;
}
}