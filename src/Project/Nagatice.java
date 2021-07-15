/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;




import Project.Data.Category;
import Project.Data.Feedback;
import Project.Data.Product;
import Project.Data.ProjectSignUp;
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
    public static final String PRODUCT_INDEX_FXML = "ProductIndexUI.fxml";
    public static final String PRODUCT_EDIT_FXML = "ProductEditUI.fxml";
    public static final String SHOPPING_ADMIN_FXML = "Admin_ShoppingCart.fxml";
    // Client
    public static final String CLIENT_FXML = "Client_user.fxml";
    public static final String CHOOSE_FXML = "Choose.fxml";
    public static final String SETTING_FXML = "Client_Settings.fxml";
    public static final String PASSWORD_FXML = "Client_Password.fxml";
    public static final String INFORMATION_FXML = "Client_Infomation.fxml";
    public static final String VIEWC2_FXML = "Client_ViewC2.fxml";
    public static final String SEARCH_FXML = "Client_Search.fxml";
    public static final String FEED_BACK_CLIENT_FXML = "FXML.fxml";
    public static final String SHOPPING_CLIENT_FXML = "Shoping_Cart.fxml";
    

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
    public void goToIndexProduct() throws IOException {
        this.goTo(PRODUCT_INDEX_FXML);
    }
    public void goToOrderAdmin() throws IOException {
        this.goTo(SHOPPING_ADMIN_FXML);
    }
    public void goToEditProduct(Product editProduct) throws IOException {
        this.goTo(PRODUCT_EDIT_FXML);
        ProductEditUIController ctrl = loader.getController();
        ctrl.initialize(editProduct);
    }
    
   //Client
    public void goTo() throws IOException {
        this.goTo(CLIENT_FXML);
    }
    
    public void goToClient(ProjectSignUp p) throws IOException {
        this.goTo(CLIENT_FXML);
        Client_Controller ctrl = loader.getController();
        ctrl.initialize(p);
    }
    
    public void goToSearch(ProjectSignUp p, Product d) throws IOException {
        this.goTo(SEARCH_FXML);
        Client_Search  ctrl = loader.getController();
        ctrl.initialize(p, d);
    }

    public void goToChoose(ProjectSignUp p , Category u) throws IOException {
        this.goTo(CHOOSE_FXML);
        Client_ChooseController ctrl = loader.getController();
        ctrl.initialize(p , u);
    }
    public void goToViewC2(ProjectSignUp p, Product d) throws IOException {
        this.goTo(VIEWC2_FXML);
        Client_ViewC2Function  ctrl = loader.getController();
        ctrl.initialize(p, d);
    }
        public void goFeedbackClient(ProjectSignUp p, Product d) throws IOException {
        this.goTo(FEED_BACK_CLIENT_FXML);
        Client_feedback ctrl = loader.getController();
        ctrl.initialize(p, d);
    }
   
    public void goToSeting(ProjectSignUp p) throws IOException {
        this.goTo(SETTING_FXML);
        Client_setting ctrl = loader.getController();
        ctrl.initialize(p);
    }
    public void goToChangePassword(ProjectSignUp p) throws IOException {
        this.goTo(PASSWORD_FXML);
        Client_passoword ctrl = loader.getController();
        ctrl.initialize(p);
    }
    
    public void goToInformationClient(ProjectSignUp p) throws IOException {
        this.goTo(INFORMATION_FXML);
        Client_infomation ctrl = loader.getController();
        ctrl.initialize(p);
    }
    
    public void goToShopping(ProjectSignUp p) throws IOException {
        this.goTo(SHOPPING_CLIENT_FXML);
        Shopping_cart  ctrl = loader.getController();
        ctrl.initialize(p);
    }
   
}
