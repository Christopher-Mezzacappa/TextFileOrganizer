/**
 * Constructor for the Exception that extends the Exception class
 * @author Christopher & Keshan
 *
 */
public class BadIsbn13Exception extends Exception {
	
	/**
	 * Constructor for the excpetion 
	 * @param file file that the error is found in 
	 * @param record all the info that of the book that has the error 
	 */
	public BadIsbn13Exception(String file, String record) {
		super("Semantic error in file: " + file + "\n" + "========================" + "Error type : Bad ISB13" + "\n" + "Record : " + record + "\n");
	}
	
	/**
	 * Acessor for the message we set a parameter 
	 */
	public String getMessage() {
		return super.getMessage();
	}
}
