package model;

/**
 * Created by brian on 12/12/16.
 */
public class ChildFlight extends Flight {

    private static final Double CHILD_PRICE = 60.0;
    private double price;

    public ChildFlight(String origin, String destination, Double deapartPrice, Double returnPrice, Double price, String flightTime, String returnTime) {
        super(origin, destination, deapartPrice, returnPrice, price, flightTime, returnTime);
        setPrice(price);
    }


    @Override
    public void setPrice(Double price) {
        this.price = CHILD_PRICE;
    }


}
