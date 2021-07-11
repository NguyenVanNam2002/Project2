/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client_Drink;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.sql.SQLException;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author DELL
 */
public class Drink_Controller {

    @FXML
    private TableView<Drink> tvDrink;

    @FXML
    private TableColumn<Drink, String> tcName;

    @FXML
    private TableColumn<Drink, Integer> tcPrice;
    
    @FXML
    private JFXTextField txtSearch;

    @FXML
    private JFXButton btnView;

    @FXML
    void btnViewClick(ActionEvent event) throws IOException, SQLException {
        Drink view = tvDrink.getSelectionModel().getSelectedItem();

        if (view == null) {
            selectImgWarning();
        } else {
            Navigator.getInstance().goToView(view);
        }
    }

    private void selectImgWarning() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Please select a Images");
        alert.setHeaderText("Please enter selected you want choose ! ");
        alert.showAndWait();
    }
     private void Search() throws SQLException {
        FilteredList<Drink> filteredData = new FilteredList<>(Drink.selectAll(), b -> true);
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
                }
                return false; // Does not match.
            });
        });
        SortedList<Drink> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tvDrink.comparatorProperty());
        tvDrink.setItems(sortedData);
     }
    public void initialize() throws SQLException {
        System.out.println("Index Controller");

        tvDrink.setItems(Drink.selectAll());
        tcName.setCellValueFactory((drink) -> {
            return drink.getValue().getNameProperty();
        });

        tcPrice.setCellValueFactory((drink) -> {
            return drink.getValue().getPriceProperty();
        });
        Search();

    }

}
