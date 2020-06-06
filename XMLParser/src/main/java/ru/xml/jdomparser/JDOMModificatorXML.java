package ru.xml.jdomparser;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class JDOMModificatorXML {
    public static void main(String[] args) {
        SAXBuilder builder = new SAXBuilder();
        try {
            Document doc = builder.build(new File(JDOMModificatorXML.class.getClassLoader().getResource("users.xml").getPath()));
            Element root = doc.getRootElement();

            List<Element> elements = root.getChildren("user");

            for (Element e : elements) {
                //updating firstName tag
                String name = e.getChildText("firstName");
                if (name != null) {
                    e.getChild("firstName").setText(name.toUpperCase());
                }

                //modifying id attribute
                String gender = e.getChildText("gender");
                if (gender != null && gender.equalsIgnoreCase("Male")) {
                    String id = e.getAttributeValue("id");
                    e.getAttribute("id").setValue(id + "M");
                } else {
                    String id = e.getAttributeValue("id");
                    e.getAttribute("id").setValue(id + "F");
                }

                //deleting gender tag
                e.removeChild("gender");

                e.addContent(new Element("salary").setText(String.valueOf(new Random().nextInt(10000))));
            }

            XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());

            outputter.output(doc, new FileOutputStream(
                    new File(JDOMModificatorXML.class.getClassLoader().getResource("").getPath()
                                     + File.separator
                                     + "modif_users.xml")));

        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
