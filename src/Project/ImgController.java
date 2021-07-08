/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import Project.Data.Product;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

/**
 *
 * @author DELL
 */
public class ImgController implements Initializable {

    @FXML
    private FlowPane view;
    
        @FXML
    private JFXButton btnBack;

    @FXML
    void btnBackClick(ActionEvent event) throws IOException {
         Nagatice.getInstance().goToIndexProduct();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void initialize(Product pros) throws SQLException {
        Class<?> views = this.getClass();
        List<String> img = Product.selectImgByID(pros.getID());

        for (String i : img) {
            System.out.println(i);
//             File file = new File("/src/img/" + i);
            Image image = new Image(i);
            ImageView imageView = new ImageView(image);
//            imageView.setX(10);
//            imageView.setY(180);
            imageView.setFitWidth(520);
            imageView.setFitHeight(520);
            view.getChildren().addAll(imageView);
     

}
    }
}
