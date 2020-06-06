package ru.xml.saxparser.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ru.xml.common.entity.Gender;
import ru.xml.common.entity.User;

import java.util.ArrayList;
import java.util.List;

public class MySAXHandler extends DefaultHandler {

    private boolean hasFirstName;
    private boolean hasLastName;
    private boolean hasAge;
    private boolean hasGander;

    private List<User> userList;
    private User user;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equalsIgnoreCase("user")) {

            String id = attributes.getValue("id");

            user = new User();
            user.setId(Integer.parseInt(id));

            if (userList == null) {
                userList = new ArrayList<>();
            }
        } else if (qName.equalsIgnoreCase("firstName")) {
            hasFirstName = true;
        } else if (qName.equalsIgnoreCase("lastName")) {
            hasLastName = true;
        } else if (qName.equalsIgnoreCase("age")) {
            hasAge = true;
        } else if (qName.equalsIgnoreCase("gender")) {
            hasGander = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("user")) {
            userList.add(user);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        if (hasFirstName) {
            String firstName = new String(ch, start, length);
            user.setFirstName(firstName);
            hasFirstName = false;
        } else if (hasLastName) {
            String lastName = new String(ch, start, length);
            user.setLastName(lastName);
            hasLastName = false;
        } else if (hasAge) {
            String age = new String(ch, start, length);
            user.setAge(Integer.parseInt(age));
            hasAge = false;
        } else if (hasGander) {
            String gender = new String(ch, start, length);
            user.setGender(gender.equalsIgnoreCase("Male") ? Gender.MALE : Gender.FEMALE);
            hasGander = false;
        }
    }

    public List<User> getUserList() {
        return userList;
    }
}
