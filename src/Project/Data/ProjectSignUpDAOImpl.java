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

/**
 *
 * @author Admin
 */
public class ProjectSignUpDAOImpl implements ProjectSignUpDAP{
    DbType database = DbType.SQL;
    
    public  ProjectSignUp insert(ProjectSignUp inserts){
        
        String sql = "INSERT into account_client (ten_tai_khoan,mat_khau,nick_name, phone , address)"
                + "VALUES (?, ?, ?, ?, ?)";
        ResultSet key = null;
        try (
            Connection conn = DbProject.getConnection(database);
            PreparedStatement stmt = 
                    conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ) {
            
            stmt.setString(1, inserts.getAccount());
            stmt.setString(2, inserts.getPassword());
            stmt.setString(3, inserts.getName());
            stmt.setString(4, inserts.getPhone());
            stmt.setString(5, inserts.getAddress());
          
            
           
            int rowInserted = stmt.executeUpdate();
            if (rowInserted == 1) {
               
                return inserts;
            } else {
                System.err.println("No Manager inserted");
                
                return null;
            }  
        } catch (Exception e) {
            return null;
        }
     }
}
