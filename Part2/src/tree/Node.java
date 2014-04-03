package tree;


/**
 * This class denotes a single node in a tree.
 * 
 * @author Aneesh Garg
 */
public class Node {

	/**
	 * Stores the key in node.
	 */
	private int key;
	/**
	 * Stores reference to left child of the node.
	 */
	private Node left;
	/**
	 * Stores reference to right child of the node.
	 */
	private Node right;
	/**
	 * Stores reference to parent of the node.
	 */
	private Node parent;

	public Node(int key) {
		super();
		this.key = key;
	}

	/**
	 * Whether the node is root node or not
	 * 
	 * @return boolean true or false
	 */
	public boolean isRoot() {
		return parent == null;
	}

	/**
	 * Whether the node is leaf node or not
	 * 
	 * @return boolean true or false
	 */
	public boolean isLeaf() {
		return getLeft() == null && getRight() == null;
	}

	/**
	 * Whether the node is internal node of not
	 * 
	 * @return boolean true or false
	 */
	public boolean isInternal() {
		return getLeft() != null || getRight() != null;
	}

	/**
	 * Whether any children of this node is a leaf node or not
	 * 
	 * @return boolean true or false
	 */
	public boolean isAboveExternal() {
		if (isLeaf())
			return false;
		else 
			return (getLeft() == null || getRight() == null);
	}

	/**
	 * Whether the node is right child of its parent or not.
	 * 
	 * @return boolean true or false
	 */
	public boolean isRightChild() {
		if (isRoot())
			return false;
		else {
			if (this.equals(parent.getRight()))
				return true;
			else
				return false;
		}
	}

	/**
	 * Gets height of the tree starting at calling node.
	 * 
	 * @return int height
	 */
	public int getHeight() {
		if (isLeaf())
			return 0;
		else {
			int height = 0;
			if (getLeft() != null)
				height = max(height, getLeft().getHeight());
			if (getRight() != null)
				height = max(height, getRight().getHeight());
			return 1 + height;
		}
	}

	/**
	 * Returns max of two numbers.
	 * 
	 * @param n1
	 *            : First number
	 * @param n2
	 *            : Second number
	 * @return maximum of two numbers
	 */
	private int max(int n1, int n2) {
		if (n1 > n2)
			return n1;
		else
			return n2;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	@Override
	public String toString() {
		return String.valueOf(getKey());
	}

}
