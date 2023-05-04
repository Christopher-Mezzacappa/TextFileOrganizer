import java.io.Serializable;
/**
 * 
 * @author christopher and Keshan
 *
 */
public class Book implements Serializable{
	
	/**
	 * Attributes for the class which are the characteristics of the books 
	 */
	private String title;
	private String author;
	private double price;
	private long isbn;
	private String genre;
	private int year;
	
	/**
	 * Parametrized constructor 
	 * @param x is an array of strings 
	 */
	public Book(String[] x) {
		this.title = x[0];
		this.author = x[1];
		this.price = Double.parseDouble(x[2]);
		this.isbn = Long.parseLong(x[3]);
		this.genre = x[4];
		this.year = Integer.parseInt(x[5]);
	}
	
	/**
	 * Acessor for the title attribute 
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Mutator for the title attribute
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Acessor for the authors 
	 * @return authors 
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Mutator for the author 
	 * @param author
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * Acessor for the isbn 
	 * @return isbn
	 */
	public long getIsbn() {
		return isbn;
	}

	/**
	 * Mutator for the isbn 
	 * @param isbn
	 */
	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}

	/**
	 * Acessor for the genre attribute 
	 * @return attibute 
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * Mutator for the genre attrbiute 
	 * @param genre
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * Accessor for the year attrbiute 
	 * @return
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Mutator for the year of the book 
	 * @param year
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * Equals method that returns boolean
	 */
	public boolean equals(Object obj) {
		if (this == null | obj == null | obj.getClass() != this.getClass()) {
			return false;
		}
		
		Book hold = (Book) obj;
		
		return ((this.title == hold.title) | (this.author == hold.author) | (this.isbn == hold.isbn) | (this.genre == hold.genre) | (this.year == hold.year));
	}

	/**
	 * toString method 
	 */
	public String toString() {
		return title + " " + author + " " + this.price +" " + isbn + " " + genre + " " + year;
	}
	
	
	
	
}
