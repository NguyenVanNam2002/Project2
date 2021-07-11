/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  Project;

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
public class Client_Controller {
    private ProjectSignUp psu;
    @FXML
    private JFXButton btnMenu;

    @FXML
    private JFXButton btnFeedback;
    
    @FXML
    private JFXButton btnSearch;
    
    @FXML
    private Text user;
    @FXML
    private JFXButton setting;
    @FXML
    void btnFeedback(ActionEvent event) {

    }
    @FXML
    void btnsetting(ActionEvent event) throws IOException {
        ProjectSignUp account = extractSignUpFromFields();
        Nagatice.getInstance().goToSeting(account);
    }
    @FXML
    void btnMenuClick(ActionEvent event) throws IOException {
        ProjectSignUp account = extractSignUpFromFields();
        Nagatice.getInstance().goToChooseCategory(account);
    }

    @FXML
    void btnSearchClick(ActionEvent event) {

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
