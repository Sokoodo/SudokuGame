package com.lodz.sudoku.model;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * This class has the job to remove numbers from the sudoku
 * using thee backtracking algorithm.
 */
public class BackTrackingSudokuRemover implements SudokuRemover {

    private static final Logger logger = Logger.getLogger(BackTrackingSudokuRemover.class.getName());

    /**
     * This variable contains the sudoku board.
     */
    private final SudokuBoard board;

    /**
     * This variable contains the copy of the sudoku board.
     */
    private final SudokuBoard copyBoard;

    /**
     * This variable contains number of fields that should be
     * missing on this sudoku.
     */
    private final int nOfMissingFields;

    /**
     * This variable contains the shuffled list of all the fields.
     */
    private final List<Integer> allFields;

    /**
     * This variable contains the number of possible boards
     * after removing numbers.
     */
    private int currentNumOfPossibleBoards;

    /**
     * This variable contains the maximum number of fields in the sudoku - 1.
     */
    private static final int MAX_N_INT_STREAM_FIELDS = 80;

    /**
     * This variable contains the maximum value a number can reach in a sudoku.
     */
    private static final int MAX_N_SUDOKU_BOARD = 9;

    /**
     * This variable contains the maximum value of the range.
     */
    private static final int MAX_N_RANGE_SUDOKU = 10;

    /**
     * This is the constructor of the class.
     * @param sBoard is the sudoku board
     * @param missingFields is the number of fields that should be missing
     */
    public BackTrackingSudokuRemover(final SudokuBoard sBoard,
                                     final int missingFields) {
        this.board = sBoard;
        this.nOfMissingFields = missingFields;
        this.copyBoard = this.board.clone();
        allFields = IntStream.range(0, 80).boxed().collect(Collectors.toList());
        Collections.shuffle(allFields);
    }

    /**
     * This method has the job to remove an exact number of
     * fields from the sudoku board while letting it still be resolvable.
     */
    public void remove() {
        int counter = 0;
        for (Integer i : allFields) {
            if (counter >= nOfMissingFields) {
                this.setOriginalBoardToCopy();
                return;
            }
            int row = i / 9;
            int col = i % 9;
            currentNumOfPossibleBoards = 0;
            int temp = copyBoard.getField(row, col).getFieldValue();
            copyBoard.setNumber(row, col, 0);
            checkNumbOfCombinations(this.copyBoard);
            if (currentNumOfPossibleBoards > 1) {
                copyBoard.setNumber(row, col, temp);
            } else {
                counter++;
            }
        }
        this.setOriginalBoardToCopy();
    }

    /**
     * This method has the job to check the number of possible
     * combinations while numbers are missing.
     * @param sBoard is the sudoku board
     * @return true if the number of combinations is 1
     */
    private boolean checkNumbOfCombinations(final SudokuBoard sBoard) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board.getField(row, col).getFieldValue() == 0) {
                    List<Integer> range =
                            IntStream.range(1, 10).boxed().collect(Collectors.toList());
                    Collections.shuffle(range);
                    for (int number : range) {
                        board.setNumber(row, col, number);
                        if (board.getField(row, col).getFieldValue() == number) {
                            if (this.checkNumbOfCombinations(board)) {
                                return true;
                            } else {
                                board.setNumber(row, col, 0);
                            }
                        }
                    }
                    return false;
                }
            }
        }
        this.currentNumOfPossibleBoards++;
        return false;
    }

    /**
     * This method has the job to change the value of a field
     * in the original board to the value of a field in the copy board.
     */
    private void setOriginalBoardToCopy() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (this.copyBoard.getField(row, col).getFieldValue() == 0) {
                    this.board.setNumber(row, col, 0);
                    this.board.getField(col, row).setEditable(true);
                }
            }
        }
    }
}
