/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project.Data;

import Project.DbProject.DbProject;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
public class Product {
    
    private ObjectProperty<Integer> id;
    private StringProperty name;
    private ObjectProperty<Integer> price;
    private StringProperty properties;
    private StringProperty img;
    
    public Product() {
        id = new SimpleObjectProperty<>(null);
        name = new SimpleStringProperty();
        price = new SimpleObjectProperty<>();
        properties = new SimpleStringProperty();
        img = new SimpleStringProperty();
        
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
    
    public static ObservableList<Product> selectAll() throws SQLException {
        ObservableList<Product> products = FXCollections.observableArrayList();
        
        try (
                Connection connect = DbProject.getConnection();
                Statement stsm = connect.createStatement();
                ResultSet result = stsm.executeQuery("SELECT * FROM products");) {
            
            while (result.next()) {
                Product a = new Product();
                a.setId(result.getInt("ProductID"));
                a.setName(result.getString("Name"));
                a.setPrice(result.getInt("Price"));
                a.setProperties(result.getString("Properties"));
                a.setImg(result.getString("ImgLink"));
                products.add(a);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return products;
        
    }
    
    public static ObservableList selectImgByID(int id) throws SQLException {
        ObservableList products = FXCollections.observableArrayList();
        String sql = "SELECT ImgLink FROM products WHERE ProductID = ?";
        try (
                Connection connect = DbProject.getConnection();
                           Statement stsm = connect.createStatement();
           ResultSet result = stsm.executeQuery("SELECT ImgLink FROM products WHERE ProductID = " + id);){
            while (result.next()) {
                products.add(result.getString("ImgLink"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return products; 
    }
    
    public static Product insert(Product newProduct) throws SQLException {
        String sql = "INSERT INTO products (Name, Price, Properties, ImgLink) "
                + "VALUES (?, ?, ?, ?)";
        ResultSet key = null;
        try (
                Connection connect = DbProject.getConnection();
                PreparedStatement stsm = connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            stsm.setString(1, newProduct.getName());
            stsm.setInt(2, newProduct.getPrice());
            stsm.setString(3, newProduct.getProperties());
            stsm.setString(4, newProduct.getImg());
            
            int rowInserted = stsm.executeUpdate();
            if (rowInserted == 1) {
                key = stsm.getGeneratedKeys();
                key.next();
                int newKey = key.getInt(1);
                newProduct.setId(newKey);
                return newProduct;
            } else {
                System.out.println("No Product inserted");
                return null;
            }
        } catch (Exception e) {
            System.err.println(e);
            return null;
        } finally {
            if (key != null) {
                key.close();
            }
        }
    }
    
    public static boolean delete(Product deleteProduct) {
        String sql = "DELETE  FROM products "
                + "WHERE ProductID = ?";
        try (
                Connection connect = DbProject.getConnection();
                PreparedStatement stmt = connect.prepareStatement(sql);) {
            
            stmt.setInt(1, deleteProduct.getID());
            
            int rowDeleted = stmt.executeUpdate();
            
            if (rowDeleted == 1) {
                return true;
            } else {
                System.err.println("No Product deleted");
                return false;
            }
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }
    
    public static boolean update(Product updateProduct) {
        String sql = "UPDATE products SET "
                + "Name = ?, "
                + "Price = ?, "
                + "Properties = ? ,"
                + "ImgLink = ? "
                + "WHERE ProductID = ?";
        try (
                Connection connect = DbProject.getConnection();
                PreparedStatement stmt = connect.prepareStatement(sql);) {
            
            stmt.setString(1, updateProduct.getName());
            stmt.setInt(2, updateProduct.getPrice());
            stmt.setString(3, updateProduct.getProperties());
            stmt.setString(4, updateProduct.getImg());
            stmt.setInt(5, updateProduct.getID());
            
            int rowUpdated = stmt.executeUpdate();
            
            if (rowUpdated == 1) {
                return true;
            } else {
                System.err.println("No Product is updated");
                return false;
            }
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }
}
