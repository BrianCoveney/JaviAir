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
    private String cardNumber;
    private LocalDate expiryDate;
    private String ccvNumber;


    public CreditCard(String cardNumber){
        this.cardNumber = cardNumber;
    }


    public CreditCard(String name, String address1, String address2, String address3, String cardType, String cardNumber,
                      LocalDate expiryDate, String ccvNumber ) {
        setName(name);
        setAddress1(address1);
        setAddress2(address2);
        setAddress3(address3);
        setCardType(cardType);
        setCardNumber(cardNumber);
        setExpiryDate(expiryDate);
        setCCVNumber(ccvNumber);
    }


    public String validateName(String input) {
        String validText = "^[\\p{L} .'-]+$";

        try {
            if (!input.matches(validText)) {
                UtilityClass.errorMessageName();
            }
        }catch (Exception e) {
            e.getMessage();
        }
        return input;
    }




    public String validateCreditCardNumber(String inputCCNum) {

        // reference CC regex:
        // https://www.safaribooksonline.com/library/view/regular-expressions-cookbook/9781449327453/ch04s20.html
        String visa = "(4[0-9]{12}(?:[0-9]{3})?)";

        try {

            if(!inputCCNum.matches(visa)) {
                UtilityClass.errorMessageCreditCardNumber();
            }

        }catch (Exception e) {
            e.getMessage();
        }
        return inputCCNum;
    }



    public String validateCCVNumber(String inputCCV) {

        // is numeric and 3 digits in length
        String validCCVInt = "[0-9]{3}";
        try {
            if (!inputCCV.matches(validCCVInt)) {
                UtilityClass.errorMessageCreditCardCCVNumber();
            }
        }catch (Exception e) {
            e.getMessage();
        }
        return inputCCV;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        validateName(name);

    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public void setCardNumber(String cardNumber) {
        validateCreditCardNumber(cardNumber);
        this.cardNumber = cardNumber;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setCCVNumber(String ccvNumber) {
        validateCCVNumber(ccvNumber);
        this.ccvNumber = ccvNumber;
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
                ", cardCCVNumber=" + ccvNumber +
                '}';
    }
}
