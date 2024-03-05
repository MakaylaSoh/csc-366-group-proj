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
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.UniqueConstraint;

import javax.validation.constraints.NotNull;

@Entity  // indicates that this class maps to a database table
@Table(
    name = "work_shift",     // may be omitted for default table naming
    uniqueConstraints = @UniqueConstraint(columnNames={"employee_id", "clock_in_time"}) // requires @Column(name=...) 
)
public class ScheduledWorkShift {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="date")
    private LocalDate date;

    
    @NotNull
    @Column(name="clock_in_time")
    private LocalTime inTime;

    @Column(name = "clock_out_time")
    private LocalTime outTime;

    @Column(name = "total_hours")
    private double totalHours;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;
    
    public ScheduledWorkShift() { }
    
    public ScheduledWorkShift(LocalDate date, LocalTime inTime, LocalTime outTime, double totalHours) {
        this.date = date;
        this.inTime = inTime;
        this.outTime = outTime;
        this.totalHours = totalHours;
    }
    
    public Long getId() {
	return id;
    }
    public void setId(Long id) {
	this.id = id;
    }

    public LocalTime getInTime() {
        return this.inTime;
    }

    public void setInTime(LocalTime inTime) {
        this.inTime = inTime;
    }

    public LocalTime getOutTime() {
        return this.outTime;
    }

    public void setOutTime(LocalTime outTime) {
        this.outTime = outTime;
    }

    public void setTotalHours(double totalHours) {
        this.totalHours = totalHours;
    }

    public double getTotalHours() {
        return this.totalHours;
    }


    

    
}
