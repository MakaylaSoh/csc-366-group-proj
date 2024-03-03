package csc366.jpademo;

import java.util.Set;
import java.util.HashSet;
import java.util.StringJoiner;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.UniqueConstraint;

import javax.validation.constraints.NotNull;

@Entity
public class Store {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) // generates id
    private Long id;

    @Column(name="country")
    private String country;  

    @Column(name="state")
    private String state;

    @Nullable
    @Column(name="city")
    private String city;  

    @Column(name="street")
    private String street;  
    
    @Column(name="zipcode")
    private int zipcode;
    
    public Store() { }
    
    public Store(String country, String state, String city, String street, int zipcode) {
        this.country = country;
        this.state = state;
        this.cty = city;
        this.street = street;
        this.zipCode = zipCode;
    }
    
    public Long getId() {
	    return id;
    }

    public void setId(Long id) {
	    this.id = id;
    }

    public String getCountry() {
	    return state;
    }

    public void setCountry(String state) {
	    this.state = state;
    }

    public String getState() {
	    return state;
    }

    public void setState(String state) {
	    this.state = state;
    }

    public String getCity() {
	    return city;
    }

    public void setCity(String city) {
	    this.city = city;
    }

    public String getStreet() {
	    return street;
    }

    public void setStreet(String street) {
	    this.street = street;
    }

    public int getZipcode() {
	    return zipCode;
    }

    public void setZipcode(int zipcode) {
	    this.zipCode = zipCode;
    }

    @Override
    public String toString()
    {
	    StringJoiner sj = new StringJoiner("," , Address.class.getSimpleName() + "[" , "]");
	    sj.add(id.toString()).add(country).add(state).add(city).add(street).add(zipcode);
	    return sj.toString();
    }    
}
