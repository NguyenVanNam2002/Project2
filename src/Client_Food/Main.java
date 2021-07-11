/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client_Food;

import Client_Drink.*;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;



/**
 *
 * @author DELL
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Navigator.getInstance().setStage(primaryStage);
        Navigator.getInstance().goToIndex();
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

