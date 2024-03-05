package csc366.jpademo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkInstanceRepository extends JpaRepository<WorkInstance, Long>{
    //GET
    @Query("select wi from Work_Instance wi where wi.id = :workInstanceID")
    WorkInstance findByWorkInstanceID(@Param("workInstanceID") Long workInstanceID);
    List<WorkInstance> findByDate(LocalDate Date);
    List<WorkInstance> findByClockInTime(LocalTime clockInTime);

    List<WorkInstance> findByClockOutTime(LocalTime clockOutTime);
    List<WorkInstance> findByEmployee(Employee employee);

    //UPDATE - no updates, a customer can only have one account with legal first/last name

    //DELETE
    @Modifying
    @Query("delete from Work_Instance wi where wi.id = :workInstanceId")
    void deleteByWorkInstanceId(@Param("workInstanceId") Long workInstanceId);
}