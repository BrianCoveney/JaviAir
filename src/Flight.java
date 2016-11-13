/**
 * Created by brian on 22/10/16.
 */
public class Flight{

    private String origin;
    private String destination;
    private Double deapartPrice;
    private Double returnPice;
    private Double price;


    public Flight (String origin, String destination, Double deapartPrice, Double returnPice, Double price) {
        this.origin = origin;
        this.destination = destination;
        this.deapartPrice = deapartPrice;
        this.returnPice = returnPice;
        this.price = price;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Double getDeapartPrice() {
        return deapartPrice;
    }

    public void setDeapartPrice(Double deapartPrice) {
        this.deapartPrice = deapartPrice;
    }

    public Double getReturnPice() {
        return returnPice;
    }

    public void setReturnPice(Double returnPice) {
        this.returnPice = returnPice;
    }

    public Double getPrice() { return price; }

    public Double setPrice(Double price) { return price; }



    @Override
    public String toString() {return "€"+this.price.toString();}

    public String toStringDept()  { return this.origin +" > "+ this.destination +"\t = €"+ this.deapartPrice; }

    public String toStringReturn()  { return this.destination +" > "+ this.origin +"\t = €"+ this.returnPice; }
}
