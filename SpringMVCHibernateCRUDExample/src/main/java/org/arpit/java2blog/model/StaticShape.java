package org.arpit.java2blog.model;

import javax.persistence.*;

@Entity
@Table(name = "staticShape")
public class StaticShape {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @Column(name="shape")
    String shape;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "staticShape")
    private Flag flag;

    public StaticShape() { }

    public StaticShape(String shape) {
        this.shape = shape;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public Flag getFlag() {
        return flag;
    }

    public void setFlag(Flag flag) {
        this.flag = flag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return shape;
    }
}
