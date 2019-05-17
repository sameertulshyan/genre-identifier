import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;

/**
 * Class models a Book object.
 */
public class Book implements Comparable<Object> {
	private String title;
	private String description;

	private ArrayList<Score> genreScores;

	public Book(String newTitle, String newDescription) {
		title = newTitle;
		description = newDescription;
		genreScores = new ArrayList<Score>();
	}

	public String getTitle() {
		return this.title;
	}

	public String getDescription() {
		return this.description;
	}

	public ArrayList<Score> getGenreScores() {
		return this.genreScores;
	}

	/**
	 * Method generates a score for a given Genre based on the presence of the Genre's keyword's in the Book's description
	 */
	public void generateScore(Genre givenGenre) {
		//count the presence of a certain keyword
		int hits = 0;

		//instantiate a list to track keywords found in this description
		ArrayList<String> keywordList = new ArrayList<String>();

		//get the list mapping String keywords to Integer scores from the Genre object
		HashMap<String, Integer> keyWordScores = givenGenre.getKeywords();

		//iterate through the keywords for this Genre
		for (String keyword : keyWordScores.keySet()) {
			//get the description for this Book
			String descriptionText = this.description;

			//index pointer
			int left = 0;

			//while the description contains instances of this keyword
			while (left != -1) {
				//get the next instance and store it in our pointer variable
				left = descriptionText.indexOf(keyword, left);

				if (left != -1) {
					// check the keyword list to see if this is a new keyword for this Book 
					if (!(keywordList.contains(keyword))) {
						keywordList.add(keyword); //add it
					}
					//update count of instances
					hits++;
					//move the index pointer to after this instance so we don't loop indefinitely 
					left += keyword.length();
				}
			}
		}



		//track if genre is present in Results list
		boolean genrePresent = false;

		//Sum value.
		int totalValue = 0;

		for (int i = 0; i < keywordList.size(); i++) {
			totalValue += keyWordScores.get(keywordList.get(i));
		}

		int mean = 0;
		//if we found any keywords from this genre, use it to determine the average
		if (keywordList.size() > 0) {
			mean = totalValue / keywordList.size();
		}

		//iterate through the scores for this genre for this book
		for (Score score : this.genreScores) {
			//locate scores with the same genre
			if (score.getGenre().equals(givenGenre.getName())) {
				//update the flag
				genrePresent = true;
				//update the score
				score.setScore(hits * mean);
			}
		}

		//check the flag and add a new Score if this is the first score for this genre
		if (!genrePresent) {
			Score newScore = new Score(givenGenre.getName(), hits * mean);
			this.genreScores.add(newScore);
		}

		//Sort list of scores so highest scores are first
		Collections.sort(this.genreScores, Collections.reverseOrder());
	}

	/**
	 * Method returns the first three (highest) scores for this book
	 */
	public ArrayList<Score> getFirstThreeScores() {
		ArrayList<Score> scoresList = new ArrayList<Score>();
		
		//iterate through first three elements of the list of genreScores
		for (int i = 0; i < 3; i++) {
			Score topScore = this.genreScores.get(i);
			//ensure that this is a valid score (handle possible edge case where no matches with any genre)
			if (topScore.getScore() > 0) {
				scoresList.add(topScore);
			}
		}
		return scoresList;
	}

	/**
	 * We define equality as two books with the same title AND description.
	 */
	@Override
	public boolean equals(Object o) {
		if (o != null && o instanceof Book) {
			Book secondBook = (Book) o;
			boolean sameTitle = this.title.equals(secondBook.getTitle());
			boolean sameDescription = this.description.equals(secondBook.getDescription());
			return sameTitle && sameDescription;
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.title, this.description);
	}

	/**
	 * Need to implement compareTo. We define Book comparison as a String comaprison of title.
	 */
	@Override
	public int compareTo(Object o) {
		if (this == o) return 0;

		if (o instanceof Book) {
			Book secondBook = (Book) o;
			return this.title.compareTo(secondBook.title);
		}

		throw new IllegalArgumentException("Invalid Object type provided.");
	}
}
