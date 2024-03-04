package csc366.jpademo;

import java.util.StringJoiner;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.UniqueConstraint;

import java.time.LocalDate;

@Entity
@Table(
    name = "employee",     // may be omitted for default table naming
    uniqueConstraints = @UniqueConstraint(columnNames={"last_name", "first_name"}) // requires @Column(name=...) 
)
public class Employee {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) // generates id
    private Long id;

    @Column(name="first_name")
    private String firstName;

    @Column(unique=true, name="last_name")
    private String lastName;

    @Column(name="birthday")
    private LocalDate birthday;

    @Column(name="position")
    private String position;
    
    // each employee only has a single store
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    // each employee only has one bank account
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id")
    private Bank bank;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_shift_id")
    private List<ScheduledWorkShift> shifts = new ArrayList<>();


    public Employee() { }
    
    public Employee(String firstName, String lastName, LocalDate birthday, String position, Store store, Bank bank, Employee manager) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.position = position;
        this.store = store;
        this.bank = bank;
        // this.manager = manager;
    }

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
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

    public LocalDate getBirthday() {
        return this.birthday;
    }
    
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getPosition() {
        return this.position;
    }
    
    public void setPosition(String position) {
        this.position = position;
    }

    public Store getStore() {
        return this.store;
    }
    
    public void setStore(Store store) {
        this.store = store;
    }

    public Bank getBank() {
        return this.bank;
    }
    
    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public void setShifts(ArrayList<ScheduledWorkShift> shifts) {
        this.shifts = shifts;
    }
    public List<ScheduledWorkShift> getShifts() {
        return this.shifts;
    }

    // public Employee getManager() {
    //     return this.manager;
    // }
    
    // public void setManager(Employee manager) {
    //     this.manager = manager;
    // }    

    @Override
    public String toString() {
	    StringJoiner sj = new StringJoiner("," , Employee.class.getSimpleName() + "[" , "]");
	    sj.add(id.toString()).add(firstName).add(lastName).add(position).add("store="+store.toString()).add("bank="+bank.toString());
	    return sj.toString();
    }
}
