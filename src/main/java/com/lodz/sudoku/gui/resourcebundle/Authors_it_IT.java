package com.lodz.sudoku.gui.resourcebundle;

import java.util.ListResourceBundle;

public class Authors_it_IT extends ListResourceBundle {

    private static final Object[][] contents = {
            {"Authors: ", "Autori: "},
            {"Francesco Santarelli", "Francesco Santarelli"},
            {"Giacomo Piergentili", "Giacomo Piergentili"},
            {"Change Language", "Cambia Lingua"},
            {"Choose Difficulty", "Scegli Difficoltà"},
            {"Start Game", "GIOCA"}
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }

}