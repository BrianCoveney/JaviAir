import helpers.Consts;
import junit.framework.TestCase;
import model.Flight;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static helpers.Consts.CORK;
import static helpers.Consts.MADRID;

/**
 * Created by brian on 14/12/16.
 */
public class FlightTest extends TestCase{

    private LocalDate now = LocalDate.now();
    private LocalDate nowPlusOneYear = LocalDate.now().plusYears(1);
    private Flight flight;
    private double validDouble = Consts.ONE_HND;
    private double inValidDouble = Consts.TWO_HND;


    @Before
    public void setUp() throws Exception {
        super.setUp();
        this.flight = new Flight();
    }

    @Test
    public void testCheckInvalidDates() throws Exception {
        boolean result1 = flight.checkInvalidDates(now, nowPlusOneYear);
        assertTrue(result1);
    }

    @Test
    public void testGetFlightPrice() throws Exception {

        double result1 = flight.getFlightPrice(CORK, MADRID);
        assertEquals(validDouble, result1);

        double result2 = flight.getFlightPrice(CORK, MADRID);
        assertNotSame(inValidDouble, result2);

    }

}