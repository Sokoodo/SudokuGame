package com.lodz.sudoku.model;

import java.io.Serializable;

/**
 * This interface has the job to be the parent to all
 * the classes whose jobs are to solve the sudoku.
 */
public interface SudokuSolver extends Serializable {

    /**
     * Each class that implements this interface has to have
     * this method to solve the sudoku.
     * @param board is the sudoku board
     * @param row is the specific row of the board
     * @param col is the specific column of the board
     * @return true if you can solve it
     */
    boolean solve(SudokuBoard board, int row, int col);

}
