module org.fsp.kotlintictactoe {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;


    opens org.fsp.kotlintictactoe to javafx.fxml;
    exports org.fsp.kotlintictactoe;
}