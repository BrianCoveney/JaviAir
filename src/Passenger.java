import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by brian on 11/11/16.
 */
public class Passenger {

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private boolean baggageSelect;
    private double baggagePrice;

    public Passenger(String firstName, String lastName, LocalDate dateOfBirth, boolean baggageSelect) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.baggageSelect = baggageSelect;

    }

    public Passenger(String firstName, String lastName, LocalDate dateOfBirth, boolean baggageSelect, double baggagePrice) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.baggageSelect = baggageSelect;
        this.baggagePrice = baggagePrice;
    }

    public boolean isBaggageSelect() {
        return baggageSelect;
    }

    public double getBaggagePrice() {
        return baggagePrice;
    }

    public void setBaggagePrice(double baggagePrice) {
        this.baggagePrice = baggagePrice;
    }

    public double addBaggagePrice(){

        double bagPrice = 0.0;

        if(isBaggageSelect() == true) {
            bagPrice = bagPrice + 30;
        }
        return bagPrice;
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
                "\tDate of birth:\t " + formattedDob +
                "\n\t" + "Baggage: \t" + addBaggagePrice();
    }


    public double toStringPrice() {

        double bagPrice = 0.0;

        if(isBaggageSelect() == true) {
            bagPrice = bagPrice + 30;
        }
        return bagPrice;

    }

}
