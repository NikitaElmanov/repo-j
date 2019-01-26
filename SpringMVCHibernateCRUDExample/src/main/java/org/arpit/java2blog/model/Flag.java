package org.arpit.java2blog.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "flag")
public class Flag {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @Column(name = "shape")
    private String shape;

    @OneToMany(mappedBy = "flag", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Country> countries;

    public Flag() { }

    public int getId() {
        return id;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return shape;
    }
}
