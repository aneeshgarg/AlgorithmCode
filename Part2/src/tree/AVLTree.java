package tree;

import java.util.ArrayList;

import exception.EmptyTreeException;
import exception.InvalidTreeOperation;
import exception.KeyNotFoundException;

/**
 * This class implements AVL Tree
 * 
 * @author Aneesh Garg
 * 
 */
public class AVLTree extends BinarySearchTree {

	public AVLTree() {
		super();
	}

	public AVLTree(ArrayList<Integer> keys) {
		super(keys);
	}

	@Override
	public Node insertItem(int key) {
		Node insertedNode = super.insertItem(key);

		// Checking whether any node imbalances
		Node imBalancedNode = insertedNode.getParent();
		int balanceFactor = imBalancedNode.calculateBalanceFactor();
		while (imBalancedNode != null && balanceFactor < 2) {
			imBalancedNode = imBalancedNode.getParent();
			if (imBalancedNode != null)
				balanceFactor = imBalancedNode.calculateBalanceFactor();
		}
		
		//there is imbalance in the tree so resolve it
		if (imBalancedNode != null) {
			Node leftChild = imBalancedNode.getLeft();
			Node rightChild = imBalancedNode.getRight();
			//Outside Case: insertion into left subtree of left child of imbalance node alpha.
			if (key < leftChild.getKey())
				rotateRight(imBalancedNode);
			//Outside Case: insertion into right subtree of right child of imbalance node alpha.
			else if (key > rightChild.getKey())
				rotateLeft(imBalancedNode);
			//Outside Case: insertion into right subtree of left child of imbalance node alpha 
			else if (key > leftChild.getKey()) {
				
			}
			//Outside Case: insertion into left subtree of right child of imbalance node alpha
			else if (key < rightChild.getKey()) {
				
			}
		}
		return insertedNode;
	}

	private void rotateLeft(Node imBalancedNode) {
		System.out.println("Rotate Left: "+imBalancedNode.getKey());
		Node parentImbalanced = imBalancedNode.getParent();
		Node rightChild = imBalancedNode.getRight();
		Node rightChildLeftSubtree = rightChild.getLeft();
		
		//breaking connections between alpha - right child and right child - left subtree
		imBalancedNode.setRight(null);
		rightChild.setLeft(null);
		
		//Making right child left subtree as right child of alpha
		imBalancedNode.setRight(rightChildLeftSubtree);
		rightChildLeftSubtree.setParent(imBalancedNode);
		
		rightChild.setLeft(imBalancedNode);
		if (parentImbalanced != null){
			if (imBalancedNode.isRightChild())
				parentImbalanced.setRight(rightChild);
			else
				parentImbalanced.setLeft(rightChild);
		}
		rightChild.setParent(imBalancedNode.getParent());
		imBalancedNode.setParent(rightChild);
	}

	private void rotateRight(Node imBalancedNode) {
		System.out.println("Rotate Right: "+imBalancedNode.getKey());
		Node parentImbalanced = imBalancedNode.getParent();
		Node leftChild = imBalancedNode.getLeft();
		Node leftChildRightSubtree = leftChild.getRight();
		
		//breaking connections between alpha - left child and left child - right subtree
		imBalancedNode.setLeft(null);
		leftChild.setRight(null);
		
		//Making left child right subtree as left child of alpha  
		imBalancedNode.setLeft(leftChildRightSubtree);
		leftChildRightSubtree.setParent(imBalancedNode);
		
		leftChild.setRight(imBalancedNode);
		if (parentImbalanced != null){
			if (imBalancedNode.isRightChild())
				parentImbalanced.setRight(leftChild);
			else
				parentImbalanced.setLeft(leftChild);
		}
		leftChild.setParent(imBalancedNode.getParent());
		imBalancedNode.setParent(leftChild);
	}

	@Override
	public Node removeItem(int key) throws EmptyTreeException,
			KeyNotFoundException, InvalidTreeOperation {
		// TODO Auto-generated method stub
		return super.removeItem(key);
	}
	
	@Override
	public void printTree() {
		Node current = getRoot();
		// Go to the left most node of the tree. this will be the start of
		// inorder traversal
		while (current.getLeft() != null)
			current = current.getLeft();
		System.out.print("Inorder: " + current.toString() +"("+current.calculateBalanceFactor()+")");
		Node next = nextInorder(current);
		while (next != null) {
			System.out.print(", " + next.toString() +"("+current.calculateBalanceFactor()+")");
			next = nextInorder(next);
		}
		System.out.println();
	}

}
