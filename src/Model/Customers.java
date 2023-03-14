package Model;

/**
 * The customers class.
 *
 * @author Joseph DeMuth
 */
public class Customers {

        private int id;
        private String name;
        private String address;
        private String postal_code;
        private String phone;
        private int division_id;
        private String country;
        private String division;

    /**
     * The constructor for the customers class.
     *
     * @param id the customers' id.
     * @param name the customers name.
     * @param address the customers address.
     * @param postal_code the customers postal code.
     * @param phone the customers phone number.
     * @param division_id the customers division id.
     * @param country the customers country name.
     * @param division the customers division name.
     */
        public  Customers(int id, String name, String address, String postal_code, String phone, int division_id , String country, String division ){

            this.id = id;
            this.name = name;
            this.address = address;
            this.postal_code = postal_code;
            this.phone = phone;
            this.division_id = division_id;
            this.country= country;
            this.division = division;

        }

    /**
     * Overloaded customer constructor for the report "customer by country".
     *
     * @param name the customer's name.
     * @param country the customer's country name.
     * @param division the customer's division name.
     */
    public  Customers(String name, String country, String division ){

        this.name = name;
        this.country= country;
        this.division = division;

    }

    /**
     *  Overloaded customer constructor.
     *
     * @param id the customer's id.
     * @param name the customer's name
     * @param address the customer's address.
     * @param postal_code the customer's postal code.
     * @param phone the customer's phone number.
     * @param division_id the customer's division id.
     */
           public  Customers(int id, String name, String address, String postal_code, String phone, int division_id ) {

            this.id = id;
            this.name = name;
            this.address = address;
            this.postal_code = postal_code;
            this.phone = phone;
            this.division_id = division_id;

         }

    /**
     * Gets the Customer ID.
     *
     * @return  id.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets Customer ID
     *
     * @param id the customer's id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the Customer Name.
     *
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets Customer Name
     *
     * @param name the customer's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the Customer Address.
     *
     * @return address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets Customer Address.
     *
     * @param address the customer's address.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the Customer Postal Code.
     *
     * @return postal code.
     */
    public String getPostal_code() {
        return postal_code;
    }

    /**
     * Sets Customer Postal Code.
     *
     * @param postal_code the customer's postal code.
     */
    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    /**
     * Gets the Customer Phone.
     *
     * @return phone.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets Customer Phone.
     *
     * @param phone the customer's phone number.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets the Customer Division ID.
     *
     * @return Customer division  id.
     */
    public  int getDivision_id() {
        return division_id;
    }

    /**
     * Sets Customer Division ID.
     *
     * @param division_id the customer's division id.
     */
    public void setDivision_id(int division_id) {
        this.division_id = division_id;
    }

    /**
     * Gets the Customer Division.
     *
     * @return Customer division.
     */
    public String getDivision() {
        return division;
    }

    /**
     * Gets the Customer Country.
     *
     * @return Customer country.
     */
    public String getCountry() {
        return country;
    }

}
