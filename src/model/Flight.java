package model;

/**
 * Created by brian on 22/10/16.
 */
public class Flight {

    private String origin;
    private String destination;
    private Double price;

    public Flight (String origin, String destination, Double price){
        this.origin = origin;
        this.destination = destination;
        this.price = price;
    }

    public Flight (String origin, String destination){
        this.origin = origin;
        this.destination = destination;

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

    public Double getPrice() { return price; }

    public Double setPrice(Double price) { return price; }

    @Override
    public String toString() {

        return this.origin +" > "+ this.destination +"\t = €"+ this.price;
    }

    public String toStringReturn()  { return this.destination +" > "+ this.origin +"\t = €"+ this.price; }
}
