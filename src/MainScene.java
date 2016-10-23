import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private TextArea textArea;
    private ListView listView;

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
        comboOrigin.getItems().addAll(airportList);
        comboOrigin.getStyleClass().add("comboOrigin"); // add css class

        labelDestination = new Label("To: ");
        comboDestination = new ComboBox<>();
        comboDestination.getItems().addAll(airportList);
        comboDestination.setEditable(true);

        labelDateReturn = new Label("Return");
        datePickerReturn = new DatePicker();
        datePickerReturn.setPromptText("pick a date");
        datePickerReturn.setEditable(true);

        labelDateDeparture = new Label("Depart");
        datePickerDeparture = new DatePicker();
        datePickerDeparture.setPromptText("pick a date");
        datePickerDeparture.setEditable(true);

        // add listener to combo which inserts the flight selection, into TextView
        comboOrigin.setOnAction(event -> selectedFlight());


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

    // method from the listener for comboOrigin
    private void selectedFlight() {
        textArea.setText(comboOrigin.getSelectionModel().getSelectedItem());
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

        textArea = new TextArea();
        textArea.setPrefColumnCount(3);
        textArea.setPrefRowCount(3);
        textArea.setEditable(false);
        GridPane.setColumnSpan(textArea, 3);
        GridPane.setRowSpan(textArea, 2);

        listView = new ListView(airportList);
        listView.setPrefHeight(100);
        listView.setPrefWidth(150);

        gridPaneMiddle.add(textArea, 0, 0);
        gridPaneMiddle.add(listView, 4, 0);

        return gridPaneMiddle;
    }



	public static void main(String[] args) {
		launch(args);
	}

	
}






















