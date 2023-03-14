package Model;

import java.time.LocalDateTime;

/**
 * The appointments class.
 *
 * @author Joseph DeMuth
 */
public class Appointments {

    private int id;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    private int customer_id;
    private int user_id;
    private int contact_id;
    private String contactName;

    /**
     * Constructor for the appointments class.
     *
     * @param id the appointment id.
     * @param title the appointment title.
     * @param description the appointment description.
     * @param location the appointment location.
     * @param type the appointment type.
     * @param start the appointment start time.
     * @param end the appointment end time.
     * @param customer_id the appointment customer id.
     * @param user_id the appointment user id.
     * @param contact_id the appointment contact id.
     */
    public  Appointments(int id, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int customer_id, int user_id, int contact_id ){

        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customer_id = customer_id;
        this.user_id = user_id;
        this.contact_id = contact_id;
    }

    /**
     * Overloaded constructor for report by Contact.
     *
     * @param id the appointment id.
     * @param title the appointment title.
     * @param description the appointment description.
     * @param type the appointment type.
     * @param start the appointment start time.
     * @param end the appointment end time.
     * @param customer_id the appointment customer id.
     * @param contactName the appointment customer name.
     */
    public Appointments(int id, String title, String description, String type, LocalDateTime start, LocalDateTime end, int customer_id, String contactName) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customer_id = customer_id;
        this.contactName = contactName;

    }

    /**
     * Overloaded constructor for report by month.
     *
     * @param title the appointment title.
     * @param type the appointment type.
     * @param customer_id the appointment customer id.
     */
    public Appointments( String type, String title, int customer_id) {
        this.type = type;
        this.title = title;;
        this.customer_id = customer_id;

    }

    /**
     * Overloaded constructor for report by Contact.
     *
     * @param id the appointment id.
     * @param title the appointment title.
     * @param description the appointment description.
     * @param type the appointment type.
     * @param start the appointment start time.
     * @param end the appointment end time.
     * @param customer_id the appointment customer id.
     */
    public Appointments(int id, String title, String description, String type, LocalDateTime start, LocalDateTime end, int customer_id) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customer_id = customer_id;

    }

    /**
     * Returns the Appointment ID.
     * @return id.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the Appointment ID.
     * @param id the appointment id.
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     *Returns the Appointment Title.
     * @return title.
     */
    public String getTitle() {
        return title;
    }
    /**
     *Sets the Appointment Title.
     * @param title the appointment title.
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     *Returns the Appointment Description.
     * @return description.
     */
    public String getDescription() {
        return description;
    }
    /**
     *Sets the Appointment Description.
     * @param description the appointment description.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     *Returns the Appointment Location.
     * @return location.
     */
    public String getLocation() {
        return location;
    }
    /**
     *Sets the Appointment Location.
     * @param location the appointment location.
     */
    public void setLocation(String location) {
        this.location = location;
    }
    /**
     *Returns the Appointment Type.
     * @return type.
     */
    public String getType() {
        return type;
    }
    /**
     *Sets the Appointment Type.
     * @param type the appointment type.
     */
    public void setType(String type) {
        this.type = type;
    }
    /**
     *Returns the Appointment Start time as LocalDateTime.
     * @return start.
     */
    public LocalDateTime getStart() {
        return start;
    }
    /**
     *Sets the Appointment Start time.
     * @param start the appointment start time.
     */
    public void setStart(LocalDateTime start) {
        this.start = start;
    }
    /**
     * Returns the Appointment End time as a LocalDateTime.
     * @return end.
     */
    public LocalDateTime getEnd() {
        return end;
    }
    /**
     * Sets the Appointment End time.
     * @param end the appointment end time.
     */
    public void setEnd(LocalDateTime end) {
        this.end = end;
    }
    /**
     *Returns the Appointment Customer ID.
     * @return customer id.
     */
    public int getCustomer_id() {
        return customer_id;
    }
    /**
     *Sets the Appointment customer ID.
     * @param customer_id the appointment customer id.
     */
    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }
    /**
     *Returns the Appointment User ID.
     * @return user id.
     */
    public int getUser_id() {
        return user_id;
    }
    /**
     *Sets the Appointment User ID.
     * @param user_id the appointment user id.
     */
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    /**
     *Returns the Appointment Contact ID.
     * @return contact id.
     */
    public int getContact_id() {
        return contact_id;
    }

    /**
     *Returns the Appointment Contact Name.
     * @return contactName.
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Sets the Appointment Contact Name.
     *
     * @param contactName the appointment contact name.
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     *Sets the Appointment Contact ID.
     *
     * @param contact_id the appointment contact id.
     */
    public void setContact_id(int contact_id) {
        this.contact_id = contact_id;
    }

}

