package csc366.jpademo;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import javax.persistence.*;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import javax.validation.constraints.NotNull;

@Entity  // indicates that this class maps to a database table
@Table(
        name = "product",     // may be omitted for default table naming
        uniqueConstraints = @UniqueConstraint(columnNames={"id"}) // requires @Column(name=...)
)
public class Product {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier_id;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @NotNull
    @Column(name="cost")
    private Float cost;

    @Column(name="quantity")
    private Integer quantity;

    public Product(Float cost) {
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Supplier getSupplier_id() {
        return supplier_id;
    }
    public void setSupplier_id(Supplier supplier_id) {
        this.supplier_id = supplier_id;
    }

    public Float getCost() {
        return cost;
    }
    public void setCost(Float cost) {
        this.cost = cost;
    }

    public Integer getQuantity() { return quantity; }

    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("," , Customer.class.getSimpleName() + "[" , "]");
        sj.add(id.toString()).add(supplier_id.toString()).add(cost.toString());
        return sj.toString();
    }
}
