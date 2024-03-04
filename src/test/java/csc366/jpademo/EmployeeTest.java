package csc366.jpademo;

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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;
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
public class EmployeeTest {
	private final static Logger log = LoggerFactory.getLogger(EmployeeTest.class);

    @Autowired
    private StoreRepository storeRepository;
	@Autowired
    private EmployeeRepository employeeRepository;
	@Autowired
    private BankRepository bankRepository;

	private final Store store1 = new Store("country1", "state1", "city1", "street1", 11111);
	ScheduledWorkShift shift1 = new ScheduledWorkShift(LocalDate.of(2024, 5, 10), LocalTime.of(9, 30), LocalTime.of(5, 30), 8.0);
	private final List<ScheduledWorkShift> shifts = new ArrayList<ScheduledWorkShift>();
	private final Bank bank1 = new Bank("first1", "last1", "123456789", "Capital One", null);
    private final Employee emp1 = new Employee("First1", "Last1", LocalDate.of(2002, 9, 12), "position1", store1, bank1, shifts);

    @BeforeEach
    private void setup() {
        // add some stores to the repo
		shifts.add(shift1);
        storeRepository.saveAndFlush(store1);
		bankRepository.saveAndFlush(bank1);
		employeeRepository.saveAndFlush(emp1);
		bank1.setEmployee(emp1);
		bankRepository.saveAndFlush(bank1);
    }

	@Test
    @Order(1)
    public void testFindById() {
        Long empId = emp1.getId();
        Employee retEmp = employeeRepository.findByEmployeeId(empId);

        log.info(retEmp.toString());

        assertNotNull(retEmp);
        assertEquals(retEmp, emp1); 
    }

	@Test
    @Order(2)
    public void testFindByStore() {
        List<Employee> retEmp = employeeRepository.findByStore(store1);

        log.info(retEmp.toString());

        assertNotNull(retEmp);
        assertEquals(retEmp.size(), 1); 
    }

	@Test
    @Order(3)
    public void testUpdatePosition() {
        Employee retEmp = employeeRepository.updatePosition(emp1.getId(), "Manager");

        log.info(retEmp.toString());

        assertNotNull(retEmp);
        assertEquals(retEmp.getPosition(), "Manager"); 
    }

	@Test
    @Order(3)
    public void testDelete() {
        employeeRepository.deleteByEmployeeId(emp1.getId());
		Employee retEmp = employeeRepository.findByEmployeeId(emp1.getId());

		assertNull(retEmp);
    }
    
}
