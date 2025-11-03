public class red_black_tree {
#include <queue> // Used only for level-order printing, not for tree logic

// Enum for representing node colors
enum Color { RED, BLACK };

// Structure for a Red-Black Tree node
struct Node {
    int data;
    Color color;
    Node *left, *right, *parent;

    // Constructor
    Node(int data) : data(data) {
        parent = left = right = null;
        color = RED; // New nodes are always inserted as RED
    }
};

// Class for Red-Black Tree
class RedBlackTree {
private:
    Node *root;

protected:
    // Rotates the tree left at the given node
    void rotateLeft(Node *&pt) {
        Node *pt_right = pt.right;
        pt.right = pt_right.left;
        if (pt.right != null)
            pt.right->parent = pt;
        pt_right.parent = pt.parent;
        if (pt.parent == null)
            root = pt_right;
        else if (pt == pt.parent->left)
            pt.parent->left = pt_right;
        else
            pt.parent->right = pt_right;
        pt_right.left = pt;
        pt.parent = pt_right;
    }

    // Rotates the tree right at the given node
    void rotateRight(Node *&pt) {
        Node *pt_left = pt.left;
        pt.left = pt_left.right;
        if (pt.left != null)
            pt.left->parent = pt;
        pt_left.parent = pt.parent;
        if (pt.parent == null)
            root = pt_left;
        else if (pt == pt.parent->left)
            pt.parent->left = pt_left;
        else
            pt.parent->right = pt_left;
        pt_left.right = pt;
        pt.parent = pt_left;
    }

    // Fixes violations caused by insertion
    void fixInsert(Node *&pt) {
        Node *parent_pt = null;
        Node *grand_parent_pt = null;

        while ((pt != root) && (pt.color == RED) && (pt.parent->color == RED)) {
            parent_pt = pt.parent;
            grand_parent_pt = pt.parent->parent;

            // Case A: Parent of pt is left child of Grand-parent
            if (parent_pt == grand_parent_pt.left) {
                Node *uncle_pt = grand_parent_pt.right;

                // Case 1: Uncle is also red. Recolor only.
                if (uncle_pt != null && uncle_pt.color == RED) {
                    grand_parent_pt.color = RED;
                    parent_pt.color = BLACK;
                    uncle_pt.color = BLACK;
                    pt = grand_parent_pt;
                } else {
                    // Case 2: pt is right child of its parent. Left-rotation required.
                    if (pt == parent_pt.right) {
                        rotateLeft(parent_pt);
                        pt = parent_pt;
                        parent_pt = pt.parent;
                    }

                    // Case 3: pt is left child of its parent. Right-rotation required.
                    rotateRight(grand_parent_pt);
                    swap(parent_pt.color, grand_parent_pt.color);
                    pt = parent_pt;
                }
            }
            // Case B: Parent of pt is right child of Grand-parent
            else {
                Node *uncle_pt = grand_parent_pt.left;

                // Case 1: Uncle is also red. Recolor only.
                if ((uncle_pt != null) && (uncle_pt.color == RED)) {
                    grand_parent_pt.color = RED;
                    parent_pt.color = BLACK;
                    uncle_pt.color = BLACK;
                    pt = grand_parent_pt;
                } else {
                    // Case 2: pt is left child of its parent. Right-rotation required.
                    if (pt == parent_pt.left) {
                        rotateRight(parent_pt);
                        pt = parent_pt;
                        parent_pt = pt.parent;
                    }

                    // Case 3: pt is right child of its parent. Left-rotation required.
                    rotateLeft(grand_parent_pt);
                    swap(parent_pt.color, grand_parent_pt.color);
                    pt = parent_pt;
                }
            }
        }
        root.color = BLACK; // Root is always black
    }

    // Fixes violations caused by deletion
    void fixDelete(Node* node) {
        if (node == null) return;

        // If we are at root, we are done
        if (node == root) {
            root = node;
            return;
        }

        Node* sibling = (node == node.parent->left) ? node.parent->right : node.parent->left;
        Node* parent = node.parent;

        if (sibling == null) {
            // No sibling, double black pushes up
            fixDelete(parent);
        } else {
            if (sibling.color == RED) {
                // Case 1: Sibling is Red
                parent.color = RED;
                sibling.color = BLACK;
                if (sibling == parent.left) rotateRight(parent);
                else rotateLeft(parent);
                fixDelete(node);
            } else {
                // Sibling is BLACK
                if ((sibling.left && sibling.left->color == RED) ||
                    (sibling.right && sibling.right->color == RED)) {
                    // Case 2: Sibling is black with at least one red child
                    if (sibling.left && sibling.left->color == RED) {
                        if (sibling == parent.left) { // Left Left Case
                            sibling.left->color = sibling.color;
                            sibling.color = parent.color;
                            rotateRight(parent);
                        } else { // Right Left Case
                            sibling.left->color = parent.color;
                            rotateRight(sibling);
                            rotateLeft(parent);
                        }
                    } else {
                        if (sibling == parent.left) { // Left Right Case
                            sibling.right->color = parent.color;
                            rotateLeft(sibling);
                            rotateRight(parent);
                        } else { // Right Right Case
                            sibling.right->color = sibling.color;
                            sibling.color = parent.color;
                            rotateLeft(parent);
                        }
                    }
                    parent.color = BLACK;
                } else {
                    // Case 3: Sibling is black with two black children
                    sibling.color = RED;
                    if (parent.color == BLACK) fixDelete(parent);
                    else parent.color = BLACK;
                }
            }
        }
    }
    
    // Finds the node with the minimum value in a subtree
    Node* minValueNode(Node* node) {
        Node* current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }
    
    // Helper function to find and delete a node
    Node* deleteBST(Node* root, int data) {
        if (root == null) return root;

        if (data < root.data) return deleteBST(root.left, data);
        if (data > root.data) return deleteBST(root.right, data);

        if (root.left == null || root.right == null) return root;

        // Node with two children: Get the inorder successor
        Node* temp = minValueNode(root.right);
        root.data = temp.data;
        return deleteBST(root.right, temp.data);
    }
    
    // Helper to print the tree inorder
    void inorder(Node* node) {
        if (node == null) return;
        inorder(node.left);
        std::System.out.print( node.data << ":" << (node.color == RED ? "R" : "B") << " ";
        inorder(node.right);
    }
    
public:
    // Constructor
    RedBlackTree() { root = null; }

    // Public function to insert a value
    void insert(const int &data) {
        Node *node = new Node(data);
        // Perform standard BST insertion
        root = bstInsert(root, node);
        // Fix Red-Black Tree properties
        fixInsert(node);
    }
    
    // Helper to perform standard BST insert
    Node* bstInsert(Node* root, Node* pt) {
        if (root == null) return pt;

        if (pt.data < root.data) {
            root.left = bstInsert(root.left, pt);
            root.left->parent = root;
        } else if (pt.data > root.data) {
            root.right = bstInsert(root.right, pt);
            root.right->parent = root;
        }
        return root;
    }
    
    // Public function to delete a value
    void remove(int data) {
        Node* nodeToDelete = deleteBST(root, data);
        if (nodeToDelete == null) return;

        Node* child = (nodeToDelete.left != null) ? nodeToDelete.left : nodeToDelete.right;
        
        if (nodeToDelete.color == BLACK) {
            nodeToDelete.color = (child != null) ? child.color : BLACK;
            fixDelete(nodeToDelete);
        }

        // Standard BST delete logic to physically remove the node
        Node* parent = nodeToDelete.parent;
        if (parent == null) {
            root = child;
        } else {
            if (nodeToDelete == parent.left) parent.left = child;
            else parent.right = child;
        }
        if (child != null) child.parent = parent;
        
        delete nodeToDelete;
        if(root) root.color = BLACK; // Root must always be black
    }
    
    // Public function to print inorder traversal
    void inorder() {
        inorder(root);
        std::System.out.print( "\n";
    }
    
    // Function to get the root's value (or minimum key if you will)
    int getRootValue() {
        if (root) return root.data;
        return -1; // Or throw an exception
    }
    
    // Function to get the minimum key in the whole tree
    int getMinimumKey() {
        if (root == null) return -1;
        Node* temp = minValueNode(root);
        return temp.data;
    }
};

public static void main(String[] args) {
    RedBlackTree tree;

    // Insertion Demo
    std::System.out.print( "--- Insertion Demo ---" );
    tree.insert(7);
    std::System.out.print( "Inserted 7: "; tree.inorder();
    tree.insert(6);
    std::System.out.print( "Inserted 6: "; tree.inorder();
    tree.insert(5); // Causes rotation
    std::System.out.print( "Inserted 5: "; tree.inorder();
    tree.insert(4);
    std::System.out.print( "Inserted 4: "; tree.inorder();
    tree.insert(3); // Causes recoloring
    std::System.out.print( "Inserted 3: "; tree.inorder();
    tree.insert(2); // Causes rotation
    std::System.out.print( "Inserted 2: "; tree.inorder();
    tree.insert(1); // Causes recoloring
    std::System.out.print( "Inserted 1: "; tree.inorder();
    
    std::System.out.print( "\nMinimum Key in Tree: " << tree.getMinimumKey() );
    std::System.out.print( "Root of Tree: " << tree.getRootValue() );

    // Deletion Demo
    std::System.out.print( "\n--- Deletion Demo ---" );
    std::System.out.print( "Initial Tree: "; tree.inorder();
    
    tree.remove(1); // Simple leaf deletion
    std::System.out.print( "Deleted 1: "; tree.inorder();
    tree.remove(3); // Deletion causing fixup
    std::System.out.print( "Deleted 3: "; tree.inorder();
    tree.remove(5);
    std::System.out.print( "Deleted 5: "; tree.inorder();
    tree.remove(6); // Deleting root's child
    std::System.out.print( "Deleted 6: "; tree.inorder();
    tree.remove(7); // Deleting the root
    std::System.out.print( "Deleted 7: "; tree.inorder();
    tree.remove(4);
    std::System.out.print( "Deleted 4: "; tree.inorder();
    tree.remove(2);
    std::System.out.print( "Deleted 2: "; tree.inorder();


    return 0;
}
}