package com.lodz.sudoku.gui;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Gui view start simply load and start the first page of the game.
 */
public class GUIViewStart extends Application {

    private static final Logger logger = Logger.getLogger(GUIViewStart.class.getName());

    /**
     * This method simply start the GUInterface and set the scene.
     *
     * @param stage window to open
     */
    public void start(final Stage stage) {
        try {
            HomeGUIController controller = new HomeGUIController();
            controller.showStage();
        } catch (Exception e) {
            logger.log(Level.FINE, e.toString(), e);
        }
    }

}
