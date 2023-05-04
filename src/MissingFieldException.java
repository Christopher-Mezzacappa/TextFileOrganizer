/**
 * Extends the Exception class
 * @author christopher and keshan
 *
 */
public class MissingFieldException extends Exception{

	/**
	 * Default constructor that calls super and has a fixed string 
	 */
	public MissingFieldException() {
		super("Missing Field Exception");
	}
	
	/**
	 * Param Constructor 
	 * @param filename file that has the syntax error 
	 * @param record the contents of the book 
	 * @param missing the fild that is missing 
	 */
	public MissingFieldException(String filename, String record, String missing) {
		super("Syntax error in file: " + filename + "\n" + "========================" +"\n" + "Error : missing " + missing + " field" +"\n" + "Record : " + record + "\n");
	}
	
	/**
	 * To String method
	 */
	public String getMessage() {
		return super.getMessage();
	}
	
}
