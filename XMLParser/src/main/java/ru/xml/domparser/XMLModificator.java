package ru.xml.domparser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class XMLModificator {
    public static void main(String[] args) {
        File xmlFile = new File(XMLModificator.class.getClassLoader().getResource("users.xml").getPath());
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;

        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();

            //updateElementVal(document);

            addNewElement(document);

            //deleteElement(document);
            
            writeXmlFile(document);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    private static void writeXmlFile(Document document) throws TransformerException {
        document.getDocumentElement().normalize();
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(
                new File(XMLModificator.class.getClassLoader().getResource("").getPath()
                                 + File.separator
                                 + "users_updated.xml"));
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(source, result);
        System.out.println("users.xml file was updated successful and was saved into users_updated.xml file!!!");
    }

    private static void updateElementVal(Document document) {
        NodeList nodes = document.getElementsByTagName("user");
        Element user;

        for (int i = 0; i < nodes.getLength(); i++) {
            user = (Element) nodes.item(i);
            Node name = user.getElementsByTagName("firstName").item(0).getFirstChild();
            name.setNodeValue(name.getNodeValue().toUpperCase());
        }
    }

    private static void addNewElement(Document document) {
        NodeList nodes = document.getElementsByTagName("user");
        Element user;

        for (int i = 0; i < nodes.getLength(); i++) {
            user = (Element) nodes.item(i);
            Element newElement = document.createElement("salary");
            newElement.appendChild(document.createTextNode(String.valueOf(new Random().nextInt(10000))));
            user.appendChild(newElement);
        }
    }

    private static void deleteElement(Document document) {
        NodeList nodes = document.getElementsByTagName("user");
        Element user;

        for (int i = 0; i < nodes.getLength(); i++) {
            user = (Element) nodes.item(i);
            Node idElement = user.getElementsByTagName("id").item(0);
            user.removeChild(idElement);
        }
    }
}
