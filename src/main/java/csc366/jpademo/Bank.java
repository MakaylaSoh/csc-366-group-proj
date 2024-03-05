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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.UniqueConstraint;

import javax.validation.constraints.NotNull;

@Entity
@Table(
    name = "bank",     // may be omitted for default table naming
    uniqueConstraints = @UniqueConstraint(columnNames={"accountNumber", "first_name", "last_name"}) // requires @Column(name=...) 
)
public class Bank {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) // generates id
    private Long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="accountNumber")
    private String accountNumber;

    @Column(name="name")
    private String name;

    // each bank only has one employee associated
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Bank() { }
    
    public Bank(String firstName, String lastName, String accountNumber, String name, Employee employee) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountNumber = accountNumber;
        this.name = name;
        this.employee = employee;
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

    public String getAccountNumber() {
        return this.accountNumber;
    }
    
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public Employee getEmployee() {
        return this.employee;
    }
    
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
	    StringJoiner sj = new StringJoiner("," , Bank.class.getSimpleName() + "[" , "]");
	    sj.add(id.toString()).add(firstName).add(lastName).add(accountNumber).add(name).add("employee="+employee.toString());
	    return sj.toString();
    }
}
