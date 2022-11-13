package sudoku;

public interface SudokuSolver {
	/**
	 * Solves sudoku recursively, returns true if solvable, false if unsolvable. 
	 * @return whether sudoku is solvable
	 */
	boolean solve();

	/**
	 * Puts digit in the box row, col.
	 * 
	 * @param row   The row
	 * @param col   The column
	 * @param digit The digit to insert in box row, col
	 * @throws IllegalArgumentException if row, col or digit is outside the range
	 *                                  [0..9]
	 */
	void add(int row, int col, int digit);

	/**
	 * Removes the digit in row, col and replaces it with 0
	 * @param row   The row
	 * @param col   The column
	 */
	void remove(int row, int col);

	/**
	 * Returns the digit in row, col
	 * @param row   The row
	 * @param col   The column
	 * @return the digit on row, col
	 */
	int get(int row, int col);

	/**
	 * Checks that all filled in digits follows the the rules of sudoku.
	 * @return whether the current matrix follows the rules of sudoku
	 */
	boolean isValid();

	/**
	 * Removes all non-zero digits from the sudoku and replaces them with 0.
	 */
	void clear();

	/**
	 * Fills the grid with the digits in m. The digit 0 represents an empty box.
	 * 
	 * @param m the matrix with the digits to insert
	 * @throws IllegalArgumentException if m has the wrong dimension or contains
	 *                                  values outside the range [0..9]
	 */
	void setMatrix(int[][] m);

	/**
	 * Returns the matrix representing the sudoku-board.
	 * @return the matrix representing the sudoku-board
	 */
	int[][] getMatrix();
}
