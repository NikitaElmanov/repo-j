package org.arpit.java2blog.model;

import javax.persistence.*;

/*
 * This is our model class and it corresponds to Country table in database
 */
@Entity
@Table(name="COUNTRY")
public class Country{
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;

	@Column(name = "name")
	String countryName;

	@Column(name = "population")
	int population;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "f_id")
	private Flag flag;

    public Country() { }

    public Country(String countryName, int population) {
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

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void setId(int id) {
        this.id = id;
    }
}