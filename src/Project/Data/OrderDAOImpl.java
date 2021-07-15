/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project.Data;

import Project.DbProject.DbProject;
import Project.DbProject.DbType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class OrderDAOImpl implements OrderDAO{
    DbType data = DbType.SQL;
    
    public  Order insert(Order order){
        String sql = "INSERT INTO order_detail(ProductID, Client_ID , Total_price, Quantity , Indate) "
                + " VALUES (? , ? , ? , ?, ? )";
        ResultSet key = null;
        try (Connection con = DbProject.getConnection(data);
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ){
            stmt.setInt(1, order.getProductID());
            stmt.setString(2, order.getAccount());
            stmt.setString(3, order.getTotalPrice().toString());
            stmt.setInt(4, order.getQuantity());
            stmt.setString(5, order.getDate());
            int row = stmt.executeUpdate();
            if(row ==1 ){
                key = stmt.getGeneratedKeys();
                key.next();
                int newkey = key.getInt(1);
                order.setID(newkey);
                return order;
            
            }else{
                return null;
            }
        } catch (Exception e) {
            return null;
        }finally {
            try {
                if (key != null) key.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    
    }
}
