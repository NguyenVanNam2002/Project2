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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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
    private JFXButton btnInsert;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnView;

    @FXML
    void btnDeleteClick(ActionEvent event) {
        Product selectedProduct = tvProduct.getSelectionModel().getSelectedItem();

        if (selectedProduct == null) {
            selectProductWarning();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Are you sure to do you want delete : " + "\n"
                    + "Name :" + selectedProduct.getName()
                    + "Price :" + selectedProduct.getPrice());
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
    void btnViewClick(ActionEvent event) throws IOException, SQLException {
          Product view = tvProduct.getSelectionModel().getSelectedItem();

        if (view == null) {
            selectImgWarning();
        } else {
            Nagatice.getInstance().goToViewProduct(view);
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
    }

}
