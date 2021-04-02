import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FreqList {

	HashMap<String, Integer> freqList;
	LinkedHashMap<String, String> originList;
	protected static boolean hasResults = true;

	public FreqList() {
		this.freqList = new HashMap<>();
		this.originList = new LinkedHashMap<>();
	}

	public void getFreqList(ArrayList<String> query) throws FileNotFoundException {

		File folder = new File("..\\Web Search Engine\\Web Pages");
		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) {
			if (file.isFile()) {
				int occurrence = 0;
				TST<Integer> st = new TST<Integer>();
				StringBuilder sb = new StringBuilder();
				Scanner s = new Scanner(new FileReader(file));
				while (s.hasNextLine()) {
					sb.append(s.nextLine());
				}
				String text = sb.toString();
				StringTokenizer stk = new StringTokenizer(text);
				while (stk.hasMoreTokens()) {
					String token = stk.nextToken().toLowerCase();
					if (st.contains(token)) {
						st.put(token, st.get(token) + 1);
					} else {
						st.put(token, 1);
					}
				}
				for (String word : query) {
					if (st.contains(word)) {
						occurrence += st.get(word);
					}
				}
				freqList.put(file.getName(), occurrence);
//				System.out.println(freqList);
			}
		}
//		freqList.entrySet().forEach(entry -> {
//			System.out.println(entry.getKey() + "\n" + entry.getValue() + "\n");
//		});
	}

	public LinkedHashMap<String, String> sortList(int numResult, int multiplier) throws FileNotFoundException {

		ArrayList<Integer> list = new ArrayList<>();
		LinkedHashMap<String, Integer> sortedFreqList = new LinkedHashMap<>();
		ArrayList<String> webpage = new ArrayList<>();
		ArrayList<String> url = new ArrayList<>();
		LinkedHashMap<String, String> completeList = new LinkedHashMap<>();
		for (Map.Entry<String, Integer> entry : freqList.entrySet()) {
			list.add(entry.getValue());
		}
		Collections.sort(list, Collections.reverseOrder());
		for (int num : list) {
			for (Entry<String, Integer> entry : freqList.entrySet()) {
				if (entry.getValue().equals(num)) {
					sortedFreqList.put(entry.getKey(), num);
				}
			}
//			System.out.println(sortedFreqList);
		}
//		sortedFreqList.entrySet().forEach(entry -> {
//			System.out.println(entry.getKey() + "\n" + entry.getValue() + "\n");
//		});
		for (String key : sortedFreqList.keySet()) {
			webpage.add(key);
		}
		getOriginList();
		for (String page : webpage) {
			url.add(originList.get(page));
		}
		int upperBound = numResult * (multiplier + 1);
		if (upperBound <= webpage.size()) {
			for (int i = numResult * multiplier; i < upperBound; i++) {
				completeList.put(url.get(i), webpage.get(i));
			}
		} else {
			for (int i = numResult * multiplier; i < webpage.size(); i++) {
				completeList.put(url.get(i), webpage.get(i));
			}
		}
		if (completeList.isEmpty()) {
			hasResults = false;
		}
		return completeList;
	}

	private void getOriginList() throws FileNotFoundException {

		ArrayList<String> pageList = new ArrayList<>();
		ArrayList<String> urlList = new ArrayList<>();
		Scanner page = new Scanner(new File("Pages.txt"));
		while (page.hasNextLine()) {
			pageList.add(page.nextLine());
		}
		Scanner URL = new Scanner(new File("URLs.txt"));
		while (URL.hasNextLine()) {
			urlList.add(URL.nextLine());
		}
		Iterator<String> itr = urlList.listIterator();
		for (String p : pageList) {
			if (itr.hasNext()) {
				originList.put(p, itr.next());
			}
		}
	}
}
