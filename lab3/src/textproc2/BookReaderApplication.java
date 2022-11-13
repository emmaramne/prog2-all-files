package textproc2;

import java.awt.Container;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JList;
import javax.swing.JScrollPane;

import textproc.GeneralWordCounter;
import textproc.TextProcessor;

public class BookReaderApplication {

	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner scan = new Scanner(new File("undantagsord.txt"));
		Set<String> stopwords = new HashSet<>();
		while (scan.hasNext()) {
			stopwords.add(scan.next());
		}
		GeneralWordCounter counter = new GeneralWordCounter(stopwords);
		scan.close();
		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning
		while (s.hasNext()) {
			String word = s.next().toLowerCase();
			counter.process(word);
		}

		s.close();
		
		//counter.report();
		new BookReaderController(counter);
		
		
		
		

	}

}
