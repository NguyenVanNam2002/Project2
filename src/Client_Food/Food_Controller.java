/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client_Food;

import com.jfoenix.controls.JFXTextField;
import java.sql.SQLException;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author DELL
 */
public class Food_Controller {

    @FXML
    private TableView<Food> tvFood;

    @FXML
    private TableColumn<Food, String> tcName;

    @FXML
    private TableColumn<Food, Integer> tcPrice;

    @FXML
    private JFXTextField txtSearch;

//      @FXML
//    void btnViewClick(ActionEvent event) throws IOException, SQLException {
//          Drink view = tvDrink.getSelectionModel().getSelectedItem();
//
//        if (view == null) {
//            selectImgWarning();
//        } else {
//            Navigator.getInstance().goToView(view);
//        }
//    }
//       private void selectImgWarning() {
//        Alert alert = new Alert(Alert.AlertType.WARNING);
//        alert.setTitle("Please select a Images");
//        alert.setHeaderText("Please enter selected you want choose ! ");
//        alert.showAndWait();
//    }
    private void Search() throws SQLException {
        FilteredList<Food> filteredData = new FilteredList<>(Food.selectAll(), b -> true);
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
        SortedList<Food> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tvFood.comparatorProperty());
        tvFood.setItems(sortedData);

    }

    public void initialize() throws SQLException {
        System.out.println("Index Controller");

        tvFood.setItems(Food.selectAll());
        tcName.setCellValueFactory((drink) -> {
            return drink.getValue().getNameProperty();
        });

        tcPrice.setCellValueFactory((drink) -> {
            return drink.getValue().getPriceProperty();
        });
        Search();

    }

}
