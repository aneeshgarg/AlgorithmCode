package tree;

import java.util.ArrayList;

import exception.EmptyTreeException;
import exception.InvalidTreeOperation;
import exception.KeyNotFoundException;

/**
 * This class implements binary Search Tree ADT.
 * 
 * @author Aneesh Garg
 * 
 */
public class BinarySearchTree {
	public static boolean DEBUG = true;

	private Node root;
	private int size;

	public BinarySearchTree() {
	}

	public BinarySearchTree(ArrayList<Integer> keys) {
		for (int key : keys) {
			insertItem(key);
			if (DEBUG) {
				inOrder(root);
				System.out.println();
			}
		}
	}

	/**
	 * Inserts a key into a binary search tree.
	 * 
	 * @param key
	 *            : Key to be inserted.
	 */
	public void insertItem(int key) {
		if (isEmpty())
			root = new Node(key);
		else {
			Node node = new Node(key);
			Node current = this.root;
			while (true)
				if (current.getKey() > key && current.getLeft() != null)
					current = current.getLeft();
				else if (current.getKey() <= key && current.getRight() != null)
					current = current.getRight();
				else
					break;
			if (current.getKey() > key)
				current.setLeft(node);
			else
				current.setRight(node);
			node.setParent(current);
		}
		size++;
	}

	/**
	 * Finds and Return Node object in the tree with a given key.
	 * 
	 * @param key
	 *            : Key of the item to be searched
	 * @return Node with key equal to input key of null if node is not found
	 */
	public Node findItem(int key) {
		if (isEmpty())
			return null;
		else {
			Node current = this.root;
			while (true)
				if (current.getKey() > key && current.getLeft() != null)
					current = current.getLeft();
				else if (current.getKey() < key && current.getRight() != null)
					current = current.getRight();
				else if (current != null && current.getKey() == key)
					return current;
				else
					return null;
		}
	}

	/**
	 * Removes the node with a given key.
	 * 
	 * @param key
	 *            : Key of the node to be removed
	 * @throws EmptyTreeException
	 *             : thrown if tree is empty
	 * @throws KeyNotFoundException
	 *             : thrown if key is not present in the tree
	 * @throws InvalidTreeOperation
	 *             : thrown if any invalid operation takes place in the tree
	 */
	public void removeItem(int key) throws EmptyTreeException,
			KeyNotFoundException, InvalidTreeOperation {
		if (isEmpty())
			throw new EmptyTreeException();
		Node item = findItem(key);
		if (item == null)
			throw new KeyNotFoundException("Key " + key + " not found !!!");
		else {
			if (item.isLeaf()) {
				Node parent = item.getParent();
				if (parent.isRightChild())
					parent.setRight(null);
				else
					parent.setLeft(null);
			} else if (item.isAboveExternal())
				removeAboveExternal(item);
			else {
				Node next = nextInorder(item);
				removeAboveExternal(next);
				next.setParent(item.getParent());
				next.setLeft(item.getLeft());
				next.setRight(item.getRight());
			}
			size--;
		}
	}

	/**
	 * This method removes a node if any of its children is external node.
	 * 
	 * @param item
	 */
	public void removeAboveExternal(Node item) {
		if (item.isAboveExternal()) {
			Node leftChild = item.getLeft();
			Node rightChild = item.getRight();
			Node parent = item.getParent();

			if (leftChild != null && rightChild != null) {
				if (item.isRightChild())
					parent.setRight(rightChild);
				else
					parent.setLeft(rightChild);

				rightChild.setParent(parent);

				rightChild.setLeft(leftChild);
				leftChild.setParent(rightChild);
			} else if (leftChild == null && rightChild != null) {
				rightChild.setParent(parent);
				if (item.isRightChild())
					parent.setRight(rightChild);
				else
					parent.setLeft(rightChild);
			} else if (leftChild != null && rightChild == null) {
				leftChild.setParent(parent);
				if (item.isRightChild())
					parent.setRight(leftChild);
				else
					parent.setLeft(leftChild);
			}
		}
	}

	/**
	 * This method finds the node which comes next in inorder traversal of the
	 * binary search tree.
	 * 
	 * @param node
	 *            : Node whose next inorder is to be found
	 * @return Node : next inorder node
	 */
	public static Node nextInorder(Node node) {
		if (node == null)
			return null;

		Node current = node.getRight();

		// If current node has right subtree then the next inorder node is
		// leftmost node in right subtree
		if (current != null) {
			while (current.getLeft() != null)
				current = current.getLeft();
			return current;
		}

		// If current node does not have right subtree then next inorder node
		// will be at upper levels
		current = node;
		// keep going if current is the right child. Stop only if current
		// becomes the left child of a parent
		while (current.getParent() != null && current.isRightChild())
			current = current.getParent();

		if (current.isRoot())
			return null;
		else if (!current.isRightChild())
			return current.getParent();
		else
			return null;
	}

	/**
	 * This method prints the binary search tree according to inorder traversal
	 */
	public void printTree() {
		Node current = this.root;
		// Go to the left most node of the tree. this will be the start of
		// inorder traversal
		while (current.getLeft() != null)
			current = current.getLeft();
		System.out.print("Inorder: " + current.toString());
		Node next = nextInorder(current);
		while (next != null) {
			System.out.print(", " + next.toString());
			next = nextInorder(next);
		}
		System.out.println();
	}

	public void inOrder(Node node) {
		if (node != null) {
			if (node.isInternal())
				inOrder(node.getLeft());
			System.out.print(node.toString() + " ");
			if (node.isInternal())
				inOrder(node.getRight());
		}
	}

	/**
	 * Gets the height of binary search tree
	 * 
	 * @return int height
	 */
	public int getHeight() {
		if (isEmpty())
			return 0;
		else
			return getRoot().getHeight();
	}

	/**
	 * Whether three is empty or not
	 * 
	 * @return boolean true or false
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
