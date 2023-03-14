package DataBase;

import Helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This is the user's database access class.
 *
 * @author Joseph DeMuth
 */
public class DBUsers {

    /**
     * Populates the ObservableList with User IDs from the Client_schedule Data Base.
     *
     *
     * @return The all users in an Observable List.
     */
    public static ObservableList<Integer> getAllUserIDs(){


        ObservableList<Integer> getAllUserIDs = FXCollections.observableArrayList();


        try {
            String sql = "SELECT User_ID FROM client_schedule.users;";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                getAllUserIDs.add(rs.getInt("User_ID"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getAllUserIDs;
    }


}
