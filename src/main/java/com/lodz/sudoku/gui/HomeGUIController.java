package com.lodz.sudoku.gui;

import com.lodz.sudoku.model.DifficultyLevel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is the controller of the home window
 * to choose difficulty and start the game.
 */
public class HomeGUIController implements Initializable {

    private static final Logger logger = Logger.getLogger(HomeGUIController.class.getName());

    /**
     * Choice box to select the difficulty.
     */
    @FXML
    public ChoiceBox<DifficultyLevel> difficulty;

    /**
     * List of Difficulty Levels.
     */
    private ObservableList<DifficultyLevel> difficultyLevels;

    /**
     * This variable contains the stage that is going to be shown.
     */
    private final Stage thisStage;


    @FXML
    public Label authors;

    @FXML
    public Label author1;

    @FXML
    public Label author2;

    @FXML
    public Label difficultyLbl;

    @FXML
    public Button language;

    @FXML
    public Button startBtn;

    private ResourceBundle resourceBundle;

    private boolean flag = true;

    /**
     * This is the constructor of this controller.
     */
    public HomeGUIController() {
        thisStage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("/home.fxml"), resourceBundle);
            loader.setController(this);
            thisStage.setScene(new Scene(loader.load()));
            thisStage.setTitle("Sudoku");
            thisStage.setResizable(false);
        } catch (IOException e) {
            logger.log(Level.FINE, e.toString(), e);
        }
    }


    /**
     * Show the stage that was loaded in the constructor.
     */
    public void showStage() {
        thisStage.show();
    }

    /**
     * Method to go in the page of the game after select difficulty.
     *
     * @throws IOException Signals that an I/O exception
     *                     of some sort has occurred
     */
    @FXML
    public void start() throws IOException {
        if (difficulty.getValue() != null) {
            thisStage.close();
            GameGUIController controller2 = new GameGUIController(this);
            controller2.showStage();
        }
    }

    /**
     * refreshes the list of levels adding all levels from ENUM.
     */
    private void refresh() {
        difficultyLevels.addAll(Arrays.asList(DifficultyLevel.values()));
    }

    /**
     * initializes the choiceBox with difficulty levels.
     *
     * @param url            a pointer to a "resource" on the World Wide Web
     * @param resourceBundle Resource bundles contain locale-specific objects
     */
    @Override
    public void initialize(final URL url, final ResourceBundle resourceBundle) {

        difficultyLevels = FXCollections.observableArrayList();
        refresh();
        for (DifficultyLevel dl : difficultyLevels) {
            difficulty.getItems().add(dl);
        }
        if (flag) {
            if (!Locale.getDefault().equals(new Locale("en", "en"))) {
                Locale.setDefault(new Locale("en", "en"));
                flag = false;
            }
        }
        ResourceBundle resourceAuthors
                = ResourceBundle.getBundle("com.lodz.sudoku.gui.resourcebundle.Authors",
                Locale.getDefault());
        authors.setText(resourceAuthors.getString("Authors: "));
        author1.setText(resourceAuthors.getString("Francesco Santarelli"));
        author2.setText(resourceAuthors.getString("Giacomo Piergentili"));
        language.setText(resourceAuthors.getString("Change Language"));
        startBtn.setText(resourceAuthors.getString("Start Game"));
        difficultyLbl.setText(resourceAuthors.getString("Choose Difficulty"));
    }

    public int getDifficulty() {
        return difficulty.getSelectionModel().getSelectedItem().getNumber();
    }

    public void changeLanguage() {
        if (Locale.getDefault().equals(new Locale("en", "en"))) {
            Locale.setDefault(new Locale("it", "it"));
        } else {
            Locale.setDefault(new Locale("en", "en"));
        }
        resourceBundle = ResourceBundle.getBundle("Lang", Locale.getDefault());
        try {
            updateLanguage();
        } catch (IOException e) {
            logger.log(Level.FINE, e.toString(), e);
        }
    }

    private void updateLanguage() throws IOException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Lang", Locale.getDefault());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/home.fxml"), resourceBundle);
        loader.setController(this);
        thisStage.setScene(new Scene(loader.load()));
    }

}