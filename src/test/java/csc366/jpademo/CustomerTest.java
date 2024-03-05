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
public class CustomerTest {
    private final static Logger log = LoggerFactory.getLogger(CustomerTest.class);

    @Autowired
    private CustomerRepository customerRepository;

    private final Customer customer1 = new Customer("First1", "Last1");
    private final Customer customer2 = new Customer("First1", "Last2");
    private final Customer customer3 = new Customer("First2", "Last3");
    private final Customer customer4 = new Customer("First2", "Last4");

    @BeforeEach
    private void setup() {
        // add some stores to the repo
        customerRepository.saveAndFlush(customer1);
        customerRepository.saveAndFlush(customer2);
        customerRepository.saveAndFlush(customer3);
        customerRepository.saveAndFlush(customer4);
    }

    @Test
    @Order(1)
    public void testFindById() {
        Long customer3Id = customer3.getId();
        Customer retCustomer = customerRepository.findByCustomerID(customer3Id);

        log.info(retCustomer.toString());

        assertNotNull(retCustomer);
        assertEquals(retCustomer, customer3);

    }

    @Test
    @Order(2)
    public void testFindByFirstName() {
        List<Customer> retCustomer = customerRepository.findByFirstName("First1");

        log.info(retCustomer.toString());

        assertNotNull(retCustomer);
        assertEquals(retCustomer.size(), 2);
    }

    @Test
    @Order(3)
    public void testFindByLastName() {
        List<Customer> retCustomer = customerRepository.findByLastName("Last1");

        log.info(retCustomer.toString());

        assertNotNull(retCustomer);
        assertEquals(retCustomer.size(), 1);
    }

    @Test
    @Order(4)
    public void testDeleteByCustomerId() {
        Long customer3Id = customer3.getId();
        customerRepository.deleteCustomerByCustomerId(customer3Id);

        Customer retCustomer = customerRepository.findByCustomerID(customer3Id);
        assertNull(retCustomer);
    }
}