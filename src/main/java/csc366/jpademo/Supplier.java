package csc366.jpademo;

import java.util.StringJoiner;

import javax.persistence.*;

import javax.validation.constraints.NotNull;

@Entity  // indicates that this class maps to a database table
@Table(
        name = "supplier",     // may be omitted for default table naming
        uniqueConstraints = @UniqueConstraint(columnNames={"id"}) // requires @Column(name=...)
)
public class Supplier {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private SupplierProduct supplier_product_id;

    public Supplier() { }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public SupplierProduct getSupplier_product_id() {return supplier_product_id; }

    public void setSupplier_product_id(SupplierProduct id) {this.supplier_product_id = id; }
    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("," , Customer.class.getSimpleName() + "[" , "]");
        sj.add(id.toString()).add(supplier_product_id.toString());
        return sj.toString();
    }
}
