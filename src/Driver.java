import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Driver {
	public static void main(String arg[]) throws FileNotFoundException {

		Scanner s = new Scanner(System.in);
		System.out.println("Welcome to Search Engine\n");
		String input = "";
		String choice = "";
		String nextResults = "";
		int numResults = 0;
		int multiplier = 0;
		
		do {
			System.out.println("Please type word(s) or phrase(s) that you want to search: \n");
			input = s.nextLine();
			System.out.println("\nHow many result(s) would you like to see? \n");
			numResults = s.nextInt();
			s.nextLine();
			do {
				//remove stop words
				StopWords sw = new StopWords();
				ArrayList<String> inputRemoveSW = sw.removeStopWords(input);
				//remove unnecessary part of words by stemming
				Stemmer st = new Stemmer();
				ArrayList<String> inputStem = st.wordsStemming(inputRemoveSW);
//				for (String in : inputStem) {
//					System.out.println(in);
//				}
				//get input words frequency
				FreqList f = new FreqList();
				f.getFreqList(inputStem);
				//sort results
				LinkedHashMap<String, String> sortedResults = f.sortList(numResults, multiplier);
				if (FreqList.hasResults) {
					System.out.println();
					System.out.println(sortedResults.size() + " related results: \n");
					//print search results in pairs
					sortedResults.entrySet().forEach(entry -> {
						System.out.println(entry.getKey() + "\n" + entry.getValue().replace(".txt", "") + "\n");
					});
					System.out.println("Would you like to see the next page? Y/N\n");
					nextResults = s.nextLine();
					if (nextResults.equals("Y")) {
						multiplier++;
					}
				} else {
					System.out.println("\nNo more results found\n");
					nextResults = "N";
				}
			} while (nextResults.equals("Y"));
			System.out.println("\nTo continue search type 1. Otherwise, type 2 to EXIT\n");
			choice = s.nextLine();
			System.out.println();
		} while (choice.equals("1"));
		s.close();

	}
}
