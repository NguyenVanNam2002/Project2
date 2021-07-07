/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 *
 * @author icom
 */
public class ChooseController {

    @FXML
    private JFXButton btnViewFood;

    @FXML
    private JFXButton btnViewDrink;

    @FXML
    void btnViewDrinkClick(ActionEvent event) throws IOException {
        Nagatice.getInstance().goToDrink();
    }

    @FXML
    void btnViewFoodClick(ActionEvent event) throws IOException {
        Nagatice.getInstance().goToFood();
    }
}
