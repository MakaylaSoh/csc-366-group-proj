package csc366.jpademo;

import java.util.List;
import java.util.ArrayList;
import java.util.StringJoiner;

import java.time.LocalDate;
import java.time.LocalTime;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.OrderColumn;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.UniqueConstraint;

import javax.validation.constraints.NotNull;

@Entity  // indicates that this class maps to a database table
@Table(
    name = "work_shift",     // may be omitted for default table naming
    uniqueConstraints = @UniqueConstraint(columnNames={"last_name", "first_name"}) // requires @Column(name=...) 
)
public class ScheduledWorkShift {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long shift_id;

    @Column(name="date")
    private LocalDate date;

    
    @NotNull
    @Column(name="clock_in_time")
    private LocalTime inTime;

    @Column(name = "clock_out_time")
    private LocalTime outTime;

    @Column(name = "total_hours")
    private double totalHours;

    @OneToMany(mappedBy = "employee_id", fetch = FetchType.LAZY)
    @JoinColumn(name = "empl_id")
    private Employee employee;
    
    public ScheduledWorkShift() { }
    
    public ScheduledWorkShift(LocalDate date, Long emplId, LocalTime inTime, LocalTime outTime, double totalHours) {
	this.date = date;
    this.emplID = emplId;
    this.inTime = inTime;
    this.outTime = outTime;
    this.totalHours = totalHours;
    }
    
    public Long getId() {
	return shift_id;
    }
    public void setId(Long id) {
	this.shift_id = id;
    }
    
    public String getFirstName() {
	return firstName;
    }
    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public String getLastName() {
	return lastName;
    }
    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public String getEmail() {
	return email;
    }
    public void setEmail(String email) {
	this.email = email;
    }

    public void addAddress(Address a) {
	addresses.add(a);
	a.setPerson(this);
    }
    public void removeAddress(Address a) {
	addresses.remove(a);
	a.setPerson(null);
    }
    public List<Address> getAddresses() {
	return this.addresses;
    }
    
    @Override
    public String toString() {
	StringJoiner sj = new StringJoiner("," , Person.class.getSimpleName() + "[" , "]");
	sj.add(id.toString()).add(firstName).add(lastName).add("addresses="+addresses.toString());
	return sj.toString();
    }

    @Override
    public int hashCode() {
	return 366;
    }
    
}
