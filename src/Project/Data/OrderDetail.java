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

/**
 *
 * @author Admin
 */
public class OrderDetail {
    private ObjectProperty<Integer> id ;
    private StringProperty productID;
    private ObjectProperty<Integer> quantity;

    
    public OrderDetail(){
        id = new SimpleObjectProperty<>(null);
     
        productID = new SimpleStringProperty();
        quantity = new SimpleObjectProperty<>();
      
    }
    
    public Integer getID(){
        return id.get();
    }
   
    public String getProductID(){
        return  productID.get();
    }
    
    public Integer getQuantity(){
        return quantity.get();
    }
    
    public void setID(int id){
        this.id.set(id);
    }
  
    public void setProductID(String productID){
        this.productID.set(productID);
    }
    public void setQuantity(int quantity){
        this.quantity.set(quantity);
    }
    
    public ObjectProperty<Integer> getIDProperty(){
       return this.id;
    }
    
    public StringProperty getProductIDProperty(){
       return this.productID;
    }
    
    public ObjectProperty<Integer> getQuantityProperty(){
       return this.quantity;
    }
  
}
