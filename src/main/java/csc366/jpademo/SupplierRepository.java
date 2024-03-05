package csc366.jpademo;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long>{
    // GET
    @Query("from Supplier s where s.id = :supplierId")
    Supplier findBySupplierId(@Param("supplierId") Long supplierId);

    // UPDATE
    @Modifying
    @Query("update Supplier s set s.id = :newId where s.id = :oldId")
    void updateId(@Param("oldId") Long oldId, @Param("newId") Long newId);

    // DELETE  
    @Modifying
    @Query("delete from Supplier s where s.id = :supplierId")
    void deleteBySupplierId(@Param("supplierId") Long supplierId);
} 
