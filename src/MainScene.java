import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainScene extends Application {

    private RadioButton radioBtnOneWay;
    private RadioButton radioBtnReturn;
	private ComboBox<String> comboOrigin;
	private ComboBox<String> comboDestination;
	private Label labelOrigin, labelDestination, labelDateDeparture, labelDateReturn;
    private DatePicker datePickerDeparture;
    private DatePicker datePickerReturn;
    private GridPane gridPane, gridPaneMiddle;
    private final ToggleGroup toggleGroup = new ToggleGroup();
    private ImageView imageView;
    private Image image;
    private HBox hBoxImage;
    private TextArea textAreaDepart, textAreaReturn;

    public static final ObservableList airportList =
            FXCollections.observableArrayList();


	@Override
	public void start(Stage primaryStage) throws Exception{

        airportList.addAll("ORK","MAD","SBK","JER","CDG","STN","AGP");

        VBox vBox = new VBox();
        vBox.getChildren().addAll(
                createTopGridPane(), createMiddleGridPane());

        Scene scene = new Scene(vBox, 900, 650);
        scene.getStylesheets().add("/style/stylesheet.css");
		primaryStage.setScene(scene);
		primaryStage.setTitle("JaviAir App");
		primaryStage.show();
	}


	public Node createTopGridPane(){

        gridPane = new GridPane();
        gridPane.setPadding(new Insets(5, 10, 5, 100));
        gridPane.getStyleClass().add("grid");

        radioBtnOneWay = new RadioButton("One Way");
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
        comboOrigin.setOnAction(event -> selectedFlight(event));

        labelDestination = new Label("To: ");
        comboDestination = new ComboBox<>();
        comboDestination.setPromptText("select airport");
        comboDestination.getItems().addAll(airportList);
        comboDestination.setEditable(true);
        comboDestination.setOnAction(event -> selectedFlight(event));

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


        comboOrigin.getEditor().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(newValue.length() > 3){
                    comboOrigin.setId("error");
                } else {
                    comboOrigin.setId("default");
                }
            }
        });


        gridPane.add(radioBtnOneWay, 0, 0); gridPane.add(radioBtnReturn, 2, 0);
        gridPane.add(labelOrigin, 0, 1); gridPane.add(labelDestination, 2, 1);
        gridPane.add(comboOrigin, 0, 2); gridPane.add(insertIcon(), 1, 2); gridPane.add(comboDestination, 2, 2);
        gridPane.add(labelDateDeparture, 0, 3); gridPane.add(labelDateReturn, 2, 3);
        gridPane.add(datePickerDeparture, 0, 4); gridPane.add(insertIcon(), 1, 4); gridPane.add(datePickerReturn, 2, 4);


        // gridPane.add(Node, colIndex, rowIndex, colSpan, rowSpan):
        gridPane.add(new Separator(), 0, 6, 3, 1);

        return gridPane;

    }

    private void selectedFlight(ActionEvent event) {
        if(event.getSource() == comboOrigin){
            if(!comboOrigin.getItems().contains(comboOrigin.getValue())) {
                errorMessage();
            }else{
                String theFlightOrigin = comboOrigin.getValue();
                textAreaDepart.setText(theFlightOrigin + " ");
            }
        }
        else if(event.getSource() == comboDestination){
            if(!comboDestination.getItems().contains(comboDestination.getValue())) {
                errorMessage();
            }else{
                String theFlightOrigin = comboDestination.getValue();
                textAreaReturn.setText(theFlightOrigin + " ");
            }
        }
    }


    public void errorMessage(){
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
    public Node insertIcon(){
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


    public Node createMiddleGridPane(){

        gridPaneMiddle = new GridPane();
        gridPaneMiddle.setPadding(new Insets(5, 10, 5, 100));
        gridPaneMiddle.getStyleClass().add("grid");

        textAreaDepart = new TextArea();
        textAreaDepart.setPrefColumnCount(3);
        textAreaDepart.setPrefRowCount(3);
        textAreaDepart.setEditable(false);
        GridPane.setColumnSpan(textAreaDepart, 3);
        GridPane.setRowSpan(textAreaDepart, 2);

        textAreaReturn = new TextArea();
        textAreaReturn.setPrefColumnCount(3);
        textAreaReturn.setPrefRowCount(3);
        textAreaReturn.setEditable(false);
        GridPane.setColumnSpan(textAreaReturn, 3);
        GridPane.setRowSpan(textAreaReturn, 2);

        gridPaneMiddle.add(textAreaDepart, 0, 0);
        gridPaneMiddle.add(textAreaReturn, 4, 0);

        return gridPaneMiddle;
    }



	public static void main(String[] args) {
		launch(args);
	}

	
}






















