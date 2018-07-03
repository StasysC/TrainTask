package Model;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Data {
    Trip _trip = new Trip();
    //******* Trains data *************
    LinkedHashMap<String, List<String>> _trainsData = new LinkedHashMap();

    public LinkedHashMap<String, List<String>> getTrainsData() {
        return _trainsData;
    }

    public void setTrainsData(String _id, List<String> _dataFiller) {
        _trainsData.put(_id, _dataFiller);
    }

    //****** Dublicate trains data *************
    LinkedHashMap<String, List<String>> _trainsDublicateData = new LinkedHashMap();

    public LinkedHashMap<String, List<String>> getTrainsDublicateData() {
        return _trainsDublicateData;
    }

    public void setTrainsDublicateData(String _id, List<String> _dataFiller) {
        _trainsDublicateData.put(_id, _dataFiller);
    }

    //******* Stations data ************
    LinkedHashMap<String, List<String>> _stationsData = new LinkedHashMap();

    public LinkedHashMap<String, List<String>> getStationsData() {
        return _stationsData;
    }

    public void setStationsData(String _id, List<String> _dataFiller) {
        _stationsData.put(_id, _dataFiller);
    }

    //********** Dublicate stations data *********
    LinkedHashMap<String, List<String>> _stationsDublicateData = new LinkedHashMap();

    public LinkedHashMap<String, List<String>> getStationsDublicateData() {
        return _stationsDublicateData;
    }

    public void setStationsDublicateData(String _id, List<String> _dataFiller) {
        _stationsDublicateData.put(_id, _dataFiller);
    }

    //******* Trips data **************
    LinkedHashMap<String, Trip> _tripsData = new LinkedHashMap();

    public LinkedHashMap<String, Trip> getTripsData() {
        return _tripsData;
    }

    public void setTripsData(String _id, Trip _dataFiller) {
        _tripsData.put(_id, _dataFiller);
    }

    //********** Dublicate trips data *********
    LinkedHashMap<String, Trip> _tripsDublicateData = new LinkedHashMap();

    public LinkedHashMap<String, Trip> getTripsDublicateData() {
        return _tripsDublicateData;
    }

    public void setTripsDublicateData(String _id, Trip _dataFiller) {
        _tripsDublicateData.put(_id, _dataFiller);
    }


    //******** Seats data**********************
    LinkedHashMap<String, Integer> _seatsData = new LinkedHashMap<>();

    public LinkedHashMap<String, Integer> getSeatsData() {
        return _seatsData;
    }

    public void setSeatsData(String _station, Integer _seat) {
        int tmp = getSeatsData().get(_station);
        tmp += _seat;
        this._seatsData.put(_station,tmp);
    }


    //******** Top Stations *******************
    List<Map.Entry<String, Integer>> _topStations = new LinkedList<>();
    public List<Map.Entry<String, Integer>> getTopStations() {
        return _topStations;
    }

    public void setTopStations(List<Map.Entry<String, Integer>> _topStations) {
        this._topStations = _topStations;
    }


    //*****************************************

    //******** Discared trips data ************
    List<String> _discardedTrips = new LinkedList<>();

    public List<String> getDiscardedTrips() {
        return _discardedTrips;
    }

    public void setDiscardedTrips(String _discardedTrips) {
        this._discardedTrips.add(_discardedTrips);
    }
    //*********** Visited station data *************
    LinkedHashMap<String, List<String>> _trainVisitedStation = new LinkedHashMap<>();
    public LinkedHashMap<String, List<String>> getTrainVisitedStation() {
        return _trainVisitedStation;
    }

    public void setTrainVisitedStation(LinkedHashMap<String, List<String>> _trainVisitedStation) {
        this._trainVisitedStation = _trainVisitedStation;
    }
    //*****************************************
}
