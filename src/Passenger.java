import javafx.scene.control.RadioButton;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Created by brian on 11/11/16.
 */
public class Passenger {

    private String firstName;
    private String lastName;
    private RadioButton baggageSelect;
    private LocalDate dateOfBirth;
    private ArrayList<Passenger> passengers;


    public Passenger(String firstName, String lastName, LocalDate dateOfBirth, RadioButton baggageSelect) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.baggageSelect = baggageSelect;
    }

    public RadioButton getBaggageSelect() {
        return baggageSelect;
    }

    public void setBaggageSelect(RadioButton baggageSelect) {
        this.baggageSelect = baggageSelect;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public ArrayList<Passenger> getPassengers() {
        return this.passengers;
    }


    @Override
    public String toString() {

        LocalDate date = this.dateOfBirth;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDob = date.format(formatter);

        return  " Details:"+
                "\n\n\tFirst name:\t " + this.firstName +
                "\n" +
                "\tLast name:\t " + this.lastName +
                "\n" +
                "\tDate of birth:\t " + formattedDob;
    }
}
