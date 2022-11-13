package textproc2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import java.awt.Color.*;
import javax.swing.*;

import textproc.GeneralWordCounter;

public class BookReaderController {

	public BookReaderController(GeneralWordCounter counter) {
		SwingUtilities.invokeLater(() -> createWindow(counter, "BookReader", 100, 300));
	}
	
	private void createWindow(GeneralWordCounter counter, String title, int width, int height) {
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container pane = frame.getContentPane();
		
		
		//D4
		SortedListModel<Map.Entry<String, Integer>> sortedlist = new SortedListModel<>(counter.getWordList());
		JList<Map.Entry<String, Integer>> list = new JList<>(sortedlist);
		JScrollPane scrollpane = new JScrollPane(list);
		pane.add(scrollpane);
		
		//D5-D6
		JPanel southpanel = new JPanel();
		JRadioButton alpha = new JRadioButton("Alphabetic");
		JRadioButton freq = new JRadioButton("Frequency");
		ButtonGroup group = new ButtonGroup();
		group.add(alpha);
		group.add(freq);
		southpanel.add(alpha);
		alpha.addActionListener(e -> {
			sortedlist.sort((e1, e2) -> e1.getKey().compareTo(e2.getKey()));
		});
		southpanel.add(freq);
		freq.addActionListener(e -> {
			sortedlist.sort((e1, e2) -> e2.getValue() - e1.getValue());
		}
		);
		freq.doClick();
		
		pane.add(southpanel, BorderLayout.SOUTH);
		
		//D7
		JPanel northpanel = new JPanel();
		JTextField searchbar = new JTextField(10);
		JButton findbutton = new JButton("Find");
		findbutton.addActionListener(e -> {
			boolean found = false;
			String word = searchbar.getText();
			word = word.trim();
			word = word.toLowerCase();
			for (int i = 0; i < sortedlist.getSize(); i++) {
				if (sortedlist.getElementAt(i).getKey().equals(word)) {
					list.setSelectedValue(sortedlist.getElementAt(i), true);;
					found = true;
				}
			}
			
			if (!found) {
				JOptionPane notfound = new JOptionPane();
				//notfound.showMessageDialog(pane, "Could not find word");
				notfound.showMessageDialog(pane, ":(", "Word not found", JOptionPane.PLAIN_MESSAGE);
			}
		});
		northpanel.add(searchbar);
		northpanel.add(findbutton);
		pane.add(northpanel, BorderLayout.NORTH);
		frame.getRootPane().setDefaultButton(findbutton);
		
		frame.pack();
		frame.setVisible(true);
		
	}
	
}
