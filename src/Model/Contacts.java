package Model;
/**
 * The contacts class.
 *
 * @author Joseph DeMuth
 */
public class Contacts {

    private int ContactID;
    private String Name;
    private  String Email;

    /**
     * Constructor for the contacts class.
     *
     * @param ContactID the contact's id.
     * @param Name the contact's name.
     * @param Email the contact's email address.
     */
    public Contacts(int ContactID,String Name, String Email) {
        this.ContactID = ContactID;
        this.Name = Name;
        this.Email = Email;
    }

    /**
     * Gets the contact ID.
     *
     * @return ContactID
     */
    public int getContactID() {
        return ContactID;
    }

    /**
     * Sets the Contact ID.
     *
     * @param contactID the contact's id.
     */
    public void setContactID(int contactID) {
        ContactID = contactID;
    }

    /**
     * Gets the contact Name.
     *
     * @return Contact Name.
     */
    public String getName() {
        return Name;
    }

    /**
     * SEts the Contact Name.
     *
     * @param name the contact's name.
     */
    public void setName(String name) {
        Name = name;
    }

    /**
     * Gets the contact Email.
     *
     * @return Contact Email.
     */
    public String getEmail() {
        return Email;
    }

    /**
     * Sets the Contact Email
     *
     * @param email the contact's email address.
     */
    public void setEmail(String email) {
        Email = email;
    }
}
