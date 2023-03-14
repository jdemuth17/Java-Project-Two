package Main;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Joseph DeMuth
 */
public class Main extends Application {

    ResourceBundle rb;

    /**
     *
     * @param primaryStage Loads the Log in screen.
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.rb = rb;

        ResourceBundle rb = ResourceBundle.getBundle("language_files/Nat");


        Parent root = FXMLLoader.load(getClass().getResource("/View/Main.fxml"));
        primaryStage.setTitle(rb.getString("text1"));
        primaryStage.setScene(new Scene(root, 436, 335));
        primaryStage.show();

    }

    /**
     *Main method.
     * @param args
     */
    public static void main(String[] args) {
        //uncomment the following line to and change "en" to "fr" to test translation.
        //Locale.setDefault(new Locale("en"));
        launch(args);
    }

}

