package Model;

/**
 * This is the first level divisions class.
 *
 * @author Joseph DeMuth
 */
public class FirstLevelDivisions {

    private int DivisionID;
    private  String Division;
    private int CountryID;

    /**
     * Constructor for the FirstLevelDivisions class.
     *
     * @param DivisionID the division's id.
     * @param Division the division's name.
     * @param CountryID the division's country id.
     */
    public FirstLevelDivisions(int DivisionID, String Division, int CountryID) {
        this.DivisionID = DivisionID;
        this.Division = Division;
        this.CountryID = CountryID;

    }

    /**
     * Gets the Division ID.
     *
     * @return Division ID.
     */
    public int getDivisionID() {
        return DivisionID;
    }

    /**
     * Sets the Division ID.
     *
     * @param divisionID the division's id.
     */
    public void setDivisionID(int divisionID) {
        DivisionID = divisionID;
    }

    /**
     * Gets the Division.
     *
     * @return Division.
     */
    public String getDivision() {
        return Division;
    }

    /**
     * Sets the Division.
     *
     * @param division the division's name.
     */
    public void setDivision(String division) {
        Division = division;
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
     * Sets the Country ID.
     *
     * @param countryID the division's country id.
     */
    public void setCountryID(int countryID) {
        CountryID = countryID;
    }





}
