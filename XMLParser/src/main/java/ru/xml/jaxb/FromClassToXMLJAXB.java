package ru.xml.jaxb;

import ru.xml.jaxb.entity.Book;
import ru.xml.jaxb.entity.BookStore;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.Arrays;

public class FromClassToXMLJAXB {
    public static void main(String[] args) {
        Book book = new Book();
//        book.setAuthor("Lora");
        book.setId(1);
        book.setName("Bobobob");
        book.setPublisher("Publisher1-ONe");

        Book book2 = new Book();
//        book2.setAuthor("Dan");
        book2.setId(2);
        book2.setName("Catch myself");
        book2.setPublisher("Publisher2-Two");

        BookStore store = new BookStore();
        store.setName("Ozon");
//        store.setLocation("Ryazan");
        store.setBookList(Arrays.asList(book, book2));

        try {
            convertClassesToXML(store);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private static void convertClassesToXML(BookStore store) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(BookStore.class);
        Marshaller marshaller = context.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        //output to console
        //marshaller.marshal(store, System.out);

        //output to file
        marshaller.marshal(store,
                           new File(FromClassToXMLJAXB.class.getClassLoader().getResource("").getPath()
                                            + File.separator
                                            + "jaxb_bookstore.xml"));
    }
}
