/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import Project.Data.Order;
import Project.Data.OrderDAO;
import Project.Data.OrderDAOImpl;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 *
 * @author Admin
 */
public class Admin_ShoppingCart {
    private OrderDAO or = new OrderDAOImpl();
    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private ImageView image;

    @FXML
    private Text u;

    @FXML
    private TableView<Order> tableview;

    @FXML
    private TableColumn<Order, Integer> orderID;

    @FXML
    private TableColumn<Order, String> product;

    @FXML
    private TableColumn<Order, Integer> quantity;

    @FXML
    private TableColumn<Order, Integer> totalprice;

    @FXML
    private TableColumn<Order, String> date;

    @FXML
    private TableColumn<Order,String> account;

    @FXML
    private JFXButton btncategory;

    @FXML
    private JFXButton btnfeedback;

    @FXML
    private JFXButton btncustomer;

    @FXML
    private JFXButton logout;

    @FXML
    private Text theviewname;

    @FXML
    private Text theviewprice;

    @FXML
    private Text theviewpropertis;
    
    public void initialize(){
    
     
    }
    @FXML
    void btnDeleteClick(ActionEvent event) {

    }

    @FXML
    void btnUpdateClick(ActionEvent event) {

    }

     @FXML
    void btncategory(ActionEvent event) throws IOException {
        Nagatice.getInstance().goToCategoryIndex();
    }

    @FXML
    void btncustomer(ActionEvent event) throws IOException {
        Nagatice.getInstance().goToCustomerIndex();
    }

    @FXML
    void btnfeedback(ActionEvent event) throws IOException {
        Nagatice.getInstance().goToFeedbackIndex();
    }

    @FXML
    void btnlogout(ActionEvent event) throws IOException {
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
    void btnproduct(ActionEvent event) throws IOException {
        Nagatice.getInstance().goToIndexProduct();
    }
}
