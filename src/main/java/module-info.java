module com.scrambler {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;

    exports com.scrambler;
    exports com.scrambler.controllers;
    opens com.scrambler.controllers to javafx.fxml;
    exports com.scrambler.classes;
    opens com.scrambler.classes to javafx.fxml;
}