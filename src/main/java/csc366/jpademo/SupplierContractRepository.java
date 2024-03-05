package csc366.jpademo;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

@Repository
public interface SupplierContractRepository extends JpaRepository<SupplierContract, Long>{
    // GET
    @Query("from SupplierContract s where s.id = :supplierContractId")
    SupplierContract findBySupplierContractId(@Param("supplierContractId") Long supplierContractId);
    List<SupplierContract> findBySupplier(Supplier supplier);

    // UPDATE
    @Modifying
    @Query("update SupplierContract s set s.supplier = :newSupplier where s.supplier = :oldSupplier")
    void updateSupplier(@Param("oldSupplier") Supplier oldSupplier, @Param("newSupplier") Supplier newSupplier);

    // DELETE  
    @Modifying
    @Query("delete from SupplierContract s where s.id = :supplierContractId")
    void deleteBySupplierContractId(@Param("supplierContractId") Long supplierContractId);
} 