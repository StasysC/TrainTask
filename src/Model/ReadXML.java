
package Model;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ReadXML {
    static int FILES_COUNT = 10;
    Data _data = new Data();

    public static void main(String[] args) {
        ReadXML obj = new ReadXML();
        obj.readXML("stations");
        obj.readXML("trains");
        obj.readXML("trips");

    }

    void readXML(String fileName) {

        // for (int i = 0; i < FILES_COUNT; i++) {
        Document document = createDocument(fileName);
        String root = document.getDocumentElement().getNodeName();
        String rootNode = cutLastLetter(root);
        chosenFile(rootNode, document);
        //*****FOR TESTING*********
//        int trainSize = 0;
//        for (Map.Entry<String, List<String>> i : _data.getStationsData().entrySet()) {
//            System.out.println("Name: " + i.getKey() + " Version: " + i.getValue().get(0) + " ID: " + i.getValue().get(1));
//            trainSize++;
//        }
//        System.out.println(trainSize);
//
//        int stationSize = 0;
//        for (Map.Entry<String, List<String>> i : _data.getTrainsData().entrySet()) {
//            System.out.println("ID: " + i.getKey() + " Version: " + i.getValue().get(0) + " Seats: " + i.getValue().get(1));
//            stationSize++;
//        }
//        System.out.println(stationSize);//
        int tripsSize = 0;
        for (Map.Entry<String, List<String>> i : _data.getTripsData().entrySet()) {
            System.out.println("ID: " + i.getKey() + " Version: " + i.getValue().get(0) + " Train: " + i.getValue().get(1));
            tripsSize++;
        }
        System.out.println(tripsSize);


        //************************
    }

    private void chosenFile(String rootNode, Document document) {
        NodeList nodeList = document.getElementsByTagName(rootNode);
        for (int j = 0; j < nodeList.getLength(); j++) {
            Node node = nodeList.item(j);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                chooseElementData(element, rootNode, document);
            }

        }
    }

    private void chooseElementData(Element element, String rootNode, Document document) {
        switch (rootNode) {
            case "station":
                readStationsData(element, rootNode);
                break;
            case "train":
                readTrainsData(element, rootNode);
                break;
            case "trip":
                readTripsData(element, rootNode, document);
                break;
        }
    }

    private void readStationsData(Element element, String rootNode) {
        String id = element.getElementsByTagName("id").item(0).getTextContent();
        String version = element.getAttribute("version");
        String name = element.getElementsByTagName("name").item(0).getTextContent();
        List<String> dataFiller = new LinkedList<>();
        dataFiller.add(version);
        dataFiller.add(id);
//        if(_data.getTrainsData().containsKey(id)){
//            System.out.println("DUBLIKATAS ------------------------------------>"+ name);
//        }
        _data.setStationsData(name, dataFiller);
    }

    private void readTrainsData(Element element, String rootNode) {
        String id = element.getElementsByTagName("id").item(0).getTextContent();
        String version = element.getAttribute("version");
        String seats = element.getElementsByTagName("seats").item(0).getTextContent();
        List<String> dataFiller = new LinkedList<>();
        dataFiller.add(version);
        dataFiller.add(seats);
        if (_data.getTrainsData().containsKey(id)) {
            System.out.println("DUBLIKATAS ------------------------------------>" + id);
        }
        _data.setTrainsData(id, dataFiller);
    }

    private void readTripsData(Element element, String rootNode, Document document) {
        String id = element.getElementsByTagName("id").item(0).getTextContent();
        String version = element.getAttribute("version");
        String train = element.getElementsByTagName("train").item(0).getTextContent();
        String subStation;
        NodeList subList = document.getElementsByTagName("stations");
//        System.out.println(subList.getLength());
        for (int j = 0; j < subList.getLength(); j++) {
            Node subNode = subList.item(j);
            if (subNode.getNodeType() == Node.ELEMENT_NODE) {
                Element subElement = (Element) subNode;
                subStation = subElement.getElementsByTagName("station").item(0).getTextContent();
//                System.out.println("Substation ---------> "+subStation);
            }

        }
//        String subStation = element.getElementsByTagName("train").item(0).getTextContent();

        List<String> dataFiller = new LinkedList<>();
        dataFiller.add(version);
        dataFiller.add(train);
        if (_data.getTripsData().containsKey(id)) {
            System.out.println("DUBLIKATAS ------------------------------------>" + id);
        }
        _data.setTripsData(id, dataFiller);
    }


    private Document createDocument(String fileName) {
        Document document = null;
        try {
            File xmlFile = new File("Data/" + fileName + "." + 0 + ".xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            document = dBuilder.parse(xmlFile);
            document.getDocumentElement().normalize();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return document;
    }

    private String cutLastLetter(String word) {
        word = word.substring(0, word.length() - 1);
        return word;
    }
}