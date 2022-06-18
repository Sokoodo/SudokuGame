package com.lodz.sudoku.dao;

import com.lodz.sudoku.model.SudokuBoard;

import java.util.logging.Logger;

/**
 * Class that creates a FileSudokuBoardDao.
 */
public final class SudokuBoardDaoFactory {

    private static final Logger logger = Logger.getLogger(SudokuBoardDaoFactory.class.getName());

    /**
     * Private constructor because it's a utility class.
     */
    private SudokuBoardDaoFactory() {

    }

    /**
     * Creates FileSudokuBoardDao with a file name.
     * @param fileName name of the file
     * @return the new FileSudokuBoardDao
     */
    public static Dao<SudokuBoard> createFSudokuBoardDao(final
                                                         String fileName) {
        return new FileSudokuBoardDao(fileName);
    }

}
