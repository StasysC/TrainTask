
package Model;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public class ReadXML {
    static int FILES_COUNT = 10;
    private Data _data = new Data();

    public Data getData() {
        return _data;
    }

    public ReadXML() {
        for (int i = 0; i < FILES_COUNT; i++) {
            readXML("stations", i);
            readXML("trains", i);
            readXML("trips", i);
        }
    }

    void readXML(String fileName, int fileNumber) {
        Document document = createDocument(fileName, fileNumber);
        try {

            String root = document.getDocumentElement().getNodeName();
            String rootNode = cutLastLetter(root);
            chosenFile(rootNode, document);

        } catch (NullPointerException e) {
            return;
        }

    }

    private void chosenFile(String rootNode, Document document) {
        NodeList nodeList = document.getElementsByTagName(rootNode);
        for (int j = 0; j < nodeList.getLength(); j++) {
            Node node = nodeList.item(j);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                chooseElementData(element, rootNode);
            }
        }
    }

    private void chooseElementData(Element element, String rootNode) {
        switch (rootNode) {
            case "station":
                readStationsData(element);
                break;
            case "train":
                readTrainsData(element);
                break;
            case "trip":
                readTripsData(element);
                break;
        }
    }

    private void readStationsData(Element element) {
        String id = element.getElementsByTagName("id").item(0).getTextContent();
        String version = element.getAttribute("version");
        String name = element.getElementsByTagName("name").item(0).getTextContent();
        List<String> dataFiller = new LinkedList<>();
        dataFiller.add(version);
        dataFiller.add(name);

        if (_data.getStationsData().containsKey(id)) {
            _data.setStationsDublicateData(id, dataFiller);
//            System.out.println("STATION DUBLIKATAS ------------------------------------>" + id);
        } else {
            _data.setStationsData(id, dataFiller);
        }
    }

    private void readTrainsData(Element element) {
        String id = element.getElementsByTagName("id").item(0).getTextContent();
        String version = element.getAttribute("version");
        String seats = element.getElementsByTagName("seats").item(0).getTextContent();
        List<String> dataFiller = new LinkedList<>();
        dataFiller.add(version);
        dataFiller.add(seats);
        if (_data.getTrainsData().containsKey(id)) {
            //System.out.println("TRAIN DUBLIKATAS ------------------------------------>" + id);
            _data.setTrainsDublicateData(id, dataFiller);
        } else {
            _data.setTrainsData(id, dataFiller);
        }
    }

    private void readTripsData(Element element) {
        String id = element.getElementsByTagName("id").item(0).getTextContent();
        String version = element.getAttribute("version");
        String train = element.getElementsByTagName("train").item(0).getTextContent();
        Trip _tmpTrip = new Trip();
        List<String> subStation = new LinkedList<>();
        NodeList subNodeListLenght = element.getElementsByTagName("station");
        NodeList subNodeList = element.getElementsByTagName("stations");
        for (int j = 0; j < subNodeList.getLength(); j++) {
            Node subNode = subNodeList.item(j);
            for (int k = 0; k < subNodeListLenght.getLength(); k++) {
                if (subNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element subElement = (Element) subNode;
                    String tmpStation = subElement.getElementsByTagName("station").item(k).getTextContent();
                    subStation.add(tmpStation);
                }
            }
        }
        _tmpTrip.setTrain(train);
        _tmpTrip.setVersion(version);
        _tmpTrip.setStations(subStation);

        if (_data.getTripsData().containsKey(id)) {
//               System.out.println("TRIP DUBLIKATAS ------------------------------------>" + id);
            _data.setTripsDublicateData(id, _tmpTrip);
        } else {
            _data.setTripsData(id, _tmpTrip);
        }
    }


    private Document createDocument(String fileName, int fileNumber) {
        Document document = null;
        try {
            File xmlFile = new File("Data/" + fileName + "." + fileNumber + ".xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            document = dBuilder.parse(xmlFile);
            document.getDocumentElement().normalize();
        } catch (SAXParseException e) {
            System.out.println("File with error: " + fileName + "." + fileNumber + ".xml");
            System.out.println("Wrong line: Somewhere between 1 and " + e.getLineNumber() + " line");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        return document;
    }

    private String cutLastLetter(String word) {
        word = word.substring(0, word.length() - 1);
        return word;
    }
}
