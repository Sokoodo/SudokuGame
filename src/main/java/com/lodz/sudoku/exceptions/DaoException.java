package com.lodz.sudoku.exceptions;

import java.util.Locale;
import java.util.ResourceBundle;

public class DaoException extends Exception{

    static final String message = "daoException";

    private ResourceBundle bundle = ResourceBundle.getBundle("Lang", Locale.getDefault());

    public DaoException(String s) {
        super(s);
    }

    public DaoException(String s, Exception e) {
        super(s, e);
    }

    @Override
    public String getLocalizedMessage() {
        return bundle.getString(getMessage());
    }

}
