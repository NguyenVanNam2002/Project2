/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project.Data;

import javafx.collections.ObservableList;

/**
 *
 * @author Admin
 */
public interface OrderDAO {
    public  Order insert(Order order);
    public  Order insertOrder(Order order);
    public  ObservableList<Order> selectShopingcartALL();
}
