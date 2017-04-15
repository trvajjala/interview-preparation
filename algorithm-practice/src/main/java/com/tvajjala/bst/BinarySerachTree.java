package com.tvajjala.bst;

/**
 * Binary search tree .is an data structure where data is sorted
 *
 * @author ThirupathiReddy V
 *
 */
public class BinarySerachTree{


    public static void main(String[] args){

        final BinarySerachTree bst=new BinarySerachTree();
        bst.insert(100);
        bst.insert(89);
        bst.insert(89);
        bst.insert(89);
        bst.insert(89);
        bst.print();

    }

    public Node root;

    public void insert(int value){

        final Node node=new Node(value);

        if(root==null){
            root=node;
            return;
        }

        insertRec(root,node);
    }


    private void insertRec(Node root, Node node){

        if(root.value > node.value){
            if(root.left==null){
                root.left=node;
            }else {
                insertRec(root.left,node);
            }
        }else{
            if(root.right==null){
                root.right=node;
            }else{
                insertRec(root.right,node);
            }
        }

    }

    public void print(){

        printInOrder(root);
    }

    public void printInOrder(Node node){

        if(node==null){
            return;
        }

        printInOrder(node.left);

        System.out.print("    "+node.value +"   , ");

        printInOrder(node.right);

    }


}



class Node{

    public int value;
    public Node left;
    public Node right;

    public Node(int t){
        value=t;
    }
}