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
    public void Setdata(Product a){
        this.pd = a;
        nameproduct.setText(a.getName());
        infomaitonselect(a.getName());
    }
}
