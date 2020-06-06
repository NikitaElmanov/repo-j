package ru.xml.domparser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.xml.common.res.entity.Gender;
import ru.xml.common.res.entity.User;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FromXMLToClass {
    public static void main(String[] args) {
        File xmlFile = new File(FromXMLToClass.class.getClassLoader().getResource("users.xml").getPath());

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;

        try {

            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            System.out.println("root element has name : " + doc.getDocumentElement().getNodeName());

            NodeList listNodes = doc.getElementsByTagName("user");

            List<User> users = getFilledUsers(listNodes);

            for (User emp: users) {
                System.out.println(emp.toString());
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<User> getFilledUsers(NodeList listNodes) {
        List<User> users = new ArrayList<>();

        for (int i = 0; i < listNodes.getLength(); i++) {
            users.add(getUser(listNodes.item(i)));
        }

        return users;
    }

    private static User getUser(Node item) {
        User user = new User();

        if(item.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) item;
            user.setId(Integer.parseInt(getTagValue("id", element)));
            user.setFirstName(getTagValue("firstName", element));
            user.setLastName(getTagValue("lastName", element));
            user.setAge(Integer.parseInt(getTagValue("age", element)));
            user.setGender(getTagValue("gender", element).equalsIgnoreCase("MALE") ? Gender.MALE : Gender.FEMALE);
        }

        return user;
    }

    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }
}
