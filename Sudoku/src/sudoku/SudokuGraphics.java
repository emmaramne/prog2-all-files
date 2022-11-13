package sudoku;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

public class SudokuGraphics {
	
	public SudokuGraphics(SudokuSolver solver) {
		SwingUtilities.invokeLater(() -> createWindow(solver, "Sudoku Solver", 100, 300));
	}
	
	
	private void createWindow(SudokuSolver solver, String title, int width, int height) {
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container pane = frame.getContentPane();
		
		JTextField[][] matrix = new JTextField[9][9];
		//northpanel har alla textfields
		JPanel northpanel = new JPanel(new GridLayout(9, 9));
		Dimension dim = new Dimension();
		dim.setSize(35, 35);
		Color lightgreen = new Color(80, 210, 80);
		Color darkgreen = new Color(10, 100, 30);
		
		//Add all squares from sqinline to matrix
		int i = 0;
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				JTextField n = new JTextField();
				n.setHorizontalAlignment(JTextField.CENTER);
				n.setPreferredSize(dim);
				if (isColored(r, c)) {
					n.setBackground(lightgreen);
				} else {
					n.setBackground(darkgreen);
				}
				matrix[r][c] = n;
				northpanel.add(n);
			}
		} 
		
		JPanel buttonpanel = new JPanel();
		JButton solvebutton = new JButton("Solve");
		JButton clearbutton = new JButton("Clear");
		buttonpanel.add(solvebutton);
		solvebutton.addActionListener(e -> solve(matrix, pane, solver));
		buttonpanel.add(clearbutton);
		clearbutton.addActionListener(e -> {
			solver.clear();
			for (int r = 0; r < 9; r++) {
				for (int c = 0; c < 9; c++) {
					matrix[r][c].setText("");
					matrix[r][c].setForeground(Color.black);
				}
			}
		});
		pane.add(northpanel, BorderLayout.CENTER);
		pane.add(buttonpanel, BorderLayout.SOUTH);
		
		
		frame.pack();
		frame.setVisible(true);
	}
	
	private void solve(JTextField[][] matrix, Container pane, SudokuSolver solver) {
		boolean illegal = false;
		Color grey = new Color(170, 170, 170);
		Color darkgrey = new Color(120, 120, 120);
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				if (!matrix[r][c].getText().contentEquals("")) {
					try {
						int digit = Integer.parseInt(matrix[r][c].getText());
						if (digit < 1 || digit > 9) {
							JOptionPane wrongdigit = new JOptionPane();
							wrongdigit.showMessageDialog(pane, "Found digit/s with values outside 1-9", ":(", JOptionPane.PLAIN_MESSAGE);
							illegal = true;
							break;
						} else {
							solver.add(r, c, digit);
						}
					} catch (NumberFormatException nfe) {
						JOptionPane notdigit = new JOptionPane();
						notdigit.showMessageDialog(pane, "Found non-integer values in boxes", ":(", JOptionPane.PLAIN_MESSAGE);
						illegal = true;
						break;
					}
					
				}
				
				
			}
			if (illegal) {
				break;
			}
		}
		if (!illegal) {
			if (solver.solve()) {
				for (int r = 0; r < 9; r++) {
					for (int c = 0; c < 9; c++) {
						if (matrix[r][c].getText().contentEquals("")) { //funkade ej
							if (isColored(r, c)) {
								matrix[r][c].setForeground(darkgrey);
							} else {
								matrix[r][c].setForeground(grey);
							}
							
							matrix[r][c].setText(solver.get(r, c) + "");
						} else {
							matrix[r][c].setText(solver.get(r, c) + "");
						}
					}
				}
			} else {
				JOptionPane notsolvable = new JOptionPane();
				notsolvable.showMessageDialog(pane, "Sudoku is unsolvable", ":(", JOptionPane.PLAIN_MESSAGE);
			}
			
		}
		solver.clear();
	}
	
	private boolean isColored(int r, int c) {
		if((r >= 0 && r < 3)) {
			if ((c >= 0 && c < 3) || (c >= 6 && r < 9)) {
				return true;
			}
		}
		if((r >= 3 && r < 6 && c >= 3 && c < 6)) {
			return true;
		}
		if((r >= 6 && r < 9)) {
			if ((c >= 0 && c < 3) || (c >= 6 && r < 9)) {
				return true;
			}
		}
		return false;
	}
}
