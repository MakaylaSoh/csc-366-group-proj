package csc366.jpademo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long>{
    //GET
    @Query("select b from Bank b where b.id = :bankId")
    Bank findByBankId(@Param("bankId") Long bankId);
    List<Bank> findByFirstNameAndLastName(String firstName, String lastName);
    Bank findByAccountNumber(String accountNumber);
    List<Bank> findByName(String name);
    Bank findByEmployee(Employee employee);

    //UPDATE 
    @Modifying
    @Query("update Bank b set b.firstName = :newFirstName where b.id = :bankId")
    Employee updateFirstName(@Param("bankId") Long bankId, @Param("newFirstName") String newFirstName);

    @Modifying
    @Query("update Bank b set b.lastName = :newLastName where b.id = :bankId")
    Employee updateLastName(@Param("bankId") Long bankId, @Param("newLastName") String newLastName);

    @Modifying
    @Query("update Bank b set b.accountNumber = :newAccountNum where b.id = :bankId")
    Employee updateAccountNumber(@Param("bankId") Long bankId, @Param("newAccountNum") String newAccountNum);

    @Modifying
    @Query("update Bank b set b.name = :newBankName where b.id = :bankId")
    Employee updateBankName(@Param("bankId") Long bankId, @Param("newBankName") String newBankName);


    // DELETE
    @Modifying
    @Query("select b from Bank b where b.id = :bankId")
    long deleteByBankId(@Param("bankId") Long bankId);

}