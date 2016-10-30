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
import model.Flight;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class MainScene extends Application {

    private RadioButton radioBtnReturn;
    private Button buttonCancel;
    private Button buttonContinue;
	private ComboBox<String> comboOrigin;
	private ComboBox<String> comboDestination;
    private DatePicker datePickerDeparture;
    private DatePicker datePickerReturn;
    private GridPane gridPaneMiddle;
    private final ToggleGroup toggleGroup = new ToggleGroup();
    private ImageView imageView;
    private Image image;
    private HBox hBoxImage;
    private TextArea textAreaDepart, textAreaReturn;
    private Label labelOrigin, labelDestination, labelDateDeparture, labelDateReturn;



    private static final int MAX_PASSENGER_NO = 8;
    public static final ObservableList airportList =
            FXCollections.observableArrayList();


	@Override
	public void start(Stage primaryStage) throws Exception{

        airportList.addAll("ORK","MAD","SBK","JER","CDG","STN","AGP");

        VBox vBox = new VBox();
        vBox.getChildren().addAll(
                createTopGridPane(), createMiddleGridPane(), createBottomPane(), createAnchorPane());
        Scene scene = new Scene(vBox, 800, 700);

        scene.getStylesheets().add("/style/stylesheet.css");
		primaryStage.setScene(scene);
		primaryStage.setTitle("JaviAir App");
//        primaryStage.setOnCloseRequest(event -> {
//            event.consume();
//            confirmBoxCloseApp();
//        });
		primaryStage.show();


    }


	private GridPane createTopGridPane(){

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
        comboOrigin.setOnAction(event -> checkTypedFlightMatchesList(event));

        labelDestination = new Label("To: ");
        comboDestination = new ComboBox<>();
        comboDestination.setPromptText("select airport");
        comboDestination.getItems().addAll(airportList);
        comboDestination.setEditable(true);
        comboDestination.setOnAction(event -> checkTypedFlightMatchesList(event));

        labelDateReturn = new Label("Return");
        datePickerReturn = new DatePicker();
        datePickerReturn.setPromptText("pick a date");
        datePickerReturn.setEditable(true);
        datePickerReturn.setOnAction(event -> selectDate(event));

        labelDateDeparture = new Label("Depart");
        datePickerDeparture = new DatePicker();
        datePickerDeparture.setPromptText("pick a date");
        datePickerDeparture.setEditable(true);
        datePickerDeparture.setOnAction(event -> selectDate(event));


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


        // gridPane.add(Node, colIndex, rowIndex, colSpan, rowSpan):
        gridPane.add(new Separator(), 0, 6, 3, 1);

        return gridPane;

    }

    private void checkTypedFlightMatchesList(ActionEvent event) {

        if(event.getSource() == comboOrigin){
            if(comboOrigin.getItems().contains(comboOrigin.getValue())) {
                String theFlightOrigin = comboOrigin.getValue();
                textAreaDepart.setText(theFlightOrigin + " ");
            }
            else{
                errorMessage();
            }
        }
        else if(event.getSource() == comboDestination){
            if(comboDestination.getItems().contains(comboDestination.getValue())) {
                String theFlightOrigin = comboDestination.getValue();
                textAreaReturn.setText(theFlightOrigin + " ");
            }
            else{
                errorMessage();
            }
        }
    }


    private void errorMessage(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setContentText("The text you entered does not match a flight");
        alert.showAndWait();
    }


    private void selectDate(ActionEvent event){
        if(event.getSource() == datePickerDeparture) {
            String departDate = datePickerDeparture.getValue().toString();
            textAreaDepart.appendText(departDate);
        }else if(event.getSource() == datePickerReturn) {
            String returnDate = datePickerReturn.getValue().toString();
            textAreaReturn.appendText(returnDate);
        }
    }


    // insert the airplane icons between the flight destinations, and the flight times
    private HBox insertIcon(){
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


    private GridPane createMiddleGridPane(){

        Button buttonFlightSelect = new Button("Select Flight");
        buttonFlightSelect.setOnAction(event -> addFlightsToList());

        gridPaneMiddle = new GridPane();
        gridPaneMiddle.setAlignment(Pos.CENTER);
        gridPaneMiddle.getStyleClass().add("grid");
        gridPaneMiddle.setHalignment(buttonFlightSelect, HPos.CENTER);
        gridPaneMiddle.setColumnSpan(buttonFlightSelect, 3);

        textAreaDepart = new TextArea();
        textAreaDepart.setPrefRowCount(3);
        textAreaDepart.setEditable(false);
        textAreaDepart.setMaxWidth(200);

        textAreaReturn = new TextArea();
        textAreaReturn.setPrefRowCount(3);
        textAreaReturn.setEditable(false);
        textAreaReturn.setMaxWidth(200);

        gridPaneMiddle.add(textAreaDepart, 0, 2);
        gridPaneMiddle.add(buttonFlightSelect, 0, 3);
        gridPaneMiddle.add(textAreaReturn, 2, 2);

        // .add(Node, colIndex, rowIndex, colSpan, rowSpan):
        gridPaneMiddle.add(new Separator(), 0, 6, 3, 1);

        return gridPaneMiddle;
    }


    private HBox createBottomPane(){

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

        ObservableList numPass = FXCollections.observableList(list);
        ComboBox<Integer> comboPassengerNo = new ComboBox<>();
        comboPassengerNo.getItems().addAll(numPass);


//        comboPassengerNo.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                MainScene.setReturnedValue(comboPassengerNo.getValue());
//            }
//        });



//        comboPassengerNo.setOnAction(event -> {
//            TextField textField[];
//            Integer selectedPassengerNum = comboPassengerNo.getValue();
//
//            try {
//                if (selectedPassengerNum != null) {
//
//                    textField = new TextField[selectedPassengerNum];
//
//                    for (int i = 0; i < selectedPassengerNum; i++) {
//                        textField[i] = new TextField();
//
//                    }
//
//                }
//            }catch (Exception ex){
//               ex.printStackTrace();
//            }
//
//        });




        GridPane gridPaneLeft = new GridPane();
        gridPaneLeft.getStyleClass().add("gridPaneLeft");
        gridPaneLeft.add(label, 1, 2);
        gridPaneLeft.add(comboPassengerNo, 1, 3);
        gridPaneLeft.setMargin(comboPassengerNo, new Insets(10, 0, 0, 100));

        stackPaneLeft.setAlignment(gridPaneLeft, Pos.TOP_LEFT);
        stackPaneLeft.getChildren().addAll(gridPaneLeft);


        // RHS Pane
        StackPane stackPaneRight = new StackPane();
        stackPaneRight.setPrefHeight(310);
        stackPaneRight.setMaxWidth(465);
        stackPaneRight.getStyleClass().add("stackPaneRight");

        Label labelBorder = new Label("Passenger Details");
        labelBorder.getStyleClass().add("border-title");
        stackPaneRight.setAlignment(labelBorder, Pos.TOP_CENTER);
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

//        int maxTextFieldNum = 8;
//        TextField textFieldFName[] = new TextField[maxTextFieldNum];
//        GridPane mGridPane = new GridPane();
//        VBox vBoxFNames = new VBox();
//        for (int i = 0; i < maxTextFieldNum; i++){
//            textFieldFName[i] = new TextField();
//            textFieldFName[i].getStyleClass().add("text-field");
//
//        }
//
//        vBoxFNames.getChildren().addAll(textFieldFName);
//        mGridPane.add(vBoxFNames, maxTextFieldNum, 0);


        stackPaneRight.setAlignment(gridPane, Pos.TOP_LEFT);
        stackPaneRight.getChildren().addAll(gridPane);

        HBox hBox = new HBox();
        hBox.setHgrow(stackPaneLeft, Priority.ALWAYS);
        hBox.setHgrow(stackPaneRight, Priority.ALWAYS);
        hBox.setPadding(new Insets(10));
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(stackPaneLeft, stackPaneRight);

        return hBox;
    }




    public void confirmBoxCloseApp(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Close Program");
        alert.setContentText("Do you want to close the program?");

        Optional<ButtonType> choice = alert.showAndWait();

        if (choice.isPresent() && choice.get() == ButtonType.APPLY.OK) {
            Platform.exit();
        } else if (choice.isPresent() && choice.get() == ButtonType.CANCEL) {
            alert.close();
        }

    }

    private AnchorPane createAnchorPane(){

        AnchorPane anchorPane = new AnchorPane();
        buttonCancel = new Button("Cancel");
        buttonContinue = new Button("Continue");

        buttonCancel.setOnAction(event -> {
            confirmBoxCloseApp();
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



    private void addFlightsToList(){
        String flyOut = null;
        String flyBack = null;

        if(comboOrigin.getSelectionModel().getSelectedItem() == null ||
                comboDestination.getSelectionModel().getSelectedItem() == null) {
            errorMessage();
        }else{
            flyOut = comboOrigin.getValue();
            flyBack = comboDestination.getValue();
        }

        // simple factory method implementation
        Flight mFlight = Flight.createFlight(flyOut, flyBack);
        System.out.println(mFlight);

    }


//    public static int setReturnedValue(int result){
//        return result;
//    }



	public static void main(String[] args) {
		launch(args);
	}

	
}






















