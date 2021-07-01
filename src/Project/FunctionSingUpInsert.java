/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import Project.Data.ProjectSignUp;
import Project.Data.ProjectSignUpDAOImpl;
import Project.Data.ProjectSignUpDAP;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

/**
 *
 * @author Admin
 */
public class FunctionSingUpInsert {
    private ProjectSignUpDAP psd = new ProjectSignUpDAOImpl();
    
    private ProjectSignUp signs = null;
    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField account;

    @FXML
    private JFXTextField phone;

    @FXML
    private JFXTextField address;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXButton Back;

    @FXML
    private JFXButton SignUp;
    
    @FXML
    private Text ttt;
    @FXML
    void btnBack(ActionEvent event) throws IOException {
        Nagatice.getInstance().goToIndex();
    }

    @FXML
    void btnSignUp(ActionEvent event) {
        try{
            
                if(signs == null){
                    ProjectSignUp insert = extractSignUpFromFields();
                    insert = psd.insert(insert);
                    
                    ttt.setText("insert succesfuly");
                }
            
        }catch(Exception  e){
            System.err.println(e.getMessage());
            
          
        }
    }
    
    private ProjectSignUp extractSignUpFromFields() {
        ProjectSignUp sign = new ProjectSignUp();
        
        sign.setAccount(account.getText());
        sign.setPassword(password.getText());
        sign.setName(name.getText());
        sign.setPhone(phone.getText());
        sign.setAddress(address.getText());
        
        return sign;
    }
}
