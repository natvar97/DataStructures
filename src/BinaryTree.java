import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;

public class BinaryTree {

    class Node {
        private int value;
        private Node leftChild;
        private Node rightChild;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node=" + value;
        }
    }

    private Node root;

    public void insert(int value) {
        Node node = new Node(value);
        if (root == null) {
            root = node;
            return;
        }
        Node current = root;
        while (true) {
            if (value < current.value) {
                if (current.leftChild == null) {
                    current.leftChild = node;
                    break;
                }
                current = current.leftChild;
            } else {
                if (current.rightChild == null) {
                    current.rightChild = node;
                    break;
                }
                current = current.rightChild;
            }
        }
    }

    public boolean find(int value) {
        var current = root;
        while (current != null) {
            if (value < current.value)
                current = current.leftChild;
            else if (value > current.value)
                current = current.rightChild;
            else
                return true;
        }
        return false;
    }

    public void traversalPreOrder() {
        traversalPreOrder(root);
    }

    private void traversalPreOrder(Node root) {
        // root -> left -> right
        // print root value
        if (root == null) return;
        System.out.print(root.value + " -> ");
        traversalPreOrder(root.leftChild);
        traversalPreOrder(root.rightChild);
    }

    public void traversalInOrder() {
        traversalInOrder(root);
    }

    private void traversalInOrder(Node root) {
        if (root == null) return;
        traversalInOrder(root.leftChild);
        System.out.print(root.value + " -> ");
        traversalInOrder(root.rightChild);
    }

    public void traversalPostOrder() {
        traversalPostOrder(root);
    }

    private void traversalPostOrder(Node root) {
        if (root == null) return;
        traversalPostOrder(root.leftChild);
        traversalPostOrder(root.rightChild);
        System.out.print(root.value + " -> ");
    }

    public int height() {
        return height(root);
    }

    private int height(Node root) {
        if (root == null) return -1;
        if (isLeaf(root)) return 0;
        return 1 + Math.max(height(root.leftChild), height(root.rightChild));
    }

    public int min() {
        if (root == null) {
            throw new IllegalStateException();
        }
        // for binary search tree
        /*
        Node current = root;
        Node lastNode = current;
        while (current != null) {
            lastNode = current;
            current = current.leftChild;
        }
        return lastNode.value;
        */

        // for binary tree
        return min(root);
    }

    private int min(Node root) {
        if (isLeaf(root)) return root.value;
        if (root.leftChild == null && root.rightChild != null) return min(root.rightChild);
        if (root.leftChild != null && root.rightChild == null) return min(root.leftChild);
        int left = min(root.leftChild);
        int right = min(root.rightChild);
        return Math.min(Math.min(left, right), root.value);
    }

    private boolean isLeaf(Node root) {
        return root.leftChild == null && root.rightChild == null;
    }

    public boolean equals(BinaryTree other) {
        if (other == null || root == null) return false;
        return equals(root, other.root);
    }

    private boolean equals(Node node, Node anotherNode) {
        if (node == null && anotherNode == null) return false;
        if (node != null && anotherNode != null) {
            return node.value == anotherNode.value
                    && equals(node.leftChild, anotherNode.leftChild)
                    && equals(node.rightChild, anotherNode.rightChild);
        }
        return false;
    }

    public void swapRoot() {
        Node temp = root.leftChild;
        root.leftChild = root.rightChild;
        root.rightChild = temp;
    }

    public boolean isBinarySearchTree() {
        return isBinarySearchTree(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBinarySearchTree(Node node, int min, int max) {
        if (node == null) return true;
        if (node.value < min || node.value > max) return false;
        return isBinarySearchTree(node.leftChild, min, node.value - 1)
                && isBinarySearchTree(node.rightChild, node.value + 1, max);
    }

    public ArrayList<Integer> printNodesAtDistance(int distance) {
        ArrayList<Integer> list = new ArrayList<>();
        printNodesAtDistance(root, distance, list);
        return list;
    }

    private void printNodesAtDistance(Node node, int distance, ArrayList<Integer> list) {
        if (node == null) return;
        if (distance == 0) {
            list.add(node.value);
            return;
        }
        printNodesAtDistance(node.leftChild, distance - 1, list);
        printNodesAtDistance(node.rightChild, distance - 1, list);
    }

    public void traverseLevelOrder() {
        for (int i = 0; i <= height(); i++) {
            for (var value: printNodesAtDistance(i)) {
                System.out.println(value);
            }
        }
    }

}
