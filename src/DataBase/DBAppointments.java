package DataBase;

import Helper.JDBC;
import Model.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * This is the appointments database access class.
 *
 * @author Joseph DeMuth
 */
public class DBAppointments {

    /**
     * Populates the observableList with Appointments from the data Base.
     *
     *
     * @return All appointments in an observable List.
     */
    public static ObservableList<Appointments> getAllAppointments() {

        //Populate main screen table

        ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();

        DateTimeFormatter DateFomatter = DateTimeFormatter.ofPattern("yyyy-mm-dd hh:mm:ss");

        try {
            String sql = "SELECT `appointments`.`Appointment_ID`,`appointments`.`Title`,`appointments`.`Description`,`appointments`.`Location`,`appointments`.`Type`,`appointments`.`Start`,`appointments`.`End`,`appointments`.`Customer_ID`,`appointments`.`User_ID`,`appointments`.`Contact_ID` FROM `client_schedule`.`appointments`;";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                int customer_id = rs.getInt("Customer_ID");
                int user_id = rs.getInt("User_ID");
                int contact_id = rs.getInt("Contact_ID");


                //DateFomatter.format(start);
                //DateFomatter.format(end);


                Appointments apt = new Appointments(id, title, description, location, type, start, end, customer_id, user_id, contact_id);
                allAppointments.add(apt);
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return allAppointments;
    }

    /**
     * Populates the observableList with appointments for the next 30 days from the data Base.
     *
     *
     * @return appointments for the next 30 days in an observable List.
     */
    public static ObservableList<Appointments> getMonthAppointments() {

        //Populate main screen table

        ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();

        try {
            String sql = "SELECT `appointments`.`Appointment_ID`,`appointments`.`Title`,`appointments`.`Description`,`appointments`.`Location`,`appointments`.`Type`,`appointments`.`Start`,`appointments`.`End`,`appointments`.`Customer_ID`,`appointments`.`User_ID`,`appointments`.`Contact_ID` FROM `client_schedule`.`appointments`;";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            LocalDateTime begin = LocalDateTime.of(LocalDate.now(), LocalTime.of(0,0));
            LocalDateTime stop = begin.plusDays(30);


            while (rs.next()) {

                int id = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                int customer_id = rs.getInt("Customer_ID");
                int user_id = rs.getInt("User_ID");
                int contact_id = rs.getInt("Contact_ID");

                Appointments apt = new Appointments(id, title, description, location, type, start, end, customer_id, user_id, contact_id);
                if(start.isAfter(begin) && start.isBefore(stop)) {

                     allAppointments.add(apt);
                 }

            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return allAppointments;


    }

    /**
     * Populates the observableList with appointments for the next 7 days from the data Base.
     *
     *
     * @return appointments for the next 7 days in an Observable List.
     */
    public static ObservableList<Appointments> getWeekAppointments() {

        //Populate main screen table

        ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();

        try {
            String sql = "SELECT `appointments`.`Appointment_ID`,`appointments`.`Title`,`appointments`.`Description`,`appointments`.`Location`,`appointments`.`Type`,`appointments`.`Start`,`appointments`.`End`,`appointments`.`Customer_ID`,`appointments`.`User_ID`,`appointments`.`Contact_ID` FROM `client_schedule`.`appointments`;";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            LocalDateTime begin = LocalDateTime.of(LocalDate.now(), LocalTime.of(0,0));
            LocalDateTime stop = begin.plusDays(7);


            while (rs.next()) {

                int id = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                int customer_id = rs.getInt("Customer_ID");
                int user_id = rs.getInt("User_ID");
                int contact_id = rs.getInt("Contact_ID");

                Appointments apt = new Appointments(id, title, description, location, type, start, end, customer_id, user_id, contact_id);
                if(start.isAfter(begin) && start.isBefore(stop)) {

                    allAppointments.add(apt);
                }

            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return allAppointments;
    }

}
