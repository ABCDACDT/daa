#include <bits/stdc++.h>
using namespace std;
enum Color
{
    RED,
    BLACK
};
struct Node
{
    int data;
    Color color;
    Node *left, *right, *parent;
    int sz; // subtree size
    Node(int data)
    {
        this->data = data;
        left = right = parent = nullptr;
        color = RED;
        sz = 1;
    }
};
class RedBlackTree
{
private:
    Node *root;
    Node *TNULL;
    void initializeNULLNode(Node *node, Node *parent)
    {
        node->data = 0;
        node->color = BLACK;
        node->left = node->right = node;
        node->parent = parent;
        node->sz = 0;
    }
    // Recompute sizes for whole subtree (postorder). Returns size.
    int recomputeSizes(Node *node)
    {
        if (node == TNULL)
            return 0;
        node->sz = recomputeSizes(node->left) + recomputeSizes(node->right) + 1;
        return node->sz;
    }
    void inorderHelper(Node *node)
    {
        if (node != TNULL)
        {
            inorderHelper(node->left);
            cout << node->data << (node->color == RED ? "(R) " : "(B) ");
            inorderHelper(node->right);
        }
    }
    void preorderHelper(Node *node)
    {
        if (node != TNULL)
        {
            cout << node->data << (node->color == RED ? "(R) " : "(B) ");
            preorderHelper(node->left);
            preorderHelper(node->right);
        }
    }
    void postorderHelper(Node *node)
    {
        if (node != TNULL)
        {
            postorderHelper(node->left);
            postorderHelper(node->right);
            cout << node->data << (node->color == RED ? "(R) " : "(B) ");
        }
    }
    Node *searchTreeHelper(Node *node, int key)
    {
        if (node == TNULL || key == node->data)
            return node;
        if (key < node->data)
            return searchTreeHelper(node->left, key);
        return searchTreeHelper(node->right, key);
    }
    void leftRotate(Node *x)
    {
        Node *y = x->right;
        x->right = y->left;
        if (y->left != TNULL)
            y->left->parent = x;
        y->parent = x->parent;
        if (x->parent == nullptr)
            root = y;
        else if (x == x->parent->left)
            x->parent->left = y;
        else
            x->parent->right = y;
        y->left = x;
        x->parent = y;
        // update sizes
        y->sz = x->sz;
        x->sz = x->left->sz + x->right->sz + 1;
    }
    void rightRotate(Node *x)
    {
        Node *y = x->left;
        x->left = y->right;
        if (y->right != TNULL)
            y->right->parent = x;
        y->parent = x->parent;
        if (x->parent == nullptr)
            root = y;
        else if (x == x->parent->right)
            x->parent->right = y;
        else
            x->parent->left = y;
        y->right = x;
        x->parent = y;
        // update sizes
        y->sz = x->sz;
        x->sz = x->left->sz + x->right->sz + 1;
    }
    void fixInsert(Node *k)
    {
        Node *u;
        while (k->parent->color == RED)
        {
            if (k->parent == k->parent->parent->left)
            {
                u = k->parent->parent->right;
                if (u->color == RED)
                {
                    k->parent->color = BLACK;
                    u->color = BLACK;
                    k->parent->parent->color = RED;
                    k = k->parent->parent;
                }
                else
                {
                    if (k == k->parent->right)
                    {
                        k = k->parent;
                        leftRotate(k);
                    }
                    k->parent->color = BLACK;
                    k->parent->parent->color = RED;
                    rightRotate(k->parent->parent);
                }
            }
            else
            {
                u = k->parent->parent->left;
                if (u->color == RED)
                {
                    k->parent->color = BLACK;
                    u->color = BLACK;
                    k->parent->parent->color = RED;
                    k = k->parent->parent;
                }
                else
                {
                    if (k == k->parent->left)
                    {
                        k = k->parent;
                        rightRotate(k);
                    }
                    k->parent->color = BLACK;
                    k->parent->parent->color = RED;
                    leftRotate(k->parent->parent);
                }
            }
            if (k == root)
                break;
        }
        root->color = BLACK;
    }
    // Transplant subtree u with v
    void rbTransplant(Node *u, Node *v)
    {
        if (u->parent == nullptr)
            root = v;
        else if (u == u->parent->left)
            u->parent->left = v;
        else
            u->parent->right = v;
        v->parent = u->parent;
    }
    Node *minimum(Node *node)
    {
        while (node->left != TNULL)
            node = node->left;
        return node;
    }
    void fixDelete(Node *x)
    {
        while (x != root && x->color == BLACK)
        {
            if (x == x->parent->left)
            {
                Node *w = x->parent->right;
                if (w->color == RED)
                {
                    w->color = BLACK;
                    x->parent->color = RED;
                    leftRotate(x->parent);
                    w = x->parent->right;
                }
                if (w->left->color == BLACK && w->right->color == BLACK)
                {
                    w->color = RED;
                    x = x->parent;
                }
                else
                {
                    if (w->right->color == BLACK)
                    {
                        w->left->color = BLACK;
                        w->color = RED;
                        rightRotate(w);
                        w = x->parent->right;
                    }
                    w->color = x->parent->color;
                    x->parent->color = BLACK;
                    w->right->color = BLACK;
                    leftRotate(x->parent);
                    x = root;
                }
            }
            else
            {
                Node *w = x->parent->left;
                if (w->color == RED)
                {
                    w->color = BLACK;
                    x->parent->color = RED;
                    rightRotate(x->parent);
                    w = x->parent->left;
                }
                if (w->right->color == BLACK && w->left->color == BLACK)
                {
                    w->color = RED;
                    x = x->parent;
                }
                else
                {
                    if (w->left->color == BLACK)
                    {
                        w->right->color = BLACK;
                        w->color = RED;
                        leftRotate(w);
                        w = x->parent->left;
                    }
                    w->color = x->parent->color;
                    x->parent->color = BLACK;
                    w->left->color = BLACK;
                    rightRotate(x->parent);
                    x = root;
                }
            }
        }
        x->color = BLACK;
    }
    // Deletes node with key (if present)
    void deleteNodeHelper(Node *node, int key)
    {
        // find node containing key
        Node *z = TNULL;
        Node *x, *y;
        Node *current = node;
        while (current != TNULL)
        {
            if (current->data == key)
            {
                z = current;
                break;
            }
            if (key < current->data)
                current = current->left;
            else
                current = current->right;
        }
        if (z == TNULL)
        {
            // key not found
            return;
        }
        y = z;
        Color y_original_color = y->color;
        if (z->left == TNULL)
        {
            x = z->right;
            rbTransplant(z, z->right);
        }
        else if (z->right == TNULL)
        {
            x = z->left;
            rbTransplant(z, z->left);
        }
        else
        {
            y = minimum(z->right);
            y_original_color = y->color;
            x = y->right;
            if (y->parent == z)
            {
                x->parent = y;
            }
            else
            {
                rbTransplant(y, y->right);
                y->right = z->right;
                y->right->parent = y;
            }
            rbTransplant(z, y);
            y->left = z->left;
            y->left->parent = y;
            y->color = z->color;
        }
        delete z; // free memory
        if (y_original_color == BLACK)
        {
            fixDelete(x);
        }
        // recompute sizes for correctness (safe)
        recomputeSizes(root);
    }
    // Helper to find lower-bound node (first >= key)
    Node *lowerBoundNode(Node *node, int key)
    {
        Node *res = TNULL;
        while (node != TNULL)
        {
            if (node->data >= key)
            {
                res = node;
                node = node->left;
            }
            else
            {
                node = node->right;
            }
        }
        return res;
    }
    // Helper to find upper-bound node (first > key)
    Node *upperBoundNode(Node *node, int key)
    {
        Node *res = TNULL;
        while (node != TNULL)
        {
            if (node->data > key)
            {
                res = node;
                node = node->left;
            }
            else
            {
                node = node->right;
            }
        }
        return res;
    }
    // Helper to find k-th smallest node (0-based)
    Node *findByOrder(Node *node, int k)
    {
        if (node == TNULL)
            return TNULL;
        int leftsz = node->left->sz;
        if (k == leftsz)
            return node;
        else if (k < leftsz)
            return findByOrder(node->left, k);
        else
            return findByOrder(node->right, k - leftsz - 1);
    }

public:
    RedBlackTree()
    {
        TNULL = new Node(0);
        initializeNULLNode(TNULL, nullptr);
        root = TNULL;
    }
    void inorder()
    {
        inorderHelper(root);
        cout << "\n";
    }
    void preorder()
    {
        preorderHelper(root);
        cout << "\n";
    }
    void postorder()
    {
        postorderHelper(root);
        cout << "\n";
    }
    bool search(int key)
    {
        Node *result = searchTreeHelper(root, key);
        return (result != TNULL);
    }
    void insert(int key)
    {
        Node *node = new Node(key);
        node->parent = nullptr;
        node->left = TNULL;
        node->right = TNULL;
        node->color = RED;
        node->sz = 1;
        Node *y = nullptr;
        Node *x = root;
        // increment sizes on the path to insertion
        while (x != TNULL)
        {
            y = x;
            x->sz += 1;
            if (node->data < x->data)
                x = x->left;
            else
                x = x->right;
        }
        node->parent = y;
        if (y == nullptr)
            root = node;
        else if (node->data < y->data)
            y->left = node;
        else
            y->right = node;
        if (node->parent == nullptr)
        {
            node->color = BLACK;
            return;
        }
        if (node->parent->parent == nullptr)
            return;
        fixInsert(node);
        // recompute sizes for safety (keeps sizes correct even after complex rotations)
        recomputeSizes(root);
    }
    // public remove function
    void remove(int key)
    {
        deleteNodeHelper(this->root, key);
    }
    // returns number of elements strictly less than key
    int order_of_key(int key)
    {
        Node *node = root;
        int cnt = 0;
        while (node != TNULL)
        {
            if (key <= node->data)
            {
                node = node->left;
            }
            else
            {
                // all nodes in left subtree + this node are < key
                cnt += node->left->sz + 1;
                node = node->right;
            }
        }
        return cnt;
    }
    // lower_bound: smallest element >= key
    // returns true and sets out if found; false otherwise
    bool lower_bound(int key, int &out)
    {
        Node *n = lowerBoundNode(root, key);
        if (n == TNULL)
            return false;
        out = n->data;
        return true;
    }
    // upper_bound: smallest element > key
    // returns true and sets out if found; false otherwise
    bool upper_bound(int key, int &out)
    {
        Node *n = upperBoundNode(root, key);
        if (n == TNULL)
            return false;
        out = n->data;
        return true;
    }
    // find_by_order: find k-th smallest element (0-based).
    // returns true and sets out if 0 <= k < size(), else returns false.
    bool find_by_order(int k, int &out)
    {
        if (k < 0 || k >= size())
            return false;
        Node *n = findByOrder(root, k);
        if (n == TNULL)
            return false;
        out = n->data;
        return true;
    }
    // useful helper for debugging: get total size
    int size()
    {
        return root == TNULL ? 0 : root->sz;
    }
};
int main()
{
    RedBlackTree rbt;
    vector<int> values = {10, 20, 30, 15, 25, 5, 1};
    for (int v : values)
        rbt.insert(v);
    cout << "Inorder traversal with colors (R=Red, B=Black):\n";
    rbt.inorder();
    cout << "Preorder traversal with colors:\n";
    rbt.preorder();
    cout << "Postorder traversal with colors:\n";
    rbt.postorder();
    cout << "Size = " << rbt.size() << "\n";
    cout << "order_of_key(0) = " << rbt.order_of_key(0) << "\n";
    cout << "order_of_key(1) = " << rbt.order_of_key(1) << "\n";
    cout << "order_of_key(2) = " << rbt.order_of_key(2) << "\n";
    cout << "order_of_key(10) = " << rbt.order_of_key(10) << "\n";
    cout << "order_of_key(17) = " << rbt.order_of_key(17) << "\n";
    cout << "order_of_key(100) = " << rbt.order_of_key(100) << "\n";
    int out;
    if (rbt.lower_bound(15, out))
        cout << "lower_bound(15) = " << out << "\n";
    else
        cout << "lower_bound(15) = not found\n";
    if (rbt.lower_bound(16, out))
        cout << "lower_bound(16) = " << out << "\n";
    else
        cout << "lower_bound(16) = not found\n";
    if (rbt.upper_bound(15, out))
        cout << "upper_bound(15) = " << out << "\n";
    else
        cout << "upper_bound(15) = not found\n";
    if (rbt.upper_bound(30, out))
        cout << "upper_bound(30) = " << out << "\n";
    else
        cout << "upper_bound(30) = not found\n";
    cout << "\nDemonstrating find_by_order (0-based):\n";
    for (int k = 0; k <= rbt.size(); ++k)
    {
        if (rbt.find_by_order(k, out))
            cout << "find_by_order(" << k << ") = " << out << "\n";
        else
            cout << "find_by_order(" << k << ") = not found (out of range)\n";
    }
    cout << "Deleting 20...\n";
    rbt.remove(20);
    rbt.inorder();
    cout << "Size = " << rbt.size() << "\n";
    cout << "order_of_key(25) = " << rbt.order_of_key(25) << "\n";
    cout << "Deleting 10...\n";
    rbt.remove(10);
    rbt.inorder();
    cout << "Size = " << rbt.size() << "\n";
    cout << "order_of_key(25) = " << rbt.order_of_key(25) << "\n";
    cout << "\nfind_by_order after deletions:\n";
    for (int k = 0; k < rbt.size(); ++k)
    {
        if (rbt.find_by_order(k, out))
            cout << "find_by_order(" << k << ") = " << out << "\n";
    }
    return 0;
}