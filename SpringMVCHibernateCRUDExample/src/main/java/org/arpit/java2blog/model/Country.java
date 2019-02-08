package org.arpit.java2blog.model;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/*
 * This is our model class and it corresponds to Country table in database
 */
@Entity
@Table(name="COUNTRY")
public class Country{
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name = "name")
	private String countryName;

	@Column(name = "population")
	private Integer population;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "f_id")
	private Flag flag;

    public Country() { }

    public Country(String countryName, Integer population) {
        this.countryName = countryName;
        this.population = population;
    }

    public int getId() {
        return id;
    }

    public Flag getFlag() {
        return flag;
    }

    public void setFlag(Flag flag) {
        this.flag = flag;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public void setId(int id) {
        this.id = id;
    }
}