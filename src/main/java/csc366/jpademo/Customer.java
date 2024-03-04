package csc366.jpademo;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import javax.persistence.*;

import javax.validation.constraints.NotNull;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity  // indicates that this class maps to a database table
@Table(
        name = "customer",     // may be omitted for default table naming
        uniqueConstraints = @UniqueConstraint(columnNames={"id"}) // requires @Column(name=...)
)
public class Customer {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="first_name")
    private String firstName;

    @NotNull
    @Column(unique=true, name="last_name")
    private String lastName;

    @OneToMany(mappedBy = "customer_id",
            fetch = FetchType.LAZY)
    private List<Receipts> order_history = new ArrayList<>();

    public Customer() { }

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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
    public void setFirstName(String firstName) {this.firstName = firstName; }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Receipts> getOrder_history() {
        return this.order_history;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("," , Customer.class.getSimpleName() + "[" , "]");
        sj.add(id.toString()).add(firstName).add(lastName).add(order_history.toString());
        return sj.toString();
    }
}
