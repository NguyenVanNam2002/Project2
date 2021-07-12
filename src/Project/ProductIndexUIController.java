/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import Project.Data.Product;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
/**
 *
 * @author DELL
 */
public class ProductIndexUIController {

    @FXML
    private TableView<Product> tvProduct;

    @FXML
    private TableColumn<Product, String> tcName;

    @FXML
    private TableColumn<Product, Integer> tcPrice;

    @FXML
    private TableColumn<Product, String> tcProperties;

    @FXML
    private TableColumn<Product, String> tcImages;

    @FXML
    private TableColumn<Product, String> tcCategory;

    @FXML
    private JFXButton btnInsert;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton back;

    @FXML
    private ImageView image;
    @FXML
    private Text u;
    @FXML
    private TextField txtSearch;
    @FXML
    void btnBack(ActionEvent event) throws IOException {
        Nagatice.getInstance().goAdmin();
    }

    @FXML
    void btnDeleteClick(ActionEvent event) {
        Product selectedProduct = tvProduct.getSelectionModel().getSelectedItem();

        if (selectedProduct == null) {
            selectProductWarning();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Are you sure to do you want delete : " + "\n"
                    + "Name :" + selectedProduct.getName() + "\n"
                    + "Price :" + selectedProduct.getPrice() + "\n"
                    + "Category :" + selectedProduct.getLevel());
            alert.setTitle("Deleting");
            Optional<ButtonType> confirmationResponse
                    = alert.showAndWait();
            if (confirmationResponse.get() == ButtonType.OK) {
                Product deleteProduct = tvProduct.getSelectionModel().getSelectedItem();
                boolean result = Product.delete(deleteProduct);

                if (result) {
                    tvProduct.getItems().remove(deleteProduct);
                    System.out.println("A Product is Deleted ");
                } else {
                    System.err.println("No Product is Deleted");
                }
            }
        }

    }

    @FXML
    void btnInsertClick(ActionEvent event) throws IOException {
        Nagatice.getInstance().goToEditProduct(null);
    }

    @FXML
    void btnUpdateClick(ActionEvent event) throws IOException {
        Product updateProduct = tvProduct.getSelectionModel().getSelectedItem();

        if (updateProduct == null) {
            selectProductWarning();
        } else {
            Nagatice.getInstance().goToEditProduct(updateProduct);
        }
    }

    
    @FXML
    void onMouseClick(MouseEvent event) {
        Product nul = tvProduct.getSelectionModel().getSelectedItem();
               if (nul != null) {
                   Image myimage = new Image(getClass().getResourceAsStream(nul.getImg()));
                   image.setImage(myimage);
                }  
    }


    private void selectProductWarning() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Please select a Product");
        alert.setHeaderText("A product must be selected for the operation ");
        alert.showAndWait();
    }

    private void selectImgWarning() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Please select a Images");
        alert.setHeaderText("Please enter selected you want choose ! ");
        alert.showAndWait();
    }

    public void initialize() throws SQLException {
        System.out.println("Index Controller");

        tvProduct.setItems(Product.selectAll());
        tcName.setCellValueFactory((product) -> {
            return product.getValue().getNameProperty();
        });

        tcPrice.setCellValueFactory((product) -> {
            return product.getValue().getPriceProperty();
        });

        tcProperties.setCellValueFactory((product) -> {
            return product.getValue().getPropertiesProperty();
        });
        tcImages.setCellValueFactory((product) -> {
            return product.getValue().getImgProperty();
        });

        tcCategory.setCellValueFactory((product) -> {
            return product.getValue().getLevelProperty();
        });
        Search();
    }
    private void Search() throws SQLException {
        FilteredList<Product> filteredData = new FilteredList<>(Product.selectAll(), b -> true);
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(pro -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (pro.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (String.valueOf(pro.getPrice()).indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (pro.getProperties().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (pro.getLevel().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                return false; // Does not match.
            });
        });
        SortedList<Product> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tvProduct.comparatorProperty());
        tvProduct.setItems(sortedData);

    }

}
