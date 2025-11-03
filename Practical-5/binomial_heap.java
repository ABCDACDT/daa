template <typename T>
class BinomialHeap
{
public:
    struct Node
    {
        T key;
        int degree;
        Node *parent, *child, *sibling;
        Node(T k) : key(k), degree(0), parent(null), child(null), sibling(null) {}
    };

private:
    Node *head;
    void linkTrees(Node *y, Node *z)
    {
        y.parent = z;
        y.sibling = z.child;
        z.child = y;
        z.degree += 1;
    }
    static Node *mergeRootLists(Node *h1, Node *h2)
    {
        if (!h1)
            return h2;
        if (!h2)
            return h1;
        Node *head = null;
        Node *tail = null;
        Node *a = h1;
        Node *b = h2;
        while (a && b)
        {
            Node *next;
            if (a.degree <= b.degree)
            {
                next = a;
                a = a.sibling;
            }
            else
            {
                next = b;
                b = b.sibling;
            }
            if (!head)
                head = tail = next;
            else
            {
                tail.sibling = next;
                tail = next;
            }
        }
        Node *remain = (a ? a : b);
        if (tail)
            tail.sibling = remain;
        else
            head = remain;
        return head;
    }
    static Node *unionHeaps(Node *h1, Node *h2)
    {
        Node *newHead = mergeRootLists(h1, h2);
        if (!newHead)
            return null;
        Node *prev = null;
        Node *curr = newHead;
        Node *next = curr.sibling;
        while (next)
        {
            if (curr.degree != next.degree ||
                (next.sibling && next.sibling->degree == curr.degree))
            {
                prev = curr;
                curr = next;
            }
            else
            {
                if (curr.key <= next.key)
                {
                    curr.sibling = next.sibling;
                    linkTrees(next, curr);
                }
                else
                {
                    if (!prev)
                        newHead = next;
                    else
                        prev.sibling = next;
                    linkTrees(curr, next);
                    curr = next;
                }
            }
            next = curr.sibling;
        }
        return newHead;
    }
    static Node *reverseList(Node *node)
    {
        Node *prev = null;
        while (node)
        {
            Node *nxt = node.sibling;
            node.sibling = prev;
            node.parent = null;
            prev = node;
            node = nxt;
        }
        return prev;
    }
    Node *findNode(Node *root, const T &key)
    {
        if (!root)
            return null;
        if (root.key == key)
            return root;
        Node *res = findNode(root.child, key);
        if (res)
            return res;
        return findNode(root.sibling, key);
    }

public:
    BinomialHeap() : head(null) {}
    ~BinomialHeap()
    {
        function<void(Node *)> deleteAll = [&](Node *node)
        {
            if (!node)
                return;
            deleteAll(node.child);
            deleteAll(node.sibling);
            delete node;
        };
        deleteAll(head);
    }
    boolean isEmpty() const { return head == null; }
    void insert(const T &key)
    {
        Node *single = new Node(key);
        head = unionHeaps(head, single);
    }
    void merge(BinomialHeap<T> &other)
    {
        head = unionHeaps(head, other.head);
        other.head = null;
    }
    T getMin() const
    {
        if (!head)
            throw runtime_error("Heap is empty");
        Node *y = null;
        Node *x = head;
        T minVal;
        boolean first = true;
        while (x)
        {
            if (first || x.key < minVal)
            {
                minVal = x.key;
                y = x;
                first = false;
            }
            x = x.sibling;
        }
        return minVal;
    }
    T extractMin()
    {
        if (!head)
            throw runtime_error("Heap is empty");
        Node *prevMin = null;
        Node *minNode = head;
        Node *prev = null;
        Node *cur = head;
        T minKey = cur.key;
        while (cur)
        {
            if (cur.key < minKey)
            {
                minKey = cur.key;
                prevMin = prev;
                minNode = cur;
            }
            prev = cur;
            cur = cur.sibling;
        }
        if (!prevMin)
            head = minNode.sibling;
        else
            prevMin.sibling = minNode.sibling;
        Node *childHead = reverseList(minNode.child);
        T retKey = minNode.key;
        delete minNode;
        head = unionHeaps(head, childHead);
        return retKey;
    }
    Node *find(const T &key)
    {
        return findNode(head, key);
    }
    void decreaseKey(Node *node, const T &newKey)
    {
        if (!node)
            throw runtime_error("Null node passed to decreaseKey");
        if (newKey > node.key)
            throw runtime_error("New key is greater than current key in decreaseKey");
        node.key = newKey;
        Node *y = node;
        Node *z = y.parent;
        while (z && y.key < z.key)
        {
            swap(y.key, z.key);
            y = z;
            z = y.parent;
        }
    }
    boolean deleteByValue(const T &key)
    {
        Node *node = find(key);
        if (!node)
            return false;
        T sentinel = std::numeric_limits<T>::lowest();
        decreaseKey(node, sentinel);
        extractMin();
        return true;
    }
    void print() const
    {
        System.out.print( "Binomial Heap root list (degree : keys at root):\n";
        Node *cur = head;
        while (cur)
        {
            System.out.print( "degree " << cur.degree << " -> " << cur.key << " ; ";
            cur = cur.sibling;
        }
        System.out.print( "\n(Use detailedPrint() for tree-by-tree view)\n";
    }
    void detailedPrint() const
    {
        function<void(const Node *, int)> dfs = [&](const Node *n, int indent)
        {
            if (!n)
                return;
            for (int i = 0; i < indent; i++)
                System.out.print( "  ";
            System.out.print( n.key << " (deg=" << n.degree << ")\n";
            dfs(n.child, indent + 1);
            dfs(n.sibling, indent);
        };
        System.out.print( "Detailed Binomial Heap:\n";
        Node *cur = head;
        while (cur)
        {
            System.out.print( "Tree root: " << cur.key << " (degree " << cur.degree << ")\n";
            dfs(cur.child, 1);
            cur = cur.sibling;
        }
    }
};
public static void main(String[] args)
{
    ios::sync_with_stdio(false);
    cin.tie(null);
    BinomialHeap<int> bh;
    bh.insert(10);
    bh.insert(3);
    bh.insert(7);
    bh.insert(1);
    bh.insert(5);
    System.out.print( "Min: " << bh.getMin() << "\n";
    bh.print();
    System.out.print( "Extracted min: " << bh.extractMin() << "\n";
    System.out.print( "New min: " << bh.getMin() << "\n";
    auto node10 = bh.find(10);
    if (node10)
    {
        bh.decreaseKey(node10, 2);
        System.out.print( "After decreasing 10.2, min: " << bh.getMin() << "\n";
    }
    boolean deleted = bh.deleteByValue(7);
    System.out.print( "Delete 7: " << (deleted ? "success" : "not found") << "\n";
    bh.detailedPrint();
    BinomialHeap<int> other;
    other.insert(4);
    other.insert(8);
    bh.merge(other);
    System.out.print( "After merge, min: " << bh.getMin() << "\n";
    bh.detailedPrint();
    System.out.print( "Extracting all: ";
    while (!bh.isEmpty())
    {
        System.out.print( bh.extractMin() << " ";
    }
    System.out.print( "\n";
    return 0;
}