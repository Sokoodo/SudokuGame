package com.lodz.sudoku.model;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

/**
 * Class that represent a 3x3 box of the sudoku
 * and extends the SudokuComponent and implements
 * Cloneable and Serializable.
 */
public class SudokuBox extends SudokuComponent
        implements Cloneable, Serializable {

    private static final Logger logger = Logger.getLogger(SudokuBox.class.getName());

    /**
     * Contructor of the sudoku box class.
     *
     * @param box box we need from the superclass
     */
    public SudokuBox(final List<SudokuField> box) {
        super(box);
    }

    /**
     * Method to clone the object.
     *
     * @return clone of the classs
     * @throws CloneNotSupportedException if it
     * doesn't work
     */
    public SudokuBox clone() throws CloneNotSupportedException {
        return (SudokuBox) super.clone();
    }

    /**
     * ToString of the object.
     *
     * @return string that describes the object
     */
    public String toString() {
        return super.toString();
    }

    /**
     * To check if 2 same objects are the same.
     *
     * @param obj obj to check
     * @return true if it's the same
     */
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    /**
     * hash code of the object.
     *
     * @return hash code of the object
     */
    public int hashCode() {
        return super.hashCode();
    }

}
