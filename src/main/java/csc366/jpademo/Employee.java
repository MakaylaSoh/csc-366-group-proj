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

    @Column(name="age")
    private int age;

    @Column(name="position")
    private String position;
    
    // each employee only has a single store
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Store store;

    // each employee only has one bank account
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Bank bank;

    // each employee may have multiple managers
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Employee manager;

    public Employee() { }
    
    public Employee(String firstName, String lastName, int age, String position, Store store, Bank bank, Employee manager) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.position = position;
        this.store = store;
        this.bank = bank;
        this.manager = manager;
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

    public int getAge() {
        return this.age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }

    public String getPosition() {
        return this.age;
    }
    
    public void setPosition(Stirng position) {
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

    public Employee getManager() {
        return this.manager;
    }
    
    public void setManager(Employee manager) {
        this.manager = manager;
    }    

    @Override
    public String toString() {
	    StringJoiner sj = new StringJoiner("," , Person.class.getSimpleName() + "[" , "]");
	    sj.add(id.toString()).add(firstName).add(lastName).add(age).add(position).add("store="=store.toString()).add("bank="=bank.toString()).add("manager="+manager.toString());
	    return sj.toString();
    }
}
