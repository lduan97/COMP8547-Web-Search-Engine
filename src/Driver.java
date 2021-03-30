import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Driver {
	public static void main(String arg[]) throws FileNotFoundException {

		Scanner s = new Scanner(System.in);
		System.out.println("Welcome to Search Engine\n");
		String input = "";
		String choise = "";
		int numResults = 0;
		do {
			System.out.println("Please type words or phrases that you want to search: \n");
			input = s.nextLine();
			System.out.println("\nHow many results would you like to see? \n");
			numResults = s.nextInt();
			s.nextLine();
			StopWords sw = new StopWords();
			ArrayList<String> inputRemoveSW = sw.removeStopWords(input);
			Stemmer st = new Stemmer();
			ArrayList<String> inputStem = st.wordsStemming(inputRemoveSW);
//			for (String in : inputStem) {
//				System.out.println(in);
//			}
			FreqList f = new FreqList();
			f.getFreqList(inputStem);
			LinkedHashMap<String, String> sortedResults = f.sortList(numResults);
			System.out.println();
			System.out.println(numResults + " most related results: \n");
			sortedResults.entrySet().forEach(entry -> {
				System.out.println(entry.getKey() + "\n" + entry.getValue().replace(".txt", "") + "\n");
			});
			System.out.println("To continue search type 1. Otherwise, type 2 to EXIT");
			choise = s.nextLine();
		} while (choise.equals("1"));

	}
}
