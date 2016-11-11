import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.Optional;

/**
 * Created by brian on 08/11/16.
 */
public final class UtilityClass {


    private UtilityClass() {
        // constructor not called
    }


    // insert the airplane icons between the flight destinations, and the flight times
    public static HBox insertIcon() {
        Image image = new Image("resources/airplane.png");

        ImageView imageView = new ImageView(image);
        imageView.setImage(image);
        imageView.setFitWidth(30);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);
        imageView.getStyleClass().add("imageView");

        HBox hBoxImage = new HBox();
        hBoxImage.getChildren().add(imageView);

        return hBoxImage;
    }


    // user clicks continue without selecting a flight
    public static void errorMessageFlight() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setContentText("You need to select a flight first");
        alert.showAndWait();
    }

    // user clicks continue without selecting a flight
    public static void errorMessageDate() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setContentText("You need to select a date");
        alert.showAndWait();
    }


    // user enters incorrect format
    public static void errorMessageInput() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setContentText("The text you entered does not match a flight");
        alert.showAndWait();
    }

    // user enters incorrect format
    public static void errorMessageAddCustomer() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setContentText("No passenger details entered");
        alert.showAndWait();
    }


    public static void confirmBoxCloseApp() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Close Program");
        alert.setContentText("Do you want to close the program?");

        Optional<ButtonType> choice = alert.showAndWait();

        if (choice.isPresent() && choice.get().equals(ButtonType.OK)) {
            Platform.exit();
        } else if (choice.isPresent() && choice.get().equals(ButtonType.CANCEL)) {
            alert.close();
        }

    }
}
