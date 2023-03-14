package Model;

/**
 * This is the countries class.
 *
 * @author Joseph DeMuth
 */
public class countries {

    private int CountryID;
    private String CountryName;

    /**
     * Constructor for the countries class.
     *
     * @param CountryID the country's id.
     * @param CountryName the country's name.
     */
    public countries(int CountryID, String CountryName) {

        this.CountryID = CountryID;
        this.CountryName = CountryName;
    }

    /**
     * Gets the Country ID.
     *
     * @return Country ID.
     */
    public int getCountryID() {
        return CountryID;
    }

    /**
     * Sets fthe Country ID.
     *
     * @param countryID the country's id.
     */
    public void setCountryID(int countryID) {
        CountryID = countryID;
    }

    /**
     * Gets the Country Name.
     *
     * @return Country Name.
     */
    public String getCountryName() {
        return CountryName;
    }

    /**
     * Sets the Country Name.
     *
     * @param countryName the country's name.
     */
    public void setCountryName(String countryName) {
        CountryName = countryName;
    }








}
