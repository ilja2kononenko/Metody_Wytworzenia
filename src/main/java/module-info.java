module com.metody_wytworzenia {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.metodywytworzenia to javafx.fxml;
    exports com.metodywytworzenia;
    exports com.metodywytworzenia.controllers;
    opens com.metodywytworzenia.controllers to javafx.fxml;
}