package csc366.jpademo;

import java.util.List;
import java.util.ArrayList;
import java.util.StringJoiner;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.OrderColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.UniqueConstraint;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity  // indicates that this class maps to a database table
@Table(
    name = "work_instance" // may be omitted for default table naming
)
public class WorkInstance {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="date")
    private LocalDate date;
    
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Employee employee;

    @Column(name="clock_in_time")
    private LocalTime clock_in_time;

    @Column(name="clock_out_time")
    private LocalTime clock_out_time;

    @Column(name="total_hours")
    private Integer total_hours;

    
    public WorkInstance() { }
    
    public WorkInstance(LocalDate date, LocalTime clock_in_time, LocalTime clock_out_time, int total_hours) {
	this.date = date;
	this.clock_in_time = clock_in_time;
	this.clock_out_time = clock_out_time;
    this.total_hours = total_hours;
    }
    
    public Long getId() {
	return id;
    }
    public void setId(Long id) {
	this.id = id;
    }
    
    public LocalDate getDate() {
	return date;
    }
    public void setDate(LocalDate date) {
	this.date = date;
    }

    public LocalTime getClock_in_time() {
	return clock_in_time;
    }
    public void setLastName(LocalTime clock_in_time) {
	this.clock_in_time = clock_in_time;
    }

    public LocalTime getClock_out_time() {
	return clock_out_time;
    }
    public void setClock_out_time(LocalTime clock_out_time) {
	this.clock_out_time = clock_out_time;
    }

    public Employee getEmployee() {
	return employee;
    }
    public void setEmployee(Employee employee) {
	this.employee = employee;
    }

    public Integer getTotalHours() {
	return total_hours;
    }
    public void setTotalHours(Integer totalHours) {
	this.total_hours = total_hours;
    }


    @Override
    public String toString() {
	    StringJoiner sj = new StringJoiner("," , Person.class.getSimpleName() + "[" , "]");
	    sj.add(id.toString()).add(date.toString()).add(clock_in_time.toString()).add(clock_out_time.toString()).add(total_hours.toString()).add("employee="+employee.toString());
	    return sj.toString();
    }
    
}
