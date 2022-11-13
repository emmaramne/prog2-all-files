package textproc;


import java.util.*;


public class GeneralWordCounter implements TextProcessor {
	private Map<String, Integer> words;
	private Set<String> exceptions;
	
	public GeneralWordCounter(Set<String> exc) {
		words = new TreeMap();
		exceptions = exc;
		
	}
	
	public void process(String w) {
		if (exceptions.contains(w)) {
			
		} else if (words.containsKey(w)) {
			int n = (int) words.get(w);
			words.put(w, n + 1);
		} else {
			words.put(w, 1);
		}
		
	}

	
	public void report() {
		/*
		for (String w: words.keySet()) {
			if (words.get(w) >= 200) {
				System.out.println(w + "; " + words.get(w));
			}
			
		}
		*/
		Set<Map.Entry<String, Integer>> wordSet = words.entrySet();
		List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);
		wordList.sort(new WordCountComparator());
		for (int i = 0; i < 100; i++) {
			System.out.println(wordList.get(i).getKey() + "; " + wordList.get(i).getValue());
		}
		
	}
	
	public List<Map.Entry<String, Integer>> getWordList() {
		Set<Map.Entry<String, Integer>> tempSet = words.entrySet();
		List<Map.Entry<String, Integer>> wordList = new ArrayList(tempSet);
		return wordList;
	}

}
