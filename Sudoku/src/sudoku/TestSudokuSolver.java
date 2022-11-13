package sudoku;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestSudokuSolver {
	private SudokuSolver solver;
	private int[][] matrix;
	
	@BeforeEach
	void setUp() throws Exception {
		solver = new MySolver();
		matrix = new int[9][9];
	}
	
	private void printMatrix(int[][] m) {
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 8; c++) {
				System.out.print(m[r][c] + " ");
			}
			System.out.println(m[r][8] + " ");
		}
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testEmptySudoku() {
		assertTrue(solver.solve(), "Not solved");
		//printMatrix(solver);
	}
	
	@Test
	void testSolvableSudoku() {
		matrix[0][2] = 8;
		matrix[0][5] = 9;
		matrix[0][7] = 6;
		matrix[0][8] = 2;
		matrix[1][8] = 5;
		matrix[2][0] = 1;
		matrix[2][2] = 2;
		matrix[2][3] = 5;
		matrix[3][3] = 2;
		matrix[3][4] = 1;
		matrix[3][7] = 9;
		matrix[4][1] = 5;
		matrix[4][6] = 6;
		matrix[5][0] = 6;
		matrix[5][7] = 2;
		matrix[5][8] = 8;
		matrix[6][0] = 4;
		matrix[6][1] = 1;
		matrix[6][3] = 6;
		matrix[6][5] = 8;
		matrix[7][0] = 8;
		matrix[7][1] = 6;
		matrix[7][4] = 3;
		matrix[7][6] = 1;
		matrix[8][6] = 4;
		solver.setMatrix(matrix);
		//printMatrix(solver.getMatrix());
		assertTrue(solver.solve(), "Not solved");
		//printMatrix(solver);
	}
	
	@Test
	void testUnsolvableSudoku1() {
		solver.add(5, 0, 6);
		solver.add(0, 0, 6);
		assertFalse(solver.solve(), "Did not return as false");
		//printMatrix(solver);
	}
	
	@Test
	void testUnsolvableSudoku2() {
		solver.add(0, 0, 2);
		solver.add(0, 3, 9);
		solver.add(1, 7, 6);
		solver.add(2, 5, 1);
		solver.add(3, 0, 5);
		solver.add(3, 2, 2);
		solver.add(3, 3, 6);
		solver.add(3, 6, 4);
		solver.add(3, 8, 7);
		solver.add(4, 5, 4);
		solver.add(4, 6, 1);
		solver.add(5, 4, 9);
		solver.add(5, 5, 8);
		solver.add(5, 7, 2);
		solver.add(5, 8, 3);
		solver.add(6, 5, 3);
		solver.add(6, 7, 8);
		solver.add(7, 2, 5);
		solver.add(7, 4, 1);
		solver.add(8, 2, 7);
		//printMatrix(solver.getMatrix());
		assertFalse(solver.solve(), "Did not return as false");
		
	}
	@Test
	void testUnsolvableSudoku3() {
		solver.add(0, 1, 1);
		solver.add(0, 2, 2);
		solver.add(0, 3, 3);
		solver.add(0, 4, 4);
		solver.add(0, 5, 5);
		solver.add(1, 0, 6);
		solver.add(2, 0, 7);
		solver.add(3, 0, 8);
		solver.add(4, 0, 9);
		//printMatrix(solver.getMatrix());
		assertFalse(solver.solve(), "Did not return as false");
		//printMatrix(solver.getMatrix());
	}
	
	@Test
	void testAddWithIllegalParameter() {
		//illegal row
		IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class, () -> solver.add(-3, 7, 7), "Did not throw IllegalArgumentException, 1");
		IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class, () -> solver.add(10, 3, 7), "Did not throw IllegalArgumentException, 2");
		IllegalArgumentException exception3 = assertThrows(IllegalArgumentException.class, () -> solver.add(2, -5, 6), "Did not throw IllegalArgumentException, 3");
		IllegalArgumentException exception4 = assertThrows(IllegalArgumentException.class, () -> solver.add(2, 10, 7), "Did not throw IllegalArgumentException, 4");
		IllegalArgumentException exception5 = assertThrows(IllegalArgumentException.class, () -> solver.add(1, 1, -5), "Did not throw IllegalArgumentException, 5");
		IllegalArgumentException exception6 = assertThrows(IllegalArgumentException.class, () -> solver.add(1, 2, 10), "Did not throw IllegalArgumentException, 6");
		
		assertTrue(exception1.getMessage().contains("Illegal parameter for row and/or col"), "Threw wrong argument");
		assertTrue(exception2.getMessage().contains("Illegal parameter for row and/or col"), "Threw wrong argument");
		assertTrue(exception3.getMessage().contains("Illegal parameter for row and/or col"), "Threw wrong argument");
		assertTrue(exception4.getMessage().contains("Illegal parameter for row and/or col"), "Threw wrong argument");
		assertTrue(exception5.getMessage().contains("Illegal value for digit"), "Threw wrong argument");
		assertTrue(exception6.getMessage().contains("Illegal value for digit"), "Threw wrong argument");
	}
	
	@Test
	void testAddandRemove() {
		solver.add(0, 0, 1);
		//printMatrix(solver);
		solver.remove(0, 0);
		boolean notzero = false;
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				if (solver.get(r, c) != 0) {
					notzero = true;
				}
			}
		}
		assertFalse(notzero, "Matrix was not empty");
	}
	
	@Test
	void testGet() {
		solver.add(5, 4, 4);
		assertEquals(4, solver.get(5, 4), "Returned wrong digit");
	}
	
	@Test
	void testClear() {
		solver.add(5, 0, 6);
		solver.add(0, 0, 6);
		solver.clear();
		boolean notzero = false;
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				if (solver.get(r, c) != 0) {
					notzero = true;
				}
			}
		}
		assertFalse(notzero, "Matrix was not empty");
	}
	
	@Test
	void testIfMatrixIsAccesible() {
		solver.add(0, 0, 1);
		int[][] mymatrix = solver.getMatrix();
		mymatrix[0][1] = 2;
		printMatrix(solver.getMatrix());
	}
	
	@Test
	void testIsValid() {
		int[][] m = {
				{1,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0}
		};
		solver.setMatrix(m);
		assertTrue(solver.isValid(), "Wrong, should return true");
		
	}
	
	

}
