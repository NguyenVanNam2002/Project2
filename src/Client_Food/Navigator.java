/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client_Food;

import Client_Drink.*;
import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author DELL
 */
public class Navigator {

    public static final String INDEX_FXML = "Client_Food.fxml";
     public static final String VIEW_FXML = "ViewInf.fxml";


    private FXMLLoader loader;
    private Stage stage = null;

    private static Navigator nav = null;

    private Navigator() {
    }

    public static Navigator getInstance() {
        if (nav == null) {
            nav = new Navigator();
        }

        return nav;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    private void goTo(String fxml) throws IOException {
        this.loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxml));
//        loader.setResources(Translator.getResource());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        this.stage.setScene(scene);
    }

    public void goToIndex() throws IOException {
        this.goTo(INDEX_FXML);
    }
      public void goToView(Drink pros) throws  SQLException, IOException {
        this.goTo(VIEW_FXML);
        ViewInfController ctrl = loader.getController();
        ctrl.initialize(pros);
    }
      
}
