import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by brian on 11/11/16.
 */
public class Passenger {

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private boolean baggageSelected;
    private double baggagePrice;
    private static int counter;
    private boolean isChild;
    private boolean isAdult;
    private boolean isMinor;


    public Passenger(String firstName, String lastName, LocalDate dateOfBirth, boolean baggageSelect) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.baggageSelected = baggageSelect;


    }

    public Passenger(String firstName, String lastName, LocalDate dateOfBirth, boolean baggageSelect, double baggagePrice) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.baggageSelected = baggageSelect;
        this.baggagePrice = baggagePrice;
    }


    public boolean isBaggageSelected() {
        return baggageSelected;
    }

    public double getBaggagePrice() {
        return baggagePrice;
    }

    public void setBaggagePrice(double baggagePrice) {
        this.baggagePrice = baggagePrice;
    }


    public double setBaggagePriceSingle() {

        double bagPrice = 0.0;

        if(isBaggageSelected() == true) {
            bagPrice = bagPrice + 15;
        }
        return bagPrice;
    }


    public double setBaggagePriceReturn() {

        double bagPrice = 0.0;

        if(isBaggageSelected() == true) {
            bagPrice = bagPrice + 30;
        }
        return bagPrice;
    }


    public boolean isPassengerUnder5() {

        isChild = false;

        if(this.getDateOfBirth().isAfter(LocalDate.now().minusYears(5))){
            isChild = true;
        }
        return isChild;
    }


    public boolean isPassengerMinor() {

        isMinor = false;

        if(this.getDateOfBirth().isAfter(LocalDate.now().minusYears(5)) && this.getDateOfBirth().isBefore(LocalDate.now().minusYears(1)) ){
            isChild = true;
        }
        return isChild;
    }


    public boolean isPassengerOver18() {
        isAdult = false;

        if(this.getDateOfBirth().isBefore(LocalDate.now().minusYears(18))) {
            isAdult = true;
        }
        return isAdult;
    }




    public boolean getBaggageSelect() {
        return baggageSelected;
    }

    public void setBaggageSelected(boolean baggageSelected) {
        this.baggageSelected = baggageSelected;
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
                "\n\t" + "Baggage: \t €" + setBaggagePriceSingle();
    }

}
