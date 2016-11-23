import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Created by brian on 11/11/16.
 */
public class Passenger {

    private String firstName;
    private String lastName;
    private boolean baggageSelect;
    private LocalDate dateOfBirth;
    private ArrayList<Passenger> passengers;


    public Passenger(String firstName, String lastName, LocalDate dateOfBirth, boolean baggageSelect) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.baggageSelect = baggageSelect;
    }

    public boolean getBaggageSelect() {
        return baggageSelect;
    }

    public void setBaggageSelect(boolean baggageSelect) {
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
        String bagCost = "";
        LocalDate date = this.dateOfBirth;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDob = date.format(formatter);

        if(this.baggageSelect == true) {
            bagCost = "€60";
        } else {
            bagCost = "€0";
        }

        return  " Details:"+
                "\n\n\tFirst name:\t " + this.firstName +
                "\n" +
                "\tLast name:\t " + this.lastName +
                "\n" +
                "\tDate of birth:\t " + formattedDob +
                "\n\t" + "Baggage: \t" + bagCost;
    }

}
