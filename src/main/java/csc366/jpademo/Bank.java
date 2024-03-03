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
    uniqueConstraints = @UniqueConstraint(columnNames={"account_number", "first_name", "last_name"}) // requires @Column(name=...) 
)
public class Bank {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) // generates id
    private Long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="account_number")
    private String account_number;

    @Column(name="bank_name")
    private String bank_name;

    // each bank only has one employee associated
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Bank() { }
    
    public Bank(String firstName, String lastName, String account_number, String bank_name, Employee employee) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.account_number = account_number;
        this.bank_name = bank_name;
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
        return this.account_number;
    }
    
    public void setAccountNumber(String account_number) {
        this.account_number = account_number;
    }

    public String getBankName() {
        return this.bank_name;
    }
    
    public void setBankName(String bank_name) {
        this.bank_name = bank_name;
    }

    public Employee getEmployee() {
        return this.employee;
    }
    
    public void setEmployee(Bank bank) {
        this.employee = employee;
    }

    @Override
    public String toString() {
	    StringJoiner sj = new StringJoiner("," , Person.class.getSimpleName() + "[" , "]");
	    sj.add(id.toString()).add(firstName).add(lastName).add(account_number).add("employee="+employee.toString());
	    return sj.toString();
    }
}
