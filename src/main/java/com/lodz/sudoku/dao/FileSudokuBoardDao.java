package com.lodz.sudoku.dao;

import com.lodz.sudoku.exceptions.DaoRException;
import com.lodz.sudoku.exceptions.DaoWException;
import com.lodz.sudoku.gui.GameGUIController;
import com.lodz.sudoku.model.SudokuBoard;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class FileSudokuBoardDao that implements Dao<SudokuBoard>.
 */
public class FileSudokuBoardDao implements Dao<SudokuBoard> {

    private static final Logger logger = Logger.getLogger(FileSudokuBoardDao.class.getName());

    /**
     * Variable that indicates the file name.
     */
    private final String fname;


    private static final String fileWrite = "fileWrite";
    private static final String fileRead = "fileRead";

    /**
     * public constructor.
     *
     * @param name file name
     */
    public FileSudokuBoardDao(final String name) {
        this.fname = name;
    }

    /**
     * Method to read in the file.
     *
     * @return SudokuBoard stream
     */
    @Override
    public SudokuBoard read() throws DaoRException {
        try {
            FileInputStream fileIn = new FileInputStream(this.fname);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            return (SudokuBoard) objectIn.readObject();
        } catch (IOException | ClassNotFoundException e) {
            logger.log(Level.FINE, e.toString(), e);
            throw new DaoRException(fileRead, e);
        }
    }

    /**
     * Method to write in the file.
     *
     * @param sudokuBoard object to write.
     */
    @Override
    public void write(final SudokuBoard sudokuBoard) throws DaoWException {
        try (FileOutputStream fileOut = new FileOutputStream(this.fname);
                ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(sudokuBoard);
        } catch (IOException e) {
            logger.log(Level.FINE, e.toString(), e);
            throw new DaoWException(fileWrite, e);
        }
    }

    @Override
    public void close() {

    }
}
