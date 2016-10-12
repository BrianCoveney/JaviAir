import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainScene extends Application {

	
	private static final String COMPANY_NAME = "JAVIAIR";
	private Text textLogo;
	private Text textInfo;
	private ComboBox<String> comboFlightOrigin;
	private ComboBox<String> comboFlightDestination;
	private Label labelOrigin;
	private Label labelDestination; 
	
	@Override
	public void start(Stage primaryStage){
		BorderPane borderPane = new BorderPane();
		borderPane.setTop(createTopPane());
		borderPane.setLeft(createLeftPane());
		borderPane.setCenter(createCenterPane());
		
		Scene scene = new Scene(borderPane, 800, 500);

		primaryStage.setScene(scene);
		primaryStage.setTitle("JaviAir App");
		primaryStage.show();
	}
	
	
	public Node createTopPane(){
		textLogo = new Text(COMPANY_NAME);
		textLogo.setStyle(
				"-fx-fill: white; "
				+ "-fx-font-size: 24px;"
				+ "-fx-font-weight: bold;"
				+ "-fx-stroke: black;");
			
		
		
		
		HBox topHBox = new HBox();
		topHBox.getChildren().add(textLogo);
		topHBox.setStyle("-fx-background-color: yellow");
		topHBox.setPadding(new Insets(10,0,10,10));
		
		return topHBox;
	}
	

	public Node createLeftPane(){
		textInfo = new Text("I am in left position and I am wrapping "
				+ "to the next line when there is no space");
		
		textInfo.setWrappingWidth(100);
		textInfo.setStyle("-fx-fill: white");
		

		
		
		HBox leftHBox = new HBox();
		leftHBox.getChildren().add(textInfo);
		leftHBox.setStyle("-fx-background-color: blue");
		leftHBox.setAlignment(Pos.CENTER);
		
		return leftHBox;
	}
	
	
	public Node createCenterPane(){
		
		String[] airportList = new String[]{"ORK","MAD","SBK","JER","CDG","STN","AGP"};
		
		labelOrigin = new Label("Origin: ");
		comboFlightOrigin = new ComboBox<>();
		comboFlightOrigin.getItems().addAll(airportList);
		
		labelDestination = new Label("Destination: ");
		comboFlightDestination = new ComboBox<>();
		comboFlightDestination.getItems().addAll(airportList);
	
		
		
	
		
		HBox centerHBox = new HBox();
		centerHBox.getChildren().addAll(labelOrigin, comboFlightOrigin, labelDestination, comboFlightDestination);
		centerHBox.setStyle("-fx-background-color: orange");
		HBox.setMargin(comboFlightOrigin, new Insets( 0, 100, 0, 0));
		centerHBox.setAlignment(Pos.CENTER);
		
		return centerHBox;
	}
	

	public static void main(String[] args) {
		launch(args);
	}
	
	
}






















