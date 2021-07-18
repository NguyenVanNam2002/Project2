/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import Project.Data.Order;
import Project.Data.Product;
import java.util.Arrays;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 *
 * @author Admin
 */
public class OrderItem {
    @FXML
    private ImageView image;

    @FXML
    private Text name;

    @FXML
    private Text spinner;
    @FXML
    private CheckBox check;
    @FXML
    private Text price;
    
    private OrderListener orderlis;
    private Product o;
    @FXML
    void click(MouseEvent event) {
       if(check.isSelected()){
            orderlis.onClickListener(p);
       }
       
        
    }
    private Order  p;
     public void setData(Order fruit ,Product o , OrderListener orderlis ) {
        this.p= fruit;
        this.o = o;
        this.orderlis = orderlis ;
        spinner.setText(Integer.toString(fruit.getQuantity()));
        price.setText(Integer.toString(fruit.getTotalPrice()));
        name.setText(o.getName());
        
        Image images = new Image(getClass().getResourceAsStream(o.getImg()));
        this.image.setImage(images);
        
     }   
     
     public void initialize(){
          String[] styleClasses = new String[] { "", 
                Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL
 
        };
        spinner.getStyleClass().addAll(Arrays.asList(styleClasses));
       
      
    }
}
