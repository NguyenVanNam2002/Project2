/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import Project.Data.Category;
import Project.Data.Order;
import Project.Data.OrderDAO;
import Project.Data.OrderDAOImpl;
import Project.Data.Product;
import Project.Data.ProjectSignUp;
import Project.DbProject.DbProject;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
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
public class Client_ViewC2Function {
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
    private JFXButton Home;
    @FXML
    private Spinner<Integer> sprinner;
    
    SpinnerValueFactory<Integer> valueFactory = 
               new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 50, initialValue);
 
    @FXML
    private ImageView imageview;

    @FXML
    private Text proties;
    @FXML
    private JFXTextField category;
    @FXML
    private JFXTextField thename;
    @FXML
    private Text ordersucess;
    @FXML
    private JFXTextField theprice;

    @FXML
    private JFXButton menu;
    
    @FXML
    void btnorder(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Bạn chắc chắn muốn order ? ");
        alert.setTitle("Lưu ý ");
        Optional<ButtonType> confirmationResponse
                = alert.showAndWait();
        if (confirmationResponse.get() == ButtonType.OK) {
            try {
                Order ord = extactFromfiled();
                ord = od.insert(ord);
                ordersucess.setText("Bạn order thành công");
            } catch (Exception e) {
                ordersucess.setText("Bạn order thất bại , hệ thống đang bảo trì");
            }
            
            
        } 
    }
    @FXML
    void btnHome(ActionEvent event) throws IOException {
       ProjectSignUp menus = extractPasswordFromFields();
        Category cate = extractFromFields();
        Nagatice.getInstance().goToChoose(menus,cate);
    }
    @FXML
    void btnfeedback(ActionEvent event) throws IOException {
        ProjectSignUp acc = extractPasswordFromFields();
        Product ea = extractProductFromFields();
        Nagatice.getInstance().goFeedbackClient(acc, ea);
    }
     public void initialize(ProjectSignUp p , Product d){
        this.psu = p;
        this.pd = d;
        if(this.psu != null){
            user.setText(p.getAccount());  
        }
        if(this.pd != null){
            thename.setText(d.getName());
            infomaitonselect(d.getName());
        }
    }
     public void initialize(){
       sprinner.setValueFactory(valueFactory);
       id.setVisible(false);
    }
     private Product extractProductFromFields() {
        Product sign = new Product();
        sign.setName(thename.getText());
        return sign;
    }

    private ProjectSignUp extractPasswordFromFields() {
        ProjectSignUp sign = new ProjectSignUp(); 
        sign.setAccount(user.getText());
        return sign;
    }
    private Category extractFromFields() {
        Category ca = new Category(); 
         ca.setCat_name(category.getText());
        return  ca;
    }
    public void infomaitonselect(String user){
        String sql = "SELECT  p.* , c.* FROM products as p join category as c on p.CategoryID = c.CategoryID WHERE p.Name = ? ";
       
        try(Connection con = DbProject.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            ){
             stmt.setString(1, user);
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
        
        LocalDateTime now = LocalDateTime.now();
        String b = now.toString();
        or.setDate(b);
        
        return or;
        
    }
}
