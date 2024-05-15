import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BST<K extends Comparable<K>, V> implements Iterable<BST<K, V>.Node> {
    private Node root;
    private int size = 0;

    public class Node {
        private K key;
        private V val;
        private Node left, right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return val;
        }
    }

    public void put(K key, V val) {
        root = put(root, key, val);
    }

    private Node put(Node x, K key, V val) {
        if (x == null) {
            size++;
            return new Node(key, val);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;
        return x;
    }

    public V get(K key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return x.val;
        }
        return null;
    }

    public void delete(K key) {
        root = delete(root, key);
    }

    private Node delete(Node x, K key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
            size--;
        }
        return x;
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        return x;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }

    public int size() {
        return size;
    }

    public Iterator<Node> iterator() {
        List<Node> nodes = new ArrayList<>();
        inOrder(root, nodes);
        return nodes.iterator();
    }

    private void inOrder(Node x, List<Node> nodes) {
        if (x == null) return;
        inOrder(x.left, nodes);
        nodes.add(x);
        inOrder(x.right, nodes);
    }

    public K minKey() {
        if (root == null) return null;
        Node minNode = min(root);
        return minNode.key;
    }

    public K maxKey() {
        if (root == null) return null;
        Node maxNode = max(root);
        return maxNode.key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        return max(x.right);
    }

    public int height() {
        return height(root);
    }

    private int height(Node x) {
        if (x == null) return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }

    public void balance() {
        List<Node> nodes = new ArrayList<>();
        inOrder(root, nodes);
        root = buildBalancedBST(nodes, 0, nodes.size() - 1);
    }

    private Node buildBalancedBST(List<Node> nodes, int start, int end) {
        if (start > end) return null;
        int mid = (start + end) / 2;
        Node node = nodes.get(mid);
        node.left = buildBalancedBST(nodes, start, mid - 1);
        node.right = buildBalancedBST(nodes, mid + 1, end);
        return node;
    }
}