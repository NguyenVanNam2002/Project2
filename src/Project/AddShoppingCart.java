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
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 *
 * @author Admin
 */
public class AddShoppingCart {
    private OrderDAO od = new OrderDAOImpl();
    private Product pd;
    private ProjectSignUp psu;
      @FXML
    private Text user;
    final int initialValue = 1;
    @FXML
    private Text id;
    @FXML
    private JFXButton order;
    @FXML
    private Spinner<Integer> sprinner;
    
    SpinnerValueFactory<Integer> valueFactory = 
               new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 50, initialValue);
 
    @FXML
    private ImageView imageview;
    @FXML
    private Text thename;

    @FXML
    private Text category;

    @FXML
    private Text theprice;

    @FXML
    private Text proties;
    @FXML
    private Text ordersucess;
    @FXML
    private Text q;

    @FXML
    private Text t;


    @FXML
    void btnorder(ActionEvent event) throws IOException {
        try {
                if(equal(Integer.parseInt(id.getText()) ,user.getText())){
                    Order os = change();
                    boolean result = od.update(os);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Thêm vào giỏ thành công !");
                    Optional<ButtonType> confirmationResponse
                            = alert.showAndWait();
                    if (confirmationResponse.get() == ButtonType.OK) {
                        Select(Integer.parseInt(id.getText()));
                    }
                    
                }else{
                    Order ord = extactFromfiled();
                    ord = od.insert(ord);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Thêm vào giỏ thành công !");
                    Optional<ButtonType> confirmationResponse
                            = alert.showAndWait();
                    if (confirmationResponse.get() == ButtonType.OK) {
                        Select(Integer.parseInt(id.getText()));
                    }
                }
            } catch (Exception e) {
            }
     }

     public void initialize(){
          String[] styleClasses = new String[] { "", 
                Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL
 
        };
       
       
       for (String styleClass : styleClasses) {
            sprinner.setValueFactory(valueFactory);
             sprinner.getStyleClass().add(styleClass);
        }
       
       id.setVisible(false);
       q.setVisible(false);
       t.setVisible(false);
       user.setVisible(false);
    }

    public void infomaitonselect(int user){
        String sql = "SELECT  p.* , c.* FROM products as p join category as c on p.CategoryID = c.CategoryID WHERE p.ProductID = ? ";
       
        try(Connection con = DbProject.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            ){
             stmt.setInt(1, user);
            try (ResultSet rs = stmt.executeQuery()) {
                if(rs.next()){
                    id.setText(Integer.toString(rs.getInt("p.ProductID")));
                    category.setText(rs.getString("c.NameC"));
                    theprice.setText(rs.getString("p.Price"));
                    proties.setText(rs.getString("p.Properties"));
                    Image myimage = new Image(getClass().getResourceAsStream(rs.getString("p.ImgLink")));
                    imageview.setImage(myimage);
                    
                }
            }
            stmt.close();
            con.close();
        } catch (Exception e) {
        }
    }
    
    private Order extactFromfiled(){
        Order or = new Order();
        or.setAccount(user.getText());
        or.setProductID(id.getText());
        or.setQuantity(sprinner.getValue());
        int d = Integer.parseInt(theprice.getText()) * sprinner.getValue();
        or.setTotalPrice(d); 
        return or;
        
    }
    
    private boolean equal(int id , String user){
        String sql = "SELECT * FROM ShoppingCart WHERE productID = ? and accounts = ?";
        try (
                Connection conn = DbProject.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);) {

            stmt.setInt(1, id);
            stmt.setString(2, user);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }
    
    private void Select(int id){
        String sql = "SELECT o.*,p.* FROM  ShoppingCart as o join products as p on o.productID = p.productID WHERE p.ProductID = ? ";
        try (Connection con = DbProject.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ){
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
             q.setText(Integer.toString(rs.getInt("quantity")));
             t.setText(rs.getString("Total_price"));
            }
        } catch (Exception e) {
        }
        
    }
    
    private Order change(){
        Order oss = new Order();
        oss.setProductID(id.getText());
        oss.setQuantity(Integer.parseInt(q.getText()) + sprinner.getValue());
        int d = Integer.parseInt(theprice.getText()) * sprinner.getValue();
        oss.setTotalPrice(Integer.parseInt(t.getText()) + d);
        return oss;
    }
    
    public void setData(ProjectSignUp u , Product a){
        this.psu = u;
        this.pd= a;
        user.setText(u.getAccount());
        infomaitonselect(a.getID());
        Select(a.getID());
    
    }

    void initialize(ProjectSignUp p, Product d) {
         this.psu = p;
        this.pd = d;
        if(this.psu != null){
            user.setText(p.getAccount());  
        }
        if(this.pd != null){
            thename.setText(d.getName());
            id.setText(Integer.toString(d.getID()));
            infomaitonselect(d.getID());
            Select(d.getID());
        }
    }
    private ProjectSignUp extractSignUpFromFields() {
        ProjectSignUp sign = new ProjectSignUp();
        sign.setAccount(user.getText());
        return sign;
    }
    private Product extractProductFromFields() {
        Product sign = new Product();
        sign.setName(thename.getText());
        sign.setId(Integer.parseInt(id.getText()));
        return sign;
    }
}
