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

    private Button buttonCancel;
    private Button buttonContinue;
    private ComboBox<String> comboOrigin;
    private ComboBox<String> comboDestination;
    private DatePicker datePickerDeparture, dpDateoFBirth;
    private DatePicker datePickerReturn;
    private GridPane gridPaneMiddle;
    private GridPane gridPaneRight;
    private TextArea textAreaDepart, textAreaReturn;
    private Label labelOrigin, labelDestination, labelDateDeparture, labelDateReturn;
    private TextField tf, tfFName, tfLName;
    private HBox hBoxList;
    private String dptFlight, rtnFlight;
    private double dateDepartPrice;
    private double dateReturnPrice;
    private double flightPrice;
    private double currentPrice;
    private final ToggleGroup toggleRadioButtonGroup = new ToggleGroup();

    private GridPane gridPaneLeft;
    private Spinner<Integer> spinnerPassengerNo;

    private Stage window;
    private Scene scene1, scene2;
    private VBox vBox;


    private ObservableList<TextField> tfFirstNamesList = FXCollections.observableArrayList();
    private ObservableList<TextField> tfLastNamesList = FXCollections.observableArrayList();
    private ObservableList<DatePicker> dpDateOfBirthList = FXCollections.observableArrayList();


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
    private static String ORK_MAD_1             = "0920-1300";
    private static String ORK_SBK_1             = "1030-1400";
    private static String ORK_JER_1             = "1400-1600";
    private static String ORK_CDG_1             = "0900-1215";
    private static String ORK_CDG_2             = "1820-2105";
    private static String ORK_STN_1             = "0820-0950";
    private static String ORK_STN_2             = "1120-1305";
    private static String ORK_AGP_1             = "0800-1130";
    private static String MAD_ORK_1             = "1800-2000";
    private static String MAD_SBK_1             = "1200-1400";
    private static String MAD_JER_1             = "0620-0800";
    private static String MAD_CDG_1             = "0800-1000";
    private static String MAD_STN_1             = "1400-1520";
    private static String MAD_STN_2             = "1905-2120";
    private static String MAD_AGP_1             = "0800-0905";
    private static String SBK_ORK_1             = "1900-2020";
    private static String SBK_MAD_1             = "1800-2020";
    private static String SBK_JER_1             = "none";
    private static String SBK_CDG_1             = "0620-0715";
    private static String SBK_STN_1             = "0805-0830";
    private static String SBK_AGP_1             = "1200-1530";
    private static String JER_ORK_1             = "1000-1200";
    private static String JER_MAD_1             = "1800-2120";
    private static String JER_SBK_1             = "none";
    private static String JER_CDG_1             = "0800-1015";
    private static String JER_STN_1             = "1700-1830";
    private static String JER_AGP_1             = "0800-1130";
    private static String CDG_ORK_1             = "1330-1500";
    private static String CDG_ORK_2             = "2200-2350";
    private static String CDG_MAD_1             = "1920-2105";
    private static String CDG_SBK_1             = "1900-2005";
    private static String CDG_JER_1             = "2000-2015";
    private static String CDG_STN_1             = "1800-1830";
    private static String CDG_AGP_1             = "1150-1330";
    private static String STN_ORK_1             = "1100-1220";
    private static String STN_ORK_2             = "1800-1920";
    private static String STN_MAD_1             = "1020-1400";
    private static String STN_SBK_1             = "1800-2000";
    private static String STN_JER_1             = "0900-1030";
    private static String STN_CDG_1             = "0900-1030";
    private static String STN_AGP_1             = "0800-1100";
    private static String STN_AGP_2             = "1330-1620";
    private static String AGP_ORK_1             = "1300-1420";
    private static String AGP_MAD_1             = "2000-2105";
    private static String AGP_SBK_1             = "2000-2130";
    private static String AGP_JER_1             = "1800-1930";
    private static String AGP_CDG_1             = "1805-1230";
    private static String AGP_STN_1             = "1500-1610";
    private static String AGP_STN_2             = "2035-2105";



    // constants - flight prices
    private static final int TWO_HND_EIGHTY     = 280;
    private static final int TWO_HND_FIFTY      = 250;
    private static final int TWO_HND_FORTY      = 240;
    private static final int TWO_HND            = 200;
    private static final int ONE_HND_FIFTY      = 150;
    private static final int ONE_HND_FORTY      = 140;
    private static final int ONE_HND_TWENTY     = 120;
    private static final int ONE_HND            = 100;
    private static final int EIGHTY             = 80;
    private static final int SIXTY              = 60;
    private static final int FORTY              = 40;
    private static final int ZERO               = 0;

    // constants - misc
    private static final int MAX_PASSENGER_NO   = 8;
    private static final ObservableList airportList = FXCollections.observableArrayList();

    private Passenger passenger;


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
            if (comboOrigin.getItems().contains(comboOrigin.getValue())) {
                getSelectedFlightPrice();
            } else {
                UtilityClass.errorMessageInput();
            }
        });

        labelDestination = new Label("To: ");
        comboDestination = new ComboBox<>();
        comboDestination.setPromptText("select airport");
        comboDestination.getItems().addAll(airportList);
        comboDestination.setEditable(true);

        comboDestination.setOnAction(event -> {
            if (comboDestination.getItems().contains(comboDestination.getValue())) {
                getSelectedFlightPrice();
            } else {
                UtilityClass.errorMessageInput();
            }
        });


        labelDateReturn = new Label("Return");
        datePickerReturn = new DatePicker();
        datePickerReturn.setPromptText("pick a date");
        datePickerReturn.setEditable(true);
        datePickerReturn.setOnAction(event -> getSelectDate(event));


        labelDateDeparture = new Label("Depart");
        datePickerDeparture = new DatePicker();
        datePickerDeparture.setPromptText("pick a date");
        datePickerDeparture.setEditable(true);
        datePickerDeparture.setOnAction(event -> getSelectDate(event));


        Button btnFlightSelect = new Button("Select Flight");
        btnFlightSelect.setOnAction(event -> displaySelectedFlights());

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
        LocalDate ldDepartDate = datePickerDeparture.getValue();
        LocalDate ldReturnDate = datePickerReturn.getValue();
        String dayOfWeek = ldDepartDate.getDayOfWeek().name();
        try {

            if(ldDepartDate.isAfter(ldReturnDate)) UtilityClass.errorMessageDatesNotPossible();

            if (event.getSource().equals(datePickerDeparture)) {

                if (dayOfWeek.equals(FRI) || dayOfWeek.equals(SAT) || dayOfWeek.equals(SUN)) {
                    dateDepartPrice = flightPrice + flightPrice * 0.2;
                } else {
                    dateDepartPrice = flightPrice;
                }
            } else if (event.getSource().equals(datePickerReturn)) {
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


    private void displaySelectedFlights() {
        String flightDepart = comboOrigin.getSelectionModel().getSelectedItem();
        String flightReturn = comboDestination.getSelectionModel().getSelectedItem();
        LocalDate dateDept = datePickerDeparture.getValue();
        LocalDate dateReturn= datePickerReturn.getValue();

        try {

            if(flightDepart == null || flightReturn == null) {
                UtilityClass.errorMessageFlight();
            }
            else if (dateDept == null || dateReturn == null) {
                UtilityClass.errorMessageDate();
            }
            else {
                Flight flight = new Flight(
                        flightDepart,       // setOrigin() from variable in this method
                        flightReturn,       // setDestination() from variable in this method
                        dateDepartPrice,    // setDepartPrice() from the return of getSelectDate()
                        dateReturnPrice,    // setReturnPrice() from the return of getSelectDate()
                        currentPrice);      // setPrice() from the return of getSelectedFlightPrice()

                if (flight != null) {
                    textAreaDepart.setText(flight.toStringDept());
                    textAreaReturn.setText(flight.toStringReturn());
                    textAreaReturn.appendText("\n\nTotal: " + flight.toString());
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


        gridPaneRight = new GridPane();
        gridPaneRight.setHgap(10);
        gridPaneRight.setVgap(10);
        gridPaneRight.setPadding(new Insets(20, 0, 0, 0));


        spinnerPassengerNo = new Spinner<>();
        spinnerPassengerNo.getStyleClass().add("smallerField");

        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.ListSpinnerValueFactory<>(integerObservableList);

        spinnerPassengerNo.setValueFactory(valueFactory);

        spinnerPassengerNo.valueProperty().addListener(observable -> {

            deletePassengerDetailsRow();
            addPassengerDetailsFields();

        });


        gridPaneLeft.add(label, 1, 2);
        gridPaneLeft.add(spinnerPassengerNo, 1, 3);
        GridPane.setMargin(spinnerPassengerNo, new Insets(10, 0, 50, 50));

        StackPane stackPaneRight = new StackPane();
        stackPaneRight.setPrefHeight(310);
        stackPaneRight.setMaxWidth(465);
        stackPaneRight.getStyleClass().add("stackPaneRight");

        StackPane.setAlignment(gridPaneRight, Pos.TOP_LEFT);
        stackPaneRight.getChildren().addAll(gridPaneRight);

        Label labelBorder = new Label("Passenger Details");
        labelBorder.getStyleClass().add("border-title");
        StackPane.setAlignment(labelBorder, Pos.TOP_CENTER);
        stackPaneRight.getChildren().addAll(labelBorder);


        HBox hBox = new HBox();
        HBox.setHgrow(stackPaneLeft, Priority.ALWAYS);
        HBox.setHgrow(stackPaneRight, Priority.ALWAYS);
        hBox.setPadding(new Insets(10));
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(stackPaneLeft, stackPaneRight);

        return hBox;
    }



    private void addPassengerDetailsFields() {
        int numCustomersSelected = spinnerPassengerNo.valueProperty().getValue();

        for (int i = 1; i <= numCustomersSelected; i++) {
            tfFName = new TextField();
            tfFName.setPromptText("Passenger " + i + " first name");
            tfFirstNamesList.add(tfFName);

            tfLName = new TextField();
            tfLName.setPromptText("Passenger " + i + " last name");
            tfLastNamesList.add(tfLName);

            dpDateoFBirth = new DatePicker();
            dpDateoFBirth.setPromptText("DOB");
            dpDateOfBirthList.add(dpDateoFBirth);
            dpDateoFBirth.getStyleClass().add("smallerField");

            hBoxList = new HBox(8);
            hBoxList.getChildren().addAll(tfFName, tfLName, dpDateoFBirth);
            gridPaneRight.add(hBoxList, 1, i - 1);
        }
    }


    private void deletePassengerDetailsRow() {
        int choice = spinnerPassengerNo.getValue();

        try {
            for (int i = 0; i <= MAX_PASSENGER_NO; i++) gridPaneRight.getChildren().remove(choice);
        }catch (Exception e){
            e.getMessage();
        }
    }



    private void getDetails() {
        if (!(tfFName.getText().isEmpty() || tfLName.getText().isEmpty() || dpDateoFBirth.getValue() == null)) {
            passenger = new Passenger(tfFName.getText(), tfLName.getText(), dpDateoFBirth.getValue().toString());

            tf = new TextField();
            tf.setMinWidth(500);
            tf.setText(passenger.toString());

            window.setScene(scene2);

            Button button = new Button("Back");
            vBox.getChildren().addAll(tf, button);
            button.setOnAction(event -> window.setScene(scene1));
        }
        else {
            UtilityClass.errorMessageAddCustomer();
        }

    }


    private AnchorPane createAnchorPane() {
        AnchorPane anchorPane = new AnchorPane();
        buttonCancel = new Button("Cancel");
        buttonCancel.setOnAction(event -> UtilityClass.confirmBoxCloseApp());

        buttonContinue = new Button("Continue");
        buttonContinue.setOnAction(event -> {
            if (comboOrigin.getValue() != null || comboDestination.getValue() != null) {
                if (datePickerDeparture.getValue() != null || datePickerReturn.getValue() != null) {

                    if (!(tfFirstNamesList.isEmpty() || tfLastNamesList.isEmpty() || dpDateOfBirthList.isEmpty())) {
                        event.consume();
                        getDetails();
                    } else {
                        UtilityClass.errorMessageAddCustomer();
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
