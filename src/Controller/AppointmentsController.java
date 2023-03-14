package Controller;

import DataBase.DBAppointments;
import DataBase.DBContacts;
import DataBase.DBCustomers;
import DataBase.DBUsers;
import Helper.JDBC;
import Model.Appointments;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.*;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import static Helper.JDBC.*;

/**
 * This is the controller for the appointments screen.
 *
 * @author Joseph DeMuth
 */
public class AppointmentsController implements Initializable {

    @FXML
    private TableColumn<Appointments, ?> APPT_ID_COL;

    @FXML
    private TableView<Appointments> AppointmentsTable;

    @FXML
    private TableColumn<Appointments, ?> CONTACT_COL;

    @FXML
    private TableColumn<Appointments, ?> CUSTOMER_ID_COL;

    @FXML
    private TableColumn<Appointments, ?> DESC_COL;

    @FXML
    private TableColumn<Appointments, ?> ENDDATE_COL;

    @FXML
    private TableColumn<Appointments, ?> LOCATION_COL;

    @FXML
    private TableColumn<Appointments, ?> STARTDATE_COL;

    @FXML
    private TableColumn<Appointments, ?> TITLE_COL;

    @FXML
    private TableColumn<Appointments, ?> TYPE_COL;

    @FXML
    private TableColumn<Appointments, ?> USER_ID_COL;

    @FXML
    private TextField Appt_Description;

    @FXML
    private TextField Appt_Title;

    @FXML
    private TextField Appt_Type;

    @FXML
    private TextField AppID;

    @FXML
    private ComboBox<LocalTime> Appt_EndTime;

    @FXML
    private  TextField Appt_Location;

    @FXML
    private TextArea AlertsArea;

    @FXML
    private ComboBox<LocalTime> Appt_StartTime;

    @FXML
    private ComboBox<Integer> Appt_UserID;

    @FXML
    private ComboBox<String> Appt_Contact;

    @FXML
    private ComboBox<Integer> Appt_Customer_ID;

    @FXML
    private DatePicker Appt_Date;

    @FXML
    private Button Add_Appt;

    Stage stage;
    Parent scene;
    String alertString = "Error message";

    /**
     * Populates the appointments table and combo box's.
     * @param url Not used.
     * @param rb Not used.
     */
    public void initialize(URL url, ResourceBundle rb) {

        //Populate main screen table
        AppointmentsTable.setItems(DBAppointments.getAllAppointments());


        APPT_ID_COL.setCellValueFactory(new PropertyValueFactory<>("id"));
        TITLE_COL.setCellValueFactory(new PropertyValueFactory<>("title"));
        DESC_COL.setCellValueFactory(new PropertyValueFactory<>("description"));
        LOCATION_COL.setCellValueFactory(new PropertyValueFactory<>("location"));
        TYPE_COL.setCellValueFactory(new PropertyValueFactory<>("type"));
        STARTDATE_COL.setCellValueFactory(new PropertyValueFactory<>("start"));
        ENDDATE_COL.setCellValueFactory(new PropertyValueFactory<>("end"));
        CUSTOMER_ID_COL.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        USER_ID_COL.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        CONTACT_COL.setCellValueFactory(new PropertyValueFactory<>("contact_id"));


        Appt_Customer_ID.setItems(DBCustomers.getAllCustomerIDs());

        Appt_UserID.setItems(DBUsers.getAllUserIDs());

        Appt_Contact.setItems(DBContacts.getAllContacts());


        LocalTime Start = LocalTime.of(8,00);
        LocalTime End = LocalTime.of(22,0);
        LocalTime Start1 = LocalTime.of(8,0);
        LocalTime End1 = LocalTime.of(22,0);

        while(Start.isBefore(End.minusMinutes(14))){
            Appt_StartTime.getItems().add(Start);
            Start = Start.plusMinutes(15);
        }

        while(Start1.isBefore(End1.plusMinutes(14))){
            Appt_EndTime.getItems().add(Start1);
            Start1 = Start1.plusMinutes(15);
        }
        apptAlert();

    }

    /**
     *
     * Error message to be reused for different errors.
     */
    private void myAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(alertString);
        alert.showAndWait();
    }

    /**
     * Reports button loads the reports scene.
     *
     * @param actionEvent This is the event caused when the reports button is clicked
     * @throws IOException
     */
    @FXML
    void OnActionReportsbutton(ActionEvent actionEvent) throws IOException {

        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/Reports.fxml"));
        stage.setScene(new Scene(scene));
        stage.centerOnScreen();
        stage.show();

    }

    /**
     * Radio Button to filter appointments table by month.
     *
     * @param event This is the event caused by selecting filter by month radio button, filters the appointments' table to the next 30 days.
     */
    @FXML
    void OnActionFilterbyMonth(ActionEvent event) {

        DBAppointments.getMonthAppointments();

        AppointmentsTable.setItems(DBAppointments.getMonthAppointments());
        APPT_ID_COL.setCellValueFactory(new PropertyValueFactory<>("id"));
        TITLE_COL.setCellValueFactory(new PropertyValueFactory<>("title"));
        DESC_COL.setCellValueFactory(new PropertyValueFactory<>("description"));
        LOCATION_COL.setCellValueFactory(new PropertyValueFactory<>("location"));
        TYPE_COL.setCellValueFactory(new PropertyValueFactory<>("type"));
        STARTDATE_COL.setCellValueFactory(new PropertyValueFactory<>("start"));
        ENDDATE_COL.setCellValueFactory(new PropertyValueFactory<>("end"));
        CUSTOMER_ID_COL.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        USER_ID_COL.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        CONTACT_COL.setCellValueFactory(new PropertyValueFactory<>("contact_id"));

    }

    /**
     * Radio Button to filter appointments table by week.
     *
     * @param event This is the event caused by selecting filter by week radio button, filters the appointments' table to the next 7 days.
     */
    @FXML
    void OnActionFilterbyWeek(ActionEvent event) {

        DBAppointments.getWeekAppointments();

        AppointmentsTable.setItems(DBAppointments.getWeekAppointments());
        APPT_ID_COL.setCellValueFactory(new PropertyValueFactory<>("id"));
        TITLE_COL.setCellValueFactory(new PropertyValueFactory<>("title"));
        DESC_COL.setCellValueFactory(new PropertyValueFactory<>("description"));
        LOCATION_COL.setCellValueFactory(new PropertyValueFactory<>("location"));
        TYPE_COL.setCellValueFactory(new PropertyValueFactory<>("type"));
        STARTDATE_COL.setCellValueFactory(new PropertyValueFactory<>("start"));
        ENDDATE_COL.setCellValueFactory(new PropertyValueFactory<>("end"));
        CUSTOMER_ID_COL.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        USER_ID_COL.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        CONTACT_COL.setCellValueFactory(new PropertyValueFactory<>("contact_id"));

    }

    /**
     *  * Radio button re-populates the appointment table with all appointments after it was filtered.
     *
     * @param event Radio button selected re-populates the appointment table.
     */
    @FXML
    void OnActionAll_Appointments(ActionEvent event) {

        AppointmentsTable.setItems(DBAppointments.getAllAppointments());
        APPT_ID_COL.setCellValueFactory(new PropertyValueFactory<>("id"));
        TITLE_COL.setCellValueFactory(new PropertyValueFactory<>("title"));
        DESC_COL.setCellValueFactory(new PropertyValueFactory<>("description"));
        LOCATION_COL.setCellValueFactory(new PropertyValueFactory<>("location"));
        TYPE_COL.setCellValueFactory(new PropertyValueFactory<>("type"));
        STARTDATE_COL.setCellValueFactory(new PropertyValueFactory<>("start"));
        ENDDATE_COL.setCellValueFactory(new PropertyValueFactory<>("end"));
        CUSTOMER_ID_COL.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        USER_ID_COL.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        CONTACT_COL.setCellValueFactory(new PropertyValueFactory<>("contact_id"));

    }

    /**
     * Clears all selections from the input  fields.
     *
     * @param event  Event clears all input fields.
     */
    @FXML
    void OnActionClear(ActionEvent event) {

        AppID.setText(null);
        Appt_Title.setText(null);
        Appt_Description.setText(null);
        Appt_Location.setText(null);
        Appt_Type.setText(null);
        Appt_Customer_ID.getSelectionModel().clearSelection();
        Appt_UserID.getSelectionModel().clearSelection();
        Appt_Contact.getSelectionModel().clearSelection();
        Appt_Date.setValue(null);
        Appt_StartTime.getSelectionModel().clearSelection();
        Appt_EndTime.getSelectionModel().clearSelection();

        Add_Appt.setText("Add Appointment");
        Appt_Customer_ID.setDisable(false);

    }


    /**
     * Add Appointment Button, modifies or creates a new appointment in the Database.
     *
     * @param actionEvent sends insert or update statement with Appointment data to the database.
     *
     */

    public void OnActionAdd_Appt(ActionEvent actionEvent) {

        LocalDateTime Start = null;
        LocalDateTime End = null;

        try {

            String ApptID = AppID.getText();
            String title = Appt_Title.getText();
            String description = Appt_Description.getText();
            String location = Appt_Location.getText();
            int contact;
            try {
                contact = DBContacts.getContactID(Appt_Contact.getValue());
            } catch (Exception e) {
                contact = 0;
            }
            String type = Appt_Type.getText();
            LocalDate date = null;
            LocalTime start = null;
            LocalTime end = null;
            try {
                start = Appt_StartTime.getValue();
                end = Appt_EndTime.getValue();
                date = Appt_Date.getValue();
                Start = LocalDateTime.of(date, start);
                End = LocalDateTime.of(date, end);
                
            }catch (Exception e){
                
            }
            int customer;
            try {
                customer = Appt_Customer_ID.getValue();
            } catch (Exception e) {
                customer = 0;
            }
            int user;
            try {
                user = Appt_UserID.getValue();
            } catch (Exception e) {
                user = 0;
            }

            if (emptyFieldsCheck() && startBeforeEnd(start,end)) {
                if (!hasApptID()) {
                    if (ApptConflictCheck(Start, End, customer) && openHours(Start, End)) {

                        String sql = "INSERT INTO appointments ( Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) VALUES (?,?,?,?,?,?,?,?,?)";

                        PreparedStatement stmt = connection.prepareStatement(sql);
                        stmt.setString(1, title);
                        stmt.setString(2, description);
                        stmt.setString(3, location);
                        stmt.setString(4, type);
                        stmt.setTimestamp(5, Timestamp.valueOf(Start));
                        stmt.setTimestamp(6, Timestamp.valueOf(End));
                        stmt.setInt(7, customer);
                        stmt.setInt(8, user);
                        stmt.setInt(9, contact);
                        stmt.executeUpdate();

                        AppointmentsTable.setItems(DBAppointments.getAllAppointments());
                        APPT_ID_COL.setCellValueFactory(new PropertyValueFactory<>("id"));
                        TITLE_COL.setCellValueFactory(new PropertyValueFactory<>("title"));
                        DESC_COL.setCellValueFactory(new PropertyValueFactory<>("description"));
                        LOCATION_COL.setCellValueFactory(new PropertyValueFactory<>("location"));
                        TYPE_COL.setCellValueFactory(new PropertyValueFactory<>("type"));
                        STARTDATE_COL.setCellValueFactory(new PropertyValueFactory<>("start"));
                        ENDDATE_COL.setCellValueFactory(new PropertyValueFactory<>("end"));
                        CUSTOMER_ID_COL.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
                        USER_ID_COL.setCellValueFactory(new PropertyValueFactory<>("user_id"));
                        CONTACT_COL.setCellValueFactory(new PropertyValueFactory<>("contact_id"));

                    }
                } else if (openHours(Start, End)) {

                        String sql = "UPDATE appointments SET Title = ?, Description = ?, Location= ?, Type= ?, Start= ?, End= ?, Customer_ID= ?, User_ID= ?, Contact_ID = ? WHERE Appointment_ID = ?";

                        PreparedStatement stmt = connection.prepareStatement(sql);
                        stmt.setString(1, title);
                        stmt.setString(2, description);
                        stmt.setString(3, location);
                        stmt.setString(4, type);
                        stmt.setTimestamp(5, Timestamp.valueOf(Start));
                        stmt.setTimestamp(6, Timestamp.valueOf(End));
                        stmt.setInt(7, customer);
                        stmt.setInt(8, user);
                        stmt.setInt(9, contact);
                        stmt.setInt(10, Integer.parseInt(ApptID));
                        stmt.executeUpdate();


                        AppointmentsTable.setItems(DBAppointments.getAllAppointments());
                        APPT_ID_COL.setCellValueFactory(new PropertyValueFactory<>("id"));
                        TITLE_COL.setCellValueFactory(new PropertyValueFactory<>("title"));
                        DESC_COL.setCellValueFactory(new PropertyValueFactory<>("description"));
                        LOCATION_COL.setCellValueFactory(new PropertyValueFactory<>("location"));
                        TYPE_COL.setCellValueFactory(new PropertyValueFactory<>("type"));
                        STARTDATE_COL.setCellValueFactory(new PropertyValueFactory<>("start"));
                        ENDDATE_COL.setCellValueFactory(new PropertyValueFactory<>("end"));
                        CUSTOMER_ID_COL.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
                        USER_ID_COL.setCellValueFactory(new PropertyValueFactory<>("user_id"));
                        CONTACT_COL.setCellValueFactory(new PropertyValueFactory<>("contact_id"));
                    }
                }

        } catch (Exception e) {
            e.printStackTrace();
            Alerts(3);
        }
    }


    /**
     * Modify Appointment Button, sends selected Appointment data to the input fields.
     *
     * @param actionEvent Event, select button sends selected Appointment data to modify appointment fields.
     *
     */
    public void OnAction_Modify_Appt(ActionEvent actionEvent) {

        try {

            Appointments SelectedAppointment = AppointmentsTable.getSelectionModel().getSelectedItem();

            try {

                AppID.setText(String.valueOf(SelectedAppointment.getId()));
                Appt_Title.setText(String.valueOf(SelectedAppointment.getTitle()));
                Appt_Description.setText(String.valueOf(SelectedAppointment.getDescription()));
                Appt_Location.setText(String.valueOf(SelectedAppointment.getLocation()));
                Appt_Type.setText(String.valueOf(SelectedAppointment.getType()));
                Appt_Customer_ID.setValue(SelectedAppointment.getCustomer_id());
                Appt_UserID.setValue(SelectedAppointment.getUser_id());
                Appt_Contact.setValue(DBContacts.getContact(SelectedAppointment.getContact_id()));

                Appt_Date.setValue(SelectedAppointment.getStart().toLocalDate());
                Appt_StartTime.setValue(SelectedAppointment.getStart().toLocalTime());
                Appt_EndTime.setValue(SelectedAppointment.getEnd().toLocalTime());

            } catch (SQLException e) {
                e.printStackTrace();
            }

            Add_Appt.setText("Modify Appointment");
            Appt_Customer_ID.setDisable(true);

        } catch (Exception e) {
            alertString = "Select an Appointment";
            myAlert();

        }

    }

    /**
     * customer button, loads the customers screen.
     *
     * @param actionEvent Event, customer button activated. Loads the customer screen.
     *
     */
    public void OnActionCustomer_Scene(ActionEvent actionEvent) throws IOException {

        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/Customers.fxml"));
        stage.setScene(new Scene(scene));
        stage.centerOnScreen();
        stage.show();
    }

    /**
     * Logout button, return to the login screen..
     *
     * @param actionEvent Event, logout button activated. Return to the login screen.
     *
     */
    public void OnActionLogOut(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/Main.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /**
     * Delete Button, Deletes the selected appointment.
     *
     * @param actionEvent Deletes a selected Appointment after throwing a dialog box for confirmation.
     */
    public void OnActionDelete_Appt(ActionEvent actionEvent) {

        Appointments SelectedAppointment = AppointmentsTable.getSelectionModel().getSelectedItem();

        if (SelectedAppointment != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Confirm delete Appointment");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    try {

                        int Appt_ID = SelectedAppointment.getId();

                        String sql = "select Appointment_ID, Type from client_schedule.appointments";

                        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

                        ResultSet rs = ps.executeQuery();

                        while (rs.next()) {

                            int APPTID = rs.getInt("Appointment_ID");
                            String apptType = rs.getString("Type");

                            if (Objects.equals(APPTID, Appt_ID)) {

                                Statement stmt = null;
                                stmt =connection.createStatement();
                                stmt.execute("DELETE FROM client_schedule.appointments WHERE Appointment_ID = " + APPTID);

                                AlertsArea.setText(" Appointment Canceled! With Appoint ID " + APPTID + " and of type " + apptType + " .");
                                Timer timer = new Timer();
                                TimerTask timertask = new TimerTask() {
                                    @Override
                                    public void run() {
                                        apptAlert();
                                    }
                                };
                                timer.schedule(timertask,15000);

                            }
                        }

                        AppointmentsTable.setItems(DBAppointments.getAllAppointments());
                        APPT_ID_COL.setCellValueFactory(new PropertyValueFactory<>("id"));
                        TITLE_COL.setCellValueFactory(new PropertyValueFactory<>("title"));
                        DESC_COL.setCellValueFactory(new PropertyValueFactory<>("description"));
                        LOCATION_COL.setCellValueFactory(new PropertyValueFactory<>("location"));
                        TYPE_COL.setCellValueFactory(new PropertyValueFactory<>("type"));
                        STARTDATE_COL.setCellValueFactory(new PropertyValueFactory<>("start"));
                        ENDDATE_COL.setCellValueFactory(new PropertyValueFactory<>("end"));
                        CUSTOMER_ID_COL.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
                        USER_ID_COL.setCellValueFactory(new PropertyValueFactory<>("user_id"));
                        CONTACT_COL.setCellValueFactory(new PropertyValueFactory<>("contact_id"));

                    }catch (Exception e) {
                        alertString = "Delete Failed";
                        myAlert();
                    }
                }
            });
        } else Alerts(7);


    }

    /**
     * Exit button closes the program and ends database connection.
     *
     * @param actionEvent Exits the program after throwing a dialog box for confirmation.
     */
    public void OnActionExit(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Close the program?");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                closeConnection();
                Platform.exit();
            }
        });
    }

    /**
     * Checks that Appointment is During Operating hours.
     *
     * @return Boolean value, returns true if start and end time are during business hours.
     */
    public boolean openHours (LocalDateTime start, LocalDateTime end){

    boolean open = true;


            LocalDateTime startLocal = start;
            LocalDateTime endLocal = end;

 //           uncomment the next line and lines 599-601 for weekends closed.
 //           int weekEnd = start.getDayOfWeek().getValue();


            ZoneId LocalID = ZoneId.systemDefault();
            ZoneId EST = ZoneId.of("America/New_York");

            ZonedDateTime selectedStart = ZonedDateTime.of(startLocal, LocalID);
            ZonedDateTime selectedEnd = ZonedDateTime.of(endLocal, LocalID);

            ZonedDateTime selectedStartEST = selectedStart.withZoneSameInstant(EST);
            ZonedDateTime selectedEndEST = selectedEnd.withZoneSameInstant(EST);

            ZonedDateTime businessStartHours = ZonedDateTime.now(EST).withHour(8).withMinute(00);
            ZonedDateTime businessEndHours = ZonedDateTime.now(EST).withHour(22).withMinute(00);

            if(selectedEndEST.toLocalTime().isBefore(businessEndHours.toLocalTime()) && selectedStartEST.toLocalTime().isAfter(businessStartHours.toLocalTime())){
                System.out.println(selectedEndEST.toLocalTime() + " Appointment is during Business hours!");
                open = true;
            }else if(selectedEndEST.toLocalTime().isAfter(businessEndHours.toLocalTime()) || selectedStartEST.toLocalTime().isBefore(businessStartHours.toLocalTime())) {
                System.out.println(selectedEndEST.toLocalTime() + " Appointment is Outside of Business hours!");
                open = false;
            }
//            uncomment this if statement for weekends closed
//            if(weekEnd == 6 || weekEnd == 7){
//                open = false;
//            }

        if(open == false){
            Alerts(2);
        }
        return open;

}

    /**
     * Checks that Customer Appointments do not overlap.
     *
     * @return Boolean value, returns true if the customer does not have an appointment during the selected time frame.
     */
    public boolean ApptConflictCheck(LocalDateTime newsdt, LocalDateTime newedt, int custid) {

        boolean Valid = true;

        try{
            String sql = "SELECT Start, End, Customer_ID FROM client_schedule.appointments WHERE Customer_ID = " + custid;

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            LocalDateTime existingsdt;
            LocalDateTime existingedt;


            while (rs.next()) {

                existingsdt = rs.getTimestamp("Start").toLocalDateTime();
                existingedt = rs.getTimestamp("End").toLocalDateTime();
                if((newsdt.isBefore(existingsdt) || newsdt.isEqual(existingsdt)) && (newedt.isAfter(existingedt) || newsdt.isEqual(existingedt))){
                    Valid = false;
                }else if((newedt.isAfter(existingsdt)) && (newedt.isBefore(existingedt) || newedt.isEqual(existingedt))){
                    Valid = false;
                }else if((newsdt.isAfter(existingsdt) || (newsdt.isEqual(existingsdt)))  && (newsdt.isBefore(existingedt))){
                    Valid = false;
                }
            }

            if(Valid == false){

                Alerts(4);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Valid;

        }

    /**
     * Checks that selected Start time is before selected End time.
     *
     * @return Boolean value, returns true if start time is before end time.
     */
    public boolean startBeforeEnd(LocalTime start, LocalTime end){
        boolean startsFirst = false;

        if(start.isBefore(end)){
            startsFirst = true;
        }else Alerts(7);

        return startsFirst;
    }

    /**
     * Checks that all fields are populated.
     *
     * @return Boolean value, returns true if all input fields, other than appointment id, are populated. Otherwise it returns false.
     */
    public boolean emptyFieldsCheck(){
       boolean feildsnotempty = false;

            String title = Appt_Title.getText();
            String description = Appt_Description.getText();
            String location = Appt_Location.getText();
            String type = Appt_Type.getText();
            int contact;
            try {
                contact = DBContacts.getContactID(Appt_Contact.getValue());
            } catch (Exception e) {
                contact = 0;
            }
            LocalDate date;
            try {
                date = Appt_Date.getValue();
            } catch (Exception e) {
                date = LocalDate.of(1900,01,01);
                Alerts(5);
            }
                LocalTime start;
            try {
                start = Appt_StartTime.getValue();
            } catch (Exception e) {
                start = LocalTime.of(0,0);
                Alerts(6);
            }
                LocalTime end;
            try {
                end = Appt_EndTime.getValue();
            }catch (Exception e){
                end = LocalTime.of(0,0);
                Alerts(6);
            }
                int customer;
            try {
                customer = Appt_Customer_ID.getValue();
            } catch (Exception e) {
                customer = 0;
            }
                int user;
            try {
                user = Appt_UserID.getValue();
            } catch (Exception e) {
                user = 0;
            }
            if(title != null && description != null && location != null && contact != 0 && type != null && date != LocalDate.of(1900,01,01) && start != LocalTime.of(0,0) && end != LocalTime.of(0,0) && customer != 0 && user != 0){
                feildsnotempty = true;
            }
        if(feildsnotempty == false ){
            Alerts(1);
        }

       return feildsnotempty;
    }

    /**
     * Checks if the appointment id field has is populated or not.
     *
     * @return Boolean, returns true if the id field is populated, otherwise returns false.
     */
    public  boolean hasApptID(){
        boolean hasApptID = true;

        String ApptID = AppID.getText();
        System.out.println("Appt ID is " + ApptID);
        if(ApptID.isEmpty() || ApptID == null){
            hasApptID = false;
        }
       return  hasApptID;
    }

    /**
     * Checks for appointments within fifteen minutes of log in.
     * Displays relevant message in a textarea.
     */
    public  void apptAlert(){

        int count = 0;

        try{
        String sql = "select Appointment_ID, Start from client_schedule.appointments";

        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

            AlertsArea.setText("APPOINTMENTS WITHIN 15 MINUTES : \n");

            while (rs.next()){

                int AppointmentID = rs.getInt("Appointment_ID");
                LocalDateTime startDateTime = rs.getTimestamp("Start").toLocalDateTime();
                LocalTime start = startDateTime.toLocalTime();
                LocalDate date = startDateTime.toLocalDate();

                if(start.isBefore(LocalTime.now().plusMinutes(15)) && (start.isAfter(LocalTime.now()) || start.equals(LocalTime.now()))){
                    AlertsArea.appendText("\n Appointment ID " + AppointmentID + ", Appointment Date " +date + ", Appointment Time " + start + " !" );
                    count++;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        if(count == 0){

            AlertsArea.setText("There are no appointments in the next 15 minutes .");
        }

    }

    /**
     * Switch case of error messages to be used.
     * @param alerts different error messages to be called.
     */
    private void Alerts(int alerts) {

        Alert alert = new Alert(Alert.AlertType.ERROR);

        switch (alerts) {
            case 1:
                alert.setTitle("Error");
                alert.setContentText("All fields are required");
                alert.showAndWait();
                break;
            case 2:
                alert.setTitle("Error");
                alert.setContentText("Appointment time Outside of Business hours!");
                alert.showAndWait();
                break;
            case 3:
                alert.setTitle("Error");
                alert.setContentText("Add Appointment failed");
                alert.showAndWait();
                break;
            case 4:
                alert.setTitle("Error");
                alert.setContentText("Customer Schedule Conflict!");
                alert.showAndWait();
                break;
            case 5:
                alert.setTitle("Error");
                alert.setContentText("Pick a Date!");
                alert.showAndWait();
                break;
            case 6:
                alert.setTitle("Error");
                alert.setContentText("Pick a Time!");
                alert.showAndWait();
                break;
            case 7:
                alert.setTitle("Error");
                alert.setContentText("Appointment start must be before appointment end!");
                alert.showAndWait();
                break;
        }


    }

}


