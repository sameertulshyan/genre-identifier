## Genre Identifier
This application is designed to identify and classify books based on their genre. The application checks for the presence of certain keywords and their point values (based on a csv file that the user must provide), crawls through the text description of each book and determines the genre accordingly.

All book descriptions are run against their title (based on a JSON file that the user must provide), and then each title is printed, along with the top three genres it matches.


## Build Instructions

### Requirements
1. Java 7 or higher
2. JSON Simple (https://code.google.com/archive/p/json-simple/downloads)
3. JUnit (not required to use the application, only for testing) (https://github.com/junit-team/junit5)

In order to run this program:

1. Compile the .java files
2. Run the IdentifyCorrectGenre object file and provide as arguments:
	1. A valid path to the JSON file containing the information for each book and its description
	2. A valid path to the CSV file containing the information for each genre and its keywords

