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
import com.jfoenix.validation.RequiredFieldValidator;
import java.io.IOException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    private Text error1;

    @FXML
    private Text error2;

    @FXML
    private Text error3;

    @FXML
    private Text error4;

    @FXML
    private Text error5;

    @FXML
    void btnBack(ActionEvent event) throws IOException {
        Nagatice.getInstance().goToIndex();
    }

    @FXML
    void btnSignUp(ActionEvent event) {
        try{
            if(Validate()){
                if(signs == null){
                    ProjectSignUp insert = extractSignUpFromFields();
                    insert = psd.insert(insert);
                    
                    ttt.setText("insert succesfuly");
                }
            }
        }catch(Exception  e){
            System.err.println(e.getMessage());
            
          
        }
    }
    public void initialize() {
        System.out.println("#Insert Teacher initialized!");
   
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
    
    private boolean Validate(){
        if(name.getText().isEmpty()){
            error1.setText("Name not empty");
            return false;
        }else{
            error1.setText("");
        }
        if(name.getText().length() < 5){
            error1.setText("Name more than 5");
            return false;
        }else{
            error1.setText("");
        }
        if(name.getText().length() > 255){
            error1.setText("Name less than 255");
            return false;
        }else{
            error1.setText("");
        }
        
        if(account.getText().isEmpty() ){
            error2.setText("Account not empty");
            return false; 
        }else{
            error2.setText("");
        }
        if(account.getText().length() < 6 ){
            error2.setText("Account more than 6");
            return false; 
        }else{
            error2.setText("");
        }
        if(account.getText().length() > 25 ){
            error2.setText("Account less than 25");
            return false; 
        }else{
            error2.setText("");
        }
        if(!account.getText().contains("@gmail.com") ){
            error2.setText("Account have a @gmail.com");
            return false; 
        }else{
            error2.setText("");
        }
        if(account.getText().contains(" ") ){
            error2.setText("Account not have space");
            return false; 
        }else{
            error2.setText("");
        }
        
        if(password.getText().isEmpty() ){
            error3.setText("Password not empty");
            return false; 
        }else{
            error3.setText("");
        }
        if(password.getText().length() < 8 ){
            error3.setText("Password more than 8");
            return false; 
        }else{
            error3.setText("");
        }
        if(password.getText().length() > 16 ){
            error3.setText("Password less than 16");
            return false; 
        }else{
            error3.setText("");
        }
        if(password.getText().length() > 16 ){
            error3.setText("Password less than 16");
            return false; 
        }else{
            error3.setText("");
        }
        
        if(phone.getText().isEmpty() ){
            error4.setText("Your Phone not empty");
            return false; 
        }else{
            error4.setText("");
        }
        if(phone.getText().length() != 9 ){
            error4.setText("Your Phone Number not equals 9");
            return false; 
        }else{
            error4.setText("");
        }
        if(!phone.getText().substring(0, 2).contentEquals("09") || !phone.getText().substring(0, 2).contentEquals("03")
         ||  !phone.getText().substring(0, 2).contentEquals("08") || !phone.getText().substring(0, 2).contentEquals("07")
         || !phone.getText().substring(0, 2).contentEquals("05") 
         ){
            error4.setText("phone numbers starting with 09 , 08 , 07 , 05 and 03");
            return false; 
        }else{
            error4.setText("");
        }
        
        if(address.getText().isEmpty()){
            error5.setText("address not empty");
            return false; 
        }else{
            error5.setText("");
        }
        if(address.getText().length() < 4){
            error5.setText("Unknown address");
            return false; 
        }else{
            error5.setText("");
        }
        if(!address.getText().contains("Ha Noi") || !address.getText().contains("ha noi")){
            error5.setText("You are too far from Hanoi.");
            return false; 
        }else{
            error5.setText("");
        }
        
        
        return true;
    }
}
