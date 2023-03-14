package DataBase;

import Helper.JDBC;
import Model.Appointments;
import Model.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.Month;

/**
 * This is the reports database access class.
 *
 * @author Joseph DeMuth
 */
public class DBReports {

    /**
     * Populates the ObservableList with Country IDs from the Client_schedule Data Base.
     *
     *
     * @return returns appointments in an observable list filtered by contact name.
     */
    public static ObservableList<Appointments> scheduleByContactReport(String Contact) {

        ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();

        String contact = Contact;

        try {
            String sql = "SELECT Appointment_ID, Title , Description , Type , Start, End , Customer_ID FROM client_schedule.appointments, client_schedule.contacts WHERE appointments.Contact_ID = contacts.Contact_ID AND contacts.Contact_Name = '" + Contact +"'";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String type = rs.getString("Type");
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                int customer_id = rs.getInt("Customer_ID");

                Appointments apt = new Appointments(id, title, description, type, start, end, customer_id);
                allAppointments.add(apt);
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return allAppointments;
    }

    /**
     * Populates the ObservableList with Country IDs from the Client_schedule Data Base.
     *
     *
     * @return returns all appointments in an observable list.
     */
    public static ObservableList<Appointments> scheduleByContactReport2() {

        ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();



        try {
            String sql = "SELECT  Appointment_ID, Title , Description , Type , Start, End , Customer_ID,  Contact_Name  FROM appointments JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID ";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String type = rs.getString("Type");
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                int customer_id = rs.getInt("Customer_ID");
                String contactName = rs.getString("Contact_Name");

                Appointments apt = new Appointments(id, title, description, type, start, end, customer_id, contactName);
                allAppointments.add(apt);
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return allAppointments;
    }

    /**
     * Populates the ObservableList with Months of the year.
     *
     *
     * @return returns an observable list of the months of the year.
     */
    public static ObservableList<String> monthlist(){

        ObservableList<String> allMonths = FXCollections.observableArrayList();

        allMonths.add(String.valueOf(Month.JANUARY).toUpperCase());
        allMonths.add(String.valueOf(Month.FEBRUARY).toUpperCase());
        allMonths.add(String.valueOf(Month.MARCH).toUpperCase());
        allMonths.add(String.valueOf(Month.APRIL).toUpperCase());
        allMonths.add(String.valueOf(Month.MAY).toUpperCase());
        allMonths.add(String.valueOf(Month.JUNE).toUpperCase());
        allMonths.add(String.valueOf(Month.JULY).toUpperCase());
        allMonths.add(String.valueOf(Month.AUGUST).toUpperCase());
        allMonths.add(String.valueOf(Month.SEPTEMBER).toUpperCase());
        allMonths.add(String.valueOf(Month.OCTOBER).toUpperCase());
        allMonths.add(String.valueOf(Month.NOVEMBER).toUpperCase());
        allMonths.add(String.valueOf(Month.DECEMBER).toUpperCase());






        return allMonths;
    }

    /**
     * Populates the ObservableList with Customers based on their country from the Data Base.
     *
     *
     * @return returns customers by country in an observable list.
     */
    public static ObservableList<Customers> customersByCountry(String Country){
        ObservableList<Customers> CustByCountry = FXCollections.observableArrayList();

        String country1 = Country;

        try {
            String sql = "SELECT customers.Customer_Name, countries.Country, first_level_divisions.Division FROM customers JOIN first_level_divisions ON customers.Division_ID = first_level_divisions.Division_ID JOIN countries ON first_level_divisions.Country_ID = countries.Country_ID WHERE Country = '" + country1 +"'";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                String name = rs.getString("Customer_Name");
                String country = rs.getString("Country");
                String division = rs.getString("Division");

                Customers Cust = new Customers(name,  country,  division);
                CustByCountry.add(Cust);
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return CustByCountry;
    }

    /**
     * Populates the ObservableList with Country IDs from the Data Base.
     *
     *
     * @return returns all appointments in an observable list.
     */
    public static ObservableList<Customers> customersByCountry2(){
        ObservableList<Customers> CustByCountry = FXCollections.observableArrayList();



        try {
            String sql = "SELECT customers.Customer_Name, countries.Country, first_level_divisions.Division FROM customers JOIN first_level_divisions ON customers.Division_ID = first_level_divisions.Division_ID JOIN countries ON first_level_divisions.Country_ID = countries.Country_ID ";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                String name = rs.getString("Customer_Name");
                String country = rs.getString("Country");
                String division = rs.getString("Division");

                Customers Cust = new Customers(name,  country,  division);
                CustByCountry.add(Cust);
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return CustByCountry;
    }

    /**
     * Populates the ObservableList with Appointments from the Data Base.
     *
     *
     * @return returns appointments by type and month Based on month and grouped by type.
     */
    public static ObservableList<Appointments> AppointmentTypeByMonth2(String Month){
        ObservableList<Appointments> AppointmentTypes = FXCollections.observableArrayList();

        String month = Month;


        try {
            String sql = "SELECT monthname(Start) as Start, Type,  COUNT(Type) as count FROM appointments GROUP BY type, monthname(Start)  ORDER BY count DESC";


            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                String Start = rs.getString("Start");
                String type;
                String start;
                int customer_id;

                if(Start.equalsIgnoreCase( Month)) {


                    start = rs.getString("Start").toUpperCase();

                    type = rs.getString("Type");

                    customer_id = rs.getInt("count");

                    Appointments apt = new Appointments(type, start, customer_id);
                    AppointmentTypes.add(apt);
                }
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return AppointmentTypes;
    }

}
