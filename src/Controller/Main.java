package Controller;

import Model.Data;
import Model.ReadXML;
import Model.Trip;
import sun.text.normalizer.UTF16;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    private static int TOP_STATIONS_COUNT = 15;

    ReadXML _readXML = new ReadXML();
    Data _data = _readXML.getData();
    LinkedHashMap<String, List<String>> _trainVisitedStation = _data.getTrainVisitedStation();
    LinkedHashMap<String, Trip> _trips = _data.getTripsData();
    LinkedHashMap<String, Trip> _dublicateTrips = _data.getTripsDublicateData();
    LinkedHashMap<String, List<String>> _stations = _data.getStationsData();
    LinkedHashMap<String, List<String>> _dublicateStations = _data.getStationsDublicateData();
    LinkedHashMap<String, List<String>> _trains = _data.getTrainsData();
    LinkedHashMap<String, List<String>> _dublicateTrains = _data.getTrainsDublicateData();
    List<String> _discaredTrips = _data.getDiscardedTrips();
    LinkedHashMap<String, Integer> _seatsData = _data.getSeatsData();
    List<Map.Entry<String, Integer>> _topStations = _data.getTopStations();


    public Main() {
        runProgram();
    }

    public void runProgram() {
        matchRecord();
        _topStations = descendingSeats(_seatsData);
        createFiles(_discaredTrips);
    }

    private void matchRecord() {
        for (String tripKey : _trips.keySet()) {
            Trip currentTrip = _trips.get(tripKey);

            String currentTripVersion = currentTrip.getVersion();
            List<String> currenTrainData = _trains.get(currentTrip.getTrain());
            String currentTrainID = currentTrip.getTrain();
            List<String> currentTripStations = currentTrip.getStations();
            List<String> visitedStations = new LinkedList<>();
            List<String> stationFiller = new LinkedList<>();
            boolean noTrainOnTrip = false;
            boolean missingStationOnTrip = false;
            for (String station : currentTripStations) {
                visitedStations = _stations.get(station);
                try {
                    String visitedStationVersion = visitedStations.get(0);
                    String visitedStationName = visitedStations.get(1);
                    int currentTrainSeats = Integer.parseInt(currenTrainData.get(1));

                    try {
                        String currentTrainVersion = currenTrainData.get(0);
                        if (currentTrainVersion.equals(currentTripVersion)) {
                            if (currentTrainVersion.equals(visitedStationVersion)) {
                                stationFiller.add(visitedStationName);
                                _trainVisitedStation.put(currentTrainID, stationFiller);
                                //Calculate seats on station
                                if (_seatsData.get(visitedStationName) == null) {
                                    _seatsData.put(visitedStationName, currentTrainSeats);
                                } else {
                                    int seats = _seatsData.get(visitedStationName);
                                    seats += currentTrainSeats;
                                    _seatsData.put(visitedStationName, seats);
                                }
                            }
                        }
                    } catch (NullPointerException e) {
                        missingStationOnTrip = true;
                    }
                } catch (NullPointerException e) {
                    noTrainOnTrip = true;
                }
            }
            if (noTrainOnTrip || missingStationOnTrip) {
//                System.out.println("TRIP W/O TRAIN ---->" + tripKey);
                _discaredTrips.add(tripKey);
            }
        }
    }

    public List<Map.Entry<String, Integer>> descendingSeats(LinkedHashMap<String, Integer> seatsData) {
        Map<String, Integer> tmp = sortByValue(seatsData);
        List<Map.Entry<String, Integer>> topStation = new ArrayList<>();
        int i = 0;
        for (Map.Entry<String, Integer> j : tmp.entrySet()) {
            if (i >= TOP_STATIONS_COUNT) {
                break;
            } else {
                i++;
                topStation.add(j);
            }
        }
        return topStation;
    }

    public <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());
        list = reverseList(list);
        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    public <K, V extends Comparable<? super V>> List<Map.Entry<K, V>> reverseList(List<Map.Entry<K, V>> list) {
        List<Map.Entry<K, V>> result = new LinkedList<>();
        for (int i = list.size() - 1; i >= 0; i--) {
            result.add(list.get(i));
        }
        return result;
    }

    public List<String> formatForFile (){
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        List<String> tmp = new LinkedList<>();
        for(Map.Entry i : _topStations){
            map.put(i.getKey().toString(),i.getValue().toString());
            List<String> local = new LinkedList<>();
            local.add(i.getKey().toString());
            local.add(i.getValue().toString());
            String tmpLocal = local.toString();
            tmp.add(tmpLocal);
        }

        return tmp;
    }

    public void createFiles( List<String> badTrips){
        try {
            List<String> local = formatForFile();
            Files.write(Paths.get("TopStations.txt"), local);
            Files.write(Paths.get("BadTrips.txt"), badTrips);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}


