import controller.FlightController;
import javafx.application.Application;
import javafx.application.Platform;
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
import model.Flight;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class MainScene extends Application {

    private ObservableList<Flight> observableList;
    private RadioButton radioBtnReturn;
    private Button buttonCancel;
    private Button buttonContinue;
	private ComboBox<String> comboOrigin;
	private ComboBox<String> comboDestination;
    private DatePicker datePickerDeparture;
    private DatePicker datePickerReturn;
    private GridPane gridPaneMiddle;
    private ImageView imageView;
    private Image image;
    private HBox hBoxImage;
    private TextArea textAreaDepart, textAreaReturn;
    private Label labelOrigin, labelDestination, labelDateDeparture, labelDateReturn;
    private ComboBox<Integer> comboPassengerNo;
    private String dptFlight, rtnFlight;
    private double departPrice;
    private double returnPrice;
    private double flightPrice;
    private double currentPrice;
    private static final int MAX_PASSENGER_NO = 8;
    private static final ObservableList airportList = FXCollections.observableArrayList();
    private final ToggleGroup toggleGroup = new ToggleGroup();
    Flight mFlight;


	@Override
	public void start(Stage primaryStage) throws Exception
    {
        airportList.addAll("ORK","MAD","SBK","JER","CDG","STN","AGP");

        VBox vBox = new VBox();
        vBox.getChildren().addAll(
                createTopGridPane(), createMiddleGridPane(), createBottomPane(), createAnchorPane());
        Scene scene = new Scene(vBox, 800, 700);

        scene.getStylesheets().add("/style/stylesheet.css");
		primaryStage.setScene(scene);
		primaryStage.setTitle("JaviAir App");
		primaryStage.show();
    }



	private GridPane createTopGridPane()
    {

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.getStyleClass().add("grid");

        RadioButton radioBtnOneWay = new RadioButton("One Way");
        radioBtnOneWay.setToggleGroup(toggleGroup);
        radioBtnReturn = new RadioButton("Return");
        radioBtnReturn.setToggleGroup(toggleGroup);
        radioBtnReturn.fire(); // return btn on by default

        labelOrigin = new Label("From: ");
        comboOrigin = new ComboBox<>();
        comboOrigin.setEditable(true);
        comboOrigin.setPromptText("select airport");
        comboOrigin.getItems().addAll(airportList);
        comboOrigin.getStyleClass().add("comboOrigin"); // add css class
        comboOrigin.setOnAction(event -> {
            if(comboOrigin.getItems().contains(comboOrigin.getValue()))
            {
//                textAreaDepart.setText(comboOrigin.getValue().toString());
                getSelectedFlightPrice();
            }
            else{
                errorMessage();
            }
        });

        labelDestination = new Label("To: ");
        comboDestination = new ComboBox<>();
        comboDestination.setPromptText("select airport");
        comboDestination.getItems().addAll(airportList);
        comboDestination.setEditable(true);

        comboDestination.setOnAction(event -> {

            if(comboDestination.getItems().contains(comboDestination.getValue()))
            {
//                textAreaReturn.setText(comboDestination.getValue().toString());
                getSelectedFlightPrice();
            }
            else{
                errorMessage();
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
            if(newValue.length() > 3){
                comboOrigin.setId("error");
            }
            else {
                comboOrigin.setId("default");
            }
        });


        gridPane.add(radioBtnOneWay, 0, 1); gridPane.add(radioBtnReturn, 2, 1);
        gridPane.add(labelOrigin, 0, 2); gridPane.add(labelDestination, 2, 2);
        gridPane.add(comboOrigin, 0, 3); gridPane.add(insertIcon(), 1, 3); gridPane.add(comboDestination, 2, 3);
        gridPane.add(labelDateDeparture, 0, 4); gridPane.add(labelDateReturn, 2, 4);
        gridPane.add(datePickerDeparture, 0, 5); gridPane.add(insertIcon(), 1, 5); gridPane.add(datePickerReturn, 2, 5);
        gridPane.add(btnFlightSelect, 1, 7);

        // gridPane.add(Node, colIndex, rowIndex, colSpan, rowSpan):
        gridPane.add(new Separator(), 0, 8, 3, 1);

        return gridPane;

    }




    private Double getSelectedFlightPrice()
    {
        dptFlight = comboOrigin.getSelectionModel().getSelectedItem();
        rtnFlight = comboDestination.getSelectionModel().getSelectedItem();
        Callback<DatePicker, DateCell> monthCellFactory = this.getMonthCellFactory();


        if( dptFlight == "ORK" && rtnFlight == "MAD" || dptFlight == "MAD" && rtnFlight == "ORK" ){
            flightPrice =  100;
        }
        else if( dptFlight == "ORK" && rtnFlight == "SBK" || dptFlight == "SBK" && rtnFlight == "ORK" ){ flightPrice = 100; }
        else if( dptFlight == "ORK" && rtnFlight == "JER" || dptFlight == "JER" && rtnFlight == "ORK" ){ flightPrice = 120; }
        else if( dptFlight == "ORK" && rtnFlight == "CDG" || dptFlight == "CDG" && rtnFlight == "ORK" ){ flightPrice = 80; }
        else if( dptFlight == "ORK" && rtnFlight == "STN" || dptFlight == "STN" && rtnFlight == "ORK" ){ flightPrice = 40; }
        else if( dptFlight == "ORK" && rtnFlight == "AGP" || dptFlight == "AGP" && rtnFlight == "ORK" ){ flightPrice = 240; }

        else if( dptFlight == "MAD" && rtnFlight == "SBK" || dptFlight == "SBK" && rtnFlight == "MAD" ){ flightPrice =  200; }
        else if( dptFlight == "MAD" && rtnFlight == "JER" || dptFlight == "JER" && rtnFlight == "MAD" ){ flightPrice = 200; }
        else if( dptFlight == "MAD" && rtnFlight == "CDG" || dptFlight == "CDG" && rtnFlight == "MAD" ){ flightPrice = 60; }
        else if( dptFlight == "MAD" && rtnFlight == "STN" || dptFlight == "STN" && rtnFlight == "MAD" ){ flightPrice = 60; }
        else if( dptFlight == "MAD" && rtnFlight == "AGP" || dptFlight == "AGP" && rtnFlight == "MAD" ){ flightPrice = 60; }

        else if( dptFlight == "SBK" && rtnFlight == "JER" ){ flightPrice =  0; }
        else if( dptFlight == "SBK" && rtnFlight == "CDG" || dptFlight == "CDG" && rtnFlight == "SBK" ){ flightPrice = 150; }
        else if( dptFlight == "SBK" && rtnFlight == "AGP" || dptFlight == "AGP" && rtnFlight == "SBK" ){ flightPrice = 140; }

        else if( dptFlight == "JER" && rtnFlight == "CDG" || dptFlight == "CDG" && rtnFlight == "JER" ){ flightPrice =  250; }
        else if( dptFlight == "JER" && rtnFlight == "STN" || dptFlight == "STN" && rtnFlight == "JER" ){ flightPrice = 250; }
        else if( dptFlight == "JER" && rtnFlight == "AGP" || dptFlight == "AGP" && rtnFlight == "JER" ){ flightPrice = 280; }

        else if( dptFlight == "CDG" && rtnFlight == "AGP" || dptFlight == "AGP" && rtnFlight == "CDG" ){ flightPrice = 100; }
        else if( dptFlight == "STN" && rtnFlight == "AGP" || dptFlight == "AGP" && rtnFlight == "STN" ){ flightPrice =  120; }


        else if( dptFlight == "CDG" && rtnFlight == "STN" || dptFlight == "STN" && rtnFlight == "CDG" )
        {
            // Disable April
            datePickerDeparture.setDayCellFactory(monthCellFactory);
            datePickerReturn.setDayCellFactory(monthCellFactory);

            textAreaDepart.setText(mFlight.toString());

            flightPrice =  60;
        }


        else if( dptFlight == "SBK" && rtnFlight == "STN" || dptFlight == "STN" && rtnFlight == "SBK" )
        {
            // Disable March and April
            datePickerDeparture.setDayCellFactory(monthCellFactory);
            datePickerReturn.setDayCellFactory(monthCellFactory);

            flightPrice = 80;
        }

        return flightPrice;
    }


    // Disable month of April
    Callback<DatePicker, DateCell> getMonthCellFactory (){

        final Callback<DatePicker, DateCell> monthCellFactory = new Callback<DatePicker, DateCell>() {

            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                        if( dptFlight == "SBK" && rtnFlight == "STN" || dptFlight == "STN" && rtnFlight == "SBK" )
                        {
                            // Disable March and April
                            if ( item.getMonth() == Month.APRIL || item.getMonth() == Month.MARCH )
                            {
                                setDisable(true);
                                setStyle("-fx-background-color: #ffc0cb;");
                            }
                        }
                        else if ( dptFlight == "CDG" && rtnFlight == "STN" || dptFlight == "STN" && rtnFlight == "CDG" )
                        {
                            // Disable April
                            if ( item.getMonth() == Month.APRIL )
                            {
                                setDisable(true);
                                setStyle("-fx-background-color: #ffc0cb;");
                            }
                        }
                    }
                };
            }
        };
        return monthCellFactory;
    }







    // take the returned flightPrice from getSelectedFlight() and add 20% if day is Fri - Sun
    private Double getSelectDate(ActionEvent event)
    {
        if (event.getSource() == datePickerDeparture)
        {
            LocalDate departDate = datePickerDeparture.getValue();
            String theDepartDay = departDate.getDayOfWeek().name();
            if (theDepartDay.equals("FRIDAY") || theDepartDay.equals("SATURDAY") || theDepartDay.equals("SUNDAY"))
            {
                departPrice = flightPrice + flightPrice * 0.2;
            }
            else
            {
                departPrice = flightPrice;
            }

        }
        else if(event.getSource() == datePickerReturn)
        {
            LocalDate returnDate = datePickerReturn.getValue();
            String theReturnDay = returnDate.getDayOfWeek().name();
            if(theReturnDay.equals("FRIDAY") || theReturnDay.equals("SATURDAY") || theReturnDay.equals("SUNDAY"))
            {
                returnPrice = flightPrice + flightPrice * 0.2;
            }
            else
            {
                returnPrice = flightPrice;
            }
        }

        currentPrice = departPrice + returnPrice;

        return currentPrice;
    }


    private void displaySelectedFlights()
    {
        String flyOut = comboOrigin.getSelectionModel().getSelectedItem();
        String flyBack = comboDestination.getSelectionModel().getSelectedItem();
        String finalPrice = String.valueOf(currentPrice);

        Flight mFlight = new Flight(flyOut, flyBack, finalPrice);
        FlightController.getInstance().addFlight(mFlight);

        textAreaDepart.setText( flyOut +" > " +  flyBack + "\t€" + departPrice );
        textAreaReturn.setText( flyOut +" > " +  flyBack + "\t€" + returnPrice );
        textAreaReturn.appendText("\n\nTotal: " + currentPrice);


        if(flyOut == "ORK" && flyBack == "MAD"){ textAreaDepart.appendText("\n0920-1300");}
        else if( flyOut == "ORK" && flyBack == "SBK"){ textAreaDepart.appendText("\n1030-1400"); }
        else if( flyOut == "ORK" && flyBack == "SBK"){ textAreaDepart.appendText("\n1030-1400"); }
        else if( flyOut == "ORK" && flyBack == "CDG"){ textAreaDepart.appendText("\n0920-1215"); textAreaReturn.appendText("\n1820-2105"); }
    }


    private void errorMessage()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setContentText("The text you entered does not match a flight");
        alert.showAndWait();
    }


    // insert the airplane icons between the flight destinations, and the flight times
    private HBox insertIcon()
    {
        image = new Image("resources/airplane.png");
        imageView = new ImageView(image);
        imageView.setImage(image);
        imageView.setFitWidth(30);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);
        imageView.getStyleClass().add("imageView");

        hBoxImage = new HBox();
        hBoxImage.getChildren().add(imageView);

        return hBoxImage;
    }



    private GridPane createMiddleGridPane()
    {

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


    private HBox createBottomPane()
    {
        // LHS Pane
        StackPane stackPaneLeft = new StackPane();
        stackPaneLeft.setPrefHeight(300);
        stackPaneLeft.setMaxWidth(200);
        stackPaneLeft.getStyleClass().add("stackPaneLeft");

        Label label = new Label("Number of passengers:");
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i <= MAX_PASSENGER_NO; i++ ) {
            list.add(i);
        }

        ObservableList<Integer> numPass = FXCollections.observableList(list);
        comboPassengerNo = new ComboBox<>();
        comboPassengerNo.getItems().addAll(numPass);

        comboPassengerNo.setOnAction(event -> setReturnedValue());


        GridPane gridPaneLeft = new GridPane();
        gridPaneLeft.getStyleClass().add("gridPaneLeft");
        gridPaneLeft.add(label, 1, 2);
        gridPaneLeft.add(comboPassengerNo, 1, 3);
        GridPane.setMargin(comboPassengerNo, new Insets(10, 0, 0, 100));

        StackPane.setAlignment(gridPaneLeft, Pos.TOP_LEFT);
        stackPaneLeft.getChildren().addAll(gridPaneLeft);


        // RHS Pane
        StackPane stackPaneRight = new StackPane();
        stackPaneRight.setPrefHeight(310);
        stackPaneRight.setMaxWidth(465);
        stackPaneRight.getStyleClass().add("stackPaneRight");

        Label labelBorder = new Label("Passenger Details");
        labelBorder.getStyleClass().add("border-title");
        StackPane.setAlignment(labelBorder, Pos.TOP_CENTER);
        stackPaneRight.getChildren().addAll(labelBorder);

        TextField fName = new TextField();
        fName.setPromptText("enter first name");
        TextField lName = new TextField();
        lName.setPromptText("enter last name");
        TextField fName2 = new TextField(); TextField lName2 = new TextField();
        TextField fName3 = new TextField();TextField lName3 = new TextField();
        TextField fName4 = new TextField(); TextField lName4 = new TextField();
        TextField fName5 = new TextField(); TextField lName5 = new TextField();
        TextField fName6 = new TextField(); TextField lName6 = new TextField();
        TextField fName7 = new TextField(); TextField lName7 = new TextField();
        TextField fName8 = new TextField(); TextField lName8 = new TextField();
        TextField dob = new TextField(); dob.setPromptText("17/09/77"); dob.getStyleClass().add("dobField");
        TextField dob2= new TextField(); dob2.getStyleClass().add("dobField");
        TextField dob3 = new TextField(); dob3.getStyleClass().add("dobField");
        TextField dob4 = new TextField(); dob4.getStyleClass().add("dobField");
        TextField dob5 = new TextField(); dob5.getStyleClass().add("dobField");
        TextField dob6 = new TextField(); dob6.getStyleClass().add("dobField");
        TextField dob7 = new TextField(); dob7.getStyleClass().add("dobField");
        TextField dob8 = new TextField(); dob8.getStyleClass().add("dobField");


        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 0, 0, 0));
        gridPane.add(fName , 1, 0); gridPane.add(lName, 2, 0); gridPane.add(dob, 3, 0);
        gridPane.add(fName2, 1, 1); gridPane.add(lName2, 2, 1); gridPane.add(dob2, 3, 1);
        gridPane.add(fName3 , 1, 2); gridPane.add(lName3, 2, 2); gridPane.add(dob3, 3, 2);
        gridPane.add(fName4, 1, 3); gridPane.add(lName4, 2, 3); gridPane.add(dob4, 3, 3);
        gridPane.add(fName5, 1, 4); gridPane.add(lName5, 2, 4); gridPane.add(dob5, 3, 4);
        gridPane.add(fName6, 1, 5); gridPane.add(lName6, 2, 5); gridPane.add(dob6, 3, 5);
        gridPane.add(fName7, 1, 6); gridPane.add(lName7, 2, 6); gridPane.add(dob7, 3, 6);
        gridPane.add(fName8, 1, 7); gridPane.add(lName8, 2, 7); gridPane.add(dob8, 3, 7);


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

    private int setReturnedValue()
    {
        int result = comboPassengerNo.getValue();
        System.out.println(result);
        return result;
    }


    private void confirmBoxCloseApp()
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Close Program");
        alert.setContentText("Do you want to close the program?");

        Optional<ButtonType> choice = alert.showAndWait();

        if (choice.isPresent() && choice.get() == ButtonType.OK)
        {
            Platform.exit();
        }
        else if (choice.isPresent() && choice.get() == ButtonType.CANCEL)
        {
            alert.close();
        }

    }

    private AnchorPane createAnchorPane()
    {
        AnchorPane anchorPane = new AnchorPane();
        buttonCancel = new Button("Cancel");
        buttonContinue = new Button("Continue");
        buttonContinue.setOnAction(event ->  displaySelectedFlights());

        buttonCancel.setOnAction(event -> confirmBoxCloseApp());

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






















