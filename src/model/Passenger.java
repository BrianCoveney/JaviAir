package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.lang.Integer.parseInt;

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
    private String numberDNI;
    private boolean isInfant, isChild, isAdult, isOverFive;


    public Passenger(String numberDNI){
        this.numberDNI = numberDNI;
    }


    public Passenger(String firstName, String lastName, String numberDNI, LocalDate dateOfBirth, boolean baggageSelect, boolean spanishSelected) {
        this.firstName = firstName;
        this.lastName = lastName;
        setNumberDNI(numberDNI);
        setDateOfBirth(dateOfBirth);
        this.baggageSelected = baggageSelect;
        this.spanishSelected = spanishSelected;

    }



    // referenced Colin Manning's code here:
    // http://mcom.cit.ie/staff/Computing/CManning/soft6008-2015/js09/js-09-dni.html
    public boolean validateDNINumber() {

        String dniCopy = this.numberDNI;
        String madString = "TRWAGMYFPDXBNJZSQVHLCKE";
        String dniLetter = "";
        char dniLetter2;
        int dniNum;
        int dniMod;

        try {
            if (dniCopy != null) {

                if (!((dniCopy.length() == 9) || dniCopy.length() == 10)) {
                    return false;
                }

                if (dniCopy.length() == 10) {

                    if (this.numberDNI.charAt(8) == '-') {
                        dniCopy = this.numberDNI.replace("-", "");

                    } else {
                        return false;
                    }
                }

                dniNum = parseInt(dniCopy.substring(0, 8), 10);
                dniLetter = dniCopy.substring(8, 9).toUpperCase();
                dniMod = dniNum % 23;
                dniLetter2 = madString.charAt(dniMod);

                if (!dniLetter.matches(String.valueOf(dniLetter2))) {
                    return false;
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return true;
    }



    public boolean validateFirstName() {
        String validText = "^[\\p{L} .'-]+$";
        if (getFirstName().matches(validText)) {
            return false;
        }
        return true;
    }


    public boolean validateLastName() {
        String validText = "^[\\p{L} .'-]+$";
        if (getLastName().matches(validText)) {
            return false;
        }
        return true;
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
            spaPrice = 5;
        }
        return spaPrice;
    }


    public double setBaggagePriceSingle() {
        double bagPrice = 0.0;
        if(isBaggageSelected() == true) {
            bagPrice = 15;
        }
        return bagPrice;
    }


    public double setBaggagePriceReturn() {
        double bagPrice = 0.0;
        if(isBaggageSelected() == true) {
            bagPrice = 30;
        }
        return bagPrice;
    }


    public boolean isPassengerInfant() {
        isInfant = false;
        if(this.getDateOfBirth().isAfter(LocalDate.now().minusYears(1))) {
            isInfant = true;
        }
        return isInfant;
    }

    public boolean isPassengerAChild() {
        isChild = false;
        if(this.getDateOfBirth().isAfter(LocalDate.now().minusYears(5)) &&
                this.getDateOfBirth().isBefore(LocalDate.now().minusYears(1))){
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

    public boolean isPassengerOver5() {
        isOverFive = false;
        if(this.getDateOfBirth().isBefore(LocalDate.now().minusYears(5))) {
            isOverFive = true;
        }
        return isOverFive;
    }



    public String getNumberDNI() {return numberDNI;}

    public void setNumberDNI(String numberDNI) {
        validateDNINumber();
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
                "\n\t" + "Baggage: \t\t €" + setBaggagePriceReturn();
    }



    public String toStringSingleFlight() {

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
