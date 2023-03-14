package Model;

/**
 * This is the users class.
 *
 * @author Joseph DeMuth
 */
public class Users {

    private int UserID;
    private String UserName;
    private String Password;

    /**
     *  Constructor for the users class.
     *
     *
     * @param UserID The user's id.
     * @param UserName The user's username for login.
     * @param Password The user's password for login.
     */
    public Users(int UserID,String UserName, String Password) {
        this.UserID = UserID;
        this.UserName = UserName;
        this.Password = Password;
    }

    /**
     * Gets the User ID.
     *
     * @return User id.
     */
    public int getUserID() {
        return UserID;
    }

    /**
     * Sets the User ID.
     *
     * @param userID The user's id.
     */
    public void setUserID(int userID) {
        UserID = userID;
    }

    /**
     * Gets the Username.
     *
     * @return User Name.
     */
    public String getUserName() {
        return UserName;
    }

    /**
     * Sets the Username.
     *
     * @param userName Username for login.
     */
    public void setUserName(String userName) {
        UserName = userName;
    }

    /**
     * Gets the User Password.
     *
     * @return Password.
     */
    public String getPassword() {
        return Password;
    }

    /**
     * Sets the User Password.
     *
     * @param password User's password for login.
     */
    public void setPassword(String password) {
        Password = password;
    }
}
