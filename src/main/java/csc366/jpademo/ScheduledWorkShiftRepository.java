package csc366.jpademo;

import java.util.*;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Scheduled;

@Repository
public interface ScheduledWorkShiftRepository extends JpaRepository<ScheduledWorkShift, Long>{
    // GET
    @Query("from ScheduledWorkShift s where s.id = :scheduledWorkShiftId")
    ScheduledWorkShift findByScheduledWorkShiftId(@Param("scheduledWorkShiftId") Long scheduledWorkShiftId);
    List<ScheduledWorkShift> findByDate(LocalDate date);
    List<ScheduledWorkShift> findByClockInTiime(LocalTime inTiime);
    List<ScheduledWorkShift> findByClockOutTime(LocalTime outTime);
    List<ScheduledWorkShift> findByTotalHours(double total_hours);
    ScheduledWorkShift findByScheduledWorkShiftEmployee(@Param("scheduledWorkShiftEmployee") Employee scheduledWorkShiftEmployee);

    // UPDATE
    @Modifying
    @Query("update ScheduledWorkShift s set s.employee = :newEmployee where s.employee = :oldEmployee")
    void updateEmployee(@Param("oldEmployee") Employee oldEmployee, @Param("newEmployee") Employee newEmployee);

    // DELETE  
    @Modifying
    @Query("delete from ScheduledWorkShift s where s.id = :scheduledWorkShiftId")
    void deleteByScheduledWorkShiftId(@Param("scheduledWorkShiftId") Long scheduledWorkShiftId);

    @Modifying
    @Query("delete from ScheduledWorkShift s where s.employee = :scheduledWorkShiftEmployee")
    void deleteByScheduledWorkShiftEmployee(@Param("scheduledWorkShiftEmployee") Long scheduledWorkShiftEmployee);
} 