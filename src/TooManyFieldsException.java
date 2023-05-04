/**
 * 
 * @author christopher and keshan
 *
 */
public class TooManyFieldsException extends Exception{

	/**
	 * Default constructor 
	 */
	public TooManyFieldsException() {
		super("Too many fields exception");
	}
	
	/**
	 * Param Constructor 
	 * @param filename file that contains the syntax error 
	 * @param record contents of the book that has the error 
	 */
	public TooManyFieldsException(String filename, String record) {
		super("Syntax error in file: " + filename + "\n" + "========================" +"\n" + "Error : Too many fields " + "\n" + "Record : " + record + "\n");
	}
	
	/**
	 * To String method 
	 */
	public String getMessage() {
		return super.getMessage();
	}
}
