/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Admin
 */
public class InFutional  {
    @FXML
    private JFXButton category;

    @FXML
    private JFXButton product;

    @FXML
    private JFXButton feedback;

    @FXML
    private JFXButton customer;
    
    @FXML
    private JFXButton logout;


    @FXML
    void btncustome(ActionEvent event) throws IOException {
        Nagatice.getInstance().goToCustomerIndex();
    }

    @FXML
    void btnfeedback(ActionEvent event) throws IOException {
        Nagatice.getInstance().goToFeedbackIndex();
    }

    @FXML
    void btnproduct(ActionEvent event) throws IOException {
        Nagatice.getInstance().goToIndexProduct();
    }

    @FXML
    void categorybtn(ActionEvent event) throws IOException {
        Nagatice.getInstance().goToCategoryIndex(); 
    }
    
    @FXML
    void logout(ActionEvent event) throws IOException {
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Bạn chắc chắn muốn đăng xuất ?");
        alert.setTitle("Lưu ý ");
        Optional<ButtonType> confirmationResponse
                = alert.showAndWait();
        if (confirmationResponse.get() == ButtonType.OK) {
            Nagatice.getInstance().goToIndex();
        } 
    }
    public void initialize(){
        
    }
}
