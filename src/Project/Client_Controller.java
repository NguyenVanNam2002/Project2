/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  Project;

import Project.Data.Category;
import Project.Data.Product;
import Project.Data.ProjectSignUp;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 *
 * @author icom
 */
public class Client_Controller {
    private ProjectSignUp psu;
    @FXML
    private Text user;

    @FXML
    private JFXButton setting;

    @FXML
    private JFXButton btnDrink;



    @FXML
    private JFXButton btnSearch;

    @FXML
    private TextField productname;

    @FXML
    private JFXButton Food;
    
    @FXML
    private Text drink;

    @FXML
    private Text food;

    @FXML
    void btnDrink(ActionEvent event) throws IOException {
         ProjectSignUp menus = extractSignUpFromFields();
        Nagatice.getInstance().goToChoose(menus);
    }


    @FXML
    void btnFoodclik(ActionEvent event) throws IOException {
        ProjectSignUp menus = extractSignUpFromFields();
       
        Nagatice.getInstance().goToChoose(menus);
    }
    @FXML
    void btnsetting(ActionEvent event) throws IOException {
        ProjectSignUp account = extractSignUpFromFields();
        Nagatice.getInstance().goToSeting(account);
    }
    

    @FXML
    void btnSearchClick(ActionEvent event) throws IOException {
        ProjectSignUp account = extractSignUpFromFields();
        Product name = extractProductFromFields();
        Nagatice.getInstance().goToSearch(account, name);
    }
    @FXML
    void shopping(MouseEvent event) throws IOException {
        ProjectSignUp shop = extractSignUpFromFields();
        Nagatice.getInstance().goToShopping(shop);
    }
    public void initialize(ProjectSignUp p){
        this.psu = p;
        if(this.psu != null){
            user.setText(p.getAccount());
        }
    }
    
    public void initialize(){
        food.setText("Food");
        drink.setText("Drink");
        food.setVisible(false);
        drink.setVisible(false);
    }
   
    private ProjectSignUp extractSignUpFromFields() {
        ProjectSignUp sign = new ProjectSignUp(); 
        sign.setAccount(user.getText());
        return sign;
    }
    
    private Product extractProductFromFields() {
        Product pro = new Product(); 
        pro.setName(productname.getText());
        return pro;
    }
    private Category extractFoodFromFields() {
        Category ca = new Category(); 
         ca.setCat_name(food.getText());
        return  ca;
    }
    private Category extractDrinkFoodFromFields() {
        Category ca = new Category(); 
         ca.setCat_name(drink.getText());
        return  ca;
    }
}
