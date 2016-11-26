import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;


public class MainScene extends Application {

    // constants - flight airports
    private static final String CORK = "ORK";
    private static final String MADRID = "MAD";
    private static final String ST_BRIEUC = "SBK";
    private static final String JERSEY = "JER";
    private static final String PARIS = "CDG";
    private static final String STANSTED = "STN";
    private static final String MALAGA = "AGP";
    private static final String FRI = "FRIDAY";
    private static final String SAT = "SATURDAY";
    private static final String SUN = "SUNDAY";
    // constants - flight prices
    private static final int TWO_HND_EIGHTY = 280;
    private static final int TWO_HND_FIFTY = 250;
    private static final int TWO_HND_FORTY = 240;
    private static final int TWO_HND = 200;
    private static final int ONE_HND_FIFTY = 150;
    private static final int ONE_HND_FORTY = 140;
    private static final int ONE_HND_TWENTY = 120;
    private static final int ONE_HND = 100;
    private static final int EIGHTY = 80;
    private static final int SIXTY = 60;
    private static final int FORTY = 40;
    private static final int ZERO = 0;
    private static final double BAGGAGE_PRICE = 15.0;
    private static final Double CHILD_PRICE = 60.0;
    private static final Double CHILD_TOTAL_PRICE = CHILD_PRICE * 2;
    private static final Double BABY_PRICE = 0.0;
    private static final int MAX_PASSENGER_NO = 8;
    private static final ObservableList<Integer> baggageNumbers = FXCollections.observableArrayList();
    private static final ObservableList airportList = FXCollections.observableArrayList();
    // constants  flight times
    private static String ORK_MAD_1 = "0920-1300";
    private static String ORK_SBK_1 = "1030-1400";
    private static String ORK_JER_1 = "1400-1600";
    private static String ORK_CDG_1 = "0900-1215";
    private static String ORK_CDG_2 = "1820-2105";
    private static String ORK_STN_1 = "0820-0950";
    private static String ORK_STN_2 = "1120-1305";
    private static String ORK_AGP_1 = "0800-1130";
    private static String MAD_ORK_1 = "1800-2000";
    private static String MAD_SBK_1 = "1200-1400";
    private static String MAD_JER_1 = "0620-0800";
    private static String MAD_CDG_1 = "0800-1000";
    private static String MAD_STN_1 = "1400-1520";
    private static String MAD_STN_2 = "1905-2120";
    private static String MAD_AGP_1 = "0800-0905";
    private static String SBK_ORK_1 = "1900-2020";
    private static String SBK_MAD_1 = "1800-2020";
    private static String SBK_JER_1 = "none";
    private static String SBK_CDG_1 = "0620-0715";
    private static String SBK_STN_1 = "0805-0830";
    private static String SBK_AGP_1 = "1200-1530";
    private static String JER_ORK_1 = "1000-1200";
    private static String JER_MAD_1 = "1800-2120";
    private static String JER_CDG_1 = "0800-1015";
    private static String JER_STN_1 = "1700-1830";
    private static String JER_AGP_1 = "0800-1130";
    private static String CDG_ORK_1 = "1330-1500";
    private static String CDG_ORK_2 = "2200-2350";
    private static String CDG_MAD_1 = "1920-2105";
    private static String CDG_SBK_1 = "1900-2005";
    private static String CDG_JER_1 = "2000-2015";
    private static String CDG_STN_1 = "1800-1830";
    private static String CDG_AGP_1 = "1150-1330";
    private static String STN_ORK_1 = "1100-1220";
    private static String STN_ORK_2 = "1800-1920";
    private static String STN_MAD_1 = "1020-1400";
    private static String STN_SBK_1 = "1800-2000";
    private static String STN_JER_1 = "0900-1030";
    private static String STN_CDG_1 = "0900-1030";
    private static String STN_AGP_1 = "0800-1100";
    private static String STN_AGP_2 = "1330-1620";
    private static String AGP_ORK_1 = "1300-1420";
    private static String AGP_MAD_1 = "2000-2105";
    private static String AGP_SBK_1 = "2000-2130";
    private static String AGP_JER_1 = "1800-1930";
    private static String AGP_CDG_1 = "1805-1230";
    private static String AGP_STN_1 = "1500-1610";
    private static String AGP_STN_2 = "2035-2105";
    private VBox vBoxRadioBtns1, vBoxRadioBtns2;
    private String mFlightTimes, flightTime1, flightTime2, selectedDeptTime, selectedReturnTime;
    private int numberOfChildren, numberOfAdults;
    private final ToggleGroup toggleGroupFlights = new ToggleGroup();
    private final ToggleGroup toggleGroupFlightTimes_1 = new ToggleGroup();
    private final ToggleGroup toggleGroupFlightTimes_2 = new ToggleGroup();
    ObservableList<Integer> spinnerObservableList = FXCollections.observableArrayList();
    private ImageView imageViewReturn, imageViewDept;
    private Button buttonCancel, buttonContinue;
    private ComboBox<String> comboOrigin;
    private ComboBox<String> comboDestination;
    private RadioButton radioBtn1, radioBtn2, radioBtn3, radioBtn4, radioBtn5, radioBtn6, radioBtn7, radioBtn8;
    private DatePicker datePickerDeparture;
    private DatePicker datePickerReturn;
    private GridPane gridPaneMiddle;
    private TextArea textAreaDepart, textAreaReturn;
    private Label labelOrigin, labelDestination, labelDateDeparture, labelDateReturn;
    private ListView listView;
    private RadioButton radioButtonReturn, radioButtonDeptTime1, radioButtonDeptTime2, radioButtonReturnTime1, radioButtonReturnTime2;
    private String dptFlight, rtnFlight;
    private double dateDepartPrice;
    private double dateReturnPrice;
    private double flightPrice;
    private double currentPrice;
    private LocalDate ldDepartDate, ldReturnDate;
    private GridPane gridPaneLeft;
    private Stage window;
    private Scene scene1, scene2;
    private VBox vBox;
    private ObservableList<TextField> tfFirstNamesList = FXCollections.observableArrayList();
    private ObservableList<DatePicker> dpDateOfBirthList = FXCollections.observableArrayList();
    private ObservableList<RadioButton> radioBtnList = FXCollections.observableArrayList();
    public TextField fName, fName2, fName3, fName4, fName5, fName6, fName7, fName8,
            lName, lName2, lName3, lName4, lName5, lName6, lName7, lName8;
    private DatePicker dateOfBirth1, dateOfBirth2, dateOfBirth3, dateOfBirth4, dateOfBirth5, dateOfBirth6, dateOfBirth7, dateOfBirth8;
    private Spinner<Integer> spinnerPassengerNo;
    // reference to the Passenger and FLight objects
    private List<Passenger> passengerList = FXCollections.observableArrayList();
    private List<LocalDate> dobList = FXCollections.observableArrayList();
    private List<Flight> flightList = FXCollections.observableArrayList();
    private Passenger passenger1, passenger2, passenger3, passenger4, passenger5, passenger6, passenger7, passenger8;
    private Flight flight, flightForChild, flightForBaby;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;

        // add Constants to the ObservableList
        airportList.addAll(CORK, MADRID, ST_BRIEUC, JERSEY, PARIS, STANSTED, MALAGA);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(
                createTopGridPane(), createMiddleGridPane(), createBottomPane(), createAnchorPane());
        scene1 = new Scene(vBox, 800, 800);

        listView = new ListView();

        this.vBox = new VBox();
        scene2 = new Scene(this.vBox, 700, 650);

        scene1.getStylesheets().add("/stylesheet.css");
        primaryStage.setScene(scene1);
        primaryStage.setTitle("JaviAir App");
        primaryStage.show();
    }

    private GridPane createTopGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.getStyleClass().add("grid");

        RadioButton radioButtonOneWay = new RadioButton("One Way");
        radioButtonOneWay.setToggleGroup(toggleGroupFlights);
        radioButtonReturn = new RadioButton("Return");
        radioButtonReturn.setToggleGroup(toggleGroupFlights);
        radioButtonReturn.fire(); // return btn on by default

        toggleGroupFlights.selectedToggleProperty().addListener(observable -> {
            if (toggleGroupFlights.getSelectedToggle() == radioButtonOneWay) {
                datePickerReturn.setDisable(true);
                imageViewReturn.setVisible(false);
                radioButtonReturnTime1.setVisible(false);
                radioButtonReturnTime2.setVisible(false);


            } else if (toggleGroupFlights.getSelectedToggle() == radioButtonReturn) {
                datePickerReturn.setDisable(false);
                imageViewReturn.setVisible(true);
                radioButtonReturnTime1.setVisible(true);
                radioButtonReturnTime2.setVisible(true);

            }
        });


        radioButtonDeptTime1 = new RadioButton();
        radioButtonDeptTime2 = new RadioButton();
        radioButtonReturnTime1 = new RadioButton();
        radioButtonReturnTime2 = new RadioButton();


        vBoxRadioBtns1 = new VBox(10);
        vBoxRadioBtns1.getChildren().addAll(radioButtonDeptTime1, radioButtonDeptTime2);
        vBoxRadioBtns1.setMinWidth(200);


        vBoxRadioBtns2 = new VBox(10);
        vBoxRadioBtns2.getChildren().addAll(radioButtonReturnTime1, radioButtonReturnTime2);
        vBoxRadioBtns2.setMinWidth(200);


        labelOrigin = new Label("From: ");
        comboOrigin = new ComboBox<>();
        comboOrigin.setEditable(true);
        comboOrigin.setPromptText("select airport");
        comboOrigin.getItems().addAll(airportList);

        comboOrigin.getStyleClass().add("comboOrigin"); // add css class
        comboOrigin.setOnAction(event -> {

            // Need to enforce a new date selection, when user changes the airport
            datePickerDeparture.getEditor().clear();
            datePickerReturn.getEditor().clear();
            datePickerDeparture.setValue(null);
            datePickerReturn.setValue(null);


            if (comboOrigin.getItems().contains(comboOrigin.getValue())) {
                disableFlights();
                getSelectedFlightPrice();
            } else {
                UtilityClass.errorMessageInput();
            }
        });

        labelDestination = new Label("To: ");
        comboDestination = new ComboBox<>();
        comboDestination.setPromptText("select airport");
        comboDestination.setEditable(true);
        comboDestination.setOnAction(event -> {

            // Need to enforce a new date selection, when user changes the airport
            datePickerDeparture.getEditor().clear();
            datePickerReturn.getEditor().clear();
            datePickerDeparture.setValue(null);
            datePickerReturn.setValue(null);


            if (comboDestination.getItems().contains(comboDestination.getValue())) {
                getSelectedFlightPrice();
            }
        });


        labelDateDeparture = new Label("Depart");
        datePickerDeparture = new DatePicker();
        datePickerDeparture.setPromptText("pick a date");
        datePickerDeparture.setEditable(true);
        datePickerDeparture.setOnAction(event -> {
            event.consume();
            checkForInvalidDates();
            getSelectDate(event);

        });


        labelDateReturn = new Label("Return");
        datePickerReturn = new DatePicker();
        datePickerReturn.setPromptText("pick a date");
        datePickerReturn.setEditable(true);
        datePickerReturn.setOnAction(event -> {
            event.consume();
            checkForInvalidDates();
            getSelectDate(event);


        });


        Button btnFlightSelect = new Button("Select Flight");
        btnFlightSelect.setOnAction(event -> {
            if (comboDestination.getSelectionModel().isEmpty()) {
                UtilityClass.errorMessageFlight();
            }

            displaySelectedFlights();
//            getDepartTime();

        });

        comboOrigin.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 3) {
                comboDestination.setId("error");
            } else {
                comboDestination.setId("default");
            }
        });

        comboDestination.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 3) {
                comboOrigin.setId("error");
            } else {
                comboOrigin.setId("default");
            }
        });

        gridPane.add(radioButtonOneWay, 0, 1);
        gridPane.add(radioButtonReturn, 2, 1);
        gridPane.add(labelOrigin, 0, 2);
        gridPane.add(labelDestination, 2, 2);
        gridPane.add(comboOrigin, 0, 3);
        gridPane.add(UtilityClass.insertIcon(), 1, 3);
        gridPane.add(comboDestination, 2, 3);
        gridPane.add(labelDateDeparture, 0, 4);
        gridPane.add(labelDateReturn, 2, 4);
        gridPane.add(datePickerDeparture, 0, 5);
        gridPane.add(UtilityClass.insertIcon(), 1, 5);
        gridPane.add(datePickerReturn, 2, 5);
        gridPane.add(btnFlightSelect, 1, 7);

        // gridPane.add(Node, colIndex, rowIndex, colSpan, rowSpan):
        gridPane.add(new Separator(), 0, 8, 3, 1);

        return gridPane;
    }

    private void disableFlights() {
        String flightDepart = comboOrigin.getSelectionModel().getSelectedItem();


        try {
            if (flightDepart.equals(CORK)) {
                comboDestination.getItems().clear();
                comboDestination.getItems().addAll(MADRID, ST_BRIEUC, JERSEY, PARIS, STANSTED, MALAGA);
            } else if (flightDepart.equals(MADRID)) {
                comboDestination.getItems().clear();
                comboDestination.getItems().addAll(CORK, ST_BRIEUC, JERSEY, PARIS, STANSTED, MALAGA);
            } else if (flightDepart.equals(PARIS)) {
                comboDestination.getItems().clear();
                comboDestination.getItems().addAll(CORK, MADRID, ST_BRIEUC, JERSEY, STANSTED, MALAGA);
            } else if (flightDepart.equals(STANSTED)) {
                comboDestination.getItems().clear();
                comboDestination.getItems().addAll(CORK, MADRID, ST_BRIEUC, JERSEY, PARIS, MALAGA);
            } else if (flightDepart.equals(MALAGA)) {
                comboDestination.getItems().clear();
                comboDestination.getItems().addAll(CORK, MADRID, ST_BRIEUC, JERSEY, PARIS, STANSTED);
            } else if (flightDepart.equals(ST_BRIEUC) || flightDepart.equals(JERSEY)) {
                comboDestination.getItems().clear();
                comboDestination.getItems().addAll(CORK, MADRID, PARIS, STANSTED, MALAGA);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private Double getSelectedFlightPrice() {
        dptFlight = comboOrigin.getSelectionModel().getSelectedItem();
        rtnFlight = comboDestination.getSelectionModel().getSelectedItem();


        try {

            if (dptFlight != null && rtnFlight != null) {
                if (dptFlight.equals(CORK) && rtnFlight.equals((MADRID)) || dptFlight.equals(MADRID) && rtnFlight.equals(CORK)) {
                    flightPrice = ONE_HND;
                } else if (dptFlight.equals(CORK) && rtnFlight.equals(ST_BRIEUC) || dptFlight.equals(ST_BRIEUC) && rtnFlight.equals(CORK)) {
                    flightPrice = ONE_HND;
                } else if (dptFlight.equals(CORK) && rtnFlight.equals(JERSEY) || dptFlight.equals(JERSEY) && rtnFlight.equals(CORK)) {
                    flightPrice = ONE_HND_TWENTY;
                } else if (dptFlight.equals(CORK) && rtnFlight.equals(PARIS) || dptFlight.equals(PARIS) && rtnFlight.equals(CORK)) {
                    flightPrice = EIGHTY;
                } else if (dptFlight.equals(CORK) && rtnFlight.equals(STANSTED) || dptFlight.equals(STANSTED) && rtnFlight.equals(CORK)) {
                    flightPrice = FORTY;
                } else if (dptFlight.equals(CORK) && rtnFlight.equals(MALAGA) || dptFlight.equals(MALAGA) && rtnFlight.equals(CORK)) {
                    flightPrice = TWO_HND_FORTY;
                } else if (dptFlight.equals(MADRID) && rtnFlight.equals(ST_BRIEUC) || dptFlight.equals(ST_BRIEUC) && rtnFlight.equals(MADRID)) {
                    flightPrice = TWO_HND;
                } else if (dptFlight.equals(MADRID) && rtnFlight.equals(JERSEY) || dptFlight.equals(JERSEY) && rtnFlight.equals(MADRID)) {
                    flightPrice = TWO_HND;
                } else if (dptFlight.equals(MADRID) && rtnFlight.equals(PARIS) || dptFlight.equals(PARIS) && rtnFlight.equals(MADRID)) {
                    flightPrice = SIXTY;
                } else if (dptFlight.equals(MADRID) && rtnFlight.equals(STANSTED) || dptFlight.equals(STANSTED) && rtnFlight.equals(MADRID)) {
                    flightPrice = SIXTY;
                } else if (dptFlight.equals(MADRID) && rtnFlight.equals(MALAGA) || dptFlight.equals(MALAGA) && rtnFlight.equals(MADRID)) {
                    flightPrice = SIXTY;
                } else if (dptFlight.equals(ST_BRIEUC) && rtnFlight.equals(JERSEY)) {
                    flightPrice = ZERO;
                } else if (dptFlight.equals(ST_BRIEUC) && rtnFlight.equals(PARIS) || dptFlight.equals(PARIS) && rtnFlight.equals(ST_BRIEUC)) {
                    flightPrice = ONE_HND_FIFTY;
                } else if (dptFlight.equals(ST_BRIEUC) && rtnFlight.equals(MALAGA) || dptFlight.equals(MALAGA) && rtnFlight.equals(ST_BRIEUC)) {
                    flightPrice = ONE_HND_FORTY;
                } else if (dptFlight.equals(JERSEY) && rtnFlight.equals(PARIS) || dptFlight.equals(PARIS) && rtnFlight.equals(JERSEY)) {
                    flightPrice = TWO_HND_FIFTY;
                } else if (dptFlight.equals(JERSEY) && rtnFlight.equals(STANSTED) || dptFlight.equals(STANSTED) && rtnFlight.equals(JERSEY)) {
                    flightPrice = TWO_HND_FIFTY;
                } else if (dptFlight.equals(JERSEY) && rtnFlight.equals(MALAGA) || dptFlight.equals(MALAGA) && rtnFlight.equals(JERSEY)) {
                    flightPrice = TWO_HND_EIGHTY;
                } else if (dptFlight.equals(PARIS) && rtnFlight.equals(MALAGA) || dptFlight.equals(MALAGA) && rtnFlight.equals(PARIS)) {
                    flightPrice = ONE_HND;
                } else if (dptFlight.equals(STANSTED) && rtnFlight.equals(MALAGA) || dptFlight.equals(MALAGA) && rtnFlight.equals(STANSTED)) {
                    flightPrice = ONE_HND_TWENTY;
                } else if (dptFlight.equals(PARIS) && rtnFlight.equals(STANSTED) || dptFlight.equals(STANSTED) && rtnFlight.equals(PARIS)) {
                    flightPrice = SIXTY;
                } else if (dptFlight.equals(ST_BRIEUC) && rtnFlight.equals(STANSTED) || dptFlight.equals(STANSTED) && rtnFlight.equals(ST_BRIEUC)) {
                    flightPrice = EIGHTY;
                }
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }


        // Disable March and/or April in the DatePicker.
        // Also disable date before current time and after 6 months from now.
        // JavaFX DatePicker Tutorial website: http://o7planning.org/en/11085/javafx-datepicker-tutorial#a3667895
        Callback<DatePicker, DateCell> monthCellFactory = this.getMonthCellFactory();
        datePickerDeparture.setDayCellFactory(monthCellFactory);
        datePickerReturn.setDayCellFactory(monthCellFactory);

        return flightPrice;
    }

    // Disable March and/or April in the DatePicker
    // JavaFX DatePicker Tutorial website: http://o7planning.org/en/11085/javafx-datepicker-tutorial#a3667895g
    private Callback<DatePicker, DateCell> getMonthCellFactory() {

        final Callback<DatePicker, DateCell> monthCellFactory = new Callback<DatePicker, DateCell>() {

            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                        LocalDate currentDate = LocalDate.now();
                        LocalDate nowPlusSixMonths = currentDate.plusMonths(6);

                        if (item.isBefore(currentDate) || item.isAfter(nowPlusSixMonths)) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }

                        if (item != null) {
                            if (dptFlight.equals(ST_BRIEUC) && rtnFlight.equals(STANSTED) || dptFlight.equals(STANSTED) && rtnFlight.equals(ST_BRIEUC)) {
                                // Disable March and April
                                if (item.getMonth().equals(Month.APRIL) || item.getMonth().equals(Month.MARCH)) {
                                    setDisable(true);
                                    setStyle("-fx-background-color: #ffc0cb;");
                                }
                            } else if (dptFlight.equals(PARIS) && rtnFlight.equals(STANSTED) || dptFlight.equals(STANSTED) && rtnFlight.equals(PARIS)) {
                                // Disable April
                                if (item.getMonth().equals(Month.APRIL)) {
                                    setDisable(true);
                                    setStyle("-fx-background-color: #ffc0cb;");
                                }
                            }
                        }
                    }
                };
            }
        };
        return monthCellFactory;
    }

    // take the returned 'flightPrice' from getSelectedFlight() and add 20% if day is Fri - Sun
    private Double getSelectDate(ActionEvent event) {
        try {
            if (event.getSource().equals(datePickerDeparture)) {
                ldDepartDate = datePickerDeparture.getValue();
                // set variable to the day of the week, from the selected date
                String dayOfWeek = ldDepartDate.getDayOfWeek().name();

                if (dayOfWeek.equals(FRI) || dayOfWeek.equals(SAT) || dayOfWeek.equals(SUN)) {
                    dateDepartPrice = flightPrice + flightPrice * 0.2;
                } else {
                    dateDepartPrice = flightPrice;
                }
            } else if (event.getSource().equals(datePickerReturn)) {
                ldReturnDate = datePickerReturn.getValue();
                String dayOfWeek = ldReturnDate.getDayOfWeek().name();
                if (dayOfWeek.equals(FRI) || dayOfWeek.equals(SAT) || dayOfWeek.equals(SUN)) {
                    dateReturnPrice = flightPrice + flightPrice * 0.2;
                } else {
                    dateReturnPrice = flightPrice;
                }
            }
            currentPrice = dateDepartPrice + dateReturnPrice;
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

        return currentPrice;
    }

    private void checkForInvalidDates() {
        LocalDate departDate = datePickerDeparture.getValue();
        LocalDate returnDate = datePickerReturn.getValue();

        if (departDate != null && returnDate != null) {
            if (departDate.isAfter(returnDate)) {
                UtilityClass.errorMessageDatesNotPossible();
                datePickerReturn.getEditor().setText(null);
            }
        }
    }

    private void displaySelectedFlights() {
        String flightDepart = comboOrigin.getSelectionModel().getSelectedItem();
        String flightReturn = comboDestination.getSelectionModel().getSelectedItem();
        LocalDate dateDept = datePickerDeparture.getValue();
        LocalDate dateReturn = datePickerReturn.getValue();

        try {

            if (flightDepart == null) {
                UtilityClass.errorMessageFlight();
            } else if (dateDept == null || dateReturn == null && radioButtonReturn.isSelected()) {
                UtilityClass.errorMessageDate();
            } else {

                setFlightPriceAdult();

                if (flight != null) {
//                    textAreaDepart.setText(flight.displayDeptDetails());
//                    textAreaReturn.setText(flight.displayReturnDetails());
//                    textAreaReturn.appendText("\n\n" + flight.getPrice());

                }

                if (flightDepart.equals(CORK) && flightReturn.equals(MADRID)) {
                    flightTime1 = ORK_MAD_1;
                    flightTime2 = null;
                } else if (flightDepart.equals(CORK) && flightReturn.equals(ST_BRIEUC)) {
                    flightTime1 = ORK_SBK_1;
                    flightTime2 = null;
                } else if (flightDepart.equals(CORK) && flightReturn.equals(JERSEY)) {
                    flightTime1 = ORK_JER_1;
                    flightTime2 = null;
                } else if (flightDepart.equals(CORK) && flightReturn.equals(PARIS)) {
                    flightTime1 = ORK_CDG_1;
                    flightTime2 = ORK_CDG_2;
                } else if (flightDepart.equals(CORK) && flightReturn.equals(STANSTED)) {
                    flightTime1 = ORK_STN_1;
                    flightTime2 = ORK_STN_2;
                } else if (flightDepart.equals(CORK) && flightReturn.equals(MALAGA)) {
                    flightTime1 = ORK_AGP_1;
                    flightTime2 = null;
                } else if (flightDepart.equals(MADRID) && flightReturn.equals(CORK)) {
                    flightTime1 = MAD_ORK_1;
                    flightTime2 = null;
                } else if (flightDepart.equals(MADRID) && flightReturn.equals(ST_BRIEUC)) {
                    flightTime1 = MAD_SBK_1;
                    flightTime2 = null;
                } else if (flightDepart.equals(MADRID) && flightReturn.equals(JERSEY)) {
                    flightTime1 = MAD_JER_1;
                    flightTime2 = null;
                } else if (flightDepart.equals(MADRID) && flightReturn.equals(PARIS)) {
                    flightTime1 = MAD_CDG_1;
                    flightTime2 = null;
                } else if (flightDepart.equals(MADRID) && flightReturn.equals(STANSTED)) {
                    flightTime1 = MAD_STN_1;
                    flightTime2 = MAD_STN_2;
                } else if (flightDepart.equals(MADRID) && flightReturn.equals(MALAGA)) {
                    flightTime1 = MAD_AGP_1;
                    flightTime2 = null;
                } else if (flightDepart.equals(ST_BRIEUC) && flightReturn.equals(CORK)) {
                    flightTime1 = SBK_ORK_1;
                    flightTime2 = null;
                } else if (flightDepart.equals(ST_BRIEUC) && flightReturn.equals(MADRID)) {
                    flightTime1 = SBK_MAD_1;
                    flightTime2 = null;
                } else if (flightDepart.equals(ST_BRIEUC) && flightReturn.equals(JERSEY)) {
                    flightTime1 = SBK_JER_1;
                    flightTime2 = null;
                } else if (flightDepart.equals(ST_BRIEUC) && flightReturn.equals(PARIS)) {
                    flightTime1 = SBK_CDG_1;
                    flightTime2 = null;
                } else if (flightDepart.equals(ST_BRIEUC) && flightReturn.equals(STANSTED)) {
                    flightTime1 = SBK_STN_1;
                    flightTime2 = null;
                } else if (flightDepart.equals(ST_BRIEUC) && flightReturn.equals(MALAGA)) {
                    flightTime1 = SBK_AGP_1;
                    flightTime2 = null;
                } else if (flightDepart.equals(JERSEY) && flightReturn.equals(CORK)) {
                    flightTime1 = JER_ORK_1;
                    flightTime2 = null;
                } else if (flightDepart.equals(JERSEY) && flightReturn.equals(MADRID)) {
                    flightTime1 = JER_MAD_1;
                    flightTime2 = null;
                } else if (flightDepart.equals(JERSEY) && flightReturn.equals(PARIS)) {
                    flightTime1 = JER_CDG_1;
                    flightTime2 = null;
                } else if (flightDepart.equals(JERSEY) && flightReturn.equals(STANSTED)) {
                    flightTime1 = JER_STN_1;
                    flightTime2 = null;
                } else if (flightDepart.equals(JERSEY) && flightReturn.equals(MALAGA)) {
                    flightTime1 = JER_AGP_1;
                    flightTime2 = null;
                } else if (flightDepart.equals(PARIS) && flightReturn.equals(CORK)) {
                    flightTime1 = CDG_ORK_1;
                    flightTime2 = CDG_ORK_2;
                } else if (flightDepart.equals(PARIS) && flightReturn.equals(MADRID)) {
                    flightTime1 = CDG_MAD_1;
                    flightTime2 = null;
                } else if (flightDepart.equals(PARIS) && flightReturn.equals(ST_BRIEUC)) {
                    flightTime1 = CDG_SBK_1;
                    flightTime2 = null;
                } else if (flightDepart.equals(PARIS) && flightReturn.equals(JERSEY)) {
                    flightTime1 = CDG_JER_1;
                    flightTime2 = null;
                } else if (flightDepart.equals(PARIS) && flightReturn.equals(STANSTED)) {
                    flightTime1 = CDG_STN_1;
                    flightTime2 = null;
                } else if (flightDepart.equals(PARIS) && flightReturn.equals(MALAGA)) {
                    flightTime1 = CDG_AGP_1;
                    flightTime2 = null;
                } else if (flightDepart.equals(STANSTED) && flightReturn.equals(CORK)) {
                    flightTime1 = STN_ORK_1;
                    flightTime2 = STN_ORK_2;
                } else if (flightDepart.equals(STANSTED) && flightReturn.equals(MADRID)) {
                    flightTime1 = STN_MAD_1;
                    flightTime2 = null;
                } else if (flightDepart.equals(STANSTED) && flightReturn.equals(ST_BRIEUC)) {
                    flightTime1 = STN_SBK_1;
                    flightTime2 = null;
                } else if (flightDepart.equals(STANSTED) && flightReturn.equals(JERSEY)) {
                    flightTime1 = STN_JER_1;
                    flightTime2 = null;
                } else if (flightDepart.equals(STANSTED) && flightReturn.equals(PARIS)) {
                    flightTime1 = STN_CDG_1;
                    flightTime2 = null;
                } else if (flightDepart.equals(STANSTED) && flightReturn.equals(MALAGA)) {
                    flightTime1 = STN_AGP_1;
                    flightTime2 = STN_AGP_2;
                } else if (flightDepart.equals(MALAGA) && flightReturn.equals(CORK)) {
                    flightTime1 = AGP_ORK_1;
                    flightTime2 = null;
                } else if (flightDepart.equals(MALAGA) && flightReturn.equals(MADRID)) {
                    flightTime1 = AGP_MAD_1;
                    flightTime2 = null;
                } else if (flightDepart.equals(MALAGA) && flightReturn.equals(ST_BRIEUC)) {
                    flightTime1 = AGP_SBK_1;
                    flightTime2 = null;
                } else if (flightDepart.equals(MALAGA) && flightReturn.equals(JERSEY)) {
                    flightTime1 = AGP_JER_1;
                    flightTime2 = null;
                } else if (flightDepart.equals(MALAGA) && flightReturn.equals(PARIS)) {
                    flightTime1 = AGP_CDG_1;
                    flightTime2 = null;
                } else if (flightDepart.equals(MALAGA) && flightReturn.equals(STANSTED)) {
                    flightTime1 = AGP_STN_1;
                    flightTime2 = AGP_STN_2;

                    mFlightTimes = AGP_STN_1 + "\n" + AGP_STN_2;
                }


                displayFlightDetails();

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


    private void displayFlightDetails() {

        // setting times for Departure radio buttons
        radioButtonDeptTime1.setText(flight.displayDeptDetails() + "\n" + flightTime1);
        radioButtonReturnTime1.setText(flight.displayReturnDetails() + "\n" + flightTime1);

        radioButtonDeptTime1.setToggleGroup(toggleGroupFlightTimes_1);
        radioButtonDeptTime2.setToggleGroup(toggleGroupFlightTimes_1);

        toggleGroupFlightTimes_1.selectedToggleProperty().addListener(observable -> {

            if (toggleGroupFlightTimes_1.getSelectedToggle() == radioButtonDeptTime1) {
                selectedDeptTime = flightTime1;

            } else if (toggleGroupFlightTimes_1.getSelectedToggle() == radioButtonDeptTime2) {
                selectedDeptTime = flightTime2;
            }

        });

        if (flightTime2 != null) {

            // setting times for Return radio buttons
            radioButtonDeptTime2.setText(flight.displayDeptDetails() + "\n" + flightTime2);
            radioButtonReturnTime2.setText(flight.displayReturnDetails() + "\n" + flightTime2);

            radioButtonDeptTime2.setVisible(true);
            radioButtonReturnTime2.setVisible(true);


            radioButtonReturnTime1.setToggleGroup(toggleGroupFlightTimes_2);
            radioButtonReturnTime2.setToggleGroup(toggleGroupFlightTimes_2);
            toggleGroupFlightTimes_2.selectedToggleProperty().addListener(observable -> {

                if (toggleGroupFlightTimes_2.getSelectedToggle() == radioButtonReturnTime1) {
                    selectedReturnTime = flightTime1;
                } else if (toggleGroupFlightTimes_2.getSelectedToggle() == radioButtonReturnTime2) {
                    selectedReturnTime = flightTime2;
                }

            });

        } else {
            radioButtonDeptTime2.setVisible(false);
            radioButtonReturnTime2.setVisible(false);
        }


    }


    private GridPane createMiddleGridPane() {
        Button buttonFlightSelect = new Button("Select Flight");
        buttonFlightSelect.setOnAction(event -> displaySelectedFlights());

        gridPaneMiddle = new GridPane();
        gridPaneMiddle.setAlignment(Pos.CENTER);
        gridPaneMiddle.getStyleClass().add("grid");
        GridPane.setHalignment(buttonFlightSelect, HPos.CENTER);
        GridPane.setColumnSpan(buttonFlightSelect, 3);

        textAreaDepart = new TextArea();
        textAreaDepart.setPrefRowCount(3);
        textAreaDepart.setEditable(false);
        textAreaDepart.setMaxWidth(200);
        textAreaDepart.setMinHeight(75);

        textAreaReturn = new TextArea();
        textAreaReturn.setPrefRowCount(3);
        textAreaReturn.setEditable(false);
        textAreaReturn.setMaxWidth(200);

        imageViewDept = new ImageView();
        Image imageDept = new Image("departures.png");
        imageViewDept.setImage(imageDept);
        imageViewDept.setFitWidth(100);
        imageViewDept.setFitHeight(100);
        imageViewDept.setSmooth(true);
        imageViewDept.setCache(true);

        imageViewReturn = new ImageView();
        Image imageReturn = new Image("arrivals.png");
        imageViewReturn.setImage(imageReturn);
        imageViewReturn.setFitWidth(100);
        imageViewReturn.setFitHeight(100);
        imageViewReturn.setSmooth(true);
        imageViewReturn.setCache(true);

        gridPaneMiddle.add(imageViewDept, 0, 2);
        gridPaneMiddle.add(buttonFlightSelect, 0, 3);
        gridPaneMiddle.add(imageViewReturn, 2, 2);
        gridPaneMiddle.add(vBoxRadioBtns1, 0, 3);
        gridPaneMiddle.add(vBoxRadioBtns2, 2, 3);


        // .add(Node, colIndex, rowIndex, colSpan, rowSpan):
        gridPaneMiddle.add(new Separator(), 0, 5, 3, 1);

        return gridPaneMiddle;
    }

    private HBox createBottomPane() {
        // LHS Pane

        gridPaneLeft = new GridPane();
        gridPaneLeft.getStyleClass().add("gridPaneLeft");

        StackPane stackPaneLeft = new StackPane();
        stackPaneLeft.setPrefHeight(300);
        stackPaneLeft.setMaxWidth(150);
        stackPaneLeft.getStyleClass().add("stackPaneLeft");
        StackPane.setAlignment(gridPaneLeft, Pos.TOP_LEFT);
        stackPaneLeft.getChildren().addAll(gridPaneLeft);


        Label label = new Label("Number of passengers:");


        spinnerObservableList.addAll(0, 1, 2, 3, 4, 5, 6, 7, 8);


        spinnerPassengerNo = new Spinner<>();
        spinnerPassengerNo.getStyleClass().add("mySpinner");

        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.ListSpinnerValueFactory<>(spinnerObservableList);

        spinnerPassengerNo.setValueFactory(valueFactory);

        spinnerPassengerNo.valueProperty();

        spinnerPassengerNo.valueProperty().addListener(observable -> {
            getSelectedNumOfPassengers();
        });


        gridPaneLeft.add(label, 1, 2);
        gridPaneLeft.add(spinnerPassengerNo, 1, 3);
        GridPane.setMargin(spinnerPassengerNo, new Insets(10, 0, 50, 50));

        // RHS Pane
        StackPane stackPaneRight = new StackPane();
        stackPaneRight.setPrefHeight(310);
        stackPaneRight.setMaxWidth(550);
        stackPaneRight.getStyleClass().add("stackPaneRight");

        Label labelBorder = new Label("Passenger Details");
        labelBorder.getStyleClass().add("border-title");
        StackPane.setAlignment(labelBorder, Pos.TOP_CENTER);
        Label labelBag = new Label("Baggage");
        labelBag.getStyleClass().add("baggage-title");
        StackPane.setAlignment(labelBag, Pos.TOP_RIGHT);

        stackPaneRight.getChildren().addAll(labelBorder, labelBag);

        fName = new TextField();
        fName.setDisable(true);
        fName.setPromptText("first name"); // fName.setVisible(false);
        lName = new TextField();
        lName.setDisable(true);
        lName.setPromptText("last name");
        fName2 = new TextField();
        fName2.setDisable(true);
        lName2 = new TextField();
        lName2.setDisable(true);
        fName3 = new TextField();
        fName3.setDisable(true);
        lName3 = new TextField();
        lName3.setDisable(true);
        fName4 = new TextField();
        fName4.setDisable(true);
        lName4 = new TextField();
        lName4.setDisable(true);
        fName5 = new TextField();
        fName5.setDisable(true);
        lName5 = new TextField();
        lName5.setDisable(true);
        fName6 = new TextField();
        fName6.setDisable(true);
        lName6 = new TextField();
        lName6.setDisable(true);
        fName7 = new TextField();
        fName7.setDisable(true);
        lName7 = new TextField();
        lName7.setDisable(true);
        fName8 = new TextField();
        fName8.setDisable(true);
        lName8 = new TextField();
        lName8.setDisable(true);
        dateOfBirth1 = new DatePicker();
        dateOfBirth1.setDisable(true);
        dateOfBirth1.setValue(LocalDate.now().minusYears(2)); // FOR TESTING
        dateOfBirth1.setPromptText("dd/mm/yyyy");
        dateOfBirth1.getStyleClass().add("myDatePicker");
        dateOfBirth2 = new DatePicker();
        dateOfBirth2.setDisable(true);
        dateOfBirth2.getStyleClass().add("myDatePicker");
        dateOfBirth3 = new DatePicker();
        dateOfBirth3.setDisable(true);
        dateOfBirth3.getStyleClass().add("myDatePicker");
        dateOfBirth4 = new DatePicker();
        dateOfBirth4.setDisable(true);
        dateOfBirth4.getStyleClass().add("myDatePicker");
        dateOfBirth5 = new DatePicker();
        dateOfBirth5.setDisable(true);
        dateOfBirth5.getStyleClass().add("myDatePicker");
        dateOfBirth6 = new DatePicker();
        dateOfBirth6.setDisable(true);
        dateOfBirth6.getStyleClass().add("myDatePicker");
        dateOfBirth7 = new DatePicker();
        dateOfBirth7.setDisable(true);
        dateOfBirth7.getStyleClass().add("myDatePicker");
        dateOfBirth8 = new DatePicker();
        dateOfBirth8.setDisable(true);
        dateOfBirth8.getStyleClass().add("myDatePicker");


        radioBtn1 = new RadioButton();
        radioBtn2 = new RadioButton();
        radioBtn3 = new RadioButton();
        radioBtn4 = new RadioButton();
        radioBtn5 = new RadioButton();
        radioBtn6 = new RadioButton();
        radioBtn7 = new RadioButton();
        radioBtn8 = new RadioButton();
        radioBtnList.addAll(radioBtn1, radioBtn2, radioBtn3, radioBtn4, radioBtn5, radioBtn6, radioBtn7, radioBtn8);


        dpDateOfBirthList.addAll(dateOfBirth1, dateOfBirth2, dateOfBirth3, dateOfBirth4, dateOfBirth5, dateOfBirth6, dateOfBirth7, dateOfBirth8);


        tfFirstNamesList.addAll(fName, fName2, fName3, fName4, fName5, fName6, fName7, fName8);


        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 0, 0, 0));
        gridPane.add(fName, 1, 0);
        gridPane.add(lName, 2, 0);
        gridPane.add(dateOfBirth1, 3, 0);
        gridPane.add(radioBtn1, 4, 0);
        gridPane.add(fName2, 1, 1);
        gridPane.add(lName2, 2, 1);
        gridPane.add(dateOfBirth2, 3, 1);
        gridPane.add(radioBtn2, 4, 1);
        gridPane.add(fName3, 1, 2);
        gridPane.add(lName3, 2, 2);
        gridPane.add(dateOfBirth3, 3, 2);
        gridPane.add(radioBtn3, 4, 2);
        gridPane.add(fName4, 1, 3);
        gridPane.add(lName4, 2, 3);
        gridPane.add(dateOfBirth4, 3, 3);
        gridPane.add(radioBtn4, 4, 3);
        gridPane.add(fName5, 1, 4);
        gridPane.add(lName5, 2, 4);
        gridPane.add(dateOfBirth5, 3, 4);
        gridPane.add(radioBtn5, 4, 4);
        gridPane.add(fName6, 1, 5);
        gridPane.add(lName6, 2, 5);
        gridPane.add(dateOfBirth6, 3, 5);
        gridPane.add(radioBtn6, 4, 5);
        gridPane.add(fName7, 1, 6);
        gridPane.add(lName7, 2, 6);
        gridPane.add(dateOfBirth7, 3, 6);
        gridPane.add(radioBtn7, 4, 6);
        gridPane.add(fName8, 1, 7);
        gridPane.add(lName8, 2, 7);
        gridPane.add(dateOfBirth8, 3, 7);
        gridPane.add(radioBtn8, 4, 7);

        disableBagSelectionForBabyPassengers();


        StackPane.setAlignment(gridPane, Pos.TOP_LEFT);
        stackPaneRight.getChildren().addAll(gridPane);

        HBox hBox = new HBox();
        HBox.setHgrow(stackPaneLeft, Priority.ALWAYS);
        HBox.setHgrow(stackPaneRight, Priority.ALWAYS);
        hBox.setPadding(new Insets(10));
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(stackPaneLeft, stackPaneRight);

        return hBox;
    }


    private void disableBagSelectionForBabyPassengers() {

        LocalDate now = LocalDate.now();
        LocalDate nowMinus5yrs = now.minusYears(5);
        LocalDate nowMinus1yr = now.minusYears(1);

        dateOfBirth1.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (newValue.isAfter(nowMinus1yr) && newValue.isBefore(now)) {
                    radioBtnList.get(0).setDisable(true);
                } else if (newValue.isAfter(nowMinus5yrs) && newValue.isBefore(nowMinus1yr)) {
                    radioBtnList.get(0).setDisable(false);
                } else if (newValue.isAfter(now)) {
                    UtilityClass.errorMessageDatesNotPossible();
                }
            }
        });
        dateOfBirth2.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (newValue.isAfter(nowMinus1yr) && newValue.isBefore(now)) {
                    radioBtnList.get(1).setDisable(true);
                } else if (newValue.isAfter(nowMinus5yrs) && newValue.isBefore(nowMinus1yr)) {
                    radioBtnList.get(1).setDisable(false);
                } else if (newValue.isAfter(now)) {
                    UtilityClass.errorMessageDatesNotPossible();
                }
            }
        });
        dateOfBirth3.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (newValue.isAfter(nowMinus1yr) && newValue.isBefore(now)) {
                    radioBtnList.get(2).setDisable(true);
                } else if (newValue.isAfter(nowMinus5yrs) && newValue.isBefore(nowMinus1yr)) {
                    radioBtnList.get(2).setDisable(false);
                } else if (newValue.isAfter(now)) {
                    UtilityClass.errorMessageDatesNotPossible();
                }
            }
        });
        dateOfBirth4.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (newValue.isAfter(nowMinus1yr) && newValue.isBefore(now)) {
                    radioBtnList.get(3).setDisable(true);
                } else if (newValue.isAfter(nowMinus5yrs) && newValue.isBefore(nowMinus1yr)) {
                    radioBtnList.get(3).setDisable(false);
                } else if (newValue.isAfter(now)) {
                    UtilityClass.errorMessageDatesNotPossible();
                }
            }
        });
        dateOfBirth5.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (newValue.isAfter(nowMinus1yr) && newValue.isBefore(now)) {
                    radioBtnList.get(4).setDisable(true);
                } else if (newValue.isAfter(nowMinus5yrs) && newValue.isBefore(nowMinus1yr)) {
                    radioBtnList.get(4).setDisable(false);
                } else if (newValue.isAfter(now)) {
                    UtilityClass.errorMessageDatesNotPossible();
                }
            }
        });
        dateOfBirth6.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (newValue.isAfter(nowMinus1yr) && newValue.isBefore(now)) {
                    radioBtnList.get(5).setDisable(true);
                } else if (newValue.isAfter(nowMinus5yrs) && newValue.isBefore(nowMinus1yr)) {
                    radioBtnList.get(5).setDisable(false);
                } else if (newValue.isAfter(now)) {
                    UtilityClass.errorMessageDatesNotPossible();
                }
            }
        });
        dateOfBirth7.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (newValue.isAfter(nowMinus1yr) && newValue.isBefore(now)) {
                    radioBtnList.get(6).setDisable(true);
                } else if (newValue.isAfter(nowMinus5yrs) && newValue.isBefore(nowMinus1yr)) {
                    radioBtnList.get(6).setDisable(false);
                } else if (newValue.isAfter(now)) {
                    UtilityClass.errorMessageDatesNotPossible();
                }
            }
        });
        dateOfBirth8.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (newValue.isAfter(nowMinus1yr) && newValue.isBefore(now)) {
                    radioBtnList.get(7).setDisable(true);
                } else if (newValue.isAfter(nowMinus5yrs) && newValue.isBefore(nowMinus1yr)) {
                    radioBtnList.get(7).setDisable(false);
                } else if (newValue.isAfter(now)) {
                    UtilityClass.errorMessageDatesNotPossible();
                }
            }
        });
    }


    private void getSelectedNumOfPassengers() {

        if (spinnerPassengerNo.getValue() == 0) {
            fName.setDisable(true);
            lName.setDisable(true);
            dateOfBirth1.setDisable(true);
        } else if (spinnerPassengerNo.getValue() == 1) {
            fName.setVisible(true);
            fName.setDisable(false);
            lName.setDisable(false);
            dateOfBirth1.setDisable(false);
            fName2.setDisable(true);
            lName2.setDisable(true);
            dateOfBirth2.setDisable(true);
        } else if (spinnerPassengerNo.getValue() == 2) {
            fName2.setDisable(false);
            lName2.setDisable(false);
            dateOfBirth2.setDisable(false);
            fName3.setDisable(true);
            lName3.setDisable(true);
            dateOfBirth3.setDisable(true);
        } else if (spinnerPassengerNo.getValue() == 3) {
            fName3.setDisable(false);
            lName3.setDisable(false);
            dateOfBirth3.setDisable(false);
            fName4.setDisable(true);
            lName4.setDisable(true);
            dateOfBirth4.setDisable(true);
        } else if (spinnerPassengerNo.getValue() == 4) {
            fName4.setDisable(false);
            lName4.setDisable(false);
            dateOfBirth4.setDisable(false);
            fName5.setDisable(true);
            lName5.setDisable(true);
            dateOfBirth5.setDisable(true);
        } else if (spinnerPassengerNo.getValue() == 5) {
            fName5.setDisable(false);
            lName5.setDisable(false);
            dateOfBirth5.setDisable(false);
            fName6.setDisable(true);
            lName6.setDisable(true);
            dateOfBirth6.setDisable(true);
        } else if (spinnerPassengerNo.getValue() == 6) {
            fName6.setDisable(false);
            lName6.setDisable(false);
            dateOfBirth6.setDisable(false);
            fName7.setDisable(true);
            lName7.setDisable(true);
            dateOfBirth7.setDisable(true);
        } else if (spinnerPassengerNo.getValue() == 7) {
            fName7.setDisable(false);
            lName7.setDisable(false);
            dateOfBirth7.setDisable(false);
            fName8.setDisable(true);
            lName8.setDisable(true);
            dateOfBirth8.setDisable(true);
        } else if (spinnerPassengerNo.getValue() == 8) {
            fName8.setDisable(false);
            lName8.setDisable(false);
            dateOfBirth8.setDisable(false);
        }
    }


    private void setFlightPriceAdult() {
        String flightDepart = comboOrigin.getSelectionModel().getSelectedItem();
        String flightReturn = comboDestination.getSelectionModel().getSelectedItem();

        // constructor
        flight = new Flight(
                flightDepart,       // setOrigin() from variable in this method
                flightReturn,       // setDestination() from variable in this method
                dateDepartPrice,    // setDepartPrice() from the return of getSelectDate()
                dateReturnPrice,    // setReturnPrice() from the return of getSelectDate()
                currentPrice,       // setPrice() from the return of getSelectedFlightPrice()
                selectedDeptTime,
                selectedReturnTime);
    }

    public void setFlightPriceChild() {
        String flightDepart = comboOrigin.getSelectionModel().getSelectedItem();
        String flightReturn = comboDestination.getSelectionModel().getSelectedItem();

        // constructor
        flightForChild = new Flight(flightDepart, flightReturn, CHILD_PRICE, CHILD_PRICE, CHILD_TOTAL_PRICE);


    }

    private void setFlightPriceInfants() {
        String flightDepart = comboOrigin.getSelectionModel().getSelectedItem();
        String flightReturn = comboDestination.getSelectionModel().getSelectedItem();

        // constructor
        flightForBaby = new Flight(flightDepart, flightReturn, BABY_PRICE, BABY_PRICE, BABY_PRICE);
    }


    private void addPassengers() {
        passengerList = new ArrayList<>();

        passenger1 = new Passenger(fName.getText(), lName.getText(), dateOfBirth1.getValue(), radioBtn1.isSelected());
        passenger2 = new Passenger(fName2.getText(), lName2.getText(), dateOfBirth2.getValue(), radioBtn2.isSelected());
        passenger3 = new Passenger(fName3.getText(), lName3.getText(), dateOfBirth3.getValue(), radioBtn3.isSelected());
        passenger4 = new Passenger(fName4.getText(), lName4.getText(), dateOfBirth4.getValue(), radioBtn4.isSelected());
        passenger5 = new Passenger(fName5.getText(), lName5.getText(), dateOfBirth5.getValue(), radioBtn5.isSelected());
        passenger6 = new Passenger(fName6.getText(), lName6.getText(), dateOfBirth6.getValue(), radioBtn6.isSelected());
        passenger7 = new Passenger(fName7.getText(), lName7.getText(), dateOfBirth7.getValue(), radioBtn7.isSelected());
        passenger8 = new Passenger(fName8.getText(), lName8.getText(), dateOfBirth8.getValue(), radioBtn8.isSelected());
        passengerList.add(passenger1);
        passengerList.add(passenger2);
        passengerList.add(passenger3);
        passengerList.add(passenger4);
        passengerList.add(passenger5);
        passengerList.add(passenger6);
        passengerList.add(passenger7);
        passengerList.add(passenger8);

    }


    private void getDetails() {

        addPassengers();


        LocalDate now = LocalDate.now();
        LocalDate nowMinus1yr = now.minusYears(1);
        LocalDate nowMinus5yrs = now.minusYears(5);
        LocalDate nowMinus18yrs = now.minusYears(18);


        int countChildren = 0;
        int mCounter = 0;
        for (int i = 0; i < MAX_PASSENGER_NO; i++) {

            try {
                if (passengerList != null) {
                    mCounter++;


                    // OFF for Testing the UI
//                    if (passengerList.get(i).getDateOfBirth().isAfter(nowMinus18yrs) && passengerList.get(i+1).getDateOfBirth().isAfter(nowMinus18yrs) ) {

                    if (passengerList.get(i).checkForMaxChildPass()) {
                        countChildren++;


                        if(countChildren > 2) {
                            UtilityClass.errorMessageUnder18();
                            listView.getSelectionModel().clearSelection();

                            System.out.println(countChildren); // 3 selected 3 returned
                        }
                    }



                    else {
                        window.setScene(scene2);
                        System.out.println(countChildren); // change one dob, the var = 0
                    }


                    // setting variable equal to the current passenger, then getting returned value for the Flight object method
                    double bagPrice = passengerList.get(mCounter - 1).displayPriceFromBagSelected();

                    // setting variable equal to the returned value from getSelectedFlightPrice() in this class
                    double flightPrice = currentPrice;

                    // setting variable equal to the sum of the above two vars.
                    double adultPrice = bagPrice + flightPrice;

                    // setting variable equal to bagPrice plus the constant - child price total
                    double childPrice = bagPrice + CHILD_TOTAL_PRICE;


                    // add Passenger and Flight objects to the ListView displayed in the next scene (after Continue button is selected)
                    if (passengerList.get(i).getDateOfBirth().isAfter(nowMinus1yr)) {

                        setFlightPriceInfants();
                        listView.getItems().addAll("\nPassenger " + mCounter + passengerList.get(mCounter - 1), flightForBaby,
                                "\tTotal: \t\t\t\t\t = €" + BABY_PRICE + " (Babies fly free, but do not get a seat nor a checked bag)");


                    } else if (passengerList.get(i).getDateOfBirth().isAfter(nowMinus5yrs) && passengerList.get(i).getDateOfBirth().isBefore(nowMinus1yr)) {

                        setFlightPriceChild();
                        listView.getItems().addAll("\nPassenger " + mCounter + passengerList.get(mCounter - 1), flightForChild,
                                "\tTotal: \t\t\t\t\t = €" + childPrice + " (incl baggage cost, if selected)");

//                        numberOfChildren++;


                    } else if (passengerList.get(i).getDateOfBirth().isBefore(nowMinus5yrs)) {

                        setFlightPriceAdult();
                        listView.getItems().addAll("\nPassenger " + mCounter + passengerList.get(mCounter - 1), flight,
                                "\tTotal: \t\t\t\t\t = €" + adultPrice + " (incl baggage cost, if selected)");

                    }
                }
            } catch (Exception e) {
                e.getMessage();
            }


        }

        try {
            Button buttonBack = new Button("Back");
            vBox.getChildren().addAll(listView, buttonBack);
            buttonBack.setOnAction(event -> {
                listView.getItems().clear();
                window.setScene(scene1);


            });
        } catch (IllegalArgumentException iae) {
            iae.getMessage();
        }
    }


//    private void checkTwoChildMax() {
//
//        try {
//            for (int j = 0; j <= MAX_PASSENGER_NO; j++) {
//                if (passengerList.get(j).getDateOfBirth().isAfter(LocalDate.now().minusYears(5)) &&
//                        passengerList.get(j).getDateOfBirth().isBefore(LocalDate.now())) {
//
//                    numberOfChildren++;
//                }
//
//                if (numberOfChildren > 2) {
//                    UtilityClass.errorMessageLastName();
//
//                }
//            }
//        }catch (Exception e) {
//            e.getMessage();
//        }
//    }


    public void validateForEmptyFields() {

        addPassengers();

        // OFF for Testing
        if (comboOrigin.getValue() != null || comboDestination.getValue() != null) {
            if (datePickerDeparture.getValue() != null || datePickerReturn.getValue() != null) {

                try {


                    String validText = "^[\\p{L} .'-]+$";
                    int i = 0;

                    for (Passenger mPassenger : passengerList) {
                        i++;


                        if (spinnerPassengerNo.getValue() == i) {


                            if (mPassenger.getFirstName().isEmpty() || mPassenger.getLastName().isEmpty() || mPassenger.getDateOfBirth() == null) {

                                UtilityClass.errorMessageAddCustomer();

                            } else if (!mPassenger.getFirstName().matches(validText)) {
                                UtilityClass.errorMessageFirstName();
                            } else if (!mPassenger.getLastName().matches(validText)) {
                                UtilityClass.errorMessageLastName();
                            } else {
                                getDetails();
                            }
                        }
                    }
                } catch (Exception e) {
                    e.getMessage();
                }

            } else {
                UtilityClass.errorMessageDate();
            }
        } else {
            UtilityClass.errorMessageFlight();
        }
    }


    private AnchorPane createAnchorPane() {
        AnchorPane anchorPane = new AnchorPane();
        buttonCancel = new Button("Cancel");
        buttonCancel.setOnAction(event -> UtilityClass.confirmBoxCloseApp());

        buttonContinue = new Button("Continue");
        buttonContinue.setOnAction(event -> {


            validateForEmptyFields();


        });


        HBox hBox = new HBox();
        hBox.setSpacing(20);
        hBox.setPadding(new Insets(0, 10, 10, 10));
        hBox.getChildren().addAll(buttonCancel, buttonContinue);

        anchorPane.getChildren().addAll(buttonContinue, buttonCancel);
        AnchorPane.setBottomAnchor(buttonContinue, 10.0);
        AnchorPane.setRightAnchor(buttonContinue, 315.0);
        AnchorPane.setBottomAnchor(buttonCancel, 10.0);
        AnchorPane.setRightAnchor(buttonCancel, 415.0);

        return anchorPane;
    }


}