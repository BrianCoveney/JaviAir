package model;

import helpers.Consts;

/**
 * Created by brian on 22/10/16.
 */
public class Flight{

    private String origin;
    private String destination;
    private Double deapartPrice;
    private Double returnPrice;
    private Double price;
    private String departTime;
    private String returnTime;
    private static final int MAX_PASSENGER_NO = 8;
    private double flightPrice;
    private String flightTime1, flightTime2;


    public Flight(){}


    public Flight (String origin, String destination, Double deapartPrice, Double returnPrice, Double price, String flightTime, String returnTime) {
        this.origin = origin;
        this.destination = destination;
        this.deapartPrice = deapartPrice;
        this.returnPrice = returnPrice;
        this.price = price;
        this.departTime = flightTime;
        this.returnTime = returnTime;
    }



    public FlightTimes getFlightTimes(String dptFlight, String rtnFlight) {



        if (dptFlight.equals(Consts.CORK) && rtnFlight.equals(Consts.MADRID)) {
            flightTime1 = Consts.ORK_MAD_1;
            flightTime2 = null;
        } else if (dptFlight.equals(Consts.CORK) && rtnFlight.equals(Consts.ST_BRIEUC)) {
            flightTime1 = Consts.ORK_SBK_1;
            flightTime2 = null;
        } else if (dptFlight.equals(Consts.CORK) && rtnFlight.equals(Consts.JERSEY)) {
            flightTime1 = Consts.ORK_JER_1;
            flightTime2 = null;
        } else if (dptFlight.equals(Consts.CORK) && rtnFlight.equals(Consts.PARIS)) {
            flightTime1 = Consts.ORK_CDG_1;
            flightTime2 = Consts.ORK_CDG_2;
        } else if (dptFlight.equals(Consts.CORK) && rtnFlight.equals(Consts.STANSTED)) {
            flightTime1 = Consts.ORK_STN_1;
            flightTime2 = Consts.ORK_STN_2;
        } else if (dptFlight.equals(Consts.CORK) && rtnFlight.equals(Consts.MALAGA)) {
            flightTime1 = Consts.ORK_AGP_1;
            flightTime2 = null;
        } else if (dptFlight.equals(Consts.MADRID) && rtnFlight.equals(Consts.CORK)) {
            flightTime1 = Consts.MAD_ORK_1;
            flightTime2 = null;
        } else if (dptFlight.equals(Consts.MADRID) && rtnFlight.equals(Consts.ST_BRIEUC)) {
            flightTime1 = Consts.MAD_SBK_1;
            flightTime2 = null;
        } else if (dptFlight.equals(Consts.MADRID) && rtnFlight.equals(Consts.JERSEY)) {
            flightTime1 = Consts.MAD_JER_1;
            flightTime2 = null;
        } else if (dptFlight.equals(Consts.MADRID) && rtnFlight.equals(Consts.PARIS)) {
            flightTime1 = Consts.MAD_CDG_1;
            flightTime2 = null;
        } else if (dptFlight.equals(Consts.MADRID) && rtnFlight.equals(Consts.STANSTED)) {
            flightTime1 = Consts.MAD_STN_1;
            flightTime2 = Consts.MAD_STN_2;
        } else if (dptFlight.equals(Consts.MADRID) && rtnFlight.equals(Consts.MALAGA)) {
            flightTime1 = Consts.MAD_AGP_1;
            flightTime2 = null;
        } else if (dptFlight.equals(Consts.ST_BRIEUC) && rtnFlight.equals(Consts.CORK)) {
            flightTime1 = Consts.SBK_ORK_1;
            flightTime2 = null;
        } else if (dptFlight.equals(Consts.ST_BRIEUC) && rtnFlight.equals(Consts.MADRID)) {
            flightTime1 = Consts.SBK_MAD_1;
            flightTime2 = null;
        } else if (dptFlight.equals(Consts.ST_BRIEUC) && rtnFlight.equals(Consts.JERSEY)) {
            flightTime1 = Consts.SBK_JER_1;
            flightTime2 = null;
        } else if (dptFlight.equals(Consts.ST_BRIEUC) && rtnFlight.equals(Consts.PARIS)) {
            flightTime1 = Consts.SBK_CDG_1;
            flightTime2 = null;
        } else if (dptFlight.equals(Consts.ST_BRIEUC) && rtnFlight.equals(Consts.STANSTED)) {
            flightTime1 = Consts.SBK_STN_1;
            flightTime2 = null;
        } else if (dptFlight.equals(Consts.ST_BRIEUC) && rtnFlight.equals(Consts.MALAGA)) {
            flightTime1 = Consts.SBK_AGP_1;
            flightTime2 = null;
        } else if (dptFlight.equals(Consts.JERSEY) && rtnFlight.equals(Consts.CORK)) {
            flightTime1 = Consts.JER_ORK_1;
            flightTime2 = null;
        } else if (dptFlight.equals(Consts.JERSEY) && rtnFlight.equals(Consts.MADRID)) {
            flightTime1 = Consts.JER_MAD_1;
            flightTime2 = null;
        } else if (dptFlight.equals(Consts.JERSEY) && rtnFlight.equals(Consts.PARIS)) {
            flightTime1 = Consts.JER_CDG_1;
            flightTime2 = null;
        } else if (dptFlight.equals(Consts.JERSEY) && rtnFlight.equals(Consts.STANSTED)) {
            flightTime1 = Consts.JER_STN_1;
            flightTime2 = null;
        } else if (dptFlight.equals(Consts.JERSEY) && rtnFlight.equals(Consts.MALAGA)) {
            flightTime1 = Consts.JER_AGP_1;
            flightTime2 = null;
        } else if (dptFlight.equals(Consts.PARIS) && rtnFlight.equals(Consts.CORK)) {
            flightTime1 = Consts.CDG_ORK_1;
            flightTime2 = Consts.CDG_ORK_2;
        } else if (dptFlight.equals(Consts.PARIS) && rtnFlight.equals(Consts.MADRID)) {
            flightTime1 = Consts.CDG_MAD_1;
            flightTime2 = null;
        } else if (dptFlight.equals(Consts.PARIS) && rtnFlight.equals(Consts.ST_BRIEUC)) {
            flightTime1 = Consts.CDG_SBK_1;
            flightTime2 = null;
        } else if (dptFlight.equals(Consts.PARIS) && rtnFlight.equals(Consts.JERSEY)) {
            flightTime1 = Consts.CDG_JER_1;
            flightTime2 = null;
        } else if (dptFlight.equals(Consts.PARIS) && rtnFlight.equals(Consts.STANSTED)) {
            flightTime1 = Consts.CDG_STN_1;
            flightTime2 = null;
        } else if (dptFlight.equals(Consts.PARIS) && rtnFlight.equals(Consts.MALAGA)) {
            flightTime1 = Consts.CDG_AGP_1;
            flightTime2 = null;
        } else if (dptFlight.equals(Consts.STANSTED) && rtnFlight.equals(Consts.CORK)) {
            flightTime1 = Consts.STN_ORK_1;
            flightTime2 = Consts.STN_ORK_2;
        } else if (dptFlight.equals(Consts.STANSTED) && rtnFlight.equals(Consts.MADRID)) {
            flightTime1 = Consts.STN_MAD_1;
            flightTime2 = null;
        } else if (dptFlight.equals(Consts.STANSTED) && rtnFlight.equals(Consts.ST_BRIEUC)) {
            flightTime1 = Consts.STN_SBK_1;
            flightTime2 = null;
        } else if (dptFlight.equals(Consts.STANSTED) && rtnFlight.equals(Consts.JERSEY)) {
            flightTime1 = Consts.STN_JER_1;
            flightTime2 = null;
        } else if (dptFlight.equals(Consts.STANSTED) && rtnFlight.equals(Consts.PARIS)) {
            flightTime1 = Consts.STN_CDG_1;
            flightTime2 = null;
        } else if (dptFlight.equals(Consts.STANSTED) && rtnFlight.equals(Consts.MALAGA)) {
            flightTime1 = Consts.STN_AGP_1;
            flightTime2 = Consts.STN_AGP_2;
        } else if (dptFlight.equals(Consts.MALAGA) && rtnFlight.equals(Consts.CORK)) {
            flightTime1 = Consts.AGP_ORK_1;
            flightTime2 = null;
        } else if (dptFlight.equals(Consts.MALAGA) && rtnFlight.equals(Consts.MADRID)) {
            flightTime1 = Consts.AGP_MAD_1;
            flightTime2 = null;
        } else if (dptFlight.equals(Consts.MALAGA) && rtnFlight.equals(Consts.ST_BRIEUC)) {
            flightTime1 = Consts.AGP_SBK_1;
            flightTime2 = null;
        } else if (dptFlight.equals(Consts.MALAGA) && rtnFlight.equals(Consts.JERSEY)) {
            flightTime1 = Consts.AGP_JER_1;
            flightTime2 = null;
        } else if (dptFlight.equals(Consts.MALAGA) && rtnFlight.equals(Consts.PARIS)) {
            flightTime1 = Consts.AGP_CDG_1;
            flightTime2 = null;
        } else if (dptFlight.equals(Consts.MALAGA) && rtnFlight.equals(Consts.STANSTED)) {
            flightTime1 = Consts.AGP_STN_1;
            flightTime2 = Consts.AGP_STN_2;
        }

        return new FlightTimes(flightTime1, flightTime2);

    }



    public double getFlightPrice(String dptFlight, String rtnFlight) {

        try {

            if (dptFlight != null && rtnFlight != null) {
                if (dptFlight.equals(Consts.CORK) && rtnFlight.equals((Consts.MADRID)) || dptFlight.equals(Consts.MADRID) && rtnFlight.equals(Consts.CORK)) {
                    flightPrice = Consts.ONE_HND;
                } else if (dptFlight.equals(Consts.CORK) && rtnFlight.equals(Consts.ST_BRIEUC) || dptFlight.equals(Consts.ST_BRIEUC) && rtnFlight.equals(Consts.CORK)) {
                    flightPrice = Consts.ONE_HND;
                } else if (dptFlight.equals(Consts.CORK) && rtnFlight.equals(Consts.JERSEY) || dptFlight.equals(Consts.JERSEY) && rtnFlight.equals(Consts.CORK)) {
                    flightPrice = Consts.ONE_HND_TWENTY;
                } else if (dptFlight.equals(Consts.CORK) && rtnFlight.equals(Consts.PARIS) || dptFlight.equals(Consts.PARIS) && rtnFlight.equals(Consts.CORK)) {
                    flightPrice = Consts.EIGHTY;
                } else if (dptFlight.equals(Consts.CORK) && rtnFlight.equals(Consts.STANSTED) || dptFlight.equals(Consts.STANSTED) && rtnFlight.equals(Consts.CORK)) {
                    flightPrice = Consts.FORTY;
                } else if (dptFlight.equals(Consts.CORK) && rtnFlight.equals(Consts.MALAGA) || dptFlight.equals(Consts.MALAGA) && rtnFlight.equals(Consts.CORK)) {
                    flightPrice = Consts.TWO_HND_FORTY;
                } else if (dptFlight.equals(Consts.MADRID) && rtnFlight.equals(Consts.ST_BRIEUC) || dptFlight.equals(Consts.ST_BRIEUC) && rtnFlight.equals(Consts.MADRID)) {
                    flightPrice = Consts.TWO_HND;
                } else if (dptFlight.equals(Consts.MADRID) && rtnFlight.equals(Consts.JERSEY) || dptFlight.equals(Consts.JERSEY) && rtnFlight.equals(Consts.MADRID)) {
                    flightPrice = Consts.TWO_HND;
                } else if (dptFlight.equals(Consts.MADRID) && rtnFlight.equals(Consts.PARIS) || dptFlight.equals(Consts.PARIS) && rtnFlight.equals(Consts.MADRID)) {
                    flightPrice = Consts.SIXTY;
                } else if (dptFlight.equals(Consts.MADRID) && rtnFlight.equals(Consts.STANSTED) || dptFlight.equals(Consts.STANSTED) && rtnFlight.equals(Consts.MADRID)) {
                    flightPrice = Consts.SIXTY;
                } else if (dptFlight.equals(Consts.MADRID) && rtnFlight.equals(Consts.MALAGA) || dptFlight.equals(Consts.MALAGA) && rtnFlight.equals(Consts.MADRID)) {
                    flightPrice = Consts.SIXTY;
                } else if (dptFlight.equals(Consts.ST_BRIEUC) && rtnFlight.equals(Consts.JERSEY)) {
                    flightPrice = Consts.ZERO;
                } else if (dptFlight.equals(Consts.ST_BRIEUC) && rtnFlight.equals(Consts.PARIS) || dptFlight.equals(Consts.PARIS) && rtnFlight.equals(Consts.ST_BRIEUC)) {
                    flightPrice = Consts.ONE_HND_FIFTY;
                } else if (dptFlight.equals(Consts.ST_BRIEUC) && rtnFlight.equals(Consts.MALAGA) || dptFlight.equals(Consts.MALAGA) && rtnFlight.equals(Consts.ST_BRIEUC)) {
                    flightPrice = Consts.ONE_HND_FORTY;
                } else if (dptFlight.equals(Consts.JERSEY) && rtnFlight.equals(Consts.PARIS) || dptFlight.equals(Consts.PARIS) && rtnFlight.equals(Consts.JERSEY)) {
                    flightPrice = Consts.TWO_HND_FIFTY;
                } else if (dptFlight.equals(Consts.JERSEY) && rtnFlight.equals(Consts.STANSTED) || dptFlight.equals(Consts.STANSTED) && rtnFlight.equals(Consts.JERSEY)) {
                    flightPrice = Consts.TWO_HND_FIFTY;
                } else if (dptFlight.equals(Consts.JERSEY) && rtnFlight.equals(Consts.MALAGA) || dptFlight.equals(Consts.MALAGA) && rtnFlight.equals(Consts.JERSEY)) {
                    flightPrice = Consts.TWO_HND_EIGHTY;
                } else if (dptFlight.equals(Consts.PARIS) && rtnFlight.equals(Consts.MALAGA) || dptFlight.equals(Consts.MALAGA) && rtnFlight.equals(Consts.PARIS)) {
                    flightPrice = Consts.ONE_HND;
                } else if (dptFlight.equals(Consts.STANSTED) && rtnFlight.equals(Consts.MALAGA) || dptFlight.equals(Consts.MALAGA) && rtnFlight.equals(Consts.STANSTED)) {
                    flightPrice = Consts.ONE_HND_TWENTY;
                } else if (dptFlight.equals(Consts.PARIS) && rtnFlight.equals(Consts.STANSTED) || dptFlight.equals(Consts.STANSTED) && rtnFlight.equals(Consts.PARIS)) {
                    flightPrice = Consts.SIXTY;
                } else if (dptFlight.equals(Consts.ST_BRIEUC) && rtnFlight.equals(Consts.STANSTED) || dptFlight.equals(Consts.STANSTED) && rtnFlight.equals(Consts.ST_BRIEUC)) {
                    flightPrice = Consts.EIGHTY;
                }
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }


        return flightPrice;
    }




    public String getDepartTime() {
        return departTime;
    }

    public void setDepartTime(String departTime) {
        this.departTime = departTime;
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

    public String displayTotalPrice() {return "\t\t = €"+this.price;}

    public String displayDeptDetails()  { return this.origin +" > "+ this.destination +"\t = €"+ this.deapartPrice; }

    public String displayReturnDetails()  { return this.destination +" > "+ this.origin +"\t = €"+ this.returnPrice; }

    @Override
    public String toString() {
        return "\tDepart: \t\t\t" + displayDeptDetails() +
                "\n\tReturn: \t\t\t"+ displayReturnDetails() +
                "\n\tDepart Time: \t\t"+ this.departTime + "\n\tReturn Time: \t\t" + this.returnTime +
                       "\n\tFlight Price: \t\t\t"+ displayTotalPrice() + "\n";
    }

    public String toStringSingleFlight() {
        return "\tDepart: \t\t\t" + displayDeptDetails() +
                "\n\tDepart Time: \t\t"+ this.departTime +
                "\n\tFlight Price: \t\t\t"+ displayTotalPrice() + "\n";
    }

}
