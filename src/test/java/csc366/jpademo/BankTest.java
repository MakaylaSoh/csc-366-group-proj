// package csc366.jpademo;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;


// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.TestMethodOrder;
// import org.junit.jupiter.api.Order;
// import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.springframework.test.context.ContextConfiguration;
// import org.springframework.test.context.junit.jupiter.SpringExtension;
// import org.springframework.test.context.TestPropertySource;

// import java.util.List;
// import java.util.ArrayList;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

// import java.time.LocalDate;
// import java.time.LocalTime;

// @ExtendWith(SpringExtension.class)
// @DataJpaTest
// @TestPropertySource(properties = {
// 	"spring.main.banner-mode=off",
// 	"logging.level.root=ERROR",
// 	"logging.level.csc366=DEBUG",

// 	"spring.jpa.hibernate.ddl-auto=update",
// 	"spring.datasource.url=jdbc:mysql://mysql.labthreesixsix.com/csc366",
// 	"spring.datasource.username=jpa",
// 	"spring.datasource.password=demo",
	
// 	"logging.level.org.hibernate.SQL=DEBUG",
// 	"logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE", // display prepared statement parameters
// 	"spring.jpa.properties.hibernate.format_sql=true",
// 	"spring.jpa.show-sql=false",   // prevent duplicate logging
// 	"spring.jpa.properties.hibernate.show_sql=false",	
	
// 	"logging.pattern.console= %d{yyyy-MM-dd HH:mm:ss} - %msg%n"
// })
// @TestMethodOrder(OrderAnnotation.class)
// public class BankTest {
//     private final static Logger log = LoggerFactory.getLogger(BankTest.class);

//     @Autowired
//     private BankRepository bankRepository;
//     @Autowired
//     private EmployeeRepository employeeRepository;
//     private final Store store1= new Store("TestCountry", "TestState", "TestCity", "TestStreet", 12345);
//     private final LocalDate birthday = LocalDate.of(2020, 1, 8);
//     private  Employee employee1 = new Employee("firstName1", "lastName1", birthday, "manager", store1, null); 
//     private  Bank bank1 = new Bank("firstName1", "lastName1",  "0", "bank1", null);

    

//     @BeforeEach
//     private void setup() {
//         bankRepository.saveAndFlush(bank1);
//         employee1.setBank(bank1);
//         bank1.setEmployee(employee1);
//         bankRepository.saveAndFlush(bank1);
//         employeeRepository.saveAndFlush(employee1);

//     }

//     @Test
//     @Order(1)
//     public void testStore() {
//         Bank bankTest = bankRepository.findByAccountNumber("0");
//         assertNotNull(bankTest);
//     }
// }