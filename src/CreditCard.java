import java.time.LocalDate;

/**
 * Created by brian on 28/11/16.
 */
public class CreditCard {

    private String name;
    private String address1;
    private String address2;
    private String address3;
    private String cardType;
    private int cardNumber;
    private LocalDate expiryDate;
    private int cardCCVNumber;


    public CreditCard(String name, String address1, String address2, String address3, String cardType, int cardNumber,
                      LocalDate expiryDate, int cardCCVNumber ) {
        this.name = name;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.cardType = cardType;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cardCCVNumber = cardCCVNumber;


    }


    public String ValidateString(String input) {
        String validText = "^[\\p{L} .'-]+$";

        if(!input.matches(validText)) {
            UtilityClass.errorMessageName();
        }
        return input;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getCardCCVNumber() {
        return cardCCVNumber;
    }

    public void setCardCCVNumber(int cardCCVNumber) {
        this.cardCCVNumber = cardCCVNumber;
    }


    @Override
    public String toString() {
        return "CreditCard{" +
                "name='" + name + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", address3='" + address3 + '\'' +
                ", cardType='" + cardType + '\'' +
                ", cardNumber=" + cardNumber +
                ", expiryDate=" + expiryDate +
                ", cardCCVNumber=" + cardCCVNumber +
                '}';
    }
}
