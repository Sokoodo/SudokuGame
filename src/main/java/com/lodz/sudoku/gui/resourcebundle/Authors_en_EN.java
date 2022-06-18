package com.lodz.sudoku.gui.resourcebundle;

import java.util.ListResourceBundle;

public class Authors_en_EN extends ListResourceBundle {

    private static final Object[][] contents = {
            {"Authors: ", "Authors: "},
            {"Francesco Santarelli", "Francesco Santarelli"},
            {"Giacomo Piergentili", "Giacomo Piergentili"},
            {"Change Language", "Change Language"},
            {"Choose Difficulty", "Choose Difficulty"},
            {"Start Game", "START"}
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}