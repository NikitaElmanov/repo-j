package ru.xml.jdomparser;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import ru.xml.common.entity.Gender;

import java.io.FileWriter;
import java.io.IOException;

public class FromClassToXMLJDOM {
    public static void main(String[] args) {
        Document doc = new Document();
        doc.setRootElement(new Element("Users"));

        doc.getRootElement().addContent(createElement("1", "Tom", Gender.MALE, "23"));
        doc.getRootElement().addContent(createElement("2", "Ann", Gender.FEMALE, "13"));
        doc.getRootElement().addContent(createElement("3", "Nikita", Gender.MALE, "22"));

        XMLOutputter outPutter = new XMLOutputter(Format.getPrettyFormat());
        try {
            FileWriter writer = new FileWriter(FromClassToXMLJDOM.class.getClassLoader().getResource("").getPath() + "jdom_users.xml");
            outPutter.output(doc, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Element createElement(String id, String name, Gender gender, String age) {
        Element user = new Element("User");
        user.setAttribute("id", id);
        user.addContent(new Element("Name").setText(name));
        user.addContent(new Element("Gender").setText(gender.toString()));
        user.addContent(new Element("Age").setText(age));
        return user;
    }
}
