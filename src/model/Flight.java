package model;

/**
 * Created by brian on 22/10/16.
 */
public class Flight {

    private String origin;
    private String destination;
    private String cost;

    public Flight (String flightOrigin, String flightDestination, String flightCost){
        this.origin = flightOrigin;
        this.destination = flightDestination;
        this.cost = flightCost;
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

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return this.origin +" | "+ this.destination +" | "+ this.cost;
    }
}
