package com.lodz.sudoku.exceptions;

import java.util.Locale;
import java.util.ResourceBundle;

public class SudokuException extends RuntimeException{

    static final String message = "sudokuException";

    private ResourceBundle bundle = ResourceBundle.getBundle("Lang", Locale.getDefault());

    public SudokuException(String s) {
        super(s);
    }

    public SudokuException(String s, Exception e) {
        super(s, e);
    }

    @Override
    public String getLocalizedMessage() {
        return bundle.getString(getMessage());
    }
}
