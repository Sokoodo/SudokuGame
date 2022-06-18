package com.lodz.sudoku;

import com.lodz.sudoku.gui.GUIViewStart;
import javafx.application.Application;

import java.util.logging.Logger;

/**
 * Class App that contains the main and starts the program.
 */
public final class App {

    private static final Logger logger = Logger.getLogger(App.class.getName());

    /**
     * Private constructor because it's a utility class.
     */
    private App() {

    }

    /**
     * Method main that starts the GUInterface.
     *
     * @param args standard main
     */
    public static void main(final String[] args) {
        Application.launch(GUIViewStart.class);
    }

}
