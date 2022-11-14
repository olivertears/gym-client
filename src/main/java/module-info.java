module com.gym {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.gym to javafx.fxml;
    exports com.gym;
    exports com.gym.controller;
    opens com.gym.controller to javafx.fxml;
}
