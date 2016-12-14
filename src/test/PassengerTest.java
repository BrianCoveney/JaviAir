
import junit.framework.TestCase;
import model.Passenger;
import org.junit.Test;

/**
 * Created by brian on 04/12/16.
 */
public class PassengerTest extends TestCase {



    @Test
    public void testValidateDNINumber() throws Exception {


        Passenger passenger1 = new Passenger("00000010-X");
        boolean result1 = passenger1.validateDNINumber();

        assertTrue(result1);



        Passenger passenger2 = new Passenger("12345678-a");
        boolean result2 = passenger2.validateDNINumber();

        assertFalse(result2);

    }

}