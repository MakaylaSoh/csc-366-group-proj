package csc366.jpademo;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource(properties = {
        "spring.main.banner-mode=off",
        "logging.level.root=ERROR",
        "logging.level.csc366=DEBUG",

        "spring.jpa.hibernate.ddl-auto=update",
        "spring.datasource.url=jdbc:mysql://mysql.labthreesixsix.com/csc366",
        "spring.datasource.username=jpa",
        "spring.datasource.password=demo",

        "logging.level.org.hibernate.SQL=DEBUG",
        "logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE", // display prepared statement parameters
        "spring.jpa.properties.hibernate.format_sql=true",
        "spring.jpa.show-sql=false",   // prevent duplicate logging
        "spring.jpa.properties.hibernate.show_sql=false",

        "logging.pattern.console= %d{yyyy-MM-dd HH:mm:ss} - %msg%n"
})
@TestMethodOrder(OrderAnnotation.class)
public class WorkInstanceTest {
    private final static Logger log = LoggerFactory.getLogger(WorkInstanceTest.class);

    @Autowired
    private WorkInstanceRepository workInstanceRepository;

    LocalDate date1 = LocalDate.of(2024, 3, 4);
    LocalTime timeIn1 = LocalTime.of(6, 15);
    LocalTime timeOut1 = LocalTime.of(7, 15);

    LocalTime timeIn2 = LocalTime.of(6, 30);
    LocalTime timeOut2 = LocalTime.of(7, 30);
    private final WorkInstance wi1 = new WorkInstance(date1, timeIn1, timeOut1);
    private final WorkInstance wi2 = new WorkInstance(date1, timeIn2, timeOut2);

    @BeforeEach
    private void setup() {
        // add some stores to the repo
        workInstanceRepository.saveAndFlush(wi1);
        workInstanceRepository.saveAndFlush(wi2);
    }

    @Test
    @Order(1)
    public void testFindById() {
        Long wi1Id = wi1.getId();
        WorkInstance retWI = workInstanceRepository.findByWorkInstanceID(wi1Id);

        log.info(retWI.toString());

        assertNotNull(retWI);
        assertEquals(retWI, wi1);

    }

    @Test
    @Order(2)
    public void testFindByDate() {
        List<WorkInstance> retWI = workInstanceRepository.findByDate(date1);

        log.info(retWI.toString());

        assertNotNull(retWI);
        assertEquals(retWI.size(), 2);
    }

    @Test
    @Order(3)
    public void testFindByClockInTime() {
        List<WorkInstance> retWI = workInstanceRepository.findByClockInTime(timeIn1);

        log.info(retWI.toString());

        assertNotNull(retWI);
        assertEquals(retWI.size(), 1);
    }

    @Test
    @Order(4)
    public void testFindByClockOutTime() {
        List<WorkInstance> retWI = workInstanceRepository.findByClockOutTime(timeOut2);

        log.info(retWI.toString());

        assertNotNull(retWI);
        assertEquals(retWI.size(), 1);
    }

    @Test
    @Order(5)
    public void testDeleteByWorkInstanceId() {
        Long wi1Id = wi1.getId();
        workInstanceRepository.deleteByWorkInstanceId(wi1Id);

        WorkInstance retWI = workInstanceRepository.findByWorkInstanceID(wi1Id);
        assertNull(retWI);
    }
}