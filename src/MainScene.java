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
    private DatePicker datePickerDeparture;
    private DatePicker datePickerReturn;
    private GridPane gridPaneMiddle;
//    private ImageView imageView;
//    private Image image;
//    private HBox hBoxImage;
    private TextArea textAreaDepart, textAreaReturn;
    private Label labelOrigin, labelDestination, labelDateDeparture, labelDateReturn;
    private ComboBox<Integer> comboPassengerNo;
    private String dptFlight, rtnFlight;
    private double dateDepartPrice;
    private double dateReturnPrice;
    private double flightPrice;
    private double currentPrice;
    private final ToggleGroup toggleRadioButtonGroup = new ToggleGroup();
    private List<TextField> tfFirstNamesList;
    private List<TextField> tfLastNamesList;
    private List<DatePicker> dpDateOfBirthList;

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
    private static final int MAX_PASSENGER_NO = 8;
    private static final ObservableList airportList = FXCollections.observableArrayList();


    @Override
    public void start(Stage primaryStage) throws Exception {
        // add Constants to the ObservableList
        airportList.addAll(CORK, MADRID, ST_BRIEUC, JERSEY, PARIS, STANSTED, MALAGA);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(
                createTopGridPane(), createMiddleGridPane(), createBottomPane(), createAnchorPane());
        Scene scene = new Scene(vBox, 800, 750);

        scene.getStylesheets().add("/stylesheet.css");
        primaryStage.setScene(scene);
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
            else if(toggleRadioButtonGroup.getSelectedToggle() ==radioButtonReturn) {
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
        datePickerReturn.setOnAction(event -> {
            getSelectDate(event);
        });

        labelDateDeparture = new Label("Depart");
        datePickerDeparture = new DatePicker();
        datePickerDeparture.setPromptText("pick a date");
        datePickerDeparture.setEditable(true);
        datePickerDeparture.setOnAction(event -> {
            getSelectDate(event);

        });

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
        // JavaFX DatePicker Tutorial - 07planning.org
        Callback<DatePicker, DateCell> monthCellFactory = this.getMonthCellFactory();
        datePickerDeparture.setDayCellFactory(monthCellFactory);
        datePickerReturn.setDayCellFactory(monthCellFactory);

        return flightPrice;
    }


    // Disable March and/or April in the DatePicker
    // JavaFX DatePicker Tutorial - 07planning.org
    Callback<DatePicker, DateCell> getMonthCellFactory() {

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
                LocalDate ldDepartDate = datePickerDeparture.getValue();

                // set variable to the day of the week, from the selected date
                String dayOfWeek = ldDepartDate.getDayOfWeek().name();

                if (dayOfWeek.equals(FRI) || dayOfWeek.equals(SAT) || dayOfWeek.equals(SUN)) {
                    dateDepartPrice = flightPrice + flightPrice * 0.2;
                } else {
                    dateDepartPrice = flightPrice;
                }

            } else if (event.getSource().equals(datePickerReturn)) {
                LocalDate ldReturnDate = datePickerReturn.getValue();
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


    private void displaySelectedFlights() {
        String flightDepart = comboOrigin.getSelectionModel().getSelectedItem();
        String flightReturn = comboDestination.getSelectionModel().getSelectedItem();
        LocalDate dateDept = datePickerDeparture.getValue();
        LocalDate dateRetn= datePickerReturn.getValue();


        try {

            if(flightDepart == null || flightReturn == null) {
                UtilityClass.errorMessageFlight();
            }
            else if (dateDept == null || dateRetn == null) {
                UtilityClass.errorMessageDate();
            }
            else {
                Flight flight = new Flight(
                        flightDepart,       // setOrigin() from variable in this method
                        flightReturn,       // setDestination() from variable in this method
                        dateDepartPrice,    // setDepartPrice() from the return of getSelectDate()
                        dateReturnPrice,    // setReturnPrice() from the return of getSelectDate()
                        currentPrice);      // setPrice() from the return of getSelectedFlightPrice()

                FlightController.getInstance().addFlight(flight);

                if (flight != null) {
                    textAreaDepart.setText(flight.toStringDept());
                    textAreaReturn.setText(flight.toStringReturn());
                    textAreaReturn.appendText("\n\nTotal: " + flight.toString());
                }

                if (flightDepart.equals(CORK) && flightReturn.equals(MADRID)) {
                    textAreaDepart.appendText("\n0920-1300");
                } else if (flightDepart.equals(CORK) && flightReturn.equals(ST_BRIEUC)) {
                    textAreaDepart.appendText("\n1030-1400");
                } else if (flightDepart.equals(CORK) && flightReturn.equals(ST_BRIEUC)) {
                    textAreaDepart.appendText("\n1030-1400");
                } else if (flightDepart.equals(CORK) && flightReturn.equals(PARIS)) {
                    textAreaDepart.appendText("\n0920-1215");
                    textAreaReturn.appendText("\n1820-2105");
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

        GridPane gridPaneLeft = new GridPane();
        gridPaneLeft.getStyleClass().add("gridPaneLeft");

        StackPane stackPaneLeft = new StackPane();
        stackPaneLeft.setPrefHeight(300);
        stackPaneLeft.setMaxWidth(200);
        stackPaneLeft.getStyleClass().add("stackPaneLeft");
        StackPane.setAlignment(gridPaneLeft, Pos.TOP_LEFT);
        stackPaneLeft.getChildren().addAll(gridPaneLeft);


        Label label = new Label("Number of passengers:");
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i <= MAX_PASSENGER_NO; i++) {
            list.add(i);
        }


        ObservableList<Integer> numPass = FXCollections.observableList(list);
        comboPassengerNo = new ComboBox<>();
        comboPassengerNo.getItems().addAll(numPass);

        gridPaneLeft.add(label, 1, 2);
        gridPaneLeft.add(comboPassengerNo, 1, 3);
        GridPane.setMargin(comboPassengerNo, new Insets(10, 0, 50, 100));

        // RHS Pane
        StackPane stackPaneRight = new StackPane();
        stackPaneRight.setPrefHeight(310);
        stackPaneRight.setMaxWidth(465);
        stackPaneRight.getStyleClass().add("stackPaneRight");

        Label labelBorder = new Label("Passenger Details");
        labelBorder.getStyleClass().add("border-title");
        StackPane.setAlignment(labelBorder, Pos.TOP_CENTER);
        stackPaneRight.getChildren().addAll(labelBorder);

        GridPane gridPaneRight = new GridPane();
        gridPaneRight.setHgap(10);
        gridPaneRight.setVgap(10);
        gridPaneRight.setPadding(new Insets(20, 0, 0, 0));


        // add fields dynamically based on combo selection
        comboPassengerNo.setOnAction(event -> {

            int numCustomersSelected = comboPassengerNo.getSelectionModel().getSelectedItem();

            tfFirstNamesList = new ArrayList<>();
            for (int i = 1; i <= numCustomersSelected; i++) {
                TextField tfFirstName = new TextField();
                tfFirstName.setPromptText("Passenger " + i + " first name");
                tfFirstNamesList.add(tfFirstName);
                gridPaneRight.add(tfFirstName, 1 , i-1);
                tfFirstName.setOnAction(e -> firstNameListener(e)); //TODO
            }

            tfLastNamesList = new ArrayList<>();
            for (int i = 1; i <= numCustomersSelected; i++) {
                TextField tfLastName = new TextField();
                tfLastName.setPromptText("Passenger " + i + " last name");
                tfLastNamesList.add(tfLastName);
                gridPaneRight.add(tfLastName, 2 , i-1);
                tfLastName.setOnAction(e -> lastNameListener(e)); //TODO
            }

            dpDateOfBirthList = new ArrayList<>();
            for (int i = 1; i <= numCustomersSelected; i++) {
                DatePicker dpDateOfBirth = new DatePicker();
                dpDateOfBirth.setPromptText("DOB");
                dpDateOfBirthList.add(dpDateOfBirth);
                gridPaneRight.add(dpDateOfBirth, 3 , i-1);
                dpDateOfBirth.getStyleClass().add("dobField");
            }

            Button clearButton = new Button("Clear");
            Label clearLabel = new Label("Reset number of passengers?");


            gridPaneLeft.add(clearLabel, 1, 4);
            gridPaneLeft.add(clearButton, 1, 5);
            GridPane.setMargin(clearButton, new Insets(10, 0, 0, 100));

            clearButton.setOnAction(e -> {
                tfFirstNamesList.clear();
                tfLastNamesList.clear();
                dpDateOfBirthList.clear();
                gridPaneRight.getChildren().clear();
                comboPassengerNo.setValue(0);

                gridPaneLeft.getChildren().remove(clearButton);
            });

        });

        StackPane.setAlignment(gridPaneRight, Pos.TOP_LEFT);
        stackPaneRight.getChildren().addAll(gridPaneRight);

        HBox hBox = new HBox();
        HBox.setHgrow(stackPaneLeft, Priority.ALWAYS);
        HBox.setHgrow(stackPaneRight, Priority.ALWAYS);
        hBox.setPadding(new Insets(10));
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(stackPaneLeft, stackPaneRight);

        return hBox;
    }


    private void firstNameListener(ActionEvent event) {
        if(event.getSource() == tfFirstNamesList.get(0)) {
            System.out.println(" 1");
        } else if(event.getSource() == tfFirstNamesList.get(1)) {
            System.out.println(" 2");
        }
    }


    private void lastNameListener(ActionEvent event) {
        if(event.getSource() == tfLastNamesList.get(0)) {
            System.out.println("1");
        } else if(event.getSource() == tfLastNamesList.get(1)) {
            System.out.println("2");
        }
    }


    private AnchorPane createAnchorPane() {
        AnchorPane anchorPane = new AnchorPane();
        buttonCancel = new Button("Cancel");
        buttonContinue = new Button("Continue");
        buttonContinue.setOnAction(event -> displaySelectedFlights());

        buttonCancel.setOnAction(event -> UtilityClass.confirmBoxCloseApp());

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
