/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client_Food;

import Client_Drink.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

/**
 *
 * @author DELL
 */
public class ViewInfController {

    @FXML
    private FlowPane view;

//    @FXML
//    private Text txtName;
    
      @FXML
    void btnBackClick(ActionEvent event) throws IOException {
        Navigator.getInstance().goToIndex();
    }

    public void initialize(URL location, ResourceBundle resources) {

    }

    public void initialize(Drink pros) throws SQLException {
        Class<?> views = this.getClass();
        List<Integer> img = new ArrayList<Integer>();
//        img.addAll(pros.getID().toString());
//        
//        for (String i : img) {
//            System.out.println(i);
//            Image image = new Image(i);
//            ImageView imageView = new ImageView(image);
//            imageView.setFitWidth(520);
//            imageView.setFitHeight(520);
//            view.getChildren().addAll(imageView);
//
//        }
    }

}
