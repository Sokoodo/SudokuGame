package com.lodz.sudoku.model;

import com.lodz.sudoku.dao.FileSudokuBoardDao;
import com.lodz.sudoku.exceptions.SetterException;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class that represent a field of the sudoku and implements
 * Comparable, Cloneable and Serializable.
 */
public class SudokuField implements
        Cloneable, Serializable, Comparable<SudokuField> {

    private static final Logger logger = Logger.getLogger(SudokuField.class.getName());

    /**
     * value inside the sudoku field.
     */
    private int value = 0;

    /**
     * if the field can be editable or not.
     */
    private boolean editable = false;

    /**
     * method to get the number of the sudoku field.
     *
     * @return the value of the field
     */
    public int getFieldValue() {
        return value;
    }

    /**
     * Sets the field value (the number in the field).
     *
     * @param val value to put in the field
     */
    public void setFieldValue(final int val) {
        if (val < 0 || val > 9) {
            throw new SetterException("Value has to be in range 0 - 9");
        }
        this.value = val;
    }

    /**
     * method to verify if the field is editable or not.
     *
     * @return yes if it's editable
     */
    public boolean isEditable() {
        return editable;
    }

    /**
     * set if the field is editable or not.
     *
     * @param edit the boolean parameter to set
     */
    public void setEditable(final boolean edit) {
        this.editable = edit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            logger.log(Level.INFO, "The object is null");
            return false;
        }
        if (!(o instanceof SudokuField)) {
            return false;
        }
        SudokuField that = (SudokuField) o;
        return new EqualsBuilder().append(that.getFieldValue(), this.value).isEquals();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(value).toHashCode();
    }

    /**
     * Method to clone the object.
     *
     * @return clone of the classs
     * @throws CloneNotSupportedException if it doesn't work
     */
    public SudokuField clone() throws CloneNotSupportedException {
        return (SudokuField) super.clone();
    }

    /**
     * This method is overrode from the interface comparable.
     * @param o is the field to compare
     * @return 0 if it's the same, -1 if it's less or 1 if it's more
     */
    @Override
    public int compareTo(final SudokuField o) {
        if (o == null) {
            logger.log(Level.INFO, "The object is null");
            throw new NullPointerException("comparing to null");
        }
        if (this.equals(o)) {
            return 0;
        } else if (this.value < o.value) {
            return -1;
        } else {
            return 1;
        }
    }
}
