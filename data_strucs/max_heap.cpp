#include <iostream>
#include <vector>
#include <algorithm> // for std::swap

// Class for a Max Heap data structure
class MaxHeap {
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
        // While the node is not the root and its parent is smaller
        while (i != 0 && heap[getParent(i)] < heap[i]) {
            std::swap(heap[i], heap[getParent(i)]);
            i = getParent(i);
        }
    }

    // Moves a node down the tree to maintain the heap property (for extraction)
    void heapifyDown(int i) {
        int left = getLeftChild(i);
        int right = getRightChild(i);
        int largest = i;

        // Find the largest among the node and its children
        if (left < heap.size() && heap[left] > heap[largest]) {
            largest = left;
        }
        if (right < heap.size() && heap[right] > heap[largest]) {
            largest = right;
        }

        // If the largest is not the current node, swap and continue heapifying down
        if (largest != i) {
            std::swap(heap[i], heap[largest]);
            heapifyDown(largest);
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

    // Extracts (removes and returns) the maximum element from the heap
    int extractMax() {
        if (isEmpty()) {
            std::cerr << "Heap is empty!" << std::endl;
            return -1; // Or throw an exception
        }

        int maxElement = heap[0];
        heap[0] = heap.back();
        heap.pop_back();

        heapifyDown(0);
        return maxElement;
    }

    // Gets the maximum element without removing it
    int getMax() {
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
    MaxHeap maxHeap;

    std::cout << "--- Max Heap Demo ---" << std::endl;
    
    // --- Insert Operations ---
    maxHeap.insert(10);
    maxHeap.insert(20);
    maxHeap.insert(15);
    maxHeap.insert(30);
    maxHeap.insert(5);
    std::cout << "After inserting 10, 20, 15, 30, 5:" << std::endl;
    maxHeap.printHeap();
    
    // --- Get Max Operation ---
    std::cout << "\nMaximum element is: " << maxHeap.getMax() << std::endl;
    
    // --- Extract Max Operations ---
    std::cout << "Extracted max: " << maxHeap.extractMax() << std::endl;
    maxHeap.printHeap();
    
    std::cout << "Extracted max: " << maxHeap.extractMax() << std::endl;
    maxHeap.printHeap();

    std::cout << "\nFinal maximum element is: " << maxHeap.getMax() << std::endl;
    std::cout << "Final heap size: " << maxHeap.getSize() << std::endl;

    return 0;
}