package Controller;

import Model.Data;
import Model.ReadXML;
import Model.Trip;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.*;

public class Main {
    ReadXML _readXML = new ReadXML();
    Data _data = _readXML.getData();
    LinkedHashMap<String, List<String>> _trainVisitedStation = new LinkedHashMap<>();
    LinkedHashMap<String, Trip> _trips = _data.getTripsData();
    LinkedHashMap<String, Trip> _dublicateTrips = _data.getTripsDublicateData();
    LinkedHashMap<String, List<String>> _stations = _data.getStationsData();
    LinkedHashMap<String, List<String>> _dublicateStations = _data.getStationsDublicateData();
    LinkedHashMap<String, List<String>> _trains = _data.getTrainsData();
    LinkedHashMap<String, List<String>> _dublicateTrains = _data.getTrainsDublicateData();

    public static void main(String[] args) {
        Main obj = new Main();
        obj.matchRecord();
    }

    private void matchRecord() {

        for (String tripKey : _trips.keySet()) {
            Trip currentTrip = _trips.get(tripKey);

            List<String> currentTripStations = currentTrip.getStations();
            List<String> visitedStations = new LinkedList<>();
            List<String> stationFiller = new LinkedList<>();
            boolean goodTrip = false;
            for (String station : currentTripStations) {
                visitedStations = _stations.get(station);
                try {
                    String visitedStationVersion = visitedStations.get(0);
                    String visitedStationName = visitedStations.get(1);
                    String currentTripVersion = currentTrip.getVersion();

                    try {

                        List<String> train = _trains.get(currentTrip.getTrain());
                        String currentTrainVersion = train.get(0);
                        if (currentTrainVersion.equals(currentTripVersion)) {
                            if(currentTrainVersion.equals(visitedStationVersion))
                                stationFiller.add(visitedStationName);
//                                _trainVisitedStation.put(,stationFiller);
                            goodTrip = true;
                        } else {
                            goodTrip = false;
                            break;
                        }
                    } catch (NullPointerException e) {
                System.out.println("TRIPS DONT HAVE TRAINS ------->" + tripKey);
                    }
                } catch (NullPointerException e) {

                }
            }
            if (goodTrip) {
                System.out.println("GERA KELIONE ---->" + tripKey);
            }
        }
    }
}


