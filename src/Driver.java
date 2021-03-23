import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
	public static void main(String arg[]) {
		Scanner s = new Scanner(System.in);
		System.out.println("Welcome to Search Engine");
		String input = "";
		String choise = "";
		do {
		System.out.println("Please type words or phrases that you want to search:");
		input = s.nextLine();
		ArrayList<String> inputRemoveSW = StopWords.removeStopWords(input);
		ArrayList<String> inputStem = Stemmer.wordsStemming(inputRemoveSW);
		for (String in : inputStem) {
			System.out.println(in);
		}
		System.out.println("To continue search type 1. Otherwise, type 2 to EXIT");
		choise = s.nextLine();
		} while(choise.equals("1"));
		
	}
}
