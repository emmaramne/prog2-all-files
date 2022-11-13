package textproc;

import java.util.*;
import java.util.Map.Entry;

public class WordCountComparator implements Comparator<Map.Entry<String, Integer>>	{

	@Override
	public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
		if (o2.getValue().equals(o1.getValue())) {
			return o1.getKey().compareToIgnoreCase(o2.getKey());
		} else {
			return o2.getValue() - o1.getValue();
		}
		
	}

}
