/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import Project.Data.ProjectSignUp;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

/**
 *
 * @author icom
 */
public class Client_ChooseController {
    private ProjectSignUp psu;
    @FXML
    private JFXButton btnViewFood;

    @FXML
    private JFXButton btnViewDrink;
    
    @FXML
    private JFXButton menu;
    @FXML
    private Text user;
    @FXML
    void btnmenu(ActionEvent event) throws IOException {
        ProjectSignUp account = extractSignUpFromFields();
        Nagatice.getInstance().goToClient(account);
    }
    @FXML
    void btnViewDrinkClick(ActionEvent event) throws IOException {
        Nagatice.getInstance().goToDrink();
    }

    @FXML
    void btnViewFoodClick(ActionEvent event) throws IOException {
        Nagatice.getInstance().goToFood();
    }
    
    public void initialize(ProjectSignUp p){
        this.psu = p;
        if(this.psu != null){
            user.setText(p.getAccount());
        }
    
    }
    
    private ProjectSignUp extractSignUpFromFields() {
        ProjectSignUp sign = new ProjectSignUp(); 
        sign.setAccount(user.getText());
        return sign;
    }
    
}
