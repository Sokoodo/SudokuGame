module mkw_tue_1545_05{
    requires javafx.fxml;
    requires javafx.controls;
    requires org.apache.commons.lang3;
    requires java.logging;

    exports com.lodz.sudoku.gui;
    exports com.lodz.sudoku.gui.resourcebundle;
    exports com.lodz.sudoku.model;

    opens com.lodz.sudoku.gui to javafx.graphics;
    opens com.lodz.sudoku.gui.resourcebundle to javafx.graphics;
    opens com.lodz.sudoku.model to javafx.base;

}