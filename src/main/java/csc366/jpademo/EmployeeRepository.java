package csc366.jpademo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // GET
    @Query("select e from Employee e where e.id = :employeeId")
    Employee findByEmployeeId(@Param("employeeId") Long employeeId);
    Employee findByFirstNameAndLastName(String firstName, String lastName);
    Employee findByLastNameAndStore(String lastName, Store store);
    List<Employee> findByPosition(String position);
    List<Employee> findByStore(Store store);
    List<Employee> findByPositionAndStore(String position, Store store);
    List<Employee> findByBirthdayBefore(String date);

    // UPDATE
    @Modifying
    @Query("update Employee e set e.firstName = :newFirstName where e.id = :employeeId")
    Employee updateFirstName(@Param("employeeId") Long employeeId, @Param("newFirstName") String newFirstName);

    @Modifying
    @Query("update Employee e set e.lastName = :newLastName where e.id = :employeeId")
    Employee updateLastName(@Param("employeeId") Long employeeId, @Param("newLastName") String newLastName);

    @Modifying
    @Query("update Employee e set e.birthday = :newBirthday where e.id = :employeeId")
    Employee updateLastName(@Param("employeeId") Long employeeId, @Param("newBirthday") LocalDate newBirthday);

    @Modifying
    @Query("update Employee e set e.birthday = :newBirthday where e.id = :employeeId")
    Employee updateBirthday(@Param("employeeId") Long employeeId, @Param("newBirthday") LocalDate newBirthday);

    @Modifying
    @Query("update Employee e set e.store = :newStore where e.id = :employeeId")
    Employee updateStore(@Param("employeeId") Long employeeId, @Param("newStore") Store newStore);

    @Modifying
    @Query("update Employee e set e.bank = :newBank where e.id = :employeeId")
    Employee updateBank(@Param("employeeId") Long employeeId, @Param("newBank") Bank newBank);

    @Modifying
    @Query("update Employee e set e.position = :newPosition where e.id = :employeeId")
    Employee updatePosition(@Param("employeeId") Long employeeId, @Param("newPosition") String newPosition);

    // DELETE
    @Query("select e from Employee e where e.id = :employeeId")
    long deleteByEmployeeId(@Param("employeeId") Long employeeId);
}