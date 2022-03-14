package com.yahui.bst;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yanyahui <yanyahui@kuaishou.com>
 * Created on 2022-03-06
 */
public class BST<Key extends Comparable<Key>, Value> {
    private Node root;

    public class Node {
        private Key key;
        private Value val;
        private Node left;
        private Node right;
        private int N;

        public Node(Key k, Value v, int N) {
            this.key = k;
            this.val = v;
            this.N = N;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        return node.N;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node root, Key key) {
        if (root == null) {
            return null;
        }
        int cmp = key.compareTo(root.key);
        if (cmp == 0) {
            return root.val;
        } else if (cmp < 0) {
            return get(root.left, key);
        } else {
            return get(root.right, key);
        }
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node root, Key key, Value value) {
        if (root == null) {
            return new Node(key, value, 1);
        }
        int cmp = key.compareTo(root.key);
        if (cmp == 0) {
            root.val = value;
        } else if (cmp < 0) {
            root.left = put(root.left, key, value);
        } else {
            root.right = put(root.right, key, value);
        }

        root.N = size(root.right) + size(root.left) + 1;
        return root;
    }

    public Key min() {
        Node m = min(root);
        if (m == null) {
            return null;
        }
        return m.key;
    }

    private Node min(Node root) {
        if (root == null) {
            return null;
        }
        if (root.left == null) {
            return root;
        }

        return min(root.left);
    }

    public Key max() {
        Node node = max(root);
        if (node == null) {
            return null;
        }
        return node.key;
    }

    private Node max(Node root) {
        if (root == null) {
            return null;
        }

        if (root.right == null) {
            return root;
        }
        return max(root.right);
    }

    public Key floor(Key key) {
        Node f = floor(root, key);
        if (f == null) {
            return null;
        }
        return f.key;
    }

    private Node floor(Node root, Key key) {
        if (root == null) {
            return null;
        }
        int cmp = key.compareTo(root.key);
        if (cmp == 0) {
            return root;
        } else if (cmp < 0) {
            return floor(root.left, key);
        }

        Node t = floor(root.right, key);
        if (t != null) {
            return t;
        }
        return root;
    }

    public Key ceiling(Key key) {
        Node node = ceiling(root, key);
        if (node == null) {
            return null;
        }
        return node.key;
    }

    private Node ceiling(Node root, Key key) {
        if (root == null) {
            return null;
        }
        int cmp = key.compareTo(root.key);
        if (cmp == 0) {
            return root;
        } else if (cmp > 0) {
            return ceiling(root.right, key);
        }
        Node c = ceiling(root.left, key);
        if (c != null) {
            return c;
        }
        return root;
    }

    public Key select(int k) {
        Node s = select(root, k);
        if (s == null) {
            return null;
        }
        return s.key;
    }

    private Node select(Node root, int k) {
        if (root == null) {
            return null;
        }
        int t = size(root.left);
        if (t == k) {
            return root;
        } else if (t > k) {
            return select(root.left, k);
        } else {
            return select(root.right, k - t - 1);
        }
    }

    public int rank(Key key) {
        return rank(root, key);
    }

    private int rank(Node root, Key key) {
        if (root == null) {
            return 0;
        }
        int cmp = key.compareTo(root.key);
        if (cmp == 0) {
            return size(root.left);
        } else if (cmp < 0) {
            return rank(root.left, key);
        } else {
            return 1 + size(root.left) + rank(root.right, key);
        }
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node root) {
        if (root == null) {
            return null;
        }
        if (root.left == null) {
            return root.right;
        }

        root.left = deleteMin(root.left);
        root.N = size(root.left) + size(root.right) + 1;
        return root;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node root, Key key) {
        if (root == null) {
            return null;
        }
        int cmp = key.compareTo(root.key);
        if (cmp < 0) {
            root.left = delete(root.left, key);
        } else if (cmp > 0) {
            root.right = delete(root.right, key);
        } else {
            if (root.right == null) {
                return root.left;
            }
            if (root.left == null) {
                return root.right;
            }
            Node t = root;
            root = min(root.right);
            root.left = t.left;
            root.right = deleteMin(root.right);
        }
        root.N = size(root.left) + size(root.right) + 1;
        return root;
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    private Iterable<Key> keys(Key min, Key max) {
        Queue<Key> queue = new LinkedList<>();
        keys(root, min, max, queue);
        return queue;
    }

    private void keys(Node root, Key min, Key max, Queue<Key> queue) {
        if (root == null) {
            return;
        }
        int cmp1 = min.compareTo(root.key);
        if (cmp1 > 0) {
            keys(root.right, min, max, queue);
            return;
        }

        int cmp2 = max.compareTo(root.key);
        if (cmp2 < 0) {
            keys(root.left, min, max, queue);
            return;
        }

        queue.add(root.key);
        keys(root.left, min, max, queue);
        keys(root.right, min, max, queue);
    }
}
