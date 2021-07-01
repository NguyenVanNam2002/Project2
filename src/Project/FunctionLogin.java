/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 *
 * @author Admin
 */
public class FunctionLogin {
    @FXML
    private JFXTextField account;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXButton btnlogin;

    @FXML
    private JFXButton btnsingup;

    @FXML
    void btnLogin(ActionEvent event) throws IOException {
        
    }

    @FXML
    void btnSingup(ActionEvent event) throws IOException {
        Nagatice.getInstance().goSingup();
    }
}
