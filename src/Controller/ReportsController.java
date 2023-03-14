package Controller;

import DataBase.DBContacts;
import DataBase.DBCountries;
import DataBase.DBReports;
import Model.Appointments;
import Model.Customers;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * This is the controller for the reports screen.
 *
 * @author Joseph DeMuth
 */
public class ReportsController implements Initializable{

        @FXML
        private TableColumn<?, ?> AppointmentID_Col;

        @FXML
        private Button BackButton;

        @FXML
        private TableColumn<?, ?> Country_Col;

        @FXML
        private TableColumn<?, ?> CustomerID_Col;

        @FXML
        private TableColumn<?, ?> Customer_Col;

        @FXML
        private TableColumn<?, ?> Discription_col;

        @FXML
        private TableColumn<?, ?> Division_Col;

        @FXML
        private TableColumn<?, ?> End_Col;

        @FXML
        private TableColumn<?, ?> Month_Col;

        @FXML
        private TableColumn<?, ?> Number_Col;

        @FXML
        private TableColumn<?, ?> Type_Month_Col;

        @FXML
        private TableView<Appointments> ApptByMonthTable;

        @FXML
        private TableView<Appointments> ContactScheduleTable;

        @FXML
        private TableView<Customers> CustomerByCountryTable;

        @FXML
        private ComboBox<String> SelectContact;

        @FXML
        private ComboBox<String> SelectCountry;

        @FXML
        private ComboBox<String> SelectMonth;

        @FXML
        private TableColumn<?, ?> Start_Col;

        @FXML
        private TableColumn<?, ?> Title_Col;

        @FXML
        private TableColumn<?, ?> Type_Col;


        /**
         *
         * Initializes the Reports Scene. Populates the ContactSchedule, CustomerByCountry, and ApptByMonth Tables.
         *
         * @param url Not used.
         * @param resourceBundle Not used.
         */
        public void initialize(URL url, ResourceBundle resourceBundle){

                SelectContact.setItems(DBContacts.getAllContacts());
                SelectContact.getSelectionModel().selectFirst();

                SelectCountry.setItems(DBCountries.getAllCountryIDs());
                SelectCountry.getSelectionModel().selectFirst();

                SelectMonth.setItems(DBReports.monthlist());
                SelectMonth.getSelectionModel().selectFirst();

                ContactScheduleTable.setItems(DBReports.scheduleByContactReport((SelectContact.getValue())));
                AppointmentID_Col.setCellValueFactory(new PropertyValueFactory<>("id"));
                Title_Col.setCellValueFactory(new PropertyValueFactory<>("title"));
                Discription_col.setCellValueFactory(new PropertyValueFactory<>("description"));
                Type_Col.setCellValueFactory(new PropertyValueFactory<>("type"));
                Start_Col.setCellValueFactory(new PropertyValueFactory<>("start"));
                End_Col.setCellValueFactory(new PropertyValueFactory<>("end"));
                CustomerID_Col.setCellValueFactory(new PropertyValueFactory<>("customer_id"));

                CustomerByCountryTable.setItems(DBReports.customersByCountry(SelectCountry.getValue()));
                Customer_Col.setCellValueFactory(new PropertyValueFactory<>("name"));
                Country_Col.setCellValueFactory(new PropertyValueFactory<>("country"));
                Division_Col.setCellValueFactory(new PropertyValueFactory<>("division"));

                ApptByMonthTable.setItems(DBReports.AppointmentTypeByMonth2(SelectMonth.getValue()));
                Month_Col.setCellValueFactory(new PropertyValueFactory<>("title"));
                Number_Col.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
                Type_Month_Col.setCellValueFactory(new PropertyValueFactory<>("type"));

        }

        /**
         * Filters the ContactScheduleTable based on the selected contact.
         *
         * Lambda expression two. Lambda expression used to simplify filtering the ContactScheduleTable.
         *
         * @param event This is the event caused by selecting new contact.
         */
        @FXML
        void OnActionSelectContact(ActionEvent event) {

                FilteredList<Appointments> FoundAppointments = new FilteredList<>(FXCollections.observableArrayList(DBReports.scheduleByContactReport2()));

                FoundAppointments.setPredicate(Appointment -> {
                        System.out.println(Appointment.getContactName());
                        if (Appointment.getContactName().equalsIgnoreCase(SelectContact.getValue()))
                                return true;
                        else
                                return false;

                });


                System.out.println(SelectContact.getValue());

                ContactScheduleTable.setItems(FoundAppointments);
                AppointmentID_Col.setCellValueFactory(new PropertyValueFactory<>("id"));
                Title_Col.setCellValueFactory(new PropertyValueFactory<>("title"));
                Discription_col.setCellValueFactory(new PropertyValueFactory<>("description"));
                Type_Col.setCellValueFactory(new PropertyValueFactory<>("type"));
                Start_Col.setCellValueFactory(new PropertyValueFactory<>("start"));
                End_Col.setCellValueFactory(new PropertyValueFactory<>("end"));
                CustomerID_Col.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        }

        /**
         *  Filters the CoustomerByCountryTable based on the selected country.
         *
         * Lambda expression one. Lambda expression used to simplify filtering the CustomerByCountryTable.
         *
         * @param event This is the event caused by selecting a new country.
         */
        @FXML
        void OnActionSelectCountry(ActionEvent event) {

                FilteredList<Customers> FoundCustomers = new FilteredList<>(FXCollections.observableArrayList(DBReports.customersByCountry2()));

                FoundCustomers.setPredicate(Customer -> {

                        if(Customer.getCountry().equalsIgnoreCase(SelectCountry.getValue().toLowerCase()))
                                return true;
                        else
                                return false;
                });

                CustomerByCountryTable.setItems(FoundCustomers);
                Customer_Col.setCellValueFactory(new PropertyValueFactory<>("name"));
                Country_Col.setCellValueFactory(new PropertyValueFactory<>("country"));
                Division_Col.setCellValueFactory(new PropertyValueFactory<>("division"));

        }

        /**
         * Filters the Appointment by month table based on the selected month.
         *
         * @param event This is the event caused by selecting a new month.
         */
        @FXML
        void OnActionSelectMonth(ActionEvent event) {

                ApptByMonthTable.setItems(DBReports.AppointmentTypeByMonth2(SelectMonth.getValue()));
                Month_Col.setCellValueFactory(new PropertyValueFactory<>("title"));
                Number_Col.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
                Type_Month_Col.setCellValueFactory(new PropertyValueFactory<>("type"));

        }


        /**
         * Back Button Returns to the Appointments screen.
         *
         * @param actionEvent This is the event caused by clicking the back button.
         * @throws IOException
         */
        @FXML
        void OnactionBackButton(ActionEvent actionEvent) throws IOException {

                Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                Parent scene = FXMLLoader.load(getClass().getResource("/View/Appointments.fxml"));
                stage.setScene(new Scene(scene));
                stage.centerOnScreen();
                stage.show();

        }

}


