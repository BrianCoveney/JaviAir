package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Flight;

/**
 * Created by brian on 26/10/16.
 */
public class FlightController {

    private static FlightController instance;

    private ObservableList<Flight> flightList = FXCollections.observableArrayList();

    // implementing singleton pattern
    public static FlightController getInstance(){
        if(instance == null){
            instance = new FlightController();
        }
     return instance;
    }

    public void addFlight(String flightOrigin, String flightDestination){

        Flight flight = Flight.createFlight(flightOrigin, flightDestination);
        this.flightList.add(flight);

    }

    public ObservableList<Flight> getFlights(){
        return this.flightList;
    }

}
