package Controller;

import DataBase.DBCountries;
import DataBase.DBCustomers;
import DataBase.DBFirstLevelDivisions;
import Helper.JDBC;
import Model.Customers;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import static Helper.JDBC.connection;

/**
 *This is the controller for the customers screen.
 *
 * @author Joseph DeMuth
 */
public class CustomersController implements Initializable {

    @FXML
    private TextArea TextArea;

    @FXML
    private ComboBox<String> CountryCombo;

    @FXML
    private Button Cust_Add_Button;

    @FXML
    private TextField Cust_Address;

    @FXML
    private TableColumn<Customers, String> Cust_Address_Col;

    @FXML
    private TableColumn<Customers, String> Cust_Country_Col;

    @FXML
    private TextField Cust_ID;

    @FXML
    private TableColumn<Customers, Integer> Cust_ID_Col;

    @FXML
    private TextField Cust_Name;

    @FXML
    private TableColumn<Customers, String> Cust_Name_Col;

    @FXML
    private TextField Cust_Phone;

    @FXML
    private TableColumn<Customers, String> Cust_Phone_Col;

    @FXML
    private TextField Cust_Postal;

    @FXML
    private TableColumn<Customers, String> Cust_Postal_Col;

    @FXML
    private TableColumn<Customers, String> Cust_State_Col;

    @FXML
    private TableView<Customers> Customer_Table;

    @FXML
    private ComboBox<String> StateCombo;

    Stage stage;
    Parent scene;


    /**
     * Adds or Modifies a Customer in the Database.
     *
     * @param event This is the event caused by clicking the add button.
     */
    @FXML
    void OnActionAddButton(ActionEvent event) {

        int a = 0;

        try {

            try {
                a = Integer.parseInt(Cust_ID.getText());
            } catch (NumberFormatException nfe) {
                // Handle the condition when str is not a number.
                a = 0;
            }
            int CUSTID = a;
            String CUSTNAME = Cust_Name.getText();
            String CUSTADDRESS = Cust_Address.getText();
            String CUSTPOSTAL = Cust_Postal.getText();
            String CUSTPHONE = Cust_Phone.getText();
            String STATE = StateCombo.getValue();
            String COUNTRY = CountryCombo.getValue();
            int DIVISIONID = DBFirstLevelDivisions.getDivisionID(STATE);



            if ( fieldsNotEmpty()) {
                if (hasCustID()) {

                    String sql1 = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone= ?, Division_ID= ? WHERE Customer_ID= ?";

                    PreparedStatement stmt = connection.prepareStatement(sql1);
                    stmt.setString(1, CUSTNAME);
                    stmt.setString(2, CUSTADDRESS);
                    stmt.setString(3, CUSTPOSTAL);
                    stmt.setString(4, CUSTPHONE);
                    stmt.setInt(5, DIVISIONID);
                    stmt.setInt(6, CUSTID);
                    System.out.println(stmt);
                    stmt.executeUpdate();

                    Customer_Table.setItems(DBCustomers.getAllCustomers());
                    Cust_ID_Col.setCellValueFactory(new PropertyValueFactory<>("id"));
                    Cust_Name_Col.setCellValueFactory(new PropertyValueFactory<>("name"));
                    Cust_Address_Col.setCellValueFactory(new PropertyValueFactory<>("address"));
                    Cust_Postal_Col.setCellValueFactory(new PropertyValueFactory<>("Postal_code"));
                    Cust_Phone_Col.setCellValueFactory(new PropertyValueFactory<>("phone"));
                    Cust_Country_Col.setCellValueFactory(new PropertyValueFactory<>("country"));
                    Cust_State_Col.setCellValueFactory(new PropertyValueFactory<>("division"));

                } else if(!hasCustID())  {

                    String sql2 = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES (?,?,?,?,?)";

                    PreparedStatement stmt = connection.prepareStatement(sql2);
                    stmt.setString(1, CUSTNAME);
                    stmt.setString(2, CUSTADDRESS);
                    stmt.setString(3, CUSTPOSTAL);
                    stmt.setString(4, CUSTPHONE);
                    stmt.setInt(5, DIVISIONID);
                    System.out.println(stmt.toString());
                    stmt.executeUpdate();

                    Customer_Table.setItems(DBCustomers.getAllCustomers());
                    Cust_ID_Col.setCellValueFactory(new PropertyValueFactory<>("id"));
                    Cust_Name_Col.setCellValueFactory(new PropertyValueFactory<>("name"));
                    Cust_Address_Col.setCellValueFactory(new PropertyValueFactory<>("address"));
                    Cust_Postal_Col.setCellValueFactory(new PropertyValueFactory<>("Postal_code"));
                    Cust_Phone_Col.setCellValueFactory(new PropertyValueFactory<>("phone"));
                    Cust_Country_Col.setCellValueFactory(new PropertyValueFactory<>("country"));
                    Cust_State_Col.setCellValueFactory(new PropertyValueFactory<>("division"));


                }
            }
        } catch (SQLException e) {
            Alerts(2);
            e.printStackTrace();
        }

    }

    /**
     * Checks that all fields are populated.
     *
     * @return Boolean value, returns true if there are no empty in put fields, otherwise returns false.
     */
    public boolean fieldsNotEmpty() throws SQLException {
        boolean fieldsnotempty = false;

        String CUSTNAME = Cust_Name.getText();
        String CUSTADDRESS = Cust_Address.getText();
        String CUSTPOSTAL = Cust_Postal.getText();
        String CUSTPHONE = Cust_Phone.getText();
        String STATE = StateCombo.getValue();
        String COUNTRY = CountryCombo.getValue();

        if(CUSTNAME != null && CUSTADDRESS != null && CUSTPOSTAL != null && CUSTPHONE != null && STATE !=null && COUNTRY != null){
            fieldsnotempty = true;
        }

        if(fieldsnotempty == false){
            Alerts(1);
        }
        return fieldsnotempty;
    }

    /**
     * Checks if Customer has an id.
     *
     * @return Boolean value, returns true if the appointment id field is populated otherwise returns false.
     */
    public  boolean hasCustID(){
        boolean hasCustID = true;

        String CUSTID = Cust_ID.getText();
        System.out.println("Cust ID is " + CUSTID);
        if(CUSTID.isEmpty()){
            hasCustID = false;
        }
        return  hasCustID;
    }

    /**
     * Popluates the Division Combo Box with Appropriate data.
     *
     * @param event This is the event caused by selecting a country.
     * @throws SQLException
     */
    @FXML
    void OnActionCountryCombo(ActionEvent event) throws SQLException {

        ObservableList  newList = DBFirstLevelDivisions.getCountryDiv(CountryCombo.getSelectionModel().getSelectedItem());

        StateCombo.setItems(newList);

    }

    /**
     * Deletes a Customer after verification prompt.
     *
     * @param event This is the event caused by clicking the delete button.
     */
    @FXML
    void OnActionDeleteButton(ActionEvent event) {

            Customers SelectedCustomer = Customer_Table.getSelectionModel().getSelectedItem();

            if (SelectedCustomer != null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Confirm delete Customer. This will Delete All Associated Appointments !");
                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        try {

                            int Cust_ID = SelectedCustomer.getId();

                            Statement stmt1;
                            stmt1 = connection.createStatement();
                            stmt1.execute("DELETE FROM client_schedule.appointments WHERE Customer_ID = " + Cust_ID);


                            String sql = "select Customer_ID from client_schedule.customers";

                            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

                            ResultSet rs = ps.executeQuery();

                            while (rs.next()) {

                                int CUSTID = rs.getInt("Customer_ID");

                                if (Objects.equals(CUSTID, Cust_ID)) {

                                    Statement stmt2;
                                    stmt2 = connection.createStatement();
                                    stmt2.execute("DELETE FROM client_schedule.customers WHERE Customer_ID = " + CUSTID);

                                    TextArea.setText(" Customer with Customer ID of " + CUSTID + " has been removed from the DataBase!\n All associated Appointments have been canceled!");
                                    Timer timer = new Timer();
                                    TimerTask timertask = new TimerTask() {
                                        @Override
                                        public void run() {
                                            TextArea.setText("You can create new customers, or modify and delete existing customers.\nDeleting a customer will also cancel all of their appointments!");
                                        }
                                    };
                                    timer.schedule(timertask,15000);
                                }
                            }

                            Customer_Table.setItems(DBCustomers.getAllCustomers());
                            Cust_ID_Col.setCellValueFactory(new PropertyValueFactory<>("id"));
                            Cust_Name_Col.setCellValueFactory(new PropertyValueFactory<>("name"));
                            Cust_Address_Col.setCellValueFactory(new PropertyValueFactory<>("address"));
                            Cust_Postal_Col.setCellValueFactory(new PropertyValueFactory<>("Postal_code"));
                            Cust_Phone_Col.setCellValueFactory(new PropertyValueFactory<>("phone"));
                            Cust_Country_Col.setCellValueFactory(new PropertyValueFactory<>("country"));
                            Cust_State_Col.setCellValueFactory(new PropertyValueFactory<>("division"));


                        } catch (Exception e) {
                            Alerts(3);
                        }
                    }
                });
            } else Alerts(6);
    }

    /**
     * Populates modification fields with selected customer's information.
     *
     * @param event This is the event caused by clicking the select button.
     */
    @FXML
    void OnActionModifyButton(ActionEvent event) {

                Customers SelectedCustomer = Customer_Table.getSelectionModel().getSelectedItem();


                try {

                    Cust_ID.setText(String.valueOf(SelectedCustomer.getId()));
                    Cust_Name.setText(String.valueOf(SelectedCustomer.getName()));
                    Cust_Address.setText(String.valueOf(SelectedCustomer.getAddress()));
                    Cust_Postal.setText(String.valueOf(SelectedCustomer.getPostal_code()));
                    Cust_Phone.setText(String.valueOf(SelectedCustomer.getPhone()));
                    StateCombo.setValue(SelectedCustomer.getDivision());
                    CountryCombo.setValue(SelectedCustomer.getCountry());


                    Cust_Add_Button.setText("Modify Customer");

                } catch (Exception e) {
                    Alerts(6);
                }
    }

    /**
     * Clears Customer modification fields. Sets add button name.
     *
     * @param event  This is the event caused by clicking the clear button.
     */
    @FXML
    void OnActionClear(ActionEvent event) {

        Cust_ID.setText(null);
        Cust_Name.setText(null);
        Cust_Address.setText(null);
        Cust_Postal.setText(null);
        Cust_Phone.setText(null);
        StateCombo.getSelectionModel().clearSelection();
        CountryCombo.getSelectionModel().clearSelection();

        Cust_Add_Button.setText("Add Customer");

    }

    /**
     *
     * Initializes the Customers Scene. Populates the Customers table and the country combo box.
     *
     * @param url Not used.
     * @param resourceBundle Not used.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        Customer_Table.setItems(DBCustomers.getAllCustomers());
        Cust_ID_Col.setCellValueFactory(new PropertyValueFactory<>("id"));
        Cust_Name_Col.setCellValueFactory(new PropertyValueFactory<>("name"));
        Cust_Address_Col.setCellValueFactory(new PropertyValueFactory<>("address"));
        Cust_Postal_Col.setCellValueFactory(new PropertyValueFactory<>("Postal_code"));
        Cust_Phone_Col.setCellValueFactory(new PropertyValueFactory<>("phone"));
        Cust_Country_Col.setCellValueFactory(new PropertyValueFactory<>("country"));
        Cust_State_Col.setCellValueFactory(new PropertyValueFactory<>("division"));

        CountryCombo.setItems(DBCountries.getAllCountryIDs());
        TextArea.setText("You can create new customers, or modify and delete existing customers.\nDeleting a customer will also cancel all of their appointments!");

    }

    /**
     * Returns to the Appointments screen.
     *
     * @param actionEvent This is the event caused by clicking the back button.
     * @throws IOException
     */
    @FXML
    void OnActionBackButton(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/View/Appointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.centerOnScreen();
        stage.show();

    }

    /**
     * Error messages
     *
     * @param alerts different error messages to be called.
     */
    private void Alerts(int alerts) {

        Alert alert = new Alert(Alert.AlertType.ERROR);

        switch (alerts) {
            case 1:
                alert.setTitle("Error");
                alert.setContentText("All Fields Required!");
                alert.showAndWait();

            case 2:
                alert.setTitle("Error");
                alert.setContentText("Add Customer Failed");
                alert.showAndWait();

            case 3:
                alert.setTitle("Error");
                alert.setContentText("Delete Customer Failed!");
                alert.showAndWait();

            case 4:
                alert.setTitle("Error");
                alert.setContentText("Empty Fields Error");
                alert.showAndWait();

            case 5:
                alert.setTitle("Error");
                alert.setContentText("Expects String value");
                alert.showAndWait();

            case 6:
                alert.setTitle("Error");
                alert.setContentText("Select a Customer.");
                alert.showAndWait();

            case 7:
                alert.setTitle("Error");
                alert.setContentText("Select an item to remove.");
                alert.showAndWait();


        }

    }
}


