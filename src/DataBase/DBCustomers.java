package DataBase;

import Helper.JDBC;
import Model.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * This is the customers database access class.
 *
 * @author Joseph DeMuth
 */
public class DBCustomers {

    /**
     * Populates the customers observable list. With customers from the data base.
     *
     * @return All customers in an observable list.
     */
    public static ObservableList<Customers> getAllCustomers() {

        //Populate getAllCustomers List

        ObservableList<Customers> allCustomers = FXCollections.observableArrayList();

        try {
            String sql = "SELECT  `customers`.`Customer_ID`,`customers`.`Customer_Name`,`customers`.`Address`,`customers`.`Postal_Code`,`customers`.`Phone`,`customers`.`Division_ID` FROM `client_schedule`.`customers`;";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("Customer_ID");
                String name = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String postal_code = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                int division_id = rs.getInt("Division_ID");
                String country = DBCountries.getCountry(division_id);
                String division = DBFirstLevelDivisions.getDivision(division_id);


                Customers cust = new Customers(id, name, address, postal_code, phone, division_id, country, division);
                allCustomers.add(cust);
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return allCustomers;
    }

    /**
     *Populates the observableList with customer IDs from the Data Base.
     *
     *
     * @return All customer IDs in an Observable List.
     */
    public static ObservableList<Integer> getAllCustomerIDs(){


        ObservableList<Integer> getAllCustomerIDs = FXCollections.observableArrayList();


        try {
            String sql = "SELECT Customer_ID FROM client_schedule.customers;";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                getAllCustomerIDs.add(rs.getInt("Customer_ID"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getAllCustomerIDs;
    }


}
