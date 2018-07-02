package Controller;

import Model.Data;
import Model.ReadXML;
import Model.Trip;

import java.util.LinkedHashMap;
import java.util.List;

public class Main {
    ReadXML _readXML = new ReadXML();
    Data _data = _readXML.getData();
    LinkedHashMap<String, Trip> _trips = _data.getTripsData();
    LinkedHashMap<String, Trip> _dublicateTrips = _data.getTripsDublicateData();
    LinkedHashMap<String, List<String>> _stations = _data.getStationsData();
    LinkedHashMap<String, List<String>> _trains = _data.getTrainsData();
    LinkedHashMap<String, List<String>> _dublicateTrains = _data.getTrainsDublicateData();

    public static void main(String[] args) {
        Main obj = new Main();
        obj.matchRecord();
    }

    private void matchRecord() {
//        List<String> trainSeats = _trains.get("jpvKfMzXTu");
//        List<String> trainSeatsDublicate = _dublicateTrains.get("jpvKfMzXTu");
//        System.out.println("Version: " + trainSeats.get(0) + " Seats: " + trainSeats.get(1));
//        System.out.println("Version: " + trainSeatsDublicate.get(0) + " Seats: " + trainSeatsDublicate.get(1));
        System.out.println(_trips.size());
    }
}


