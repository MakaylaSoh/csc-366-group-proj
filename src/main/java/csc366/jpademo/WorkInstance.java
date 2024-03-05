package csc366.jpademo;

import java.util.List;
import java.util.ArrayList;
import java.util.StringJoiner;

import javax.persistence.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity  // indicates that this class maps to a database table
@Table(
    name = "workInstance" // may be omitted for default table naming
)
public class WorkInstance {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="date")
    private LocalDate date;
    
    @NotNull
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name="clockInTime")
    private LocalTime clockInTime;

    @Column(name="clockOutTime")
    private LocalTime clockOutTime;

    
    public WorkInstance() { }
    
    public WorkInstance(LocalDate date, LocalTime clockInTime, LocalTime clockOutTime) {
	this.date = date;
	this.clockInTime = clockInTime;
	this.clockOutTime = clockOutTime;
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

    public LocalTime getClockInTime() {
	return clockInTime;
    }
    public void setClockInTime(LocalTime clock_in_time) {
	this.clockInTime = clock_in_time;
    }

    public LocalTime getClockOutTime() {
	return clockOutTime;
    }
    public void setClockOutTime(LocalTime clock_out_time) {
	this.clockOutTime = clock_out_time;
    }

    public Employee getEmployee() {
	return employee;
    }
    public void setEmployee(Employee employee) {
	this.employee = employee;
    }


    @Override
    public String toString() {
	    StringJoiner sj = new StringJoiner("," , WorkInstance.class.getSimpleName() + "[" , "]");
	    sj.add(id.toString()).add(date.toString()).add(clockInTime.toString()).add(clockOutTime.toString()).add("employee="+employee.toString());
        return sj.toString();
    }

}
