/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client_Food;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author DELL
 */
public class Food {


    private ObjectProperty<Integer> id;
    private StringProperty name;
    private ObjectProperty<Integer> price;
    private StringProperty properties;
    private StringProperty img;
    private StringProperty level;

    public Food() {
        id = new SimpleObjectProperty<>(null);
        name = new SimpleStringProperty();
        price = new SimpleObjectProperty<>();
        properties = new SimpleStringProperty();
        img = new SimpleStringProperty();
        level = new SimpleStringProperty();

    }

    public Integer getID() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }

    public Integer getPrice() {
        return price.get();
    }

    public String getProperties() {
        return properties.get();
    }

    public String getImg() {
        return img.get();
    }

    public String getLevel() {
        return level.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setPrice(int price) {
        this.price.set(price);
    }

    public void setProperties(String properties) {
        this.properties.set(properties);
    }

    public void setImg(String img) {
        this.img.set(img);
    }

    public void setLevel(String level) {
        this.level.set(level);
    }

    public ObjectProperty<Integer> getIDProperty() {
        return this.id;
    }

    public StringProperty getNameProperty() {
        return this.name;
    }

    public ObjectProperty<Integer> getPriceProperty() {
        return this.price;
    }

    public StringProperty getPropertiesProperty() {
        return this.properties;
    }

    public StringProperty getImgProperty() {
        return this.img;
    }

    public StringProperty getLevelProperty() {
        return this.level;
    }

    public static ObservableList<Food> selectAll() throws SQLException {
        ObservableList<Food> foods = FXCollections.observableArrayList();
        try (
                Connection connect = DB_Connect.getConnection();
                Statement stsm = connect.createStatement();
                ResultSet result = stsm.executeQuery("SELECT p.Name,p.Price FROM products as p join category as c on p.CategoryID = c.CategoryID WHERE c.CategoryID = 2");) {

            while (result.next()) {
                Food a = new Food();
                a.setName(result.getString("Name"));
                a.setPrice(result.getInt("Price"));
                foods.add(a);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return foods;

    }

    public static ObservableList selectImgByID() throws SQLException {
        ObservableList food = FXCollections.observableArrayList();
        String sql = "SELECT p.ImgLink FROM products as p join category as c on p.Category = c.Category WHERE c.CategoryID = 2";
        try (
                Connection connect = DB_Connect.getConnection();
                Statement stsm = connect.createStatement();
                ResultSet result = stsm.executeQuery("SELECT p.ImgLink FROM products as p join category as c on p.CategoryID = c.CategoryID WHERE c.CategoryID = 2");) {
            while (result.next()) {
                food.add(result.getString("ImgLink"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return food;
    }

}
