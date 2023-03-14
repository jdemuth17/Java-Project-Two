package Controller;

import Helper.JDBC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import static Helper.JDBC.closeConnection;
import static Helper.JDBC.openConnection;


/**
 * This is the controller for the login screen.
 *
 * @author Joseph DeMuth.
 */
public class MainController implements Initializable {

    @FXML
    private Button CloseButton;

    @FXML
    private Label LocationLable;

    @FXML
    private Button LogInButton;

    @FXML
    private TextField PassWordInPut;

    @FXML
    private TextField UserNameInPut;

    private Stage stage;
    private Parent scene;
    String alertString = "Error message";
    ResourceBundle rb;

    /**
     * Initializes the log-in page.
     *
     * Checks location for display. Check language setting for translation, and set up global logger.
     * @param url Not used.
     * @param resourceBundle ResourceBundle is used for translating the login page.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        this.rb = rb;

        rb = ResourceBundle.getBundle("language_files/Nat");

        openConnection();

        ZoneId zone = ZoneId.systemDefault();

        LocationLable.setText(rb.getString("text") +" : " + zone);
        UserNameInPut.setPromptText(rb.getString("promptText1"));
        PassWordInPut.setPromptText(rb.getString("promptText2"));
        LogInButton.setText(rb.getString("text1"));
        CloseButton.setText(rb.getString("text2"));

    }

    /**
     *  Error message to be reused for different errors.
     */
    private void myAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(rb.getString("Error"));
        alert.setContentText(alertString);
        alert.setHeaderText(rb.getString("Error"));
        alert.showAndWait();

    }

    /**
     * Log-In button, logs in to the database and application.
     *
     * @param actionEvent Checks user provided username and password against those stored in database, then loads the appointments screen.
     */
    public void onactionLogIn(ActionEvent actionEvent) {

        String uspassword = PassWordInPut.getText();
        String usUserName = UserNameInPut.getText();
        int count = 0;


        if(uspassword.isBlank()|| usUserName.isBlank()){

            alertString = rb.getString("ErrorMessage1");
            myAlert();

        }else{

            try {
                String sql = "select User_Name, Password from users";

                PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

                ResultSet rs = ps.executeQuery();

                        while (rs.next()) {
                            String uname = rs.getString("User_Name");
                            String upassword = rs.getString("Password");

                            if (Objects.equals(uname, usUserName) && Objects.equals(upassword, uspassword)) {



                                writeToLog(usUserName, true);

                                stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                                scene = FXMLLoader.load(getClass().getResource("/View/Appointments.fxml"));
                                stage.setScene(new Scene(scene));
                                stage.centerOnScreen();
                                stage.show();

                                count++;
                            }
                        }

            } catch (Exception e) {

                writeToLog(usUserName, false);
                alertString = rb.getString("ErrorMessage2");
                myAlert();
            }

            if(count == 0){

                writeToLog(usUserName, false);
                System.out.println("NOT SUCCESSFUL");
                alertString = rb.getString("ErrorMessage3");
                myAlert();
            }

        }

    }

    /**
     * Exit button closes the program and ends the database connection.
     * @param actionEvent Exits the program after throwing a dialog box for confirmation.
     */
    @FXML
    protected void OnActionCloseButton(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(rb.getString("closePrompt"));
        alert.setTitle(rb.getString("closePrompt"));
        alert.setHeaderText(rb.getString("conformation"));
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                closeConnection();
                System.exit(0);
            }
        });

    }

    /**
     * Writes user log in attempts to a text file. Records the username, timestamp, and success or failure status to the login_activity.txt file.
     *
     * @param name The users name.
     * @param success Boolean value, true if log in is successful, otherwise it is false.
     */
    public static void writeToLog(String name, boolean success) {
        Path path = Paths.get("login_activity.txt");
        ZonedDateTime zdt = ZonedDateTime.of(LocalDateTime.now(), ZoneId.systemDefault());
        ZonedDateTime utczdt = zdt.withZoneSameInstant(ZoneId.of("UTC"));
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss.SSSSSS z");
        String successString = "Successful";    if(!success)        successString = "Unsuccessful";
        String msg = String.format("User: %s, Attempted log-in on %s - was %s\n", name, dtf.format(utczdt), successString);
        try {
            Files.writeString(path, msg, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
