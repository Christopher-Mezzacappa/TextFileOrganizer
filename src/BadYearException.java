/**
 * 
 * @author christopher & keshan
 *
 */
public class BadYearException extends Exception {

	/**
	 * Constructor for the exception 
	 * @param file the file that the Exception is found in 
	 * @param record the books contents 
	 */
	public BadYearException(String file, String record) {
		super("Semantic error in file: " + file + "\n" + "========================" + "Error type : Bad year" + "\n" + "Record : " + record + "\n");
	}
	/**
	 * An accessor method that retrieves the message attribute 
	 */
	public String getMessage() {
		return super.getMessage();
	}
}
