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
import model.Flight;

import java.util.ArrayList;
import java.util.List;


public class MainScene extends Application {

    private RadioButton radioBtnReturn;
    private Button buttonFlightSelect, buttonCancel, buttonContinue;
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
    private Label labelOrigin, labelDestination, labelDateDeparture, labelDateReturn,
            labelFName1, labelLName1, labelDOB1, labelFName2, labelLName2, labelDOB2,
            labelFName3, labelLName3, labelDOB3, labelFName4, labelLName4, labelDOB4,
            labelFName5, labelLName5, labelDOB5, labelFName6, labelLName6, labelDOB6,
            labelFName7, labelLName7, labelDOB7, labelFName8, labelLName8, labelDOB8;


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

        buttonFlightSelect = new Button("Select Flight");
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
        stackPaneLeft.setPrefHeight(275);
        stackPaneLeft.setMaxWidth(300);
        stackPaneLeft.getStyleClass().add("stackPaneLeft");

        Label label = new Label("Number of passengers:");
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i <= 8; i++ ){
            list.add(i);
        }
        ObservableList numPass = FXCollections.observableList(list);
        ComboBox comboBags = new ComboBox<>();
        comboBags.getItems().addAll(numPass);

        GridPane gridPaneLeft = new GridPane();
        gridPaneLeft.getStyleClass().add("gridPaneLeft");
        gridPaneLeft.add(label, 1, 2);
        gridPaneLeft.add(comboBags, 2, 4);

        stackPaneLeft.setAlignment(gridPaneLeft, Pos.TOP_LEFT);
        stackPaneLeft.getChildren().addAll(gridPaneLeft);


        // RHS Pane
        StackPane stackPaneRight = new StackPane();
        stackPaneRight.setPrefHeight(275);
        stackPaneRight.setMaxWidth(300);
        stackPaneRight.getStyleClass().add("stackPaneRight");

        Label labelBorder = new Label("Passenger Details");
        labelBorder.getStyleClass().add("border-title");
        stackPaneRight.setAlignment(labelBorder, Pos.TOP_CENTER);
        stackPaneRight.getChildren().addAll(labelBorder);

        // dummy text hard coded in, for now
        Label fName = new Label("Brian");
        Label lName = new Label("Coveney");
        Label dob= new Label("17/09/77");
        Label fName2 = new Label("Marie");
        Label lName2 = new Label("Coveney");
        Label dob2= new Label("17/09/55");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 0, 0, 0));
        gridPane.add(fName, 1, 0);
        gridPane.add(lName, 2, 0);
        gridPane.add(dob, 3, 0);
        gridPane.add(fName2, 1, 1);
        gridPane.add(lName2, 2, 1);
        gridPane.add(dob2, 3, 1);


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


    private AnchorPane createAnchorPane(){

        AnchorPane anchorPane = new AnchorPane();
        buttonCancel = new Button("Cancel");
        buttonContinue = new Button("Continue");

        buttonCancel.setOnAction(event -> {
            System.exit(0);
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

        // singleton implementation
//        FlightController.getInstance().addFlight(flyOut, flyBack);
//        System.out.println(FlightController.getInstance().getFlights());

    }


	public static void main(String[] args) {
		launch(args);
	}

	
}






















