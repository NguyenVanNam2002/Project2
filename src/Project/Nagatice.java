/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;



import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Admin
 */
public class Nagatice {
    public static final String login_FXML = "Login.fxml";
    public static final String sing_up_FXML = "SignUp.fxml";

    private FXMLLoader loader;
    private Stage stage = null;
    
    private static Nagatice nav = null;

    private Nagatice() {
    }
    
    public static Nagatice getInstance(){
        if (nav == null){
            nav = new Nagatice();
        }
        
        return nav;
    }
    
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }
    
    
    
    private void goTo(String fxml) throws IOException {
        this.loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxml));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        this.stage.setScene(scene);
    }
    
    public void goToIndex() throws IOException {
        this.goTo(login_FXML);
    }
    
    public void goSingup() throws IOException {
        this.goTo(sing_up_FXML);
    }
    
    
   
}
