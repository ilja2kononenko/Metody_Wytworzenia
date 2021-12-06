module com.metody_wytworzenia {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;

    opens com.metodywytworzenia to javafx.fxml;
    exports com.metodywytworzenia;
    exports com.metodywytworzenia.controllers;
    opens com.metodywytworzenia.controllers to javafx.fxml;

    opens com.administration to javafx.fxml;
    exports com.administration;
    exports com.administration.controllers;
    opens com.administration.controllers to javafx.fxml;
    exports com.administration.controllers.lists;
    opens com.administration.controllers.lists to javafx.fxml;
}