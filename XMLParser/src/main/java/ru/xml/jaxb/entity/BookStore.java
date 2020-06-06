package ru.xml.jaxb.entity;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlRootElement
@XmlType(propOrder = {
        "name",
        "bookList"
})

public class BookStore {

//    @XmlElementWrapper(name = "books")
//    @XmlElement(name = "book")
    private List<Book> bookList;
    private String name;
    @XmlTransient
    private String location;

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
