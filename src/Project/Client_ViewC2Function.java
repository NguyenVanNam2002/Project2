/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import Project.Data.Product;
import Project.Data.ProjectSignUp;
import Project.DbProject.DbProject;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 *
 * @author Admin
 */
public class Client_ViewC2Function {
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
    private TextArea proties;

    @FXML
    private JFXTextField thename;

    @FXML
    private JFXTextField theprice;

    @FXML
    private JFXButton menu;

    @FXML
    void btnHome(ActionEvent event) throws IOException {
        ProjectSignUp acc = extractPasswordFromFields();
        Nagatice.getInstance().goToClient(acc);
    }

    @FXML
    void btnmenu(ActionEvent event) throws IOException {
        ProjectSignUp menus = extractPasswordFromFields();
        Nagatice.getInstance().goToChoose(menus);
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
    private ProjectSignUp extractPasswordFromFields() {
        ProjectSignUp sign = new ProjectSignUp(); 
        sign.setAccount(user.getText());
        return sign;
    }
    public void infomaitonselect(String user){
        String sql = "SELECT  * FROM products WHERE Name = ? ";
       
        try(Connection con = DbProject.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            ){
             stmt.setString(1, user);
            try (ResultSet rs = stmt.executeQuery()) {
                if(rs.next()){
                    theprice.setText(rs.getString("Price")+" VNĐ");
                    proties.setText(rs.getString("Properties"));
                    Image myimage = new Image(getClass().getResourceAsStream(rs.getString("ImgLink")));
                    imageview.setImage(myimage);
                    
                }
            }
            stmt.close();
            con.close();
        } catch (Exception e) {
        }
    }
}
