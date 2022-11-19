module com.gym {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;


    opens com.gym to javafx.fxml;
    exports com.gym;
    exports com.gym.utils;
    opens com.gym.utils to javafx.fxml;
    exports com.gym.controller.templates;
    opens com.gym.controller.templates to javafx.fxml;
    exports com.gym.controller.pages;
    opens com.gym.controller.pages to javafx.fxml;
    exports com.gym.controller.modal;
    opens com.gym.controller.modal to javafx.fxml;
}
