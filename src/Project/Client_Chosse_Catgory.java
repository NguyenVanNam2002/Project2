/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import Project.Data.Category;
import Project.Data.ProjectSignUp;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 *
 * @author Admin
 */
public class Client_Chosse_Catgory {
    private ProjectSignUp psu;
    @FXML
    private Text user;

    @FXML
    private JFXButton menu;

    @FXML
    private Text food;

    @FXML
    private Text drink;

    @FXML
    void btnmenu(ActionEvent event) throws IOException {
        ProjectSignUp home = extractPasswordFromFields();
        Nagatice.getInstance().goToClient(home);
    }

    @FXML
    void clickDrink(MouseEvent event) throws IOException {
        ProjectSignUp menus = extractPasswordFromFields();
        Category cate = extractDrinkFoodFromFields();
        Nagatice.getInstance().goToChoose(menus,cate);
    }

    @FXML
    void clickFood(MouseEvent event) throws IOException {
        ProjectSignUp menus = extractPasswordFromFields();
        Category cate = extractFoodFromFields();
        Nagatice.getInstance().goToChoose(menus,cate);
    }
    public void initialize(ProjectSignUp p) {
        this.psu = p;
        if (this.psu != null) {
            user.setText(p.getAccount());
        }

    }
    private ProjectSignUp extractPasswordFromFields() {
        ProjectSignUp sign = new ProjectSignUp(); 
        sign.setAccount(user.getText());
        return sign;
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
    public void initialize() {
        food.setText("Food");
        drink.setText("Drink");

    }
    
}
