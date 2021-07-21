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
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
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
public class Client_passoword {
    private ProjectSignUpDAP psud = new ProjectSignUpDAOImpl();
    private ProjectSignUp psu;
    @FXML
    private Text user;

    @FXML
    private JFXButton menu;
    
    @FXML
    private Text succes;
    @FXML
    private JFXButton changepassword;

    @FXML
    private JFXPasswordField newpassword;

    @FXML
    private Text erros;
    
     @FXML
    private JFXPasswordField oldPassword;

    @FXML
    private Text erros1;

    @FXML
    private JFXPasswordField newpassword1;

    @FXML
    private Text erros11;

    @FXML
    private JFXButton backsetting;
    
     @FXML
    void btnsetting(ActionEvent event) throws IOException {
        ProjectSignUp setting = extractChangePasswordFromFields();
        Nagatice.getInstance().goToSeting(setting);
    }
    @FXML
    void btnChangePassword(ActionEvent event) {
        try {
            if(Validate()){
                ProjectSignUp password = extractPasswordFromFields();
                boolean passwords = psud.Login(password);
                if(passwords){
                    if(newpassword1.getText().contentEquals(newpassword.getText())){
                        ProjectSignUp change = extractChangePasswordFromFields();
                        change.setAccount(this.psu.getAccount());
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setHeaderText("Bạn có chắc đổi mật khẩu ?");
                        alert.setTitle("Lưu ý ");
                        Optional<ButtonType> confirmationResponse
                                = alert.showAndWait();
                        if (confirmationResponse.get() == ButtonType.OK) {
                            boolean reslut = psud.update(change);
                            if(reslut){
                                succes.setText("Đổi mật khẩu thành công");
                            }else{
                                succes.setText("Đổi mật khẩu không thành công");
                            }
                        }
                    }else{
                        erros11.setText("Nhập lại mật khẩu mới chưa chính xác !");
                    }
                }else{
                    erros1.setText("Mật khẩu cũ không chính xác !");
                }
            }
            
        } catch (Exception e) {
        }
    }

    public void initialize(ProjectSignUp p){
        this.psu = p;
        if(this.psu != null){
            user.setText(p.getAccount());
        }
    }
    private ProjectSignUp extractPasswordFromFields() {
        ProjectSignUp sign = new ProjectSignUp(); 
        sign.setAccount(user.getText());
        sign.setPassword(md5(oldPassword.getText()));
        return sign;
    }
    private ProjectSignUp extractChangePasswordFromFields() {
        ProjectSignUp sign = new ProjectSignUp(); 
        sign.setAccount(user.getText());
        sign.setPassword(md5(newpassword.getText()));
        return sign;
    }
    private String md5(String a){
        try {
            MessageDigest digs =  MessageDigest.getInstance("MD5");
            byte[] messageDigest = digs.digest(a.getBytes());
            BigInteger number = new BigInteger(1,messageDigest);
            String hashtext = number.toString(16);
            while(hashtext.length() <32 ){
                hashtext = "0"+ hashtext;
            }
            return hashtext;
        } catch (Exception e) {
        }
        return a;
    }
    private boolean Validate(){
      
        if(oldPassword.getText().isEmpty() ){
            erros1.setText("Password not empty");
            return false; 
        }else{
            erros1.setText("");
        }
        if(oldPassword.getText().length() < 8 ){
            erros1.setText("Password more than 8");
            return false; 
        }else{
            erros1.setText("");
        }
        if(oldPassword.getText().length() > 16 ){
            erros1.setText("Password less than 16");
            return false; 
        }else{
           erros1.setText("");
        }
        if(oldPassword.getText().length() > 16 ){
            erros1.setText("Password less than 16");
            return false; 
        }else{
            erros1.setText("");
        }
        
        if(newpassword.getText().isEmpty() ){
            erros.setText("Password not empty");
            return false; 
        }else{
            erros.setText("");
        }
        if(newpassword.getText().length() < 8 ){
            erros.setText("Password more than 8");
            return false; 
        }else{
            erros.setText("");
        }
        if(newpassword.getText().length() > 16 ){
            erros.setText("Password less than 16");
            return false; 
        }else{
           erros.setText("");
        }
        if(newpassword.getText().length() > 16 ){
            erros.setText("Password less than 16");
            return false; 
        }else{
            erros.setText("");
        }
        return true;
    }
}
