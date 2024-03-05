package csc366.jpademo;

import java.util.Set;
import java.util.HashSet;
import java.util.StringJoiner;
import java.util.*;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.UniqueConstraint;

import javax.validation.constraints.NotNull;

@Entity
public class SupplierContract {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private List<Store> stores = new ArrayList<>();

    public SupplierContract () {}

    public SupplierContract(Supplier supplier, List<Store> stores) {
        this.supplier = supplier;
        this.stores = stores;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Supplier getSupplier() {
        return supplier;
    }
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public List<Store> getStores() {
        return stores;
    }
    public void setStore(List<Store> stores) {
        this.stores = stores;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("," , SupplierContract.class.getSimpleName() + "[" , "]");
        sj.add(id.toString()).add(supplier.toString()).add(stores.toString());
        return sj.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SupplierContract)) return false;
        return id != null && id.equals(((SupplierContract) o).getId());
    }
}
