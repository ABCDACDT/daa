#include <iostream>
#include <vector>
#include <queue>
#include <map>

struct MinHeapNode {
    char data;
    unsigned freq;
    MinHeapNode *left, *right;

    MinHeapNode(char data, unsigned freq) {
        left = right = nullptr;
        this->data = data;
        this->freq = freq;
    }
};

// Comparison for the min-heap
struct compare {
    bool operator()(MinHeapNode* l, MinHeapNode* r) {
        return (l->freq > r->freq);
    }
};

// Prints huffman codes from the root of the built tree.
void printCodes(struct MinHeapNode* root, std::string str) {
    if (!root) return;
    if (root->data != '$') std::cout << root->data << ": " << str << "\n";
    printCodes(root->left, str + "0");
    printCodes(root->right, str + "1");
}

// Builds a Huffman Tree and prints codes.
void huffmanCodes(std::vector<char>& data, std::vector<int>& freq, int size) {
    struct MinHeapNode *left, *right, *top;
    std::priority_queue<MinHeapNode*, std::vector<MinHeapNode*>, compare> minHeap;

    for (int i = 0; i < size; ++i)
        minHeap.push(new MinHeapNode(data[i], freq[i]));

    // Iterate while size of heap doesn't become 1
    while (minHeap.size() != 1) {
        left = minHeap.top();
        minHeap.pop();

        right = minHeap.top();
        minHeap.pop();
        
        // Create a new internal node with frequency equal to the sum of the two nodes' frequencies.
        top = new MinHeapNode('$', left->freq + right->freq);
        top->left = left;
        top->right = right;
        minHeap.push(top);
    }
    printCodes(minHeap.top(), "");
}

int main() {
    std::vector<char> arr = {'a', 'b', 'c', 'd', 'e', 'f'};
    std::vector<int> freq = {5, 9, 12, 13, 16, 45};
    int size = arr.size();
    huffmanCodes(arr, freq, size);
    return 0;
}