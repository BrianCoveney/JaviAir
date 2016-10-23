/**
 * Created by brian on 22/10/16.
 */
public class Flight {

    private String flightOrigin;
    private String flightDestination;

    public String getFlightOrigin() {
        return flightOrigin;
    }

    public void setFlightOrigin(String flightOrigin) {
        this.flightOrigin = flightOrigin;
    }

    public String getFlightDestination() {
        return flightDestination;
    }

    public void setFlightDestination(String flightDestination) {
        this.flightDestination = flightDestination;
    }

    @Override
    public String toString() {
        return getFlightOrigin() +" | "+ getFlightDestination();
    }
}
