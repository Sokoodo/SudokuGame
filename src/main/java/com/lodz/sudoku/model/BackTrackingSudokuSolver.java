package com.lodz.sudoku.model;

import com.lodz.sudoku.exceptions.GetterException;
import com.lodz.sudoku.exceptions.SetterException;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * This is a subclass of SudokuSolver and its aim is to solve a sudoku using the
 * backtracking algorithm.
 */
public class BackTrackingSudokuSolver implements SudokuSolver {

    private static final Logger logger = Logger.getLogger(BackTrackingSudokuSolver.class.getName());

    /**
     * This variable contains the maximum value a number can reach in a sudoku.
     */
    private static final int MAX_N_SUDOKU_BOARD = 9;

    /**
     * This variable contains the maximum value of the range.
     */
    private static final int MAX_N_RANGE_SUDOKU = 10;

    /**
     * This method has the job to solve the sudoku using the
     * backtracking algorithm.
     * @param board is the sudoku board
     * @param sRow is the specific row of the board
     * @param sCol is the specific column of the board
     * @return true if you can solve it
     */
    @Override
    public boolean solve(final SudokuBoard board,
                         final int sRow, final int sCol) throws SetterException, GetterException {
        int row = sRow;
        int col = sCol;
        List<Integer> intList = IntStream.range(1, MAX_N_RANGE_SUDOKU)
                .boxed().collect(Collectors.toList());
        Collections.shuffle(intList);
        if (row == MAX_N_SUDOKU_BOARD - 1 && col == MAX_N_SUDOKU_BOARD) {
            return true;
        }
        if (col == MAX_N_SUDOKU_BOARD) {
            row++;
            col = 0;
        }
        if (board.getNumber(row, col) != 0) {
            return solve(board, row, col + 1);
        }
        for (int i = 0; i < MAX_N_SUDOKU_BOARD; i++) {
            if (board.isSafe(board.getGrid(), row, col, intList.get(i))) {
                board.setNumber(row, col, intList.get(i));
                if (solve(board, row, col + 1)) {
                    return true;
                }
            }
            board.setNumber(row, col, 0);
        }
        return false;
    }

}
