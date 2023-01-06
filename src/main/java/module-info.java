module com.teamuno.unofx {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;

    opens com.teamuno.unofx to javafx.fxml;
    exports com.teamuno.unofx;
    exports com.teamuno.unofx.guicontroller;
    opens com.teamuno.unofx.guicontroller to javafx.fxml;
    exports com.teamuno.unofx.utilities;
    opens com.teamuno.unofx.utilities to javafx.fxml;
    exports com.teamuno.unofx.factory;
    opens com.teamuno.unofx.factory to javafx.fxml;
}