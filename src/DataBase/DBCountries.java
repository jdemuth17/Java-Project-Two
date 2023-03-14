package DataBase;

import Helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static Helper.JDBC.connection;

/**
 * This is the countries database access class.
 *
 * @author Joseph DeMuth
 */
public class DBCountries {

    /**
     * Populates the observableList with country IDs from the Data Base.
     *
     *
     * @return All country IDs in an observable List.
     */
    public static ObservableList<String> getAllCountryIDs() {

        ObservableSet<String> observableSet = FXCollections.observableSet();
        ObservableList<String> getAllCountryIDs = null;
        try {
            String sql = "SELECT Country FROM client_schedule.countries;";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                observableSet.add( rs.getString("Country"));


            }

            getAllCountryIDs = FXCollections.observableArrayList(observableSet);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getAllCountryIDs;
    }

    /**
     * Gets country name from database based on CountryID .
     *
     * @param Divisionid the divisions id.
     * @return The country name as string, based on the division id.
     */
    public static String getCountry(int Divisionid) {

        String country = null;

        try {
            int Countryid = 0;


            String sql = "SELECT Country_ID FROM client_schedule.first_level_divisions WHERE Division_ID =" + Divisionid;

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Countryid = rs.getInt(1);
                Statement stmt2;
                stmt2 = connection.createStatement();
                stmt2.execute("SELECT Country FROM client_schedule.countries WHERE Country_ID = " + Countryid);
                ResultSet rSet = stmt2.getResultSet();
                while (rSet.next()){
                    country = rSet.getString(1);
                }

            }

        }catch (SQLException e) {
            e.printStackTrace();
        }

        return country;
    }

}
