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
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

/**
 *
 * @author Admin
 */
public class Shopping_cart {
    private OrderDAO or = new OrderDAOImpl();
    private ProjectSignUp psu;
   @FXML
    private Text user;
     @FXML
    private JFXButton orders;
    @FXML
    private JFXButton Home;

    @FXML
    private GridPane grid;

    @FXML
    private Text totalprice;
     @FXML
    private JFXButton update;
    @FXML
    private Text quantity;
     @FXML
    private Text ord;
    @FXML
    private Text productID;
    ObservableList<Order> ordr = FXCollections.observableArrayList();
    ObservableList<Product> pro = FXCollections.observableArrayList();
    @FXML
    void btnupdate(ActionEvent event) {

    }
    @FXML
    void btnDeleteClick(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Are you sure you want to delete the selected Category?");
        alert.setTitle("Deleting a Category");
        Optional<ButtonType> confirmationResponse
                = alert.showAndWait();
        if (confirmationResponse.get() == ButtonType.OK) {
            if(productID.getText() != null){
                delete(productID.getText());
                ProjectSignUp u =extractSignUpFromFields();
                Nagatice.getInstance().goToShopping(u);
            }else{
                
            }
        }
    }
      @FXML
    void btnorder(ActionEvent event) {
        Order orderss = extactFromfiled();
        orderss = or.insertOrder(orderss);
    }
    @FXML
    void btnHome(ActionEvent event) throws IOException {
        ProjectSignUp p = extractSignUpFromFields();
        Nagatice.getInstance().goToClient(p);
    }
    public void initialize(ProjectSignUp p){
        this.psu = p;
        if(this.psu != null){
            user.setText(p.getAccount());
            selectShopingcart(p.getAccount());
        }
    }
    public void initialize(){
      productID.setVisible(false);
      totalprice.setVisible(false);
      quantity.setVisible(false);
    }
    private void setChosenSnack(Order snack) {
        productID.setText(snack.getProductID());
        totalprice.setText(snack.getTotalPrice().toString());
        quantity.setText(snack.getQuantity().toString());
    }
     private ProjectSignUp extractSignUpFromFields() {
        ProjectSignUp sign = new ProjectSignUp(); 
        sign.setAccount(user.getText());
        return sign;
    } 
     
     private OrderListener orderlis;
    
    public void  selectShopingcart( String a ){
        String sql = "SELECT o.*,p.* FROM  order_detail as o join products as p on o.ProductID = p.ProductID WHERE Client_ID  = ? ";
        try (Connection con = DbProject.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ){
            stmt.setString(1, a);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Product p = new Product();
                 p.setImg(rs.getString("p.ImgLink"));
                 p.setName(rs.getString("p.Name"));
                 p.setPrice(rs.getInt("p.Price"));
                Order o = new Order();
                o.setQuantity(rs.getInt("o.Quantity"));
                o.setTotalPrice(rs.getInt("o.Total_price"));
                o.setProductID(Integer.toString(rs.getInt("o.ProductID")));
                ordr.add(o);
                pro.add(p);
            }
        } catch (Exception e) {
        }
        
        if (ordr.size() > 0) {
            orderlis = (Order snack) -> {
                
                setChosenSnack(snack);
            };
        }
        
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < ordr.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("OrderItem.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                OrderItem itemController = fxmlLoader.getController();
                itemController.setData(ordr.get(i), pro.get(i) , orderlis);
                if (column == 1) {
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
    
    public  boolean delete(String b) {
        String sql = "DELETE FROM order_detail WHERE ProductID = ?";
        try (
                Connection conn = DbProject.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);) {

            stmt.setInt(1, Integer.parseInt(b));

            int rowDeleted = stmt.executeUpdate();

            if (rowDeleted == 1) {
                return true;
            } else {
                System.err.println("No  deleted");
                return false;
            }

        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }
    
    private Order extactFromfiled(){
        Order orda = new Order();
        orda.setAccount(user.getText());
        orda.setProductID(productID.getText());
        orda.setQuantity(Integer.parseInt(quantity.getText()));
        int d = Integer.parseInt(totalprice.getText());
        orda.setTotalPrice(d);
        LocalDateTime now = LocalDateTime.now();
        String b = now.toString();
        orda.setDate(b);
        return orda;
        
    }
}
