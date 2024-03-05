package csc366.jpademo;

import java.time.LocalDate;
import java.util.StringJoiner;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity  // indicates that this class maps to a database table
@Table(
        name = "receipts",     // may be omitted for default table naming
        uniqueConstraints = @UniqueConstraint(columnNames={"id"}) // requires @Column(name=...)
)
public class Order {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="receipt_date")
    private LocalDate receipt_date;

    @Column(name="total_cost")
    private Float total_cost;

    @Column(name="rewards_earned")
    private Integer rewards_earned;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = true)
    private Customer customer_id;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private List<Product> products = new ArrayList<>();;

    

    public Order(LocalDate receipt_date, Float total_cost, Integer rewards_earned, ArrayList<Product> products) {
        this.receipt_date = receipt_date;
        this.total_cost = total_cost;
        this.rewards_earned = rewards_earned;
        this.products = products;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getReceipt_date() {
        return receipt_date;
    }

    public void setReceipt_date(LocalDate receipt_date) {
        this.receipt_date = receipt_date;
    }

    public Float getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(Float total_cost) {
        this.total_cost = total_cost;
    }

    public Integer getRewards_earned() {
        return rewards_earned;
    }

    public void setRewards_earned(Integer rewards_earned) {
        this.rewards_earned = rewards_earned;
    }

    public Customer getCustomer_id() {
        return customer_id;
    }
    public void setCustomer_id(Customer customer_id) {
        this.customer_id = customer_id;
    }

    public List<Product> getProducts() {
        return this.products;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("," , Customer.class.getSimpleName() + "[" , "]");
        sj.add(id.toString()).add(receipt_date.toString()).add(total_cost.toString()).add(rewards_earned.toString()).add(customer_id.toString()).add(products.toString());
        return sj.toString();
    }


}
