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
    void btnproduct(ActionEvent event) {

    }

    @FXML
    void categorybtn(ActionEvent event) throws IOException {
        Nagatice.getInstance().goToCategoryIndex(); 
    }
    
    @FXML
    void logout(ActionEvent event) throws IOException {
        Nagatice.getInstance().goToIndex();
    }
    public void initialize(){
        
    }
}
