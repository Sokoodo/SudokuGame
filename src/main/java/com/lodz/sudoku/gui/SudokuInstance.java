package com.lodz.sudoku.gui;

import com.lodz.sudoku.model.BackTrackingSudokuSolver;
import com.lodz.sudoku.model.SudokuBoard;

import java.util.logging.Logger;

public class SudokuInstance {

    private static final Logger logger = Logger.getLogger(SudokuInstance.class.getName());

    private static final SudokuInstance SUDOKU_INSTANCE = new SudokuInstance();

    private static boolean gameState = false;

    private static SudokuBoard sBoard = new SudokuBoard(new BackTrackingSudokuSolver());

    private SudokuInstance() {

    }

    public static SudokuInstance getSudokuInstance() {
        return SUDOKU_INSTANCE;
    }

    public static void setGameState(boolean gameState) {
        SudokuInstance.gameState = gameState;
    }

    public static boolean getGameState() {
        return gameState;
    }

    public static SudokuBoard getBoard() {
        return sBoard;
    }

    public static void setBoard(SudokuBoard sBoard) {
        SudokuInstance.sBoard = sBoard;
    }

}
