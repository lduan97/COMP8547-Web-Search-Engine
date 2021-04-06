package searchengine;
import java.util.ArrayList;
import org.tartarus.snowball.ext.englishStemmer;

public class Stemmer {
	
	/**
	 * This method will stem the input from user
	 * @param query User's input
	 * @return A list has stemmed words
	 */
	public ArrayList<String> wordsStemming(ArrayList<String> query) {
		
		ArrayList<String> wordsStemming = new ArrayList<>();
		for (String word : query) {
			englishStemmer es = new englishStemmer();
			es.setCurrent(word);
			es.stem();
			wordsStemming.add(es.getCurrent());
		}
		return wordsStemming;
		
	}
}
