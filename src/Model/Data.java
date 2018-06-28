package Model;

import java.util.LinkedHashMap;
import java.util.List;

public class Data {
    //******* Trains data *************
    LinkedHashMap<String, List<String>> _trainsData = new LinkedHashMap();

    public LinkedHashMap<String, List<String>> getTrainsData() {
        return _trainsData;
    }

    public void setTrainsData(String _id, List<String> _dataFiller) {
        _trainsData.put(_id, _dataFiller);
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
    LinkedHashMap<String, List<String>> _tripsData = new LinkedHashMap();

    public LinkedHashMap<String, List<String>> getTripsData() {
        return _tripsData;
    }
    public void setTripsData(String _id, List<String> _dataFiller) {
        _tripsData.put(_id, _dataFiller);
    }
}
