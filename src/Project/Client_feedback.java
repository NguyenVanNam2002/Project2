/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import Project.Data.Feedback;
import Project.Data.Product;
import Project.Data.ProjectSignUp;
import Project.DbProject.DbProject;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 *
 * @author Admin
 */
public class Client_feedback {
    private Product pd;
    private ProjectSignUp psu;
    @FXML
    private Text user;

    @FXML
    private Text id;

    @FXML
    private JFXButton Home;

    @FXML
    private ImageView imageview;

    @FXML
    private JFXButton feedback;

    @FXML
    private TextArea conten;

    @FXML
    private Text nameproduct;
    @FXML
    private Text succes;
     @FXML
    private TextField productname;
    @FXML
    void btnHome(ActionEvent event) throws IOException {
        ProjectSignUp menus = extractPasswordFromFields();
        Product pro = extractProductFromFields();
        Nagatice.getInstance().goToViewC2(menus,pro);
    }
     @FXML
    void btnShopping(MouseEvent event) throws IOException {
        ProjectSignUp osu = extractPasswordFromFields();
        Nagatice.getInstance().goToShopping(osu);
    }
      @FXML
    void btnSearchClick(ActionEvent event) throws IOException {
        ProjectSignUp account = extractPasswordFromFields();
        Product name = extractSearchFromFields();
        Nagatice.getInstance().goToSearch(account, name);
    }
     private Product extractSearchFromFields() {
        Product sign = new Product();
        sign.setName(productname.getText());
        return sign;
    }
    @FXML
    void btnfeedback(ActionEvent event) {
        
        try {
            
            Feedback feed = infomationinsert();
            feed = Feedback.insert(feed);
            succes.setText("Cảm ơn đã đóng góp ý kiến");
        } catch (SQLException ex) {
            Logger.getLogger(Client_feedback.class.getName()).log(Level.SEVERE, null, ex);
            succes.setText("Vui lòng kiểm tra lại ý kiến của bạn");
        }
        
    }
     private ProjectSignUp extractPasswordFromFields() {
        ProjectSignUp sign = new ProjectSignUp(); 
        sign.setAccount(user.getText());
        return sign;
    }
      private Product extractProductFromFields() {
        Product sign = new Product();
        sign.setName(nameproduct.getText());
        return sign;
    }
     public void initialize(ProjectSignUp p , Product d){
        this.psu = p;
        this.pd = d;
        if(this.psu != null){
            user.setText(p.getAccount());  
        }
        if(this.pd != null){
            nameproduct.setText(d.getName());
            infomaitonselect(d.getName());
        }
    }
     public void initialize(){
      nameproduct.setVisible(false);
    }
    private Feedback infomationinsert(){
        Feedback fe= new Feedback();
        fe.setContent(conten.getText());
        fe.setProductID(nameproduct.getText());
        return fe;
    } 
    private boolean validation(){
        if(conten.getText().isEmpty()){
            succes.setText("Vui lòng viết ý kiến của bạn vào sản phẩm");
        }
        if(conten.getText().length() < 10 ){
            succes.setText("Ý kiến của bạn quá ngắn");
        }
        return true;
    }
   
    public void infomaitonselect(String user){
        String sql = "SELECT  p.* , c.* FROM products as p join category as c on p.CategoryID = c.CategoryID WHERE p.Name = ? ";
       
        try(Connection con = DbProject.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            ){
             stmt.setString(1, user);
            try (ResultSet rs = stmt.executeQuery()) {
                if(rs.next()){
                    Image myimage = new Image(getClass().getResourceAsStream(rs.getString("p.ImgLink")));
                    imageview.setImage(myimage);
                    
                }
            }
            stmt.close();
            con.close();
        } catch (Exception e) {
        }
    }
}
