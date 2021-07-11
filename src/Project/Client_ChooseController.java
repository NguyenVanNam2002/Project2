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
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
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
    @FXML
    private Text user;

    @FXML
    private GridPane grid;

    @FXML
    private JFXButton menu;

    @FXML
    private JFXButton btnViewFood;

    @FXML
    private JFXButton btnViewDrink;
    @FXML
    private JFXComboBox<String> combobox;
    @FXML
    private Text name;

    @FXML
    private ImageView imageview;

    @FXML
    private Text Price;

    @FXML
    void onclickimage(MouseEvent event) throws IOException {
        ProjectSignUp acc = extractSignUpFromFields();
        Product ea = extractProductFromFields();
        Nagatice.getInstance().goToViewC2(acc, ea);
    }
    ObservableList<Product> lis = FXCollections.observableArrayList();

    private Product extractProductFromFields() {
        Product sign = new Product();
        sign.setName(name.getText());
        return sign;
    }

    private void setChosenSnack(Product snack) {
        name.setText(snack.getName());
        Price.setText(snack.getPrice().toString() + " VND");
        Image image = new Image(getClass().getResourceAsStream(snack.getImg()));
        imageview.setImage(image);

    }

    @FXML
    void btnmenu(ActionEvent event) throws IOException {
        ProjectSignUp account = extractSignUpFromFields();
        Nagatice.getInstance().goToChooseCategory(account);
    }

   
    private MyListener myListener;

    public void initialize(ProjectSignUp p , Category u) {
        this.psu = p;
        this.cate = u;
        if (this.psu != null) {
            user.setText(p.getAccount());
        }
        if(this.cate != null){
            where(u.getCat_name());
        }

    }

    private ProjectSignUp extractSignUpFromFields() {
        ProjectSignUp sign = new ProjectSignUp();
        sign.setAccount(user.getText());
        return sign;
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
                lis.add(u);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        if (lis.size() > 0) {
//            setChosenSnack(list.get(0));
            myListener = (Product snack) -> {
                setChosenSnack(snack);
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
                if (column == 4) {
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
            lis.removeAll(lis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Sting(String a) {
        try {
            Connection conn = DbProject.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT ImgLink,`Name`,Price FROM products ");
            while (rs.next()) {
                Product u = new Product();
                u.setName(rs.getString("Name"));
                u.setPrice(rs.getInt("Price"));
                u.setImg(rs.getString("ImgLink"));
                lis.add(u);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        if (lis.size() > 0) {

            myListener = new MyListener() {
                @Override
                public void onClickListener(Product snack) {
                    setChosenSnack(snack);
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

                if (column == 3) {
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
            if (lis != null) {
                lis.removeAll(lis);
            }
        } catch (IOException e) {

        }

    }
}
