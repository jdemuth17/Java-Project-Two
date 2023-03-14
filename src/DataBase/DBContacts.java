package DataBase;

import Helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This is the contacts database access class.
 *
 * @author Joseph DeMuth
 */
public class DBContacts {


    /**
     * Gets contact ID from database based on contact name.
     *
     * @return the contact's id.
     */
    public static int getContactID(String name) throws SQLException {


        int id = 0;

        String sql = "SELECT Contact_ID FROM client_schedule.contacts WHERE Contact_Name = '" + name + "' ;";

        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

        ResultSet contactName = ps.executeQuery();

        while (contactName.next()){
            id = contactName.getInt(1);
        }

        return id;
    }

    /**
     * Gets contact name from database based on contact ID.
     *
     * @param selectedContact the contact's id.
     * @return the contact's name.
     * @throws SQLException
     */
    public static String getContact(int selectedContact) throws SQLException {


        String name = null;

        String sql = "SELECT Contact_Name FROM client_schedule.contacts WHERE Contact_ID = " + selectedContact + " ;";

        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

        ResultSet contactName = ps.executeQuery();

        while (contactName.next()){
            name = contactName.getString(1);
        }

        return name;
    }

    /**
     * Populates the ObservableList with Contacts from the data Base.
     *
     *
     * @return All contacts in an observable List.
     */

    public static ObservableList<String> getAllContacts(){


        ObservableList<String> getAllContacts = FXCollections.observableArrayList();


        try {
            String sql = "SELECT Contact_Name FROM client_schedule.contacts;";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                getAllContacts.add(rs.getString("Contact_Name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getAllContacts;
    }


}
