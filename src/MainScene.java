import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;


public class MainScene extends Application {

    private Button buttonCancel, buttonContinue;
    private Button buttonAdd;
    private ComboBox<String> comboOrigin;
    private ComboBox<String> comboDestination;
    private DatePicker datePickerDeparture, dpDateoFBirth;
    private DatePicker datePickerReturn;
    private GridPane gridPaneMiddle;
    private GridPane gridPaneRight;
    private TextArea textAreaDepart, textAreaReturn;
    private Label labelOrigin, labelDestination, labelDateDeparture, labelDateReturn;
    private TextField tf, tf2, tfFName, tfLName;
    private ListView listView;
    private String firstName1, lastName1, dateOfBirth1;

    private HBox hBoxList;
    private String dptFlight, rtnFlight;
    private double dateDepartPrice;
    private double dateReturnPrice;
    private double flightPrice;
    private double currentPrice;
    private final ToggleGroup toggleRadioButtonGroup = new ToggleGroup();
    private LocalDate ldDepartDate, ldReturnDate;
    private GridPane gridPaneLeft;
    private Stage window;
    private Scene scene1, scene2;
    private VBox vBox;
    private ObservableList<TextField> tfFirstNamesList = FXCollections.observableArrayList();
    private ObservableList<TextField> tfLastNamesList = FXCollections.observableArrayList();
    private ObservableList<DatePicker> dpDateOfBirthList = FXCollections.observableArrayList();

    private TextField   fName, fName2, fName3, fName4, fName5, fName6, fName7, fName8,
            lName, lName2, lName3, lName4, lName5, lName6, lName7, lName8;
    private DatePicker dateoFBirth1, dateoFBirth2, dateoFBirth3, dateoFBirth4, dateoFBirth5, dateoFBirth6, dateoFBirth7, dateoFBirth8;
    private Spinner<Integer> spinnerPassengerNo;

    private FlowPane pane1, pane2;
    private Pane pane;

    // constants - flight airports
    private static final String CORK            = "ORK";
    private static final String MADRID          = "MAD";
    private static final String ST_BRIEUC       = "SBK";
    private static final String JERSEY          = "JER";
    private static final String PARIS           = "CDG";
    private static final String STANSTED        = "STN";
    private static final String MALAGA          = "AGP";
    private static final String FRI             = "FRIDAY";
    private static final String SAT             = "SATURDAY";
    private static final String SUN             = "SUNDAY";

    // constants  flight times
    static String ORK_MAD_1 = "0920-1300";
    static String ORK_SBK_1 = "1030-1400";
    static String ORK_JER_1 = "1400-1600";
    static String ORK_CDG_1 = "0900-1215";
    static String ORK_CDG_2 = "1820-2105";
    static String ORK_STN_1 = "0820-0950";
    static String ORK_STN_2 = "1120-1305";
    static String ORK_AGP_1 = "0800-1130";
    static String MAD_ORK_1 = "1800-2000";
    static String MAD_SBK_1 = "1200-1400";
    static String MAD_JER_1 = "0620-0800";
    static String MAD_CDG_1 = "0800-1000";
    static String MAD_STN_1 = "1400-1520";
    static String MAD_STN_2 = "1905-2120";
    static String MAD_AGP_1 = "0800-0905";
    static String SBK_ORK_1 = "1900-2020";
    static String SBK_MAD_1 = "1800-2020";
    static String SBK_JER_1 = "none";
    static String SBK_CDG_1 = "0620-0715";
    static String SBK_STN_1 = "0805-0830";
    static String SBK_AGP_1 = "1200-1530";
    static String JER_ORK_1 = "1000-1200";
    static String JER_MAD_1 = "1800-2120";
    static String JER_CDG_1 = "0800-1015";
    static String JER_STN_1 = "1700-1830";
    static String JER_AGP_1 = "0800-1130";
    static String CDG_ORK_1 = "1330-1500";
    static String CDG_ORK_2 = "2200-2350";
    static String CDG_MAD_1 = "1920-2105";
    static String CDG_SBK_1 = "1900-2005";
    static String CDG_JER_1 = "2000-2015";
    static String CDG_STN_1 = "1800-1830";
    static String CDG_AGP_1 = "1150-1330";
    static String STN_ORK_1 = "1100-1220";
    static String STN_ORK_2 = "1800-1920";
    static String STN_MAD_1 = "1020-1400";
    static String STN_SBK_1 = "1800-2000";
    static String STN_JER_1 = "0900-1030";
    static String STN_CDG_1 = "0900-1030";
    static String STN_AGP_1 = "0800-1100";
    static String STN_AGP_2 = "1330-1620";
    static String AGP_ORK_1 = "1300-1420";
    static String AGP_MAD_1 = "2000-2105";
    static String AGP_SBK_1 = "2000-2130";
    static String AGP_JER_1 = "1800-1930";
    static String AGP_CDG_1 = "1805-1230";
    static String AGP_STN_1 = "1500-1610";
    static String AGP_STN_2 = "2035-2105";

    // constants - flight prices
    static final int TWO_HND_EIGHTY = 280;
    static final int TWO_HND_FIFTY = 250;
    static final int TWO_HND_FORTY = 240;
    static final int TWO_HND = 200;
    static final int ONE_HND_FIFTY = 150;
    static final int ONE_HND_FORTY = 140;
    static final int ONE_HND_TWENTY = 120;
    static final int ONE_HND = 100;
    static final int EIGHTY = 80;
    static final int SIXTY = 60;
    static final int FORTY = 40;
    static final int ZERO = 0;

    static final Double CHILD_PRICE = 60.0;
    static final Double CHILD_TOTAL_PRICE = CHILD_PRICE * 2;



    // constants - misc
    static final int MAX_PASSENGER_NO   = 8;
    static final ObservableList airportList = FXCollections.observableArrayList();

    // reference to the Passenger and FLight objects
    List<Passenger> passengerList = FXCollections.observableArrayList();
    protected List<Flight> flightList;
    protected Passenger passenger1, passenger2, passenger3, passenger4, passenger5, passenger6, passenger7,passenger8;
    protected Flight flight;

    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;

        // add Constants to the ObservableList
        airportList.addAll(CORK, MADRID, ST_BRIEUC, JERSEY, PARIS, STANSTED, MALAGA);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(
                createTopGridPane(), createMiddleGridPane(), createBottomPane(), createAnchorPane());
        scene1 = new Scene(vBox, 800, 750);

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
        radioButtonOneWay.setToggleGroup(toggleRadioButtonGroup);
        RadioButton radioButtonReturn = new RadioButton("Return");
        radioButtonReturn.setToggleGroup(toggleRadioButtonGroup);
        radioButtonReturn.fire(); // return btn on by default

        toggleRadioButtonGroup.selectedToggleProperty().addListener(observable -> {
            if(toggleRadioButtonGroup.getSelectedToggle() == radioButtonOneWay) {
                comboDestination.setDisable(true);
                datePickerReturn.setDisable(true);
                textAreaReturn.setDisable(true);

            }
            else if(toggleRadioButtonGroup.getSelectedToggle() ==  radioButtonReturn) {
                comboDestination.setDisable(false);
                datePickerReturn.setDisable(false);
                textAreaReturn.setDisable(false);
            }
        });

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


        labelDateReturn = new Label("Return");
        datePickerReturn = new DatePicker();
        datePickerReturn.setPromptText("pick a date");
        datePickerReturn.setEditable(true);
        datePickerReturn.setOnAction(event -> {
            event.consume();
            checkForInvalidDates();
            getSelectDate(event);


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


        Button btnFlightSelect = new Button("Select Flight");
        btnFlightSelect.setOnAction(event ->{
            if(comboDestination.getSelectionModel().isEmpty()){
                UtilityClass.errorMessageFlight();
            }

            displaySelectedFlights();
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
            if(flightDepart.equals(CORK)) {
                comboDestination.getItems().clear();
                comboDestination.getItems().addAll(MADRID, ST_BRIEUC, JERSEY, PARIS, STANSTED, MALAGA);
            }
            else if(flightDepart.equals(MADRID)) {
                comboDestination.getItems().clear();
                comboDestination.getItems().addAll(CORK, ST_BRIEUC, JERSEY, PARIS, STANSTED, MALAGA);
            }
            else if(flightDepart.equals(PARIS)) {
                comboDestination.getItems().clear();
                comboDestination.getItems().addAll(CORK, MADRID, ST_BRIEUC, JERSEY, STANSTED, MALAGA);
            }
            else if(flightDepart.equals(STANSTED)) {
                comboDestination.getItems().clear();
                comboDestination.getItems().addAll(CORK, MADRID, ST_BRIEUC, JERSEY, PARIS, MALAGA);
            }
            else if(flightDepart.equals(MALAGA)) {
                comboDestination.getItems().clear();
                comboDestination.getItems().addAll(CORK, MADRID, ST_BRIEUC, JERSEY, PARIS, STANSTED);
            }
            else if (flightDepart.equals(ST_BRIEUC) || flightDepart.equals(JERSEY)) {
                comboDestination.getItems().clear();
                comboDestination.getItems().addAll(CORK, MADRID, PARIS, STANSTED, MALAGA);
            }
        }catch (Exception e){
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

                        if(item.isBefore(currentDate) || item.isAfter(nowPlusSixMonths)) {
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

        if(departDate != null && returnDate != null) {
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
        LocalDate dateReturn= datePickerReturn.getValue();

        try {

            if(flightDepart == null) {
                UtilityClass.errorMessageFlight();
            }
            else if (dateDept == null || dateReturn == null) {
                UtilityClass.errorMessageDate();
            }
            else {

                setFlightPriceAdult();

                if (flight != null) {
                    textAreaDepart.setText(flight.toStringDept());
                    textAreaReturn.setText(flight.toStringReturn());
                    textAreaReturn.appendText("\n\n" + flight.getPrice());
                }

                if (flightDepart.equals(CORK) && flightReturn.equals(MADRID)) {
                    textAreaDepart.appendText("\n" + ORK_MAD_1);
                } else if (flightDepart.equals(CORK) && flightReturn.equals(ST_BRIEUC)) {
                    textAreaDepart.appendText("\n" + ORK_SBK_1);
                } else if (flightDepart.equals(CORK) && flightReturn.equals(JERSEY)) {
                    textAreaDepart.appendText("\n" + ORK_JER_1);
                } else if (flightDepart.equals(CORK) && flightReturn.equals(PARIS)) {
                    textAreaDepart.appendText("\n" + ORK_CDG_1);
                    textAreaDepart.appendText("\n" + ORK_CDG_2);
                } else if (flightDepart.equals(CORK) && flightReturn.equals(STANSTED)) {
                    textAreaDepart.appendText("\n" + ORK_STN_1);
                    textAreaDepart.appendText("\n" + ORK_STN_2);
                } else if (flightDepart.equals(CORK) && flightReturn.equals(MALAGA)) {
                    textAreaDepart.appendText("\n" + ORK_AGP_1);
                } else if (flightDepart.equals(MADRID) && flightReturn.equals(CORK)) {
                    textAreaDepart.appendText("\n" + MAD_ORK_1);
                } else if (flightDepart.equals(MADRID) && flightReturn.equals(ST_BRIEUC)) {
                    textAreaDepart.appendText("\n" + MAD_SBK_1);
                } else if (flightDepart.equals(MADRID) && flightReturn.equals(JERSEY)) {
                    textAreaDepart.appendText("\n" + MAD_JER_1);
                } else if (flightDepart.equals(MADRID) && flightReturn.equals(PARIS)) {
                    textAreaDepart.appendText("\n" + MAD_CDG_1);
                } else if (flightDepart.equals(MADRID) && flightReturn.equals(STANSTED)) {
                    textAreaDepart.appendText("\n" + MAD_STN_1);
                    textAreaDepart.appendText("\n" + MAD_STN_2);
                } else if (flightDepart.equals(MADRID) && flightReturn.equals(MALAGA)) {
                    textAreaDepart.appendText("\n" + MAD_AGP_1);
                } else if (flightDepart.equals(ST_BRIEUC) && flightReturn.equals(CORK)) {
                    textAreaDepart.appendText("\n" + SBK_ORK_1);
                } else if (flightDepart.equals(ST_BRIEUC) && flightReturn.equals(MADRID)) {
                    textAreaDepart.appendText("\n" + SBK_MAD_1);
                } else if (flightDepart.equals(ST_BRIEUC) && flightReturn.equals(JERSEY)) {
                    textAreaDepart.appendText("\n" + SBK_JER_1);
                } else if (flightDepart.equals(ST_BRIEUC) && flightReturn.equals(PARIS)) {
                    textAreaDepart.appendText("\n" + STN_CDG_1);
                } else if (flightDepart.equals(ST_BRIEUC) && flightReturn.equals(STANSTED)) {
                    textAreaDepart.appendText("\n" + SBK_STN_1);
                } else if (flightDepart.equals(ST_BRIEUC) && flightReturn.equals(MALAGA)) {
                    textAreaDepart.appendText("\n" + SBK_AGP_1);
                }
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
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

        gridPaneMiddle.add(textAreaDepart, 0, 2);
        gridPaneMiddle.add(buttonFlightSelect, 0, 3);
        gridPaneMiddle.add(textAreaReturn, 2, 2);

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
        stackPaneLeft.setMaxWidth(200);
        stackPaneLeft.getStyleClass().add("stackPaneLeft");
        StackPane.setAlignment(gridPaneLeft, Pos.TOP_LEFT);
        stackPaneLeft.getChildren().addAll(gridPaneLeft);


        Label label = new Label("Number of passengers:");
        List<Integer> spinnerItemsList = new ArrayList<>();
        for (int i = 0; i <= MAX_PASSENGER_NO; i++) {
            spinnerItemsList.add(i);
        }

        ObservableList<Integer> integerObservableList
                = FXCollections.observableList(spinnerItemsList);


        spinnerPassengerNo = new Spinner<>();
        spinnerPassengerNo.getStyleClass().add("myDatePicker");

        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.ListSpinnerValueFactory<>(integerObservableList);

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
        stackPaneRight.setMaxWidth(465);
        stackPaneRight.getStyleClass().add("stackPaneRight");

        Label labelBorder = new Label("Passenger Details");
        labelBorder.getStyleClass().add("border-title");
        StackPane.setAlignment(labelBorder, Pos.TOP_CENTER);
        stackPaneRight.getChildren().addAll(labelBorder);

        fName = new TextField();  fName.setDisable(true); // fName.setVisible(false);
        lName = new TextField();  lName.setDisable(true);
        fName2 = new TextField(); fName2.setDisable(true);
        lName2 = new TextField(); lName2.setDisable(true);
        fName3 = new TextField(); fName3.setDisable(true);
        lName3 = new TextField(); lName3.setDisable(true);
        fName4 = new TextField(); fName4.setDisable(true);
        lName4 = new TextField(); lName4.setDisable(true);
        fName5 = new TextField(); fName5.setDisable(true);
        lName5 = new TextField(); lName5.setDisable(true);
        fName6 = new TextField(); fName6.setDisable(true);
        lName6 = new TextField(); lName6.setDisable(true);
        fName7 = new TextField(); fName7.setDisable(true);
        lName7 = new TextField(); lName7.setDisable(true);
        fName8 = new TextField(); fName8.setDisable(true);
        lName8 = new TextField(); lName8.setDisable(true);
        dateoFBirth1 = new DatePicker(); dateoFBirth1.setDisable(true);
        dateoFBirth1.getStyleClass().add("myDatePicker");
        dateoFBirth2 = new DatePicker(); dateoFBirth2.setDisable(true);
        dateoFBirth2.getStyleClass().add("myDatePicker");
        dateoFBirth3 = new DatePicker(); dateoFBirth3.setDisable(true);
        dateoFBirth3.getStyleClass().add("myDatePicker");
        dateoFBirth4 = new DatePicker(); dateoFBirth4.setDisable(true);
        dateoFBirth4.getStyleClass().add("myDatePicker");
        dateoFBirth5 = new DatePicker(); dateoFBirth5.setDisable(true);
        dateoFBirth5.getStyleClass().add("myDatePicker");
        dateoFBirth6 = new DatePicker(); dateoFBirth6.setDisable(true);
        dateoFBirth6.getStyleClass().add("myDatePicker");
        dateoFBirth7 = new DatePicker(); dateoFBirth7.setDisable(true);
        dateoFBirth7.getStyleClass().add("myDatePicker");
        dateoFBirth8 = new DatePicker(); dateoFBirth8.setDisable(true);
        dateoFBirth8.getStyleClass().add("myDatePicker");

        tfFirstNamesList.addAll(fName, fName2, fName3, fName4, fName5, fName6, fName7, fName8);


        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 0, 0, 0));
        gridPane.add(fName, 1, 0);
        gridPane.add(lName, 2, 0);
        gridPane.add(dateoFBirth1, 3, 0);
        gridPane.add(fName2, 1, 1);
        gridPane.add(lName2, 2, 1);
        gridPane.add(dateoFBirth2, 3, 1);
        gridPane.add(fName3, 1, 2);
        gridPane.add(lName3, 2, 2);
        gridPane.add(dateoFBirth3, 3, 2);
        gridPane.add(fName4, 1, 3);
        gridPane.add(lName4, 2, 3);
        gridPane.add(dateoFBirth4, 3, 3);
        gridPane.add(fName5, 1, 4);
        gridPane.add(lName5, 2, 4);
        gridPane.add(dateoFBirth5, 3, 4);
        gridPane.add(fName6, 1, 5);
        gridPane.add(lName6, 2, 5);
        gridPane.add(dateoFBirth6, 3, 5);
        gridPane.add(fName7, 1, 6);
        gridPane.add(lName7, 2, 6);
        gridPane.add(dateoFBirth7, 3, 6);
        gridPane.add(fName8, 1, 7);
        gridPane.add(lName8, 2, 7);
        gridPane.add(dateoFBirth8, 3, 7);

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



    private void getSelectedNumOfPassengers() {

        if(spinnerPassengerNo.getValue() == 0) {
            fName.setDisable(true);
            lName.setDisable(true);
            dateoFBirth1.setDisable(true);
        }
        else if(spinnerPassengerNo.getValue() == 1) {
            fName.setVisible(true);
            fName.setDisable(false);
            lName.setDisable(false);
            dateoFBirth1.setDisable(false);
            fName2.setDisable(true);
            lName2.setDisable(true);
            dateoFBirth2.setDisable(true);
        }
        else if(spinnerPassengerNo.getValue() == 2) {
            fName2.setDisable(false);
            lName2.setDisable(false);
            dateoFBirth2.setDisable(false);
            fName3.setDisable(true);
            lName3.setDisable(true);
            dateoFBirth3.setDisable(true);
        }
        else if(spinnerPassengerNo.getValue() == 3) {
            fName3.setDisable(false);
            lName3.setDisable(false);
            dateoFBirth3.setDisable(false);
            fName4.setDisable(true);
            lName4.setDisable(true);
            dateoFBirth4.setDisable(true);
        }
        else if(spinnerPassengerNo.getValue() == 4) {
            fName4.setDisable(false);
            lName4.setDisable(false);
            dateoFBirth4.setDisable(false);
            fName5.setDisable(true);
            lName5.setDisable(true);
            dateoFBirth5.setDisable(true);
        }
        else if(spinnerPassengerNo.getValue() == 5) {
            fName5.setDisable(false);
            lName5.setDisable(false);
            dateoFBirth5.setDisable(false);
            fName6.setDisable(true);
            lName6.setDisable(true);
            dateoFBirth6.setDisable(true);
        }
        else if(spinnerPassengerNo.getValue() == 6) {
            fName6.setDisable(false);
            lName6.setDisable(false);
            dateoFBirth6.setDisable(false);
            fName7.setDisable(true);
            lName7.setDisable(true);
            dateoFBirth7.setDisable(true);
        }
        else if(spinnerPassengerNo.getValue() == 7) {
            fName7.setDisable(false);
            lName7.setDisable(false);
            dateoFBirth7.setDisable(false);
            fName8.setDisable(true);
            lName8.setDisable(true);
            dateoFBirth8.setDisable(true);
        }
        else if(spinnerPassengerNo.getValue() == 8) {
            fName8.setDisable(false);
            lName8.setDisable(false);
            dateoFBirth8.setDisable(false);
        }
    }



    private void getDetails() {
        listView = new ListView();
        passengerList = new ArrayList<>();
        LocalDate now = LocalDate.now();
        LocalDate nowMinus5yrs = now.minusYears(5);

        passenger1 = new Passenger(fName.getText(), lName.getText(), dateoFBirth1.getValue());
        passenger2 = new Passenger(fName2.getText(), lName2.getText(), dateoFBirth2.getValue());
        passenger3 = new Passenger(fName3.getText(), lName3.getText(), dateoFBirth3.getValue());
        passenger4 = new Passenger(fName4.getText(), lName4.getText(), dateoFBirth4.getValue());
        passenger5 = new Passenger(fName5.getText(), lName5.getText(), dateoFBirth5.getValue());
        passenger6 = new Passenger(fName6.getText(), lName6.getText(), dateoFBirth6.getValue());
        passenger7 = new Passenger(fName7.getText(), lName7.getText(), dateoFBirth7.getValue());
        passenger8 = new Passenger(fName8.getText(), lName8.getText(), dateoFBirth8.getValue());
        passengerList.add(passenger1);
        passengerList.add(passenger2);
        passengerList.add(passenger3);
        passengerList.add(passenger4);
        passengerList.add(passenger5);
        passengerList.add(passenger6);
        passengerList.add(passenger7);
        passengerList.add(passenger8);


        int mCounter = 0;
        for (Passenger mPassenger : passengerList) {
            try {
                if (mPassenger != null) {
                    mCounter++;
                    if (mPassenger.getDateOfBirth().isBefore(nowMinus5yrs)) {
                        setFlightPriceAdult();
                        // add each flight from the observablelist, using a counter as the index
                        listView.getItems().addAll("Passenger " + mCounter + passengerList.get(mCounter -1), flight);
                    } else {
                        setFlightPriceChild();
                        listView.getItems().addAll("Passenger " + mCounter + passengerList.get(mCounter -1), flight);
                    }
                }
            }catch (Exception e) {
                e.getMessage();
            }
        }


        window.setScene(scene2);

        Button button = new Button("Back");
        vBox.getChildren().addAll(listView, button);
        button.setOnAction(event -> {
            window.setScene(scene1);
        });

    }


    public void setFlightPriceChild() {
        String flightDepart = comboOrigin.getSelectionModel().getSelectedItem();
        String flightReturn = comboDestination.getSelectionModel().getSelectedItem();

        flight = new Flight();
        flight.setDeapartPrice(CHILD_PRICE);
        flight.setReturnPrice(CHILD_PRICE);
        flight.setPrice(CHILD_TOTAL_PRICE);
        flight.setOrigin(flightDepart);
        flight.setDestination(flightReturn);
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
                currentPrice);      // setPrice() from the return of getSelectedFlightPrice()
    }


    private AnchorPane createAnchorPane() {
        AnchorPane anchorPane = new AnchorPane();
        buttonCancel = new Button("Cancel");
        buttonCancel.setOnAction(event -> UtilityClass.confirmBoxCloseApp());

        buttonContinue = new Button("Continue");
        buttonContinue.setOnAction(event -> {
            if (comboOrigin.getValue() != null || comboDestination.getValue() != null) {
                if (datePickerDeparture.getValue() != null || datePickerReturn.getValue() != null) {


                    if (spinnerPassengerNo.getValue() == 1) {
                        if (!(fName.getText().isEmpty() || lName.getText().isEmpty() || dateoFBirth1.getValue() == null)) {
                            getDetails();
                        } else {
                            UtilityClass.errorMessageAddCustomer();
                        }
                    }
                    else if (spinnerPassengerNo.getValue() == 2) {
                        if (!(fName2.getText().isEmpty() || lName2.getText().isEmpty() || dateoFBirth2.getValue() == null)) {
                            getDetails();
                        } else {
                            UtilityClass.errorMessageAddCustomer();
                        }
                    }
                    else if (spinnerPassengerNo.getValue() == 3) {
                        if (!(fName3.getText().isEmpty() || lName3.getText().isEmpty() || dateoFBirth3.getValue() == null)) {
                            getDetails();
                        } else {
                            UtilityClass.errorMessageAddCustomer();
                        }
                    }
                    else if (spinnerPassengerNo.getValue() == 4) {
                        if (!(fName4.getText().isEmpty() || lName4.getText().isEmpty() || dateoFBirth4.getValue() == null)) {
                            getDetails();
                        } else {
                            UtilityClass.errorMessageAddCustomer();
                        }
                    }
                    else if (spinnerPassengerNo.getValue() == 5) {
                        if (!(fName5.getText().isEmpty() || lName5.getText().isEmpty() || dateoFBirth5.getValue() == null)) {
                            getDetails();
                        } else {
                            UtilityClass.errorMessageAddCustomer();
                        }
                    }
                    else if (spinnerPassengerNo.getValue() == 6) {
                        if (!(fName6.getText().isEmpty() || lName6.getText().isEmpty() || dateoFBirth6.getValue() == null)) {
                            getDetails();
                        } else {
                            UtilityClass.errorMessageAddCustomer();
                        }
                    }
                    else if (spinnerPassengerNo.getValue() == 7) {
                        if (!(fName7.getText().isEmpty() || lName7.getText().isEmpty() || dateoFBirth7.getValue() == null)) {
                            getDetails();
                        } else {
                            UtilityClass.errorMessageAddCustomer();
                        }
                    }
                    else if (spinnerPassengerNo.getValue() == 8) {
                        if (!(fName8.getText().isEmpty() || lName8.getText().isEmpty() || dateoFBirth8.getValue() == null)) {
                            getDetails();
                        } else {
                            UtilityClass.errorMessageAddCustomer();
                        }
                    }
                } else {
                    UtilityClass.errorMessageDate();
                }
            } else {
                UtilityClass.errorMessageFlight();
            }
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



    public static void main(String[] args) {
        launch(args);
    }

}
