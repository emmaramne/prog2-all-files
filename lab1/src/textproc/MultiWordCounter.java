package textproc;


import java.util.*;

public class MultiWordCounter implements TextProcessor {
	private Map<String, Integer> words;
	
	public MultiWordCounter(String[] ws) {
		words = new TreeMap<>();
		for (String w: ws) {
			words.put(w, 0);
		}
	}
	
	public void process(String w) {
		if (words.containsKey(w)) {
			int n = (int) words.get(w);
			words.put(w, n + 1);
		}
		
	}

	
	public void report() {
		for (String key: words.keySet()) {
			System.out.println(key + ": " + words.get(key));
		}
 		
	}
	

}
