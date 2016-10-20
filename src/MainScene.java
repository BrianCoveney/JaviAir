import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainScene extends Application {

	private ComboBox<String> comboFlightOrigin;
	private ComboBox<String> comboFlightDestination;
	private Label labelOrigin;
	private Label labelDestination;
    private Label labelDateDeparture;
    private Label labelDateArrival;
    private DatePicker datePickerDeparture;
    private DatePicker datePickerArrival;

    public static final String[] AIRPORT_LIST = {"ORK","MAD","SBK","JER","CDG","STN","AGP"};


	@Override
	public void start(Stage primaryStage) throws Exception{

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(5, 100, 5, 5));
        gridPane.setHgap(100);
        gridPane.setVgap(10);

        labelOrigin = new Label("Origin: ");
        comboFlightOrigin = new ComboBox<>();
        comboFlightOrigin.getItems().addAll(AIRPORT_LIST);
        comboFlightOrigin.getStyleClass().add("comboOrigin"); // add css class


        labelDestination = new Label("Destination: ");
        comboFlightDestination = new ComboBox<>();
        comboFlightDestination.getItems().addAll(AIRPORT_LIST);

        labelDateArrival = new Label("Arriving");
        datePickerArrival = new DatePicker();
        datePickerArrival.setEditable(true);

        labelDateDeparture = new Label("Departing");
        datePickerDeparture = new DatePicker();
        datePickerDeparture.setEditable(true);

        gridPane.add(labelOrigin, 0, 0);
        gridPane.add(comboFlightOrigin, 0, 1);
        gridPane.add(labelDestination, 1, 0);
        gridPane.add(comboFlightDestination, 1, 1);

        gridPane.add(labelDateArrival, 0, 3);
        gridPane.add(datePickerArrival, 0, 4);
        gridPane.add(labelDateDeparture, 1, 3);
        gridPane.add(datePickerDeparture, 1, 4);

        Scene scene = new Scene(gridPane, 800, 650);
        scene.getStylesheets().add("/style/stylesheet.css");
		primaryStage.setScene(scene);
		primaryStage.setTitle("JaviAir App");
		primaryStage.show();
	}




	public static void main(String[] args) {
		launch(args);
	}
	
	
}






















