package sudoku;

public class MySolver implements SudokuSolver {
	private int[][] matrix;
	
	
	public MySolver() {
		matrix = new int[9][9];
	}
	@Override
	public boolean solve() {
		if (isValid()) {
			int[][] copy = new int[9][9];
			for (int r = 0; r < 9; r++) {
				for (int c = 0; c < 9; c++) {
					copy[r][c] = matrix[r][c];
				}
			}
			return solve(0, 0, copy);
		} else {
			return false;
		}
	}

	private boolean solve(int row, int col, int[][] origm) {
		//Om sudokut redan har ett permanent tal på denna ruta
		if (origm[row][col] != 0) {
			if (row == 8 && col == 8) {
				return true;
			} else if (col < 8) {
				return solve(row, col + 1, origm);
			} else {
				return solve(row + 1, 0, origm);
			}
		}
		//Om man kommit till sista rutan
		if (row == 8 && col == 8) {
			for (int i = 1; i <= 9; i++) {
				matrix[row][col] = i;
				if (isValid(row, col)) {
					return true;
				}
			}
			matrix[row][col] = 0;
			return false;
		}
		//Alla andra fall
		for (int i = 1; i <= 9; i++) {
			matrix[row][col] = i;
			if (isValid(row, col)) {
				if (col < 8) {
					if (solve(row, col + 1, origm)) {
						return true;
					}
				} else {
					if (solve(row + 1, 0, origm)) {
						return true;
					}
				}
			}
		}
		matrix[row][col] = 0;
		return false;
	}

	@Override
	public void add(int row, int col, int digit) {
		if (((row < 0 || row > 8) || (col < 0 || col > 8))) {
			throw new IllegalArgumentException("Illegal parameter for row and/or col"); //Kanske vill göra mer personliga exceptions? Kanske olika för row/col och digits?
		} else if ((digit < 0 || digit > 9)) {
			throw new IllegalArgumentException("Illegal value for digit");
		} else {
			matrix[row][col] = digit;
		}

	}

	@Override
	public void remove(int row, int col) {
		matrix[row][col] = 0;

	}

	@Override
	public int get(int row, int col) {
		return matrix[row][col];
	}

	@Override
	public boolean isValid() { 
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				if (!isValid(r, c)) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean isValid(int r, int c) {
		if (matrix[r][c] < 0 || matrix[r][c] > 9) {
			return false;
		} else if (matrix[r][c] != 0) {
			//Kollar om kolumnen följer sudoku-regler
			
			for (int rr = 0; rr < 9; rr++) {
				if (r != rr) {
					if (matrix[r][c] == matrix[rr][c]) {
						return false;
					}
				}
			}
			
			//Kollar om raden följer sudoku-regler
			
			for (int cc = 0; cc < 9; cc++) {
				if (c != cc) {
					if (matrix[r][c] == matrix[r][cc]) {
						return false;
					}
				}
			}
			
			//Kollar om regionen följer sudoku-regler
			
			for (int rr = (r/3)*3; rr < ((r/3)*3+3); rr++) {
				for (int cc = (c/3)*3; cc < ((c/3)*3+3); cc++) {
					if (!(rr == r && cc == c)) {
						if (matrix[rr][cc] == matrix[r][c]) {
							return false;
						}
					}
				}
			}
			
			
		}
		return true;
	}

	@Override
	public void clear() {
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				matrix[r][c] = 0;
			}
		}
	}

	@Override
	public void setMatrix(int[][] m) { //Behöver ge IllegalArgumentException om dimensionerna eller något tal är fel 
		if (m.length <= 9) {
			boolean valid = true;
			for (int r = 0; r < 9; r++) {
				for (int c = 0; c < 9; c++) {
					if (m[r][c] < 0 || m[r][c] > 9) {
						valid = false;
					}
				}
			}
			if (valid) {
				matrix = m;
			}
		}
	}

	@Override
	public int[][] getMatrix() {
		int[][] copy = new int[9][9];
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				copy[r][c] = matrix[r][c];
			}
		}
		Integer[][] copy3 = new Integer[9][9];
		int[][] copy2 = matrix.clone();
		return copy2;
	}

}
