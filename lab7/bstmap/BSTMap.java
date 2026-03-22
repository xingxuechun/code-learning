package bstmap;
import bstmap.Map61B;

import java.util.*;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private Node node;
    private int size;
    private Node root;


    public BSTMap() {
        root = null;
        size = 0;
    }

    public void clear(){
        root = null;
        size = 0;
    }

    public boolean containsKey(K key){
        if (size == 0) {
            return false;
        }
        if (root.key.equals(key)){
            return true;
        }
        Node now = root;
        while(now != null){
            if(now.key.equals(key)){
                return true;
            }
            if (now.key.compareTo(key) > 0){
                now = now.left;
            }
            else {
                now = now.right;
            }
        }
        return false;
    }

    public V get(K key){
        if (size == 0) {
            return null;
        }
        if (root.key == key){
            return root.value;
        }
        Node now = root;
        while (now != null){
            if (now.key.equals(key)){
                return now.value;
            }
            if (now.key.compareTo(key) < 0){
                now = now.right;
            }
            if (now.key.compareTo(key) > 0){
                now = now.left;
            }
        }
        return null;
    }

    public int size(){
        return this.size;
    }

    public void put(K key, V value){
        size++;
        if (root == null){
            root = new Node(key,value);
            return;
        }
        Node now = root;
        while (now != null){
            if (now.key.compareTo(key) > 0){
                if (now.left == null){
                    now.left = new Node(key,value);
                    break;
                }
                now = now.left;
            }
            if (now.key.compareTo(key) < 0){
                if (now.right == null){
                    now.right = new Node(key,value);
                    break;
                }
                now = now.right;
            }
            if (now.key.compareTo(key) == 0){
                return;
        }
    }}

    public Set<K> keySet(){
        throw new UnsupportedOperationException();
    }

    public V remove(K key) {
        if (size == 0) {
            return null;
        }
        Node now = root;
        if (root.equals(now)) {
            V result = root.value;
            root = null;
            size--;
            return result;
        }
        while (now != null) {
            if (now.key.compareTo(key) < 0) {
                if (now.right.key.equals(key)) {
                    V result = now.right.value;
                    if (now.right.left == null && now.right.right == null) {
                        now.right = null;
                    }
                    else {
                        now = now.right;
                        if (now.right != null){
                            Node target = find_removemininright(now);
                            now.key = target.key;
                            now.value = target.value;
                        }
                        else{
                            Node target = find_removemaxinleft(now);
                            now.key = target.key;
                            now.value = target.value;
                        }
                    }
                    size--;
                    return result;
                }
                now = now.right;
            }
            if (now.key.compareTo(key) > 0) {
                if (now.left.key.equals(key)) {
                    V result = now.left.value;
                    if (now.left.left == null && now.left.right == null) {
                        now.left = null;
                    }
                    else{
                     now = now.left;
                     if (now.right != null){
                         Node target = find_removemininright(now);
                         now.key = target.key;
                         now.value = target.value;
                     }
                     else{
                         Node target = find_removemaxinleft(now);
                         now.key = target.key;
                         now.value = target.value;
                     }
                    }
                    size--;
                    return result;
                }
                now = now.left;
                }
        }
        return null;
    }

    public V remove(K key, V value){
        throw new UnsupportedOperationException();
    }

    public Iterator<K> iterator() {
        return new BSTIterator();
    }

    private class BSTIterator implements Iterator<K>{
        private Stack<Node> stack;

        BSTIterator(){
            stack = new Stack<>();
            pushleft(root);
        }

        private void pushleft(Node node){
            while(node != null){
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
            if (!hasNext()){
                throw new NoSuchElementException();
            }

            Node curr = stack.pop();
            if (curr.right != null){
                pushleft(curr.right);
            }
            return curr.key;
        }
    }

    public void printInOrder(){
        for (K i : this){
            System.out.println(i);
        }
    }


private class Node{
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
//help funtion
    private Node find_removemininright(Node now){
        if (now.right.left!=null){
            now = now.right;
            while(now.left.left!= null){
            now = now.left;
            }
            Node result = now.left;
            now.left = null;
            return result;
        }
    Node result = now.right;
    now.right = null;
    return result;
    }

    private Node find_removemaxinleft(Node now){
        if(now.left.right!=null){
            now= now.left;
            while (now.right.right!=null){
                now = now.right;
            }
            Node result = now.left;
            now.left = null;
            return result;
        }
        Node result = now.left;
        now.left = null;
        return result;
    }
}


