/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import Project.Data.ProjectSignUp;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.text.Text;

/**
 *
 * @author Admin
 */
public class Client_setting {
    private ProjectSignUp psu;
    @FXML
    private Text user;

    @FXML
    private JFXButton menu;

    @FXML
    private JFXButton logout;
    @FXML
    private JFXButton newpassword;
     @FXML
    private JFXButton information;

    @FXML
    void btnInformation(ActionEvent event) throws IOException {
        ProjectSignUp informations = extractSignUpFromFields();
        Nagatice.getInstance().goToInformationClient(informations);
    }
    @FXML
    void btnpassword(ActionEvent event) throws IOException {
        ProjectSignUp passwrod = extractSignUpFromFields();
        Nagatice.getInstance().goToChangePassword(passwrod);
    }
    @FXML
    void btnLogout(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Bạn chắc chắn muốn đăng xuất ?");
        alert.setTitle("Lưu ý ");
        Optional<ButtonType> confirmationResponse
                = alert.showAndWait();
        if (confirmationResponse.get() == ButtonType.OK) {
            Nagatice.getInstance().goToIndex();
        } 
        
        
    }

    @FXML
    void btnmenu(ActionEvent event) throws IOException {
        ProjectSignUp menus = extractSignUpFromFields();
        Nagatice.getInstance().goToClient(menus);
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
