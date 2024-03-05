package csc366.jpademo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.TestPropertySource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource(properties = {
	"spring.main.banner-mode=off",
	"spring.jpa.hibernate.ddl-auto=update",
	"logging.level.root=ERROR",
	"logging.level.csc366=DEBUG",

	"logging.level.org.hibernate.SQL=DEBUG",
	"logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE", // display prepared statement parameters
	"spring.jpa.properties.hibernate.format_sql=true",
	"spring.jpa.show-sql=false",   // prevent duplicate logging
	"spring.jpa.properties.hibernate.show_sql=false",	
	
	"logging.pattern.console= %d{yyyy-MM-dd HH:mm:ss} - %msg%n"
})
@TestMethodOrder(OrderAnnotation.class)
public class TestSWS {

    private final static Logger log = LoggerFactory.getLogger(TestSWS.class);
    
    @Autowired
    private ScheduledWorkShiftRepository scheduledWorkShiftRepository;

    private final ScheduledWorkShift sw = new ScheduledWorkShift(LocalDate.now(), LocalTime.of(17, 59, 59), 
                                                    LocalTime.of(23, 59, 59), 6.0);
    
    @BeforeEach
    private void setup() {
	    scheduledWorkShiftRepository.saveAndFlush(sw);
    }
    
    @Test
    @Order(1)
    public void testSaveScheduledWorkShift() {
        Long swId = sw.getId();

        ScheduledWorkShift sw2 = scheduledWorkShiftRepository.findByScheduledWorkShiftId(swId);

        log.info(sw2.toString());
        
        assertNotNull(sw);
        assertEquals(sw2.getInTime(), sw.getInTime());
        assertEquals(sw2.getOutTime(), sw.getOutTime());
        assertEquals(sw2.getTotalHours(), sw.getTotalHours());
    }
    
    @Test
    @Order(2)
    public void testDeleteByScheduledWorkShiftId() {
        Long swId = sw.getId();
        scheduledWorkShiftRepository.deleteByScheduledWorkShiftId(swId);

        ScheduledWorkShift retSw = scheduledWorkShiftRepository.findByScheduledWorkShiftId(swId);
        assertNull(retSw);
    }

}
