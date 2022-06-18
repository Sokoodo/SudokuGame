package com.lodz.sudoku;

import com.lodz.sudoku.exceptions.GetterException;
import com.lodz.sudoku.exceptions.SetterException;
import com.lodz.sudoku.model.BackTrackingSudokuSolver;
import com.lodz.sudoku.model.SudokuBoard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SudokuSolverTest {
/*
    private boolean check(int row, int column, SudokuBoard board) throws SetterException {
        if (board == null) {
            throw new NullPointerException("The board is null");
        }
        if (row < 0 || row > 8) {
            throw new IndexOutOfBoundsException("The row is not between 0 and 8");
        }
        if (column < 0 || column > 8) {
            throw new IndexOutOfBoundsException("The column is not between 0 and 8");
        }
        return board.getBox(row, column).verify() &&
                board.getRow(row).verify() && board.getColumn(column).verify();
    }

    @Test
    void solveSudokuTest() throws GetterException, SetterException {
        SudokuBoard board = new SudokuBoard(new BackTrackingSudokuSolver());
        board.solveGame();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                assertTrue(check(i, j, board) && board.getNumber(i, j) != 0);
            }
        }
    }

    @Test
    void noRepeatsTest() {
        SudokuBoard sudoku = new SudokuBoard(new BackTrackingSudokuSolver());
        SudokuBoard sudoku2 = new SudokuBoard(new BackTrackingSudokuSolver());
        sudoku.solveGame();
        sudoku2.solveGame();
        assertNotEquals(sudoku, sudoku2);
    }
*/
}
