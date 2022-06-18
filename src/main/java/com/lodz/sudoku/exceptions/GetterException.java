package com.lodz.sudoku.exceptions;

public class GetterException extends SudokuException {

    public GetterException(String s) {
        super(s);
    }

    public GetterException(Exception e) {
        super(message, e);
    }

    public GetterException(String s, Exception e) {
        super(s, e);
    }

    public GetterException() {
        super(message);
    }
}
