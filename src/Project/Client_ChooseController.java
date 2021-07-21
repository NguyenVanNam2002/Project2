/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import Project.Data.Category;
import Project.Data.Product;
import Project.Data.ProjectSignUp;
import Project.DbProject.DbProject;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

/**
 *
 * @author icom
 */
public class Client_ChooseController {
    private Category cate;
    private ProjectSignUp psu;
    private Product pd;
      @FXML
    private Text user;

    @FXML
    private Text id;

    @FXML
    private JFXButton menu;

    @FXML
    private GridPane grid;
    @FXML
    private Text drink;

    @FXML
    private Text food;
    @FXML
    private Text thename;

    @FXML
    private ImageView imageview;

    @FXML
    private Text theprice;
    
    @FXML
    private Text categoryname;
    @FXML
    private TextField productname;

    ObservableList<Product> list = FXCollections.observableArrayList();
    ObservableList<Product> lis = FXCollections.observableArrayList();

    private Product extractProductFromFields() {
        Product sign = new Product();
        sign.setName(thename.getText());
        return sign;
    }
    @FXML
    void btnSearchClick(ActionEvent event) throws IOException {
        ProjectSignUp account = extractSignUpFromFields();
        Product name = extractSearchFromFields();
        Nagatice.getInstance().goToSearch(account, name);
    }
    @FXML
    void btnall(ActionEvent event) throws IOException {
        ProjectSignUp menus = extractSignUpFromFields();
        Nagatice.getInstance().goToChoose(menus);
    }
      @FXML
    void btndrink(ActionEvent event) throws IOException {
        ProjectSignUp menus = extractSignUpFromFields();
        Category cate = extractDrinkFoodFromFields();
        Nagatice.getInstance().goToChoose(menus,cate);
    }

    @FXML
    void btnfood(ActionEvent event) throws IOException {
         ProjectSignUp menus = extractSignUpFromFields();
        Category cate = extractFoodFromFields();
        Nagatice.getInstance().goToChoose(menus,cate);
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
    
    
    private Product extractSearchFromFields() {
        Product pro = new Product(); 
        pro.setName(productname.getText());
        return pro;
    }
    private void setChosenSnack(Product snack) {
        thename.setText(snack.getName());
        theprice.setText(snack.getPrice().toString() + " VND");
        Image image = new Image(getClass().getResourceAsStream(snack.getImg()));
        imageview.setImage(image);

    }
    
    @FXML
    void btnShopping(MouseEvent event) throws IOException {
        ProjectSignUp osu = extractSignUpFromFields();
        Nagatice.getInstance().goToShopping(osu);
    }
    @FXML
    void btnmenu(ActionEvent event) throws IOException {
        ProjectSignUp account = extractSignUpFromFields();
        Nagatice.getInstance().goToClient(account);
    }

   
    private MyListener myListener;

    public void initialize(ProjectSignUp p , Category u) {
        this.psu = p;
        this.cate = u;
        if (this.psu != null) {
            user.setText(p.getAccount());
        }
        if(this.cate != null){
            categoryname.setText(u.getCat_name());
            where(u.getCat_name());
        }
    }
    public void initialize(ProjectSignUp p) {
        this.psu = p;
        if (this.psu != null) {
            user.setText(p.getAccount());
            Index();
        }
       
    }
    private ProjectSignUp extractSignUpFromFields() {
        ProjectSignUp sign = new ProjectSignUp();
        sign.setAccount(user.getText());
        return sign;
    }
     public void initialize(){
        food.setText("Food");
        drink.setText("Drink");
        categoryname.setVisible(false);
        food.setVisible(false);
        drink.setVisible(false);
    }
     public void where(String b) {
        String sql = "SELECT ImgLink,Name,Price FROM products as p join category as c on p.CategoryID = c.CategoryID where c.NameC = ?";
        try (Connection conn = DbProject.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);) {

            stmt.setString(1, b);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Product u = new Product();

                u.setName(rs.getString("Name"));
                u.setPrice(rs.getInt("Price"));
                u.setImg(rs.getString("ImgLink"));
                list.add(u);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
        if (list.size() > 0) {
            myListener = (Product snack) -> {
                setChosenSnack(snack);
                if(thename.getText() != null){
                    ProjectSignUp acc = extractSignUpFromFields();
                    Product ea = extractProductFromFields();
                    try {
                        Nagatice.getInstance().goToViewC2(acc, ea);
                    } catch (IOException ex) {
                        Logger.getLogger(Client_ChooseController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };
        }
        
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < list.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(list.get(i), myListener);
                if (column == 5) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new Insets(10));

            }
        } catch (IOException e) {
            
        }
    }
    public void Index() {
        String sql = "SELECT ImgLink,Name,Price FROM products as p join category as c on p.CategoryID = c.CategoryID ";
        try (Connection conn = DbProject.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Product u = new Product();

                u.setName(rs.getString("Name"));
                u.setPrice(rs.getInt("Price"));
                u.setImg(rs.getString("ImgLink"));
                list.add(u);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
        if (list.size() > 0) {
            myListener = (Product snack) -> {
                setChosenSnack(snack);
                if(thename.getText() != null){
                    ProjectSignUp acc = extractSignUpFromFields();
                    Product ea = extractProductFromFields();
                    try {
                        Nagatice.getInstance().goToViewC2(acc, ea);
                    } catch (IOException ex) {
                        Logger.getLogger(Client_ChooseController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };
        }
        
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < list.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(list.get(i), myListener);
                if (column == 5) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new Insets(10));

            }
        } catch (IOException e) {
            
        }
    }
    
    public void Search(String b) {
        String sql = "SELECT * FROM products WHERE `Name` LIKE CONCAT ('%' , ? ,'%') ";
        try (Connection conn = DbProject.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);) {

            stmt.setString(1, b);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Product u = new Product();
                u.setId(rs.getInt("ProductID"));
                u.setName(rs.getString("Name"));
                u.setPrice(rs.getInt("Price"));
                u.setImg(rs.getString("ImgLink"));
                u.setProperties(rs.getString("Properties"));
                lis.add(u);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        if (lis.size() > 0) {
            
            myListener = (Product snack) -> {
                setChosenSnack(snack);
                if(thename.getText() != null){
                    ProjectSignUp acc = extractSignUpFromFields();
                    Product ea = extractProductFromFields();
                    try {
                        Nagatice.getInstance().goToViewC2(acc, ea);
                    } catch (IOException ex) {
                        Logger.getLogger(Client_ChooseController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };
        }
        
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < lis.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(lis.get(i), myListener);
                if (column == 5) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new Insets(10));

            }
        } catch (IOException e) {
            
        }
    }
    
    
    public void initialize(ProjectSignUp p , Product d ) {
        this.psu = p;
        this.pd = d;
        if (this.psu != null) {
            user.setText(p.getAccount());
        }
        if(this.pd != null ){

            Search(d.getName());
        }
       

    }
}
