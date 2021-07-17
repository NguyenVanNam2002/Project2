/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import Project.Data.Order;
import Project.Data.OrderDAO;
import Project.Data.OrderDAOImpl;
import Project.Data.Product;
import Project.Data.ProjectSignUp;
import Project.DbProject.DbProject;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

/**
 *
 * @author Admin
 */
public class Client_Search {
    private OrderDAO od = new OrderDAOImpl();
    private ProjectSignUp psu;
    private Product pd;
    final int initialValue = 1;
    @FXML
    private Text user;
    @FXML
    private Spinner<Integer> sprinner;
     
    SpinnerValueFactory<Integer> valueFactory = 
               new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 50, initialValue);
 
    @FXML
    private Text id;

    @FXML
    private JFXButton menu;

    @FXML
    private GridPane grid;

    @FXML
    private Text name;

    @FXML
    private ImageView imageview;

    @FXML
    private Text Price;

    @FXML
    private JFXButton btnSearch;

    @FXML
    private TextField productname;
    @FXML
    private TextArea properties;
      @FXML
    private JFXButton add;
    @FXML
    void btnShopping(ActionEvent event) throws IOException {
        ProjectSignUp osu = extractSignUpFromFields();
        Nagatice.getInstance().goToShopping(osu);
    }
    @FXML
    void btnadd(ActionEvent event) {
        try {
                Order ord = extactFromfiled();
                ord = od.insert(ord);
//                ordersucess.setText("Bạn order thành công");
            } catch (Exception e) {
//                ordersucess.setText("Bạn order thất bại , hệ thống đang bảo trì");
            }
    }
    ObservableList<Product> lis = FXCollections.observableArrayList();
    private MyListener myListener;
    @FXML
    void btnSearchClick(ActionEvent event) throws IOException {
        ProjectSignUp account = extractSignUpFromFields();
        Product namee = extractProductFromFields();
        Nagatice.getInstance().goToSearch(account, namee);
    }

    @FXML
    void btnmenu(ActionEvent event) throws IOException {
        ProjectSignUp acc = extractSignUpFromFields();
        Nagatice.getInstance().goToClient(acc);
    }
    private ProjectSignUp extractSignUpFromFields() {
        ProjectSignUp sign = new ProjectSignUp();
        sign.setAccount(user.getText());
        return sign;
    }

    private Product extractProductFromFields() {
        Product sign = new Product();
        sign.setName(productname.getText());
        return sign;
    }
    public void initialize(ProjectSignUp p , Product d ) {
        this.psu = p;
        this.pd = d;
        if (this.psu != null) {
            user.setText(p.getAccount());
        }
        if(this.pd != null ){
//            name.setText(d.getName());
            where(d.getName());
        }
       

    }
     public void initialize(){
       sprinner.setValueFactory(valueFactory);
    }
    private void setChosenSnack(Product snack) {
        id.setText(snack.getID().toString());
        name.setText(snack.getName());
        Price.setText(snack.getPrice().toString());
        Image image = new Image(getClass().getResourceAsStream(snack.getImg()));
        imageview.setImage(image);
        properties.setText(snack.getProperties());

    }

    public void where(String b) {
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
            setChosenSnack(lis.get(0));
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
        } catch (IOException e) {
            
        }
    }
     private Order extactFromfiled(){
        Order or = new Order();
        or.setAccount(user.getText());
        or.setProductID(id.getText());
        or.setQuantity(sprinner.getValue());
        int d = Integer.parseInt(Price.getText()) * sprinner.getValue();
        or.setTotalPrice(d);
        
        LocalDateTime now = LocalDateTime.now();
        String b = now.toString();
        or.setDate(b);
        
        return or;
        
    }
    
}
