package ru.xml.jdomparser;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import ru.xml.common.entity.Gender;
import ru.xml.common.entity.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FromXMLToClassByJDOM {
    public static void main(String[] args) {
        try {
            SAXBuilder builder = new SAXBuilder();
            Document doc = builder.build(new File(FromXMLToClassByJDOM.class.getClassLoader().getResource("users.xml").getPath()));

            Element root = doc.getRootElement();
            List<Element> elements = root.getChildren("user");
            List<User> users = new ArrayList<>();
            User user;

            for (Element e : elements) {
                user = new User();
                user.setId(Integer.parseInt(e.getAttributeValue("id")));
                user.setFirstName(e.getChildText("firstName"));
                user.setLastName(e.getChildText("lastName"));
                user.setAge(Integer.parseInt(e.getChildText("age")));
                user.setGender(e.getChildText("gender").equalsIgnoreCase("Male") ? Gender.MALE : Gender.FEMALE);
                users.add(user);
            }

            users.stream()
                    .forEach(System.out::println);
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
