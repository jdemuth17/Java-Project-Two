package DataBase;

import Helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This is the first level divisions database access class.
 *
 * @author Joseph DeMuth
 */
public class DBFirstLevelDivisions {


    /**
     * Populates the ObservableList with Division names from the Client_schedule Data Base.
     *
     *
     * @return All divisions in an observable List.
     */
    public static ObservableList<String> getAllDivisions(){


        ObservableList<String> getAllDivisions = FXCollections.observableArrayList();


        try {
            String sql = "SELECT Division FROM client_schedule.first_level_divisions;";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                getAllDivisions.add(rs.getString("Division"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getAllDivisions;
    }

    /**
     * Populates the ObservableList with Division names from the Client_schedule Data Base. Based on Country ID.
     *
     *
     * @return Divisions by country in an observable List.
     */
    public static ObservableList<String> getAllDivisions(int CountryID){


        ObservableList<String> getAllDivisions = FXCollections.observableArrayList();


        try {
            String sql = "SELECT Division FROM client_schedule.first_level_divisions WHERE Country_ID = " + CountryID;

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                getAllDivisions.add(rs.getString("Division"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getAllDivisions;
    }

    /**
     * Gets Division ID from database based on Division name .
     *
     *
     * @return The Division_id.
     */
    public static int getDivisionID(String divisionName) throws SQLException {
        int Division_id = 0;

        String sql = "SELECT Division_ID FROM client_schedule.first_level_divisions WHERE Division = '"  + divisionName + "'" ;

        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            Division_id = rs.getInt(1);
            break;
        }

        return Division_id;
    }

    /**
     * Gets the Division name based on the Division ID.
     *
     * @param Divisionid the division's id.
     * @return The division name.
     * @throws SQLException
     */
    public static String getDivision(int Divisionid) throws SQLException {

        String division = null;

        try {

            String sql = "SELECT Division FROM client_schedule.first_level_divisions WHERE Division_ID ="  + Divisionid;

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                division = rs.getString("Division");
                break;

            }

        }catch (SQLException e) {
            e.printStackTrace();
        }

        return division;

    }

    /**
     * Populates the ObservableList with Division names from the Client_schedule Data Base, Based on the countries and first_level_division tables having matching Country_IDs.
     *
     * @param Country the division's country.
     * @return the division's name.
     * @throws SQLException
     */
    public static ObservableList<String> getCountryDiv(String Country) throws SQLException {

        ObservableList<String> division = FXCollections.observableArrayList();

        try {

            String sql = "SELECT Division FROM client_schedule.first_level_divisions, client_schedule.countries WHERE first_level_divisions.Country_ID = countries.Country_ID AND countries.Country = '" + Country +"'";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                division.add(rs.getString("Division"));


            }

        }catch (SQLException e) {
            e.printStackTrace();
        }

        return division;

    }


}
