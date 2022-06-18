package com.lodz.sudoku;

import com.lodz.sudoku.dao.Dao;
import com.lodz.sudoku.dao.SudokuBoardDaoFactory;
import com.lodz.sudoku.exceptions.DaoRException;
import com.lodz.sudoku.exceptions.DaoWException;
import com.lodz.sudoku.model.BackTrackingSudokuSolver;
import com.lodz.sudoku.model.SudokuBoard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class FileSudokuBoardDaoTest {

    private final String string = "/";
    private final String string1 = "/";
    private final Dao<SudokuBoard> fail = SudokuBoardDaoFactory.createFSudokuBoardDao(string1);
    private final SudokuBoard sudokuBoard = new SudokuBoard(new BackTrackingSudokuSolver());

    /*@Test
    void failureFileTest() {
        File file1 = new File(string1);
        Assertions.assertFalse((file1.exists() && !file1.isDirectory()));
    }

    @Test
    void tryWithResourceTest() {
        try (
                Dao<SudokuBoard> sudokuBoardDao = SudokuBoardDaoFactory.createFSudokuBoardDao(string)) {
            sudokuBoardDao.read();
        } catch (Exception ignore) {
        }
    }

    @Test
    void sudokuBoardWriteExceptionTest() {
        assertThrows(DaoWException.class, () -> {
            fail.write(sudokuBoard);
        });
    }

    @Test
    void sudokuBoardReadExceptionTest() {
        assertThrows(DaoRException.class, () -> {
            fail.read();
        });
    }*/
}
