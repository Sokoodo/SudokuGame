package com.lodz.sudoku.gui;

import com.lodz.sudoku.dao.Dao;
import com.lodz.sudoku.dao.SudokuBoardDaoFactory;
import com.lodz.sudoku.exceptions.DaoException;
import com.lodz.sudoku.model.BackTrackingSudokuRemover;
import com.lodz.sudoku.model.BackTrackingSudokuSolver;
import com.lodz.sudoku.model.SudokuBoard;
import com.lodz.sudoku.model.SudokuField;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.adapter.JavaBeanIntegerPropertyBuilder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Controller of the game window that fills
 * the grid that implements Initializable.
 */
public class GameGUIController implements Initializable {

    private static final Logger logger = Logger.getLogger(GameGUIController.class.getName());

    /**
     * GridPane to display the sudoku.
     */
    @FXML
    public GridPane grid1;

    /**
     * Number of missing Fields.
     */
    private int nOfMissingFields;

    /**
     * The sudoku board.
     */
    private SudokuBoard sudokuBoard;

    /**
     * This method set the maximum width and height for
     * each field of the sudoku.
     */
    private static final int MAX_WIDTH_HEIGHT = 45;

    /**
     * This variable contains the stage that is going to be shown.
     */
    private Stage thisStage;

    /**
     * This variable contains the controller of the home stage.
     */
    private final HomeGUIController homeGUIController;

    /**
     * Button to switch the language.
     */
    @FXML
    public Button language1;

    @FXML
    public Button saveBtn;

    @FXML
    public Button newGameBtn;

    @FXML
    public Button loadBtn;

    private ResourceBundle resourceBundle;

    private static final String saveGame = "saveGame";
    private static final String changeLanguage = "changeLanguage";
    private static final String newGame = "newGame";
    private static final String loadGame = "loadGame";

    StringConverter<Number> sudokuConverterTool = new SudokuConverterTool();
    private final List<IntegerProperty> listFieldBinding = new ArrayList<>();

    /**
     * This is the constructor for this controller.
     *
     * @param home is the controller of the first scene
     */
    public GameGUIController(final HomeGUIController home) {
        this.nOfMissingFields = home.getDifficulty();
        this.homeGUIController = home;
        thisStage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("/game1.fxml"));
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
        thisStage.showAndWait();
    }

    /**
     * Override for Initializable that initialize the page.
     *
     * @param url            a pointer to a "resource" on the World Wide Web
     * @param resourceBundle Resource bundles contain locale-specific objects
     */
    @Override
    public void initialize(final URL url, final ResourceBundle resourceBundle) {
        try {
            if (!SudokuInstance.getGameState()) {
                this.sudokuBoard = new SudokuBoard(new BackTrackingSudokuSolver());
                this.sudokuBoard.solveGame();
                new BackTrackingSudokuRemover(sudokuBoard,
                        nOfMissingFields).remove();
                SudokuInstance.setBoard(this.sudokuBoard);
                SudokuInstance.setGameState(!SudokuInstance.getGameState());
            } else {
                this.sudokuBoard = SudokuInstance.getBoard();
            }
            fillGrid();
        } catch (NoSuchMethodException e) {
            logger.log(Level.FINE, e.toString(), e);
        }
        this.resourceBundle = ResourceBundle.getBundle("Lang", Locale.getDefault());
        setGameButtons();
    }

    /**
     * Method that fills the grid (sudokuBoard).
     *
     * @throws NoSuchMethodException Thrown when a
     *                               particular method cannot be found.
     */
    private void fillGrid() throws NoSuchMethodException {
        int numRows = grid1.getRowCount();
        int numCols = grid1.getColumnCount();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                TextField textField = new TextField();
                textField.setAlignment(Pos.CENTER);
                textField.setMaxWidth(MAX_WIDTH_HEIGHT);
                textField.setMaxHeight(MAX_WIDTH_HEIGHT);
                textField.setTextFormatter(new TextFormatter<>(c -> {
                    if (c.isContentChange()) {
                        if (c.getText().matches("[1-9] | ^$ ")) {
                            return c;
                        }
                    }
                    return c;
                }));

                SudokuField sf = this.sudokuBoard.getField(i, j);
                IntegerProperty ip = new JavaBeanIntegerPropertyBuilder().bean(sf).name("fieldValue").build();
                this.listFieldBinding.add(ip);
                textField.textProperty().bindBidirectional(ip, sudokuConverterTool);

                textField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                    textField.clear();
                    String s = event.getCharacter();
                    if (!numericalCheck(s)) {
                        textField.setText("");
                        event.consume();
                    }
                });
                textField.addEventFilter(KeyEvent.KEY_RELEASED, event -> {
                    if (!sudokuBoard.checkBoard()) {
                        textField.setText("");
                    }
                });

                if (!sf.isEditable()) {
                    textField.setDisable(true);
                }
                grid1.add(textField, i, j);
            }
        }
    }

    private boolean numericalCheck(String value) {
        String number = value.replaceAll("\\s+", "");
        for (int j = 0; j < number.length(); j++) {
            if (!(((int) number.charAt(j) > 48 && (int) number.charAt(j) <= 57))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Sets the buttons for the page with the correct language
     */
    private void setGameButtons() {
        saveBtn.setText(resourceBundle.getString(saveGame));
        language1.setText(resourceBundle.getString(changeLanguage));
        newGameBtn.setText(resourceBundle.getString(newGame));
        loadBtn.setText(resourceBundle.getString(loadGame));
    }

    public void changeLanguage() {
        if (Locale.getDefault().equals(new Locale("en", "en"))) {
            Locale.setDefault(new Locale("it", "it"));
        } else {
            Locale.setDefault(new Locale("en", "en"));
        }
        resourceBundle = ResourceBundle.getBundle("Lang", Locale.getDefault());
        try {
            reloadGame();
        } catch (IOException e) {
            logger.log(Level.FINE, e.toString(), e);
        }
    }

    /**
     * Method that reloads the page to update the language.
     *
     * @throws IOException Signals that an I/O exception of some sort has occurred
     */
    private void reloadGame() throws IOException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Lang", Locale.getDefault());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/game1.fxml"), resourceBundle);
        loader.setController(this);
        thisStage.setScene(new Scene(loader.load()));
    }

    public void saveSudokuToFile() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (.txt)", ".txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(new Stage());
        if (file != null) {
            try (Dao<SudokuBoard> boardDao = SudokuBoardDaoFactory.createFSudokuBoardDao(file.getAbsolutePath())) {
                boardDao.write(this.sudokuBoard);
            } catch (DaoException e) {
                this.natSaveGame();
                logger.log(Level.FINE, e.toString(), e);
            }
        }
    }

    public void loadSudokuFromFile() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            try (Dao<SudokuBoard> dao = SudokuBoardDaoFactory.createFSudokuBoardDao(file.getAbsolutePath())) {
                SudokuInstance.setBoard(dao.read());
                reloadGame();
            } catch (DaoException | IOException e) {
                this.natLoadGame();
                logger.log(Level.FINE, e.toString(), e);
            }
        }
    }

    public void startNewGame() {
        try {
            SudokuInstance.setGameState(false);
            reloadGame();
        } catch (IOException e) {
            logger.log(Level.FINE, e.toString(), e);
        }
    }

    private void natSaveGame() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(("Save Error"));
        alert.setHeaderText(("Save Error"));
        alert.setContentText(("Saving Failed") + "\n" + ("Try Again"));
        alert.showAndWait();
        startNewGame();
    }

    private void natLoadGame() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Load Error");
        alert.setHeaderText("Load Error");
        alert.setContentText(("Loading Failed") + "\n" + ("Try Again"));
        alert.showAndWait();
        startNewGame();
    }

}