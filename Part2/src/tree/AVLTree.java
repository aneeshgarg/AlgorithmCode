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
		if (DEBUG)
			System.out.println("Inserting " + key);
		Node insertedNode = super.insertItem(key);
		System.out.println("Inserted Node: " + insertedNode.getKey());
		if (DEBUG){
			System.out.println("After Inserting Before rotating: ");this.printTree();}

		// Checking whether any node imbalances
		Node imBalancedNode = insertedNode.getParent();
		if (imBalancedNode != null) {
			int balanceFactor = imBalancedNode.calculateBalanceFactor();
			while (imBalancedNode != null && balanceFactor < 2) {
				imBalancedNode = imBalancedNode.getParent();
				if (imBalancedNode != null)
					balanceFactor = imBalancedNode.calculateBalanceFactor();
			}
		}

		// there is imbalance in the tree so resolve it
		if (imBalancedNode != null) {
			if (DEBUG)
				System.out.println("Imbalance Node: " + imBalancedNode.getKey());
			Node leftChild = imBalancedNode.getLeft();
			Node rightChild = imBalancedNode.getRight();
			
			// Outside Case: insertion into left subtree of left child of
			// imbalance node alpha.
			if (leftChild != null && key < leftChild.getKey())
				rotateRight(imBalancedNode);
			// Outside Case: insertion into right subtree of right child of
			// imbalance node alpha.
			else if (rightChild != null && key >= rightChild.getKey()) {
				rotateLeft(imBalancedNode);
			}
			// Outside Case: insertion into left subtree of right child of
			// imbalance node alpha
			else if (rightChild != null && key >= imBalancedNode.getKey() && key < rightChild.getKey()) {
				rotateRight(rightChild);
				if (DEBUG)
					printTree();
				rotateLeft(imBalancedNode);
			}
			// Outside Case: insertion into right subtree of left child of
			// imbalance node alpha
			else if (leftChild != null && key < imBalancedNode.getKey() && key >= leftChild.getKey()) {
				rotateLeft(leftChild);
				if (DEBUG)
					printTree();
				rotateRight(imBalancedNode);
			}
		}
		return insertedNode;
	}

	/**
	 * Performs left rotation with input node as top node in rotation.
	 * @param imBalancedNode : reference to the node that is the top node.
	 */
	private void rotateLeft(Node imBalancedNode) {
		if (DEBUG)
			System.out.println("Rotate Left: " + imBalancedNode.getKey());
		Node parentImbalanced = imBalancedNode.getParent();
		Node rightChild = imBalancedNode.getRight();
		Node rightChildLeftSubtree = rightChild.getLeft();

		// breaking connections between alpha - right child and right child -
		// left subtree
		imBalancedNode.setRight(null);
		rightChild.setLeft(null);

		// Making right child left subtree as right child of alpha
		imBalancedNode.setRight(rightChildLeftSubtree);
		if (rightChildLeftSubtree != null)
			rightChildLeftSubtree.setParent(imBalancedNode);

		rightChild.setLeft(imBalancedNode);
		if (parentImbalanced != null) {
			if (imBalancedNode.isRightChild())
				parentImbalanced.setRight(rightChild);
			else
				parentImbalanced.setLeft(rightChild);
		}
		else
			this.setRoot(rightChild);
		rightChild.setParent(imBalancedNode.getParent());
		imBalancedNode.setParent(rightChild);
	}


	/**
	 * Performs right rotation with input node as top node in rotation.
	 * @param imBalancedNode : reference to the node that is the top node.
	 */
	private void rotateRight(Node imBalancedNode) {
		if (DEBUG)
			System.out.println("Rotate Right: " + imBalancedNode.getKey());
		Node parentImbalanced = imBalancedNode.getParent();
		Node leftChild = imBalancedNode.getLeft();
		Node leftChildRightSubtree = leftChild.getRight();

		// breaking connections between alpha - left child and left child -
		// right subtree
		imBalancedNode.setLeft(null);
		leftChild.setRight(null);

		// Making left child right subtree as left child of alpha
		imBalancedNode.setLeft(leftChildRightSubtree);
		if (leftChildRightSubtree != null)
			leftChildRightSubtree.setParent(imBalancedNode);

		leftChild.setRight(imBalancedNode);
		if (parentImbalanced != null) {
			if (imBalancedNode.isRightChild())
				parentImbalanced.setRight(leftChild);
			else
				parentImbalanced.setLeft(leftChild);
		}
		else
			this.setRoot(leftChild);
		leftChild.setParent(imBalancedNode.getParent());
		imBalancedNode.setParent(leftChild);
	}

	@Override
	public Node removeItem(int key) throws EmptyTreeException,
			KeyNotFoundException, InvalidTreeOperation {
		if (isEmpty())
			throw new EmptyTreeException();
		Node item = findItem(key);
		Node next = nextInorder(item);		
		Node removedNode = null;
		if (item == null)
			throw new KeyNotFoundException("Key " + key + " not found !!!");
		else {
			removedNode = super.removeItem(key);
			if (key == 63) {
				if (DEBUG){
					printTree();
					System.out.println("Root: "+getRoot().getKey());
				}
			}
			Node w = null;
			if (item.getLeft() != null && item.getRight() != null)
				w = next;
			else
				w = removedNode.getParent();
			
			Node z = w;
			while (z != null) {
				if (DEBUG)
					System.out.println("Checking for : "+ z.getKey());
				Node temp = z.getParent();
				if (z.calculateBalanceFactor() > 1)
					restructure(z);
				z = temp;
			}
		}
		return removedNode;
	}

	
	/**
	 * Performs restructuring and re-balancing of the tree after a node is
	 * deleted.
	 * 
	 * @param z
	 *            : node that is imbalanced due to removal
	 */
	private void restructure(Node z) {
		if (DEBUG)
			System.out.println("Restructuring: "+z.getKey());
		
		Node x,y;
		
		int zLeftHeight = -1;
		int zRightHeight = -1;
		if (z.getLeft() != null)
			zLeftHeight = z.getLeft().getHeight();
		if (z.getRight() != null)
			zRightHeight = z.getRight().getHeight();
		if (zLeftHeight > zRightHeight)
			y = z.getLeft();
		else
			y = z.getRight();
		
		int yLeftHeight = -1;
		int yRightHeight = -1;
		if (y.getLeft() != null)
			yLeftHeight = y.getLeft().getHeight();
		if (y.getRight() != null)
			yRightHeight = y.getRight().getHeight();
		if (yLeftHeight > yRightHeight)
			x = y.getLeft();
		else
			x = y.getRight();
		
		//Left - Left
		if (x.getKey() < y.getKey() && y.getKey() < z.getKey())
			rotateRight(z);
		//Right - Right
		else if (x.getKey() >= y.getKey() && y.getKey() >= z.getKey())
			rotateLeft(z);
		//Left - Right
		else if (y.getKey() < z.getKey() && x.getKey() >= y.getKey()) {
			rotateLeft(y);
			rotateRight(z);
		}
		//Right - Left
		else if (y.getKey() > z.getKey() && x.getKey() <= y.getKey()) {
			rotateRight(y);
			rotateLeft(z);
		}
	}

	@Override
	public void printTree() {
		Node current = getRoot();
		// Go to the left most node of the tree. this will be the start of
		// inorder traversal
		if (current != null) {
			while (current.getLeft() != null)
				current = current.getLeft();
			System.out.print("Inorder: " + current.toString() + "("
					+ +current.calculateBalanceFactor() + ")");
			Node next = nextInorder(current);
			while (next != null) {
				System.out.print(", " + next.toString() + "("
						+ next.calculateBalanceFactor() + ")");
				next = nextInorder(next);
			}
			System.out.println();
		} else
			System.out.println("Tree is empty.");
	}

}
