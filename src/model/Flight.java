package model;

/**
 * Created by brian on 22/10/16.
 */
public class Flight {

    private String origin;
    private String destination;

    private Flight (String flightOrigin, String flightDestination){
        this.origin = flightOrigin;
        this.destination = flightDestination;
    }

    // simple factory method in place of a constructor
    public static Flight createFlight(String origin, String destination)
    {
        return new Flight(origin, destination);
    }

    public String getFlightOrigin() {
        return origin;
    }

    public void setFlightOrigin(String flightOrigin) {
        this.destination = flightOrigin;
    }

    public String getFlightDestination() {
        return destination;
    }

    public void setFlightDestination(String flightDestination) {
        this.destination = flightDestination;
    }

    @Override
    public String toString() {
        return this.origin +" | "+ this.destination;
    }
}
