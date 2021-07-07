/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;




import Project.Data.Category;
import Project.Data.Feedback;
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
    public static final String admin_FXML = "inAdmin.fxml";
    public static final String Category_INDEX_FXML = "CategoriesIndexUI.fxml";
    public static final String Category_EDIT_FXML = "CategoriesEditUI.fxml";
    public static final String Feedback_EDIT_FXML = "FeedBackEditUI.fxml";
    public static final String Feedback_index_FXML = "FeedBackIndex.fxml";
    public static final String Customer_index_FXML = "CustomerIndex.fxml";

    // Client
    public static final String CLIENT_FXML = "Client_user.fxml";
    public static final String CHOOSE_FXML = "Choose.fxml";
    public static final String FOOD_FXML = "Food.fxml";
    public static final String DRINK_FXML = "Drink.fxml";

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
     
    public void goAdmin() throws IOException {
        this.goTo(admin_FXML);
    }
    public void goToFeedbackIndex() throws IOException {
        this.goTo(Feedback_index_FXML);
    }
    
    public void goToCategoryIndex() throws IOException {
        this.goTo(Category_INDEX_FXML);
    }
    
    public void goToCustomerIndex() throws IOException {
        this.goTo(Customer_index_FXML);
    }
    
    public void goCategoryToEdit(Category editcategory) throws IOException {
        this.goTo(Category_EDIT_FXML);
        CategoryEditController ctrl = loader.getController();
        ctrl.initialize(editcategory);
    }
    
    public void goFeedbackToEdit(Feedback fe) throws IOException {
        this.goTo(Feedback_EDIT_FXML);
        FeedBackEditController ctrl = loader.getController();
        ctrl.initialize(fe);
    }
    
   //Client
    
    public void goToClient() throws IOException {
        this.goTo(CLIENT_FXML);
    }
    public void goToChoose() throws IOException {
        this.goTo(CHOOSE_FXML);
    }
    public void goToDrink() throws IOException {
        this.goTo(DRINK_FXML);
    }
    public void goToFood() throws IOException {
        this.goTo(FOOD_FXML);
    }
}
