package com.lodz.sudoku.gui;

import javafx.util.converter.NumberStringConverter;

import java.util.logging.Logger;

public class SudokuConverterTool extends NumberStringConverter {

    private static final Logger logger = Logger.getLogger(SudokuConverterTool.class.getName());

    @Override
    public String toString(Number val) {
        if (val != null) {
            if (val.equals(0)) {
                return "";
            }
        }
        return super.toString(val);
    }

    @Override
    public Number fromString(String val) {
        if (val.equals("")) {
            return 0;
        }
        return super.fromString(val);
    }

}
