import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException; 
import java.util.ArrayList;
import java.util.HashSet;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * This is an application designed to go through a list of books and identify the correct genre for each book.
 * The genre is determined by analyzing the description of each book and calculating a score based on the presence of certain genre-specific keywords.
 */
public class IdentifyCorrectGenre {
	private static ArrayList<Genre> genreList = new ArrayList<Genre>();
	public static ArrayList<Book> bookList = new ArrayList<Book>();
	
	/**
	 * Main method: accepts filenames as command line arguments and calls necessary methods
	 */
	public static void main(String[] args) throws InterruptedException {  
		//process both files based on provided arguments and build the lists of Genres and Books
		try {
			processCSV(args[1]);
			processJSON(args[0]);
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		
		//call method to build genre scores for each book
		buildScores();
		
		//iterate through the list of books
		for (Book b : bookList) {
			//print the title
			System.out.println(b.getTitle());
			//get the top three genres for this Book object
			ArrayList<Score> currentBookResults = b.getFirstThreeScores();
			
			//iterate through the genres
			for (int i = 0; i < currentBookResults.size(); i++) {
				//control branch to improve output readability
				if (i < currentBookResults.size() - 1) {
					//print the genre name and the score for this particular Book
					System.out.println(currentBookResults.get(i).getGenre() + ", " + currentBookResults.get(i).getScore());
				} else {
					//do the same and add a newline as this will be the last entry for this Book
					System.out.println(currentBookResults.get(i).getGenre() + ", " + currentBookResults.get(i).getScore() + "\n");
				}
			}
		}
	}


	/**
	 * Method accepts the fileName from the main method, opens the csv file and processes the genre, keywords and points accordingly
	 * @param fileNameCSV path to CSV.
	 */
	private static void processCSV(String fileNameCSV) {
		if (fileNameCSV == null || fileNameCSV.length() == 0) {
			System.err.println("Invalid CSV filename provided.");
		}

		try {
			BufferedReader csvReader = new BufferedReader(new FileReader(fileNameCSV));
			HashSet<String> uniqueGenres = new HashSet<String>();
			String newLine;
			csvReader.readLine(); //skip the header

			//iterate through every remaining line of the file
			while ((newLine = csvReader.readLine()) != null) {
				//split each line by commas (since this is a csv)
				String[] lineArray = newLine.split(",");

				Genre lineGenre;

				//if the genre is not present in our set
				if (uniqueGenres.add(lineArray[0])) {
					lineGenre = new Genre(lineArray[0]); //instantiate a new Genre object
					//add the keywords and points from this line to the genre's keyword,point HashMap
					lineGenre.getKeywords().put(lineArray[1].trim(), Integer.valueOf(lineArray[2].trim()));
					genreList.add(lineGenre);
				}

				//else add the keywords and points of this line to the corresponding Genre object in our set
				else {
					lineGenre = genreList.get(getGenre(lineArray[0]));
					lineGenre.getKeywords().put(lineArray[1].trim(), Integer.valueOf(lineArray[2].trim()));
				}

			}
			csvReader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method accepts the fileName from the main method, opens the json file of books and processes each book
	 * @param fileNameJSON path to JSON.
	 * @throws ParseException 
	 * @throws IOException 
	 */
	private static void processJSON(String fileNameJSON) throws IOException, ParseException {
		if (fileNameJSON == null || fileNameJSON.equals("")) {
			System.err.println("Invalid JSON filename provided.");
		}

		//instantiate objects to read file and parse JSON
		JSONParser jsParser = new JSONParser();
		FileReader reader = new FileReader(fileNameJSON);

		//parse JSON object into JSONArray
		JSONArray jsList = (JSONArray) jsParser.parse(reader);


		//iterate through JSONArray
		for (int i = 0; i < jsList.size(); i++) {
			//extract JSON object
			JSONObject currentObject = (JSONObject) jsList.get(i);
			//extract attributes and instantiate new Book object
			Book newBook = new Book((String) currentObject.get("title"), (String) currentObject.get("description"));
			//add Book object to list of Books
			IdentifyCorrectGenre.bookList.add(newBook);
		}
	}


	/**
	 * Helper method to iterate through list of existing Genre objects and return the index of the corresponding Genre
	 * @param genreName string of given genre name
	 * @return index value for genre.
	 */
	private static int getGenre(String genreName) {
		if (genreName == null || genreName.equals("")) {
			System.err.println("Invalid Genre provided in csv file.");
		}

		//iterate through list of Genre objects
		for (int i = 0; i < genreList.size(); i++) {
			Genre currentGenre = genreList.get(i);
			//compare the name of the genre we are looking for with the name of this genre
			if (genreName.equals(currentGenre.getName())) {
				return i; //return the index
			}
		}

		System.err.println("Genre exists in Set but not in List.");
		return -1;
	}

	/**
	 * Helper method iterates through each Book, and each Genre, and calls the method from Book class to output result
	 */
	private static void buildScores() {
		for (Book b : bookList) {
			for (Genre g : genreList) {
				b.generateScore(g);
			}
		}
	}
}