module com.metody_wytworzenia {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.metody_wytworzenia to javafx.fxml;
    exports com.metody_wytworzenia;
    exports com.metody_wytworzenia.Controllers;
    opens com.metody_wytworzenia.Controllers to javafx.fxml;
}