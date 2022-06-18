package com.lodz.sudoku.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class that represent a Component of the sudoku.
 */
public abstract class SudokuComponent {

    private static final Logger logger = Logger.getLogger(SudokuComponent.class.getName());

    /**
     * comp is a list of sudoku fields.
     */
    private List<SudokuField> comp =
            Arrays.asList(new SudokuField[MAX_N_SUDOKU_BOARD]);

    /**
     * This variable contains the maximum value a number can reach in a sudoku.
     */
    private static final int MAX_N_SUDOKU_BOARD = 9;

    /**
     * Initial odd number for the hashcode method.
     */
    private static final int INITIAL_ODD_NUMBER = 17;

    /**
     * Multiplier odd number for the hashcode method.
     */
    private static final int MULTIPLIER_ODD_NUMBER = 37;

    /**
     * Contructor of the sudoku component class.
     *
     * @param component list of sudoku fields
     */
    public SudokuComponent(final List<SudokuField> component) {
        this.comp = component;
    }

    /**
     * Method that verifies if the sudoku is correct.
     *
     * @return true if the column is correct
     */
    public boolean verify() {
        for (int i = 0; i < MAX_N_SUDOKU_BOARD; i++) {
            for (int j = i + 1; j < MAX_N_SUDOKU_BOARD; j++) {
                if (comp.get(i).getFieldValue() != 0
                        && comp.get(j).getFieldValue() != 0) {
                    if (comp.get(i).getFieldValue()
                            == comp.get(j).getFieldValue()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * ToString of the object.
     *
     * @return string that describes the object
     */
    public String toString() {
        return new ToStringBuilder(this)
                .append("comp", comp)
                .toString();
    }

    /**
     * To check if 2 same objects are the same.
     *
     * @param obj obj to check
     * @return true if it's the same
     */
    public boolean equals(final Object obj) {
        if (obj == null) {
            logger.log(Level.INFO, "The object is null");
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        SudokuComponent rhs = (SudokuComponent) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(comp, rhs.comp)
                .isEquals();
    }

    /**
     * hash code of the object.
     *
     * @return hash code of the object
     */
    public int hashCode() {
        return new HashCodeBuilder(INITIAL_ODD_NUMBER, MULTIPLIER_ODD_NUMBER)
                .append(comp)
                .toHashCode();
    }
}
