package ru.xml.saxparser;

import org.xml.sax.SAXException;
import ru.xml.common.res.entity.User;
import ru.xml.saxparser.handler.MySAXHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class MySAXParser {
    public static void main(String[] args) {
        SAXParserFactory factory = SAXParserFactory.newInstance();

        try {
            SAXParser parser = factory.newSAXParser();
            MySAXHandler handler = new MySAXHandler();
            parser.parse(new File(MySAXParser.class.getClassLoader().getResource("users.xml").getPath()), handler);
            List<User> users = handler.getUserList();

            users.stream().forEach(x -> System.out.println(x));
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
