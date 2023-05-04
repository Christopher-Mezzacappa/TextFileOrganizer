/**
 * 
 * @author christopher & keshan
 *
 */
public class UnknownGenreException extends Exception{

	/**
	 * Default constructor 
	 */
	public UnknownGenreException() {
		super("Missing Field Exception");
	}
	
	/**
	 * Param constructor 
	 * @param filename file name that has the syntax error 
	 * @param record the conents of the book 
	 * @param genre the genre 
	 */
	public UnknownGenreException(String filename, String record, String genre) {
		super("Syntax error in file: " + filename + "\n" + "========================" +"\n" + "Error : Unknown genre ==> " + genre +"\n" + "Record : " + record + "\n");
	}
	
	/**
	 * To String method 
	 */
	public String getMessage() {
		return super.getMessage();
	}
}
