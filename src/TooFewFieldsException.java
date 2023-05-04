/**
 * 
 * @author christopher and keshan 
 *
 */
public class TooFewFieldsException extends Exception{

	/**
	 * Default constructor 
	 */
	public TooFewFieldsException() {
		super("Too few fields exception");
	}
	
	/**
	 * param Constructor 
	 * @param filename file that contains the syntax error 
	 * @param record the contents of the books 
	 */
	public TooFewFieldsException(String filename, String record) {
		super("Syntax error in file: " + filename + "\n" + "========================" +"\n" + "Error : Too few fields " + "\n" + "Record : " + record + "\n");
	}
	
	/**
	 * To String method 
	 */
	public String getMessage() {
		return super.getMessage();
	}
}
