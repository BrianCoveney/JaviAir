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
    private boolean spanishSelected;
    private double baggagePrice;
    private double spanishResidentPrice;
    private String numberDNI;
    private boolean isChild;
    private boolean isAdult;



    public Passenger(String firstName, String lastName, String numberDNI, LocalDate dateOfBirth, boolean baggageSelect, boolean spanishSelected) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.numberDNI = numberDNI;
        this.dateOfBirth = dateOfBirth;
        this.baggageSelected = baggageSelect;
        this.spanishSelected = spanishSelected;

    }



    public boolean isSpanishSelected() {
        return spanishSelected;
    }

    public boolean isBaggageSelected() {
        return baggageSelected;
    }




    public double setSpanishRebate() {
        double spaPrice = 0.0;

        if(isSpanishSelected()){
            spaPrice = spaPrice + 5;
        }

        return spaPrice;
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

    public double removeSpanishRebate() {

        double rebate =  setSpanishRebate();

        double removed = rebate - rebate;

        return removed;
    }


    public boolean isPassengerUnder5() {

        isChild = false;

        if(this.getDateOfBirth().isAfter(LocalDate.now().minusYears(5))){
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


    public String getNumberDNI() {
        return numberDNI;
    }

    public void setNumberDNI(String numberDNI) {
        this.numberDNI = numberDNI;
    }

    public void setSpanishSelected(boolean spanishSelected) {
        this.spanishSelected = spanishSelected;
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

    public double getBaggagePrice() {
        return baggagePrice;
    }

    public void setBaggagePrice(double baggagePrice) {
        this.baggagePrice = baggagePrice;
    }


    public String spanishSelectedString() {
        String value;
        if (isSpanishSelected() == true) {
            value = "Yes";
        } else{
            value = "No";
        }
        return value;
    }


    @Override
    public String toString() {

        LocalDate date = this.dateOfBirth;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDob = date.format(formatter);

        return  " Details:"+
                "\n\n\tFirst name:\t\t " + this.firstName +
                "\n" +
                "\tLast name:\t\t " + this.lastName +
                "\n" +
                "\tDate of birth:\t\t " + formattedDob +
                "\n\t" + "Spanish Repate: \t " + spanishSelectedString() +
                "\n\t" + "Baggage: \t\t €" + setBaggagePriceSingle();
    }

}
