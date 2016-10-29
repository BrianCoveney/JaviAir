import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by brian on 24/10/16.
 */


public class ConfirmBoxCloseApp {

    public static void show(String title, String message){

        Stage primarySage = new Stage();
        primarySage.initModality(Modality.APPLICATION_MODAL);
        primarySage.setTitle(title);
        primarySage.setMinWidth(250);
        Label labelMessage = new Label(message);

        Button buttonYes = new Button("Yes");
        buttonYes.setOnAction(event -> primarySage.close());
        Button buttonNo = new Button("No");

        HBox hBox = new HBox(10);
        hBox.getChildren().addAll(labelMessage, buttonNo, buttonYes);
        hBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(hBox);
        primarySage.setScene(scene);
        primarySage.showAndWait();


    }


}
