/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import Project.Data.Order;
import Project.Data.OrderDAO;
import Project.Data.OrderDAOImpl;
import Project.Data.ProjectSignUp;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    private JFXButton Home;

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
    void btnHome(ActionEvent event) throws IOException {
        ProjectSignUp Client = extractSignUpFromFields();
        Nagatice.getInstance().goToClient(Client);
    }
    public void initialize(ProjectSignUp p){
        this.psu = p;
        if(this.psu != null){
            user.setText(p.getAccount());
            tableview.setItems(or.selectShopingcart(p.getAccount()));
            orderID.setCellValueFactory((product) -> {
                return product.getValue().getIDProperty();
            });
            product.setCellValueFactory((product) -> {
                return product.getValue().getProductIDProperty();
            });
            quantity.setCellValueFactory((product) -> {
                return product.getValue().getQuantityProperty();
            });
            totalprice.setCellValueFactory((product) -> {
                return product.getValue().getTotalPriceProperty();
            });
            
            date.setCellValueFactory((product) -> {
                return product.getValue().getDateProperty();
            });
            
            
            
            
            
        }
    }
     private ProjectSignUp extractSignUpFromFields() {
        ProjectSignUp sign = new ProjectSignUp(); 
        sign.setAccount(user.getText());
        return sign;
    }
}
