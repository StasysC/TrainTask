package Model;

import java.util.LinkedHashMap;
import java.util.List;

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
    //*****************************************
}
