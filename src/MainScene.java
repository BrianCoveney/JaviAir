import javafx.application.Application;
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
	private Label labelOrigin, labelDestination, labelDateDeparture, labelDateArrival;
    private DatePicker datePickerDeparture;
    private DatePicker datePickerArrival;
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

        Label lb = new Label("this here is text");

        VBox topVBox = new VBox();
        topVBox.getChildren().addAll(
                createTopGridPane(), createMiddleGridPane());

        HBox middleHBox = new HBox();
        middleHBox.getChildren().add(lb);

        Scene scene = new Scene(topVBox, 900, 650);
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


        labelOrigin = new Label("Origin: ");
        comboOrigin = new ComboBox<>();
        comboOrigin.setEditable(true);
        comboOrigin.getItems().addAll(airportList);
        comboOrigin.getStyleClass().add("comboOrigin"); // add css class

        labelDestination = new Label("Destination: ");
        comboDestination = new ComboBox<>();
        comboDestination.getItems().addAll(airportList);
        comboDestination.setEditable(true);

        labelDateArrival = new Label("Arriving");
        datePickerArrival = new DatePicker();
        datePickerArrival.setPromptText("pick a date");
        datePickerArrival.setEditable(true);

        labelDateDeparture = new Label("Departing");
        datePickerDeparture = new DatePicker();
        datePickerDeparture.setPromptText("pick a date");
        datePickerDeparture.setEditable(true);


        gridPane.add(radioBtnOneWay, 0, 0); gridPane.add(radioBtnReturn, 2, 0);
        gridPane.add(labelOrigin, 0, 1); gridPane.add(labelDestination, 2, 1);
        gridPane.add(comboOrigin, 0, 2); gridPane.add(insertIcon(), 1, 2); gridPane.add(comboDestination, 2, 2);
        gridPane.add(labelDateArrival, 0, 3); gridPane.add(labelDateDeparture, 2, 3);
        gridPane.add(datePickerArrival, 0, 4); gridPane.add(insertIcon(), 1, 4); gridPane.add(datePickerDeparture, 2, 4);


        // gridPane.add(Node, colIndex, rowIndex, colSpan, rowSpan):
        gridPane.add(new Separator(), 0, 6, 3, 1);

        return gridPane;

    }

    // insert the airplane icons between flight destinations and flight times
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
        textArea.setText(airportList.toString());
        textArea.setPrefColumnCount(3);
        textArea.setPrefRowCount(3);
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






















