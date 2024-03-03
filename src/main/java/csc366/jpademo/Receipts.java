package csc366.jpademo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;

import javax.persistence.*;


@Entity  // indicates that this class maps to a database table
@Table(
        name = "receipts",     // may be omitted for default table naming
        uniqueConstraints = @UniqueConstraint(columnNames={"id"}) // requires @Column(name=...)
)
public class Receipts {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

//    @OneToMany(mappedBy = "receipts",
//            fetch = FetchType.LAZY)
//    private List<Product> receipt_products = new ArrayList<>();

    @Column(name="receipt_date")
    private LocalDate receipt_date;

    @Column(name="total_cost")
    private Float total_cost;

    @Column(name="rewards_earned")
    private Integer rewards_earned;

    public Receipts(LocalDate receipt_date, Float total_cost, Integer rewards_earned) {
        this.receipt_date = receipt_date;
        this.total_cost = total_cost;
        this.rewards_earned = rewards_earned;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

//    public List<Product> getReceipt_products() {
//        return this.receipt_products;
//    }

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

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("," , Customer.class.getSimpleName() + "[" , "]");
        sj.add(id.toString()).add(receipt_date.toString()).add(total_cost.toString()).add(rewards_earned.toString());
        return sj.toString();
    }


}
