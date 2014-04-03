package exception;

/**
 * 
 * @author Aneesh Garg
 * 
 */
public class InvalidTreeOperation extends Exception {

	private static final long serialVersionUID = -6478524523729703467L;
	private static final String TREE_OPERATION_NOT_VALID = "Tree Operation not Valid !!!";

	public InvalidTreeOperation() {
		super(TREE_OPERATION_NOT_VALID);
	}

	public InvalidTreeOperation(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public InvalidTreeOperation(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidTreeOperation(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidTreeOperation(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
