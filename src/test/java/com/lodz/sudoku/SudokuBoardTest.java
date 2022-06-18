package com.lodz.sudoku;

import com.lodz.sudoku.exceptions.GetterException;
import com.lodz.sudoku.exceptions.SetterException;
import com.lodz.sudoku.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuBoardTest {

    SudokuBoard sudokuBoard = new SudokuBoard(new BackTrackingSudokuSolver());
    SudokuBoard sudokuBoard1 = new SudokuBoard(null);

    //test that check if the final board is correct with function solveSudoku
    @Test
    public void testPrinting() {
        if (sudokuBoard.solveGame()) {
            print(sudokuBoard.getGrid());
            if (sudokuBoard.checkBoard()) {
                System.out.println("Board is correct!");
            } else {
                System.out.println("Board isn't correct!");
            }
        } else {
            System.out.println("No Solution exists");
        }
    }

    /**
     * Function to print the grid.
     *
     * @param gridFields grid of fields
     */
    public void print(final SudokuField[][] gridFields) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(gridFields[i][j].getFieldValue() + " ");
            }
            System.out.println();
        }
    }

    @Test
    public void testCheckBoard() {
        assertTrue(sudokuBoard.checkBoard());
        assertTrue(sudokuBoard1.checkBoard());
    }

    @Test
    public void getNumberTest() {
        assertNotNull(sudokuBoard.getNumber(0, 0));
        sudokuBoard.setNumber(0, 0, 1);
        assertEquals(sudokuBoard.getNumber(0, 0), 1);
    }

    @Test
    public void getRowTest() {
        assertNotNull(sudokuBoard.getRow(0));
        assertTrue(sudokuBoard.getRow(0).verify());

    }

    @Test
    public void getColumnTest() {
        assertNotNull(sudokuBoard.getColumn(0));
        assertTrue(sudokuBoard.getColumn(0).verify());
    }

    @Test
    public void getBoxTest() {
        assertNotNull(sudokuBoard.getBox(0, 0));
        assertTrue(sudokuBoard.getBox(0, 0).verify());
    }


    /*@Test
    void setterTestException() {
        assertThrows(SetterException.class, () -> sudokuBoard.setNumber(10, 7, 3));
        assertThrows(SetterException.class, () -> sudokuBoard.setNumber(7, 10, 3));
        assertThrows(SetterException.class, () -> sudokuBoard.setNumber(-1, 7, 3));
        assertThrows(SetterException.class, () -> sudokuBoard.setNumber(7, -1, 3));
        assertThrows(SetterException.class, () -> sudokuBoard.setNumber(7, 7, -1));
        assertThrows(SetterException.class, () -> sudokuBoard.setNumber(7, 7, 10));
    }


    @Test
    void equalsTest() throws GetterException, SetterException {

        assertNotEquals(sudokuBoard, null);
        assertEquals(sudokuBoard, sudokuBoard);
        assertNotEquals(sudokuBoard, Object.class);

        sudokuBoard.solveGame();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuBoard1.setNumber(i, j, sudokuBoard.getNumber(i, j));
            }
        }

        assertFalse(sudokuBoard.equals(sudokuBoard1) && sudokuBoard1.equals(sudokuBoard));
        sudokuBoard1.setNumber(3, 3, 0);
        assertNotEquals(sudokuBoard, sudokuBoard1);
    }

    @Test
    void checkBoardTest() throws SetterException {
        sudokuBoard.solveGame();
        assertTrue(sudokuBoard.checkBoard());
    }

    @Test
    void HashCodeTest() {
        sudokuBoard1.solveGame();
        sudokuBoard.solveGame();
        assertNotEquals(sudokuBoard1.hashCode(), sudokuBoard.hashCode());
        assertNotEquals(sudokuBoard1, sudokuBoard);
    }*/

    @Test
    public void cloneTest() throws SetterException {
        SudokuBoard board = sudokuBoard.clone();
        assertEquals(board.getNumber(1, 0), sudokuBoard.getNumber(1, 0));
        assertEquals(board.getNumber(1, 7), sudokuBoard.getNumber(1, 7));
        assertEquals(board.getNumber(2, 2), sudokuBoard.getNumber(2, 2));
    }

   /* @Test
    void internationalizedtSetExceptionTest() {

        Locale.setDefault(new Locale("en", "en"));
        SetterException exception1 = assertThrows(
                SetterException.class,
                () -> sudokuBoard.setNumber(0, 0, -1)
        );
        assertEquals("Sudoku Field Value Provided has to be in range <0, 9>", exception1.getLocalizedMessage());

        SetterException exception2 = assertThrows(
                SetterException.class,
                () -> sudokuBoard.setNumber(-1, -1, 1)
        );
        assertEquals("Value has to be in range 0 - 8", exception2.getLocalizedMessage());

        Locale.setDefault(new Locale("it", "it"));
        SetterException exception3 = assertThrows(
                SetterException.class,
                () -> sudokuBoard.setNumber(0, 0, -1)
        );
        assertEquals("Il valore da dare a SudokuField deve essere compreso tra 0 e 9", exception3.getLocalizedMessage());

        SetterException exception4 = assertThrows(
                SetterException.class,
                () -> sudokuBoard.setNumber(-1, -1, 1)
        );
        assertEquals("Il valore deve essere compreso tra 0 e 8", exception4.getLocalizedMessage());
    }


    @Test
    void internationalizedtGetExceptionTest() {

        Locale.setDefault(new Locale("en", "en"));
        GetterException exceptionEN = assertThrows(
                GetterException.class,
                () -> sudokuBoard.getNumber(-1, -1)
        );
        assertEquals("Value has to be in range 0 - 8", exceptionEN.getLocalizedMessage());

        Locale.setDefault(new Locale("it", "it"));
        GetterException exceptionPL = assertThrows(
                GetterException.class,
                () -> sudokuBoard.getNumber(-1, -1)
        );
        assertEquals("Il valore deve essere compreso tra 0 e 8", exceptionPL.getLocalizedMessage());
    }

*/
}
