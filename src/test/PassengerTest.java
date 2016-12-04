import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by brian on 04/12/16.
 */
public class PassengerTest extends TestCase {



    @Test
    public void testValidateDNINumber() throws Exception {


        Passenger passenger1 = new Passenger("0347393-X");
        boolean result1 = passenger1.validateDNINumber();

        assertTrue(result1);



        Passenger passenger2 = new Passenger("123567-z");
        boolean result2 = passenger2.validateDNINumber();

        assertFalse(result2);

    }

}