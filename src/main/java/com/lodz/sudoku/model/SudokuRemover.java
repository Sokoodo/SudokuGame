package com.lodz.sudoku.model;

/**
 * This interface has the job to be the parent to all
 * the classes whose jobs are to remove fields from the sudoku.
 */
public interface SudokuRemover {

    /**
     * Each class that implements this interface has to have
     * this method to remove fields from the sudoku.
     */
    void remove();

}
