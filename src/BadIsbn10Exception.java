/**
 * Constructor for the Exception that extends the Exception class
 * @author Christopher & Keshan
 *
 */
public class BadIsbn10Exception extends Exception {

	
	
	
	/**
	 * Constructor for the Exception 
	 * @param file File that the error is found in 
	 * @param record A String record that is the string of the books information
	 */
	public BadIsbn10Exception(String file, String record) {
		super("Semantic error in file: " + file + "\n" + "========================" + "Error type : Bad ISB10" + "\n" + "Record : " + record + "\n");
	}
	
	/**
	 * Accessor method to retrieve the method 
	 */
	public String getMessage() {
		return super.getMessage();
	}
}
