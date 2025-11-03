#include <iostream>
#include <vector>
#include <algorithm> // for std::swap

// Class for a Min Heap data structure
class MinHeap {
private:
    std::vector<int> heap;

    // Get the index of the parent of a node
    int getParent(int i) { return (i - 1) / 2; }

    // Get the index of the left child of a node
    int getLeftChild(int i) { return 2 * i + 1; }

    // Get the index of the right child of a node
    int getRightChild(int i) { return 2 * i + 2; }

    // Moves a node up the tree to maintain the heap property (for insertion)
    void heapifyUp(int i) {
        // While the node is not the root and its parent is larger
        while (i != 0 && heap[getParent(i)] > heap[i]) {
            std::swap(heap[i], heap[getParent(i)]);
            i = getParent(i);
        }
    }

    // Moves a node down the tree to maintain the heap property (for extraction)
    void heapifyDown(int i) {
        int left = getLeftChild(i);
        int right = getRightChild(i);
        int smallest = i;

        // Find the smallest among the node and its children
        if (left < heap.size() && heap[left] < heap[smallest]) {
            smallest = left;
        }
        if (right < heap.size() && heap[right] < heap[smallest]) {
            smallest = right;
        }

        // If the smallest is not the current node, swap and continue heapifying down
        if (smallest != i) {
            std::swap(heap[i], heap[smallest]);
            heapifyDown(smallest);
        }
    }

public:
    // Checks if the heap is empty
    bool isEmpty() {
        return heap.empty();
    }

    // Gets the size of the heap
    int getSize() {
        return heap.size();
    }

    // Inserts a new key into the heap
    void insert(int key) {
        heap.push_back(key);
        int index = heap.size() - 1;
        heapifyUp(index);
    }

    // Extracts (removes and returns) the minimum element from the heap
    int extractMin() {
        if (isEmpty()) {
            std::cerr << "Heap is empty!" << std::endl;
            return -1; // Or throw an exception
        }

        int minElement = heap[0];
        heap[0] = heap.back();
        heap.pop_back();

        heapifyDown(0);
        return minElement;
    }

    // Gets the minimum element without removing it
    int getMin() {
        if (isEmpty()) {
            std::cerr << "Heap is empty!" << std::endl;
            return -1;
        }
        return heap[0];
    }

    // Helper to print the heap's internal vector
    void printHeap() {
        std::cout << "Heap: ";
        for (int val : heap) {
            std::cout << val << " ";
        }
        std::cout << std::endl;
    }
};

int main() {
    MinHeap minHeap;
    
    std::cout << "--- Min Heap Demo ---" << std::endl;

    // --- Insert Operations ---
    minHeap.insert(10);
    minHeap.insert(20);
    minHeap.insert(15);
    minHeap.insert(30);
    minHeap.insert(5);
    std::cout << "After inserting 10, 20, 15, 30, 5:" << std::endl;
    minHeap.printHeap();
    
    // --- Get Min Operation ---
    std::cout << "\nMinimum element is: " << minHeap.getMin() << std::endl;
    
    // --- Extract Min Operations ---
    std::cout << "Extracted min: " << minHeap.extractMin() << std::endl;
    minHeap.printHeap();
    
    std::cout << "Extracted min: " << minHeap.extractMin() << std::endl;
    minHeap.printHeap();

    std::cout << "\nFinal minimum element is: " << minHeap.getMin() << std::endl;
    std::cout << "Final heap size: " << minHeap.getSize() << std::endl;

    return 0;
}