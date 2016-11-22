import javafx.scene.control.RadioButton;

import java.time.LocalDate;

/**
 * Created by brian on 19/11/16.
 */
public class PassengerAdult extends Passenger {

    public PassengerAdult(String firstName, String lastName, LocalDate dateOfBirth, RadioButton baggageSelect) {
        super(firstName, lastName, dateOfBirth, baggageSelect);

    }
}
