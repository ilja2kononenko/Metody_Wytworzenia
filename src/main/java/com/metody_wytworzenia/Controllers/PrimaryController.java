package com.metody_wytworzenia.Controllers;

import com.metody_wytworzenia.Main;
import com.metody_wytworzenia.Models.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PrimaryController extends Parent implements Initializable {

    @FXML
    private Label ItemNameLabel;

    @FXML
    private Label ItemPriceLabel;

    @FXML
    private VBox chosenItemCard;

    @FXML
    private ImageView itemImage;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

    private List<Item> items = new ArrayList<>();

    public PrimaryController() {
    }

    private List<Item> getData() {
        List<Item> items = new ArrayList<>();
        Item item;
        for (int i = 0; i < 20; i++) {
            item = new Item();
            item.setName("iPhone");
            item.setPrice(i);
            item.setDescription("Rozmawiaj ze znajomymi, ściągaj aplikacje lub gry i ciesz się z możliwości oferowanych przez Xiaomi POCO X3 PRO NFC 8/256GB niebieski. Długo godzinna praca smartfona jest zagwarantowana przez baterię o wielkości 5160 mAh. Korzystaj, a gdy wskaźnik będzie bliżej zera, wtedy możesz wykorzystać funkcję szybkiego ładowania o mocy 33 W."+i);
            //item.setImageSource("@../../cart.png");
            items.add(item);
        }
        return items;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        items.addAll(getData());
        int column = 0;
        int row = 0;
        try {
            for (Item item : items) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource("item.fxml"));

                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(item);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //child, obviously column and row
                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void prepareCartStage() throws IOException {
        Stage stage = new Stage(StageStyle.DECORATED);
        Parent root = FXMLLoader.load(Main.class.getResource("delivery.fxml"));
        stage.setScene(new Scene(root, 800, 550));
        stage.setTitle("Delivery");
        stage.setResizable(false);//block windows resize
        stage.show();
    }

    @FXML
    public void prepareAccountStage() throws IOException{
        Stage stage = new Stage(StageStyle.DECORATED);
        Parent root = FXMLLoader.load(Main.class.getResource("account.fxml"));
        stage.setScene(new Scene(root, 800, 550));
        stage.setTitle("Account");
        stage.setResizable(false);//block windows resize
        stage.show();
    }
}
