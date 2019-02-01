package org.arpit.java2blog.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "flag")
public class Flag {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "shape")
    private String shape;

    public Flag() { }

    public Flag(String shape) {
        this.shape = shape;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    @Override
    public String toString() {
        return shape;
    }
}
