package com.lodz.sudoku.exceptions;

public class DaoRException extends DaoException {

    public DaoRException(String s) {
        super(s);
    }

    public DaoRException(Exception e) {
        super(message, e);
    }

    public DaoRException(String s, Exception e) {
        super(s, e);
    }

    public DaoRException() {
        super(message);
    }
}
