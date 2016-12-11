import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by brian on 11/12/16.
 */
public class CreditCardTest extends TestCase {

    private String testValidNumber = "4556737586899855";
    private String testInvalidNumber = "3556737586899855";
    private CreditCard creditCard;


    public void setUp() throws Exception {
        super.setUp();
        this.creditCard = new CreditCard();

    }


    @Test
    public void testValidateCreditCardNumber() throws Exception {

        boolean result1 = creditCard.validateCreditCardNumber(testValidNumber);
        assertTrue(result1);

        boolean result2 = creditCard.validateCreditCardNumber(testInvalidNumber);
        assertFalse(result2);

    }

}