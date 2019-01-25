package org.arpit.java2blog.model;

import javax.persistence.*;

@Entity
@Table(name = "flag")
public class Flag {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "c_id")
    private Country country;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ss_id")
    private StaticShape staticShape;

    public Flag() { }

    public int getId() {
        return id;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StaticShape getStaticShape() {
        return staticShape;
    }

    public void setStaticShape(StaticShape staticShape) {
        this.staticShape = staticShape;
    }
}
