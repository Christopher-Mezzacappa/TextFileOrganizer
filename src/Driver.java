
import java.io.FileInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.FileNotFoundException;
import java.io.EOFException;

/**
 * Driver class for assigment that contains do_part1(),do_part2() and do_part3() method 
 * @author christopher & keshan
 *
 */

/*
 * This assigment consisted of reading and writting to files in both 
 * text and binary format and exception handeling. We implemented the 
 * interace Serializable as well as many excpetion classes. We 
 */

// -----------------------------------------------------
// Assignment 3
// Question: 1,2,3
// Christopher Mezzacappa 40249451
// -----------------------------------------------------

public class Driver implements Serializable{
	
	public static String[] listCSV;
	public static ObjectInputStream[] listOIS;
	public static String[] listTypes = {"Cartoons_Comics.csv", "Hobbies_Collectibles.csv", "Movies_TV_Books.csv", "Music_Radio_Books.csv","Nostalgia_Eclectic_Books.csv","Old_Time_radio_Books.csv","Sports_Sports_Memorabilia.csv","Trains_Planes_Automobiles.csv","syntax_error_file"};
	public static String[] order = {"title","author(s)","price","isbn","genre","year"};
	public static PrintWriter[] printers;
	public static int[] bookCount = new int[listTypes.length-1];
	static int counttt= 0;
	/**
	 * Main Method 
	 * @param args
	 */
	public static void main(String[] args) {
		
		do_part1();
		do_part2();
		do_part3();
		for (int i = 0; i < listCSV.length; i++) {
			System.out.println(listCSV[i]);
		}
		
		for (int i = 0; i< printers.length; i++) {
			printers[i].close();
		}

	}
	/**
	 * reads the given file and creates ogranized files of specific type 
	 */
	public static void do_part1() {
		
		
		// all the print writers 
		Scanner sc = null;
		PrintWriter pwCart = null;
		PrintWriter pwHobb = null;
		PrintWriter pwMovi = null;
		PrintWriter pwMusi = null;
		PrintWriter pwNost = null;
		PrintWriter pwOldT = null;
		PrintWriter pwSpor = null;
		PrintWriter pwTrai = null;
		PrintWriter pwSynt = null;
		PrintWriter[] listPrinters = {pwCart, pwHobb, pwMovi, pwMusi, pwNost, pwOldT, pwSpor, pwTrai, pwSynt};
		printers =  listPrinters;
		String read[];
		String genre = null;
		String record = "";
		String rec;
		
		try { // initializes the array of text files to open
			sc = new Scanner(new FileInputStream("Part1_input_file_names.txt"));
			int hold = Integer.parseInt(sc.nextLine());
			int count = 0;
			listCSV = new String[hold];
			
			while (sc.hasNextLine()) {
				listCSV[count] = sc.nextLine();
				count++;
			}
			sc.close();
			count = 0;
			
		}
		catch (FileNotFoundException e) {
			System.out.println("Not found");
		}
		try {
			for (int i = 0; i < listPrinters.length; i++) {  // initializes the printers
				listPrinters[i] = new PrintWriter(new File(listTypes[i]));
			}
		}
		catch (FileNotFoundException e) {
			System.out.println("Not Found");
		}
		for (int i = 0; i < listCSV.length; i++) {
			try {
				sc = new Scanner(new FileInputStream(listCSV[i])); // initializes the scanners individually
				System.out.println(listCSV[i]);
				while (sc.hasNextLine()) {
					int count = 0;
					try {
					read = new String[6];					

					String hold = sc.nextLine();
					rec = String.valueOf(hold);
					if (hold.charAt(0) == '"') {
						for (int j = 1; j < hold.length(); j++) {
							if (hold.charAt(j) == '"') {
								read[0] = hold.substring(1,j);
								hold = hold.substring(j+2);
								for (int o = 0; o < hold.length(); o++) {
									if (hold.charAt(o) == ',') {
										count++;
									}
								}
								
								if (count > 4) {
									throw (new TooManyFieldsException(listCSV[i],hold));
								}
								
								if (count < 4) {
									throw (new TooFewFieldsException(listCSV[i],hold));
								}
								
								String[] hold1 = hold.split(",");

								if (hold1.length > 5) {
									throw (new TooManyFieldsException(listCSV[i],hold));
								}
								if (hold1.length < 5) {
									throw (new TooFewFieldsException(listCSV[i],hold));
								}
								for (int k = 0; k < hold1.length; k++) {
									read[k+1] = hold1[k];
								}
								for (int l = 0; l < read.length; l++) {
									if (read[l].equals("")) {
										throw (new MissingFieldException(listCSV[i],hold,order[l]));
									}
								}
								
							}
							
						}
					}
					else {
						
						for (int o = 0; o < hold.length(); o++) {
							if (hold.charAt(o) == ',') {
								count++;
							}
						}
						
						if (count < 5) {
							throw (new TooFewFieldsException(listCSV[i],hold));
						}
						
						if (count > 5) {
							throw (new TooManyFieldsException(listCSV[i],hold));
						}
						
						read = hold.split(",");
						if (read.length > 6) {
							throw (new TooManyFieldsException(listCSV[i],hold));
						}
						if (read.length < 6) {
							throw (new TooFewFieldsException(listCSV[i],hold));
						}
						for (int j = 0; j<read.length; j++) {
							if (read[j] == "") {
								throw (new MissingFieldException(listCSV[i],hold,order[j]));
							}
						}
					}
					
					
					if (!(read[4].equals("CCB")) && !(read[4].equals("HCB")) && !(read[4].equals("MTV")) && !(read[4].equals("MRB")) && !(read[4].equals("NEB")) && !(read[4].equals("OTR")) && !(read[4].equals("SSM")) && !(read[4].equals("TPA"))) {
						throw (new UnknownGenreException(listCSV[i],hold,read[4]));
					}
				
					genre = read[4];
					record = rec;
					
					}
					catch (MissingFieldException e) {
						listPrinters[listPrinters.length-1].println(e.getMessage());
						continue;
					}
					catch (TooFewFieldsException e) {
						listPrinters[listPrinters.length-1].println(e.getMessage());
						continue;
					}
					catch (TooManyFieldsException e) {
						listPrinters[listPrinters.length-1].println(e.getMessage());
						continue;
					}
					catch (UnknownGenreException e) {
						listPrinters[listPrinters.length-1].println(e.getMessage());
						continue;
					}
					
					switch (genre) {
						
					case "CCB" : 
						listPrinters[0].println(record);
						break;
						
					case "HCB" :
						listPrinters[1].println(record);
						break;
						
					case "MTV" : 
						listPrinters[2].println(record);
						break;
						
					case "MRB" :
						listPrinters[3].println(record);
						break;
					
					case "NEB" :
						listPrinters[4].println(record);
						break;
					
					case "OTR" :
						listPrinters[5].println(record);
						break;
						
					case "SSM" :
						listPrinters[6].println(record);
						break;
						
					case "TPA" :
						listPrinters[7].println(record);
						break;
						
					default :
						System.out.println("Not Done");
						break;
					}
			}

			
		}catch (FileNotFoundException e) {
				System.out.println("File " +  listCSV[i] + " was not found");
			}
			
		}
		
		sc.close();
		for (int i = 0; i< printers.length; i++) {
			printers[i].flush();
		}
	}

	// Takes all the organized files and serializes them into binary files 
	public static void do_part2() {
	
		Scanner sc = null;
		PrintWriter semFile = null;
		PrintWriter masterFile = null;
		ObjectOutputStream oos = null;
		ObjectOutputStream moos = null;
		String hold;
		String newFile;
		Book temp;
		
		try {
			semFile = new PrintWriter(new File("semantic_error_file.txt"));
			masterFile = new PrintWriter(new File("good_books.csv"));
			moos = new ObjectOutputStream(new FileOutputStream("good_books.csv.ser"));
		}
		catch (IOException e) {
			System.out.println("Wrong");
		}
		
		
		for (int i = 0; i < listTypes.length - 1; i++) {
			try {
				sc= new Scanner(new FileInputStream(listTypes[i]));
				newFile = listTypes[i] + ".ser";
				oos = new ObjectOutputStream(new FileOutputStream(newFile));
				
				
				while (sc.hasNextLine()) {
					String [] verif;
					try { 
					hold = sc.nextLine();
					if (hold.charAt(0) == '"') {
						String templine = hold;
						verif = new String[6];
						for (int j = 1; j < hold.length(); j++) {
							if (hold.charAt(j) == '"') {
								verif[0] = hold.substring(1,j);
								hold = hold.substring(j+2);
								String[] hold1 = hold.split(",");
								for (int k = 0; k < hold1.length; k++) {
									verif[k+1] = hold1[k];
								}
							}
						}
						hold = templine;
					}
					else {
						verif = hold.split(",");
					}

					if (verif[3].length()  == 10) {
						valISBN10(verif, listTypes[i], hold);
					}
					else if (verif[3].length() == 13) {
						valISBN13(verif, listTypes[i], hold);
					}
					else {
						throw (new BadIsbn10Exception(listTypes[i], hold));
					}
					
					
					valPrice(verif, listTypes[i], hold);
					valYear(verif, listTypes[i], hold);
					
					temp = new Book(verif);
					bookCount[i]++;
					counttt++;
					
					masterFile.println(hold);
					masterFile.flush();
					moos.writeObject(temp);
					moos.flush();
					oos.writeObject(temp);
					oos.flush();
				
				
				
				
			}
			catch (FileNotFoundException e) {
				System.out.println("Not found");
			}
			catch (IOException e) {
				System.out.println("IO exception");
			} 
			catch (BadIsbn10Exception e) {
				semFile.println(e.getMessage());
				semFile.flush();
				continue;
			}
			catch (BadIsbn13Exception e) {
				semFile.println(e.getMessage());
				semFile.flush();
				continue;
			}
			catch (BadPriceException e) {
				semFile.println(e.getMessage());
				semFile.flush();
				continue;
			}
			catch (BadYearException e) {
				semFile.println(e.getMessage());
				semFile.flush();
				continue;
			}
			
			
			
		}
	}
			catch (FileNotFoundException e) {
				System.out.println("Not Found");
			}
			catch (IOException e) {
				System.out.println("IO exception");
			} 
		}
	}
	
	// Interactive interface for user 
	public static void do_part3() {
		String fileName;
		Scanner key = new Scanner(System.in);
		listOIS = new ObjectInputStream[listTypes.length - 1];
		try {
			for (int i = 0; i < listTypes.length - 1; i++) {
				fileName = listTypes[i] + ".ser";
				listOIS[i] = new ObjectInputStream(new FileInputStream(fileName));
			}
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		catch (IOException e) {
			System.out.println("IOException");
		}
		System.out.println(counttt);
		Book[] cartoonslist = new Book[bookCount[0]];
		Book[] collectslist = new Book[bookCount[1]];
		Book[] moviesTVlist = new Book[bookCount[2]];
		Book[] musicRadlist = new Book[bookCount[3]];
 		Book[] nostalgilist = new Book[bookCount[4]];
		Book[] oldTimeRlist = new Book[bookCount[5]];
		Book[] sportsMelist = new Book[bookCount[6]];
		Book[] trainsPllist = new Book[bookCount[7]];
		Book[][] library = {cartoonslist, collectslist, moviesTVlist, musicRadlist, nostalgilist, oldTimeRlist, sportsMelist, trainsPllist};
 		
		for (int i = 0; i < bookCount.length; i++) {
			try {
				int count = 0;
				while (true) {
					Object obj = listOIS[i].readObject();
					library[i][count] = (Book) obj;
					count++;
				}
			}
			catch (EOFException e) {
				
			}
			catch (ClassNotFoundException e) {
				
			}
			catch (IOException e) {
				
			}
		}
		
		
		String choice;
		int show = 0;
		
		do {
			System.out.println("------------------------------------");
			System.out.println("\t" + "\t" + "Main Menu" + "\t" + "\t");
			System.out.println("------------------------------------");
			System.out.println("v : View the selected file: " + listTypes[show] + ".ser" + "\t" + "(" + bookCount[show] +" records)");
			System.out.println("s : Select a file to view");
			System.out.println("x : Exit");
			System.out.println("------------------------------------");
			System.out.println();
			System.out.print("Enter Your Choice: ");
			
			boolean wait = key.hasNext();
			choice = key.nextLine();
			
			
			if (choice.equalsIgnoreCase("v")) {
				
				
				int tracker=0;
				boolean keep = true;	
				int n = 0;
				int s = 0;
				int e = 0;
				while(keep) {
					
				System.out.println("Please enter how many books you would like to see at a time");
				
				int input = key.nextInt();
				
				if(input ==0) {
					break;
				}
				else {
					
					if(input>0) {
						
						n += input;
						
						for(int i=s;i<s+input;i++) {
							System.out.println(library[show][i]);
							tracker=i;
						}
						
						s=tracker;
						
						System.out.println("");
					}
					
					
					
					if(input<0) {
						
						n+=input;
						
						for(int i=s;i>s+input;i--) {
							System.out.println(library[show][i]);
							tracker=i;
						}
						s=tracker;
						
					}
					
				}
				
				
			}
				
				
				
			}
			
			if (choice.equalsIgnoreCase("s")) {
				System.out.println("---------------------------------");
				System.out.println("\t" + "\t" + "File Sub-Menu" + "\t" + "\t");
				System.out.println("---------------------------------");
				System.out.println(" 1 | " + listTypes[0] + ".ser" + "\t" + "\t" + "\t" + "(" + bookCount[0] +" records)");
				System.out.println(" 2 | " + listTypes[1] + ".ser" + "\t" + "\t" + "(" + bookCount[1] +" records)");
				System.out.println(" 3 | " + listTypes[2] + ".ser" + "\t" + "\t" + "\t" + "(" + bookCount[2] +" records)");
				System.out.println(" 4 | " + listTypes[3] + ".ser" + "\t" + "\t" + "\t" + "(" + bookCount[3] +" records)");
				System.out.println(" 5 | " + listTypes[4] + ".ser" + "\t" + "\t" + "(" + bookCount[4] +" records)");
				System.out.println(" 6 | " + listTypes[5] + ".ser" + "\t" + "\t" + "(" + bookCount[5] +" records)");
				System.out.println(" 7 | " + listTypes[6] + ".ser" + "\t" + "\t" + "(" + bookCount[6] +" records)");
				System.out.println(" 8 | " + listTypes[7] + ".ser" + "\t" + "\t" + "(" + bookCount[7] +" records)");
				System.out.println(" 9 | Exit ");
				System.out.println("---------------------------------");
				System.out.print("Enter your choice: ");
				if (!(key.hasNextInt())){
					System.out.println("Invalid option, returning to menu");
					continue;
				}
				int opt = key.nextInt();
				
				switch (opt) {
				case 1:
					show = opt - 1;
					continue;
				case 2:
					show = opt - 1;
					continue;
				case 3: 
					show = opt - 1;
					continue;
				case 4:
					show = opt - 1;
					continue;
				case 5:
					show = opt - 1;
					continue;
				case 6:
					show = opt - 1;
					continue;
				case 7:
					show = opt - 1;
					continue;
				case 8:
					show = opt - 1;
					continue;
				case 9:
					continue;
				default:
					System.out.println("Invalid option, returning to menu");
					continue;
				}
			}
			
			if (choice.equalsIgnoreCase("x")) {
				break;
			}
			
			else {
				System.out.println("Choice not available, please retry!");
			}
			
		} while (!(choice.equalsIgnoreCase("x")));
		
		System.out.println("Program will now close!");
		key.close();
		System.exit(0);
		
		
	}

	// Checks if the isbn fits the ISBN standards for (10)
	public static void valISBN10(String[] x, String file, String record) throws BadIsbn10Exception{ // validates isbn10
		

		int sum = 0;
		int digit;
		try {
			for (int i = 0; i < 10; i++) {
				digit= Integer.parseInt(String.valueOf(x[3].charAt(i)));
				
				sum+=digit*(10-i);
			}
			if ((sum % 11) == 0) {
				return;
			}
			else
				throw (new BadIsbn10Exception(file, record));
			}
		catch (NumberFormatException e) {
			throw (new BadIsbn10Exception(file, record));
		}
		
	}
	
	// Checks if the isbn fits the ISBN standards for (13)
	public static void valISBN13(String[] x, String file, String record) throws BadIsbn13Exception{ // validates isbn13
		int sum = 0;

		try {
			for (int i = 0; i < 13; i++) {
				if (i % 2 == 0) { //0th, 2nd, 4th and so on index 
					sum += Integer.parseInt(String.valueOf(x[3].charAt(i))); 
				}
				else {
					sum += 3 * Integer.parseInt(String.valueOf(x[3].charAt(i)));
				}
			}
			if ((sum % 10) == 0) {
				return;
			}
			else 
				throw (new BadIsbn13Exception(file, record));
		}	
		catch (NumberFormatException e) {
			throw (new BadIsbn13Exception(file, record));
		}
	}
	
	// validates the price 
	public static void valPrice(String[] x, String file, String record) throws BadPriceException{ // validates price
		double price = Double.parseDouble(x[2]);
		if (price < 0) {
			throw (new BadPriceException(file, record));
		}
		return;
	}
	
	// Validates the year 
	public static void valYear(String[] x, String file, String record) throws BadYearException { // validates year
		int year = Integer.parseInt(x[5]);
		if ((year < 1995) || (year > 2010)) {
			throw (new BadYearException(file, record));
		}
		return;
	}
}
