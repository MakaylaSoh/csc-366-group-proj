package csc366.jpademo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
    //GET
    @Query("select c from Customer c where c.id = :customerId")
    Customer findByCustomerID(@Param("customerId") Long customerId);
    List<Customer> findByFirstName(String firstName);
    List<Customer> findByLastName(String lastName);

    //UPDATE - no updates, a customer can only have one account with legal first/last name

    //DELETE
    @Modifying
    @Query("delete from Customer c where c.id = :customerId")
    void deleteCustomerByCustomerId(@Param("customerId") Long customerId);
}