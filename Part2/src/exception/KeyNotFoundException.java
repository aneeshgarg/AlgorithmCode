package exception;

/**
 * 
 * @author Aneesh Garg
 *
 */
public class KeyNotFoundException extends InvalidTreeOperation {

	private static final long serialVersionUID = 2140802301191179455L;

	private static final String KEY_NOT_FOUND = "Key not found !!!";

	public KeyNotFoundException() {
		super(KEY_NOT_FOUND);
	}

	public KeyNotFoundException(String message) {
		super(message);
	}

}
