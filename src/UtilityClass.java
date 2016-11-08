import javafx.scene.control.Alert;

/**
 * Created by brian on 08/11/16.
 */
public final class UtilityClass {

    private UtilityClass() {
        // constructor not called
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
}
