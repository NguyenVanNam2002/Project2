package Project;

import Project.Data.Product;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;


public class ItemController {
    @FXML
    private ImageView img;

    
    private Product p;
    
    @FXML
    void inclick(MouseEvent event) {
        myListener.onClickListener(p);
    }
    private MyListener myListener;
     
    public void setData(Product fruit , MyListener myListener ) {
        this.p= fruit;
        this.myListener = myListener;
        
        Image image = new Image(getClass().getResourceAsStream(fruit.getImg()));
        img.setImage(image);
    }
}
