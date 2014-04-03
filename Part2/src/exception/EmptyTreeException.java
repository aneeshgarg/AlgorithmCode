package exception;

/**
 * @author Aneesh Garg
 * 
 */
public class EmptyTreeException extends Exception {

	private static final long serialVersionUID = 7274464476691103629L;

	private static final String TREE_IS_EMPTY = "Tree is Empty !!!";

	public EmptyTreeException() {
		super(TREE_IS_EMPTY);
	}

	public EmptyTreeException(String message) {
		super(message);
	}

}
