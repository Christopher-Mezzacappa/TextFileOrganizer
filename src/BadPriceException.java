/**
 * Constructor for the Exception that extends the Exception class
 * @author christopher & Keshan
 *
 */
public class BadPriceException extends Exception {

	/**
	 * Constructor for the Exception 
	 * @param file the file that the exception is located in 
	 * @param record
	 */
	public BadPriceException(String file, String record) {
		super("Semantic error in file: " + file + "\n" + "========================" + "Error type : Bad price" + "\n" + "Record : " + record + "\n");
	}
	
	/**
	 * An Accessor method to retrive the method attrbiute 
	 */
	public String getMessage() {
		return super.getMessage();
	}
}
