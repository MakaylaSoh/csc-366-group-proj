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
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.UniqueConstraint;

import javax.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Delivery {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private LocalTime time;
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_contract_id", nullable = false)
    private SupplierContract supplier_contract;

    public Delivery() { }

    public Delivery(Long id, LocalTime time, LocalDate date, Store store, SupplierContract supplier_contract) {
        this.id = id;
        this.time = time;
        this.date = date;
        this.store = store;
        this.supplier_contract = supplier_contract;
    }

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getTime() {
        return this.time;
    }
    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalDate getDate() {
        return this.date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Store getStore() {
        return this.store;
    }
    public void setStore(Store store) {
        this.store = store;
    }

    public SupplierContract getSupplierContract() {
        return this.supplier_contract;
    }
    public void setSupplierContract(SupplierContract supplier_contract) {
        this.supplier_contract = supplier_contract;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("," , Delivery.class.getSimpleName() + "[" , "]");
        sj.add(id.toString()).add(time.toString()).add(date.toString()).add(store.toString()).add(supplier_contract.toString());
        return sj.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Delivery)) return false;
        return id != null && id.equals(((Delivery) o).getId());
    }
}
