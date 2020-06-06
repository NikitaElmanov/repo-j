package ru.xml.jaxb;

import ru.xml.jaxb.entity.Book;
import ru.xml.jaxb.entity.BookStore;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class FromXMLToClassesJAXB {
    public static void main(String[] args) {
        try {
            JAXBContext context = JAXBContext.newInstance(BookStore.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            BookStore store = (BookStore) unmarshaller.unmarshal(new File(FromXMLToClassesJAXB
                                                    .class
                                                    .getClassLoader()
                                                    .getResource("jaxb_bookstore.xml")
                                                    .getPath()));
//            System.out.println(store.getName() + "\r\n" + store.getLocation());
            List<Book> books = store.getBookList();
            books.stream()
                    .forEach(System.out::println);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}
