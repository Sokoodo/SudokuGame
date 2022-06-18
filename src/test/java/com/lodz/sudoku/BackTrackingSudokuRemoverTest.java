package com.lodz.sudoku;

import com.lodz.sudoku.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BackTrackingSudokuRemoverTest {

    SudokuBoard board5 = new SudokuBoard(new BackTrackingSudokuSolver());

    @Test
    public void removeTest(){
        board5.solveGame();
        SudokuRemover sudokuRemover = new BackTrackingSudokuRemover(board5, 42);
        sudokuRemover.remove();
        int counter = 0;
        for(SudokuField[] sf : board5.getGrid()) {
            for(SudokuField j : sf) {
                if(j.getFieldValue() == 0) {
                    counter++;
                }
            }
        }
        Assertions.assertEquals(42, counter);
    }

}
