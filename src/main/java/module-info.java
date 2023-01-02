module com.teamuno.unofx {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;

    opens com.teamuno.unofx to javafx.fxml;
    exports com.teamuno.unofx;
    exports com.teamuno.unofx.controller;
    opens com.teamuno.unofx.controller to javafx.fxml;
    exports com.teamuno.unofx.utilities;
    opens com.teamuno.unofx.utilities to javafx.fxml;
}