package Model;

import java.util.*;

public class Trip {
    String _id;
    String _version;
    String _train;
    List<String> _stations = new LinkedList<>();

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getVersion() {
        return _version;
    }

    public void setVersion(String _version) {
        this._version = _version;
    }

    public String getTrain() {
        return _train;
    }

    public void setTrain(String _train) {
        this._train = _train;
    }

    public List<String> getStations() {
        return _stations;
    }

    public void setStations(List<String> _stations) {
        this._stations = _stations;
    }


}
