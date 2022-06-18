package com.lodz.sudoku.model;

import com.lodz.sudoku.exceptions.GetterException;
import com.lodz.sudoku.exceptions.SetterException;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class that represent a Sudoku Board and implements
 * Cloneable and Serializable.
 */
public class SudokuBoard implements Cloneable, Serializable {

    private static final Logger logger = Logger.getLogger(SudokuBoard.class.getName());

    /**
     * current grid of this sudoku filled with SudokuFields.
     */
    private SudokuField[][] grid =
            new SudokuField[MAX_N_SUDOKU_BOARD][MAX_N_SUDOKU_BOARD];

    /**
     * sudoku solver for the current sudoku.
     */
    private final SudokuSolver sudokuSolver;

    /**
     * This variable contains the maximum value a number can reach in a sudoku.
     */
    private static final int MAX_N_SUDOKU_BOARD = 9;

    /**
     * This variable contains the maximum value of the number that is used
     * to find the right rows and columns.
     */
    private static final int DIVIDEND_NUMBER = 3;

    /**
     * Initial odd number for the hashcode method.
     */
    private static final int INITIAL_ODD_NUMBER = 17;

    /**
     * Multiplier odd number for the hashcode method.
     */
    private static final int MULTIPLIER_ODD_NUMBER = 37;

    /**
     * Comstructor that initializes the sudokuboard with sudokufields and
     * select a sudokuSolver for it.
     *
     * @param sudokuSolver1 the solver to use
     */
    public SudokuBoard(final SudokuSolver sudokuSolver1) {
        for (int i = 0; i < MAX_N_SUDOKU_BOARD; i++) {
            for (int j = 0; j < MAX_N_SUDOKU_BOARD; j++) {
                grid[i][j] = new SudokuField();
            }
        }
        if (sudokuSolver1 == null) {
            logger.log(Level.INFO, "The sudoku solver is null");
            this.sudokuSolver = new BackTrackingSudokuSolver();
        } else {
            this.sudokuSolver = sudokuSolver1;
        }
    }

    /**
     * getter of the grid we are using.
     *
     * @return the grid of the sudoku
     */
    public SudokuField[][] getGrid() {
        return grid;
    }


    public void removeFields(int numberOfFields) {
        new BackTrackingSudokuRemover(this, numberOfFields);
    }

    /**
     * get a sudoku field object in a specific position x y.
     *
     * @param x x position to get the sudoku field
     * @param y y position to get the sudoku field
     * @return the sudoku field in that position
     */
    public SudokuField getField(final int x, final int y) {
        return grid[y][x];
    }

    /**
     * Getter of the number in a specific position x y.
     *
     * @param x x position to get the number
     * @param y y position to get the number
     * @return the number in that position
     */
    public int getNumber(final int x, final int y) {
        if (x < 0 || x > MAX_N_SUDOKU_BOARD - 1) {
            throw new GetterException("0-8");
        }
        if (y < 0 || y > MAX_N_SUDOKU_BOARD - 1) {
            throw new GetterException("0-8");
        }
        return grid[x][y].getFieldValue();
    }

    /**
     * set the number in a specific position.
     *
     * @param x x position to put the number
     * @param y y position to put the number
     * @param n number to put in that position
     */
    public void setNumber(final int x, final int y, final int n) {
        if (x < 0 || x > MAX_N_SUDOKU_BOARD - 1) {
            throw new SetterException("0-8");
        }
        if (y < 0 || y > MAX_N_SUDOKU_BOARD - 1) {
            throw new SetterException("0-8");
        }
        if (n < 0 || n > 9) {
            throw new SetterException("0-9");
        }
        grid[x][y].setFieldValue(n);
    }

    /**
     * Method that starts the sudoku solver to solve the game.
     *
     * @return true if it solved correctly
     */
    public boolean solveGame() {
        return sudokuSolver.solve(this, 0, 0);
    }

    /**
     * function that check if the number passed is already
     * present in the same row, column or box.
     *
     * @param gridFields grid to check
     * @param row row to put the number to check
     * @param col col to put the number to check
     * @param num number to check
     * @return true if it's safe
     */
    public boolean isSafe(final SudokuField[][] gridFields,
                          final int row, final int col, final int num) {
        for (int x = 0; x <= MAX_N_SUDOKU_BOARD - 1; x++) {
            if (gridFields[row][x].getFieldValue() == num) {
                return false;
            }
        }
        for (int x = 0; x <= MAX_N_SUDOKU_BOARD - 1; x++) {
            if (gridFields[x][col].getFieldValue() == num) {
                return false;
            }
        }
        int startRow = row - row % DIVIDEND_NUMBER;
        int startCol = col - col % DIVIDEND_NUMBER;
        for (int i = 0; i < DIVIDEND_NUMBER; i++) {
            for (int j = 0; j < DIVIDEND_NUMBER; j++) {
                if (gridFields[i + startRow][j
                        + startCol].getFieldValue() == num) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * method that checks if the board is correct using
     * the method verify.
     *
     * @return true if it's verified
     */
    public boolean checkBoard() {
        for (int i = 0; i < MAX_N_SUDOKU_BOARD; i++) {
            if (!getRow(i).verify() || !getColumn(i).verify()) {
                return false;
            }
            if (i == 0 || i == DIVIDEND_NUMBER
                    || i == 2 * DIVIDEND_NUMBER) {
                for (int j = 0; j < MAX_N_SUDOKU_BOARD;
                     j = j + DIVIDEND_NUMBER) {
                    if (!getBox(i, j).verify()) {
                        return false;
                    }

                }
            }
        }
        return true;
    }

    /**
     * Getter of the sudoku row.
     *
     * @param x position of the row
     * @return sudoku row required
     */
    public SudokuRow getRow(final int x) {
        return new SudokuRow(Arrays.asList(grid[x]));
    }

    /**
     * Getter of the sudoku column.
     *
     * @param x position of the column
     * @return sudoku column required
     */
    public SudokuColumn getColumn(final int x) {
        SudokuField[] column = new SudokuField[MAX_N_SUDOKU_BOARD];
        for (int i = 0; i < MAX_N_SUDOKU_BOARD; i++) {
            column[i] = new SudokuField();
            column[i].setFieldValue(grid[i][x].getFieldValue());
        }
        return new SudokuColumn(Arrays.asList(column));
    }

    /**
     * Getter of the sudoku box.
     *
     * @param x x position
     * @param y y position
     * @return sudoku box required
     */
    public SudokuBox getBox(final int x, final int y) {
        SudokuField[] box = new SudokuField[MAX_N_SUDOKU_BOARD];
        int startRow = x - x % DIVIDEND_NUMBER;
        int startCol = y - y % DIVIDEND_NUMBER;
        int count = 0;
        for (int i = 0; i < DIVIDEND_NUMBER; i++) {
            for (int j = 0; j < DIVIDEND_NUMBER; j++) {
                box[count] = new SudokuField();
                box[count].setFieldValue(grid[i + startRow]
                        [j + startCol].getFieldValue());
                count++;
            }
        }
        return new SudokuBox(Arrays.asList(box));
    }

    /**
     * ToString of the object.
     *
     * @return string that describes the object
     */
    public String toString() {
        return new ToStringBuilder(this)
                .append("grid", grid)
                .append("SudokuSolver", sudokuSolver)
                .toString();
    }

    /**
     * To check if 2 same objects are the same.
     *
     * @param obj obj to check
     * @return true if it's the same
     */
    public boolean equals(final Object obj) {
        if (obj == null) {
            logger.log(Level.INFO, "The object is null");
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        SudokuBoard rhs = (SudokuBoard) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(grid, rhs.grid)
                .append(sudokuSolver, rhs.sudokuSolver)
                .isEquals();
    }

    /**
     * hash code of the object.
     *
     * @return hash code of the object
     */
    public int hashCode() {
        return new HashCodeBuilder(INITIAL_ODD_NUMBER, MULTIPLIER_ODD_NUMBER)
                .append(grid)
                .append(sudokuSolver)
                .toHashCode();
    }

    /**
     * Method to clone the object.
     *
     * @return clone of the classs
     * @throws CloneNotSupportedException if it
     * doesn't work
     */
    @Override
    public SudokuBoard clone() {
        SudokuBoard cloneBoard = new SudokuBoard(this.sudokuSolver);
        for (int i = 0; i < MAX_N_SUDOKU_BOARD; i++) {
            for (int j = 0; j < MAX_N_SUDOKU_BOARD; j++) {
                cloneBoard.setNumber(i, j, this.getNumber(i, j));
            }
        }
        return cloneBoard;
    }

}
