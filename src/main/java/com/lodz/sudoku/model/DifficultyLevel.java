package com.lodz.sudoku.model;

/**
 * Enum to have the different difficulties of the game.
 */
public enum DifficultyLevel {

    /**
     * Easy difficulty with the number of missing fields.
     */
    Easy(31),

    /**
     * Medium difficulty with the number of missing fields.
     */
    Medium(45),

    /**
     * Hard difficulty with the number of missing fields.
     */
    Hard(55);

    /**
     * This variable contains the number of missing fields.
     */
    private int number;

    /**
     * This is the constructor of the enum.
     * @param nOfMissingFields is the number of missing fields.
     */
    DifficultyLevel(final int nOfMissingFields) {
        this.number = nOfMissingFields;
    }

    /**
     * @return number of missing fields.
     */
    public int getNumber() {
        return this.number;
    }

}
