package bstmap;

import java.util.*;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private Node node;
    private int size;
    private Node root;


    public BSTMap() {
        root = null;
        size = 0;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public boolean containsKey(K key) {
        if (size == 0) {
            return false;
        }
        if (root.key.equals(key)) {
            return true;
        }
        Node now = root;
        while (now != null) {
            if (now.key.equals(key)) {
                return true;
            }
            if (now.key.compareTo(key) > 0) {
                now = now.left;
            } else {
                now = now.right;
            }
        }
        return false;
    }

    public V get(K key) {
        if (size == 0) {
            return null;
        }
        if (root.key == key) {
            return root.value;
        }
        Node now = root;
        while (now != null) {
            if (now.key.equals(key)) {
                return now.value;
            }
            if (now.key.compareTo(key) < 0) {
                now = now.right;
            }
            if (now.key.compareTo(key) > 0) {
                now = now.left;
            }
        }
        return null;
    }

    public int size() {
        return this.size;
    }

    public void put(K key, V value) {
        size++;
        if (root == null) {
            root = new Node(key, value);
            return;
        }
        Node now = root;
        while (now != null) {
            if (now.key.compareTo(key) > 0) {
                if (now.left == null) {
                    now.left = new Node(key, value);
                    break;
                }
                now = now.left;
            }
            if (now.key.compareTo(key) < 0) {
                if (now.right == null) {
                    now.right = new Node(key, value);
                    break;
                }
                now = now.right;
            }
            if (now.key.compareTo(key) == 0) {
                now.value = value;
                size--;
                return;
            }
        }
    }

    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for (K i : this) {
            set.add(i);
        }
        return set;
    }

    private V removedvalue;

    public V remove(K key) {
        if (size == 0) {
            return null;
        }
        if (root.key.equals(key) && size == 1) {
            V value = root.value;
            root = null;
            size--;
            return value;
        }
        removedvalue = null;
        Node now = root;
        root = remove(now, key);

        if (removedvalue != null){
            size--;
        }
        return removedvalue;
    }

    //help funtion
    private Node findemin(Node now) {
        while (now.left != null) {
            now = now.left;
        }
        return now;
    }

    private Node removeMin(Node now){
        if (now.left == null){
            return now.right;
        }
        now.left = removeMin(now.left);
        return now;
    }

    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }
        int compare = key.compareTo(node.key);
        if (compare == 0) {
            removedvalue = node.value;
            if (node.right == null){
                return node.left;
            }
            if (node.left == null){
                return node.right;
            }
            Node min = findemin(node.right);
            node.value = min.value;
            node.key = min.key;
            node.right = removeMin(node.right);
            return node;
        }
        if (compare > 0) {
            node.right = remove(node.right,key);
            return node;
        }
        if (compare < 0) {
            node.left = remove(node.left,key);
            return node;
        }
        return null;
    }

    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    public Iterator<K> iterator() {
        return new BSTIterator();
    }

    private class BSTIterator implements Iterator<K> {
        private Stack<Node> stack;

        BSTIterator() {
            stack = new Stack<>();
            pushleft(root);
        }

        private void pushleft(Node node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public K next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Node curr = stack.pop();
            if (curr.right != null) {
                pushleft(curr.right);
            }
            return curr.key;
        }
    }

    public void printInOrder() {
        for (K i : this) {
            System.out.println(i);
        }
    }


    private class Node {
        K key;
        V value;
        Node left;
        Node right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }


}



