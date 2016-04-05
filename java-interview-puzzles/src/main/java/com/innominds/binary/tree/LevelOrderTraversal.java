package com.innominds.binary.tree;

public class LevelOrderTraversal {

    public static void main(String[] args) {

        final BinaryTree binaryTree = new BinaryTree();
        binaryTree.add(100);
        binaryTree.add(2);
        binaryTree.add(33);
        binaryTree.add(4);
        binaryTree.add(2);
        binaryTree.add(5);
        binaryTree.add(12);

        binaryTree.printLevelOrder();
    }
}

class Node {

    public int value;

    public Node left;

    public Node right;

    public Node(int data) {
        value = data;
    }

}

class BinaryTree {

    private Node root;

    public Node getRoot() {
        return root;
    }

    public void add(int data) {
        final Node node = new Node(data);

        if (root == null) {
            root = node;
            return;
        }

        add(root, node);

    }

    void add(Node root, Node dataNode) {

        if (root.value > dataNode.value) {

            if (root.left == null) {
                root.left = dataNode;
            } else {
                add(root.left, dataNode);
            }

        } else {
            if (root.right == null) {
                root.right = dataNode;
            } else {
                add(root.right, dataNode);
            }
        }

    }

    public void printLevelOrder() {
        print(root);
    }

    void print(Node node) {

        if (node == null) {
            return;
        }
        print(node.left);

        System.out.println(node.value);

        print(node.right);

    }
}