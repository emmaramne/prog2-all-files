package sudoku;

public class SudokuApplication {

	public static void main(String[] args) {
		SudokuSolver solver = new MySolver();
		
		new SudokuGraphics(solver);

	}

}
