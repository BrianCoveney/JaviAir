/**
 * Created by brian on 22/10/16.
 */
public class Flight{

    private String origin;
    private String destination;
    private Double deapartPrice;
    private Double returnPrice;
    private Double price;

    public Flight(){}


    public Flight (String origin, String destination, Double deapartPrice, Double returnPrice, Double price) {
        this.origin = origin;
        this.destination = destination;
        this.deapartPrice = deapartPrice;
        this.returnPrice = returnPrice;
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

    public Double getReturnPrice() {
        return returnPrice;
    }

    public void setReturnPrice(Double returnPrice) {
        this.returnPrice = returnPrice;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    public String toStringTotalPrice() {return "Total:\t\t = €"+this.price;}

    public String toStringDept()  { return this.origin +" > "+ this.destination +"\t = €"+ this.deapartPrice; }

    public String toStringReturn()  { return this.destination +" > "+ this.origin +"\t = €"+ this.returnPrice; }

    @Override
    public String toString() {
        return "Flight details:"+
               "\n\n\tDepart: \t" + toStringDept() +
                "\n\tReturn: \t"+ toStringReturn() +
                       "\n\tPrice: \t"+ toStringTotalPrice();
    }
}
