
class Node{

	public int value;
	public Node left;
	public Node right;

	public Node(int t){
		this.value=t;
	}

}

public class BinarySerachTree{

	public Node root;

	public void insert(int value){
		Node node=new Node(value);

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

public static void main(String[] args){

	BinarySerachTree bst=new BinarySerachTree();
	bst.insert(100);
	bst.insert(89);
	bst.insert(89);
	bst.insert(89);
	bst.insert(89);
	bst.print();

}


}