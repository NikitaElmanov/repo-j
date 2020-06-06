package ru.xml.domparser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class FromClassToXML {
    public static void main(String[] args) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            //creating and putting root 'users' node into document
            Element usersNode = doc.createElement("users");
            doc.appendChild(usersNode);

            usersNode.appendChild(createUserNode(doc, "1", "Tom", "Tomsom", "23", "Male"));

            usersNode.appendChild(createUserNode(doc, "2", "Ann", "Aniston", "34", "Female"));

            usersNode.appendChild(createUserNode(doc, "3", "Lil", "Hatson", "21", "Male"));

            writeIntoXML(doc);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    private static void writeIntoXML(Document doc) throws TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);

        StreamResult console = new StreamResult(System.out);
        StreamResult result = new StreamResult(new File(FromClassToXML.class.getClassLoader().getResource("").getPath() + "users_output.xml"));

        transformer.transform(source, result);
        transformer.transform(source, console);
    }

    private static Node createUserNode(Document doc, String id, String name, String surname, String age, String gender) {
        Element user = doc.createElement("user");
        user.setAttribute("id", id);

        user.appendChild(createUserProp(doc, user, "name", name));
        user.appendChild(createUserProp(doc, user, "surname", surname));
        user.appendChild(createUserProp(doc, user, "gender", gender));

        return user;
    }

    private static Node createUserProp(Document doc, Element user, String prop, String propVal) {
        Element textNode = doc.createElement(prop);
        textNode.appendChild(doc.createTextNode(propVal));
        return textNode;
    }
}
