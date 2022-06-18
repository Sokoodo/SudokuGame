package com.lodz.sudoku.exceptions;

public class DaoWException extends DaoException {

    public DaoWException(String s) {
        super(s);
    }

    public DaoWException(Exception e) {
        super(message, e);
    }

    public DaoWException(String s, Exception e) {
        super(s, e);
    }

    public DaoWException() {
        super(message);
    }
}
