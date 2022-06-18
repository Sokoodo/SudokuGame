package com.lodz.sudoku.exceptions;

public class SetterException extends SudokuException {

    public SetterException(String s) {
        super(s);
    }

    public SetterException(Exception e) {
        super(message, e);
    }

    public SetterException(String s, Exception e) {
        super(s, e);
    }

    public SetterException() {
        super(message);
    }
}
