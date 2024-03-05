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
public class StoreTest {
    private final static Logger log = LoggerFactory.getLogger(StoreTest.class);

    @Autowired
    private StoreRepository storeRepository;

    private final Store store1 = new Store("Country1", "State1", "City1", "Street1", 11111);
    private final Store store2 = new Store("Country1", "State2", "City2", "Street2", 22222);
    private final Store store3 = new Store("Country2", "State3", "City2", "Street3", 33333);
    private final Store store4 = new Store("Country2", "State3", "City2", "Street4", 33333);

    @BeforeEach
    private void setup() {
        // add some stores to the repo
        storeRepository.saveAndFlush(store1);
        storeRepository.saveAndFlush(store2);
        storeRepository.saveAndFlush(store3);
        storeRepository.saveAndFlush(store4);
    }

    @Test
    @Order(1)
    public void testFindById() {
        Long store3Id = store3.getId();
        Store retStore = storeRepository.findByStoreId(store3Id);

        log.info(retStore.toString());

        assertNotNull(retStore);
        assertEquals(retStore, store3);
        
    }

    @Test
    @Order(2)
    public void testFindByCountry() {
        List<Store> retStore = storeRepository.findByCountry("Country1");

        log.info(retStore.toString());

        assertNotNull(retStore);
        assertEquals(retStore.size(), 2);
    }

    @Test
    @Order(3)
    public void testFindByCountryAndState() {
        List<Store> expected = new ArrayList<Store>();
        expected.add(store2);
        List<Store> retStore = storeRepository.findByCountryAndState("Country1", "State2");

        log.info(retStore.toString());

        assertNotNull(retStore);
        assertEquals(retStore.size(), 1);
        assertEquals(retStore, expected);

    }

    @Test
    @Order(4)
    public void testFindByCountryAndStateAndCity() {
        List<Store> expected = new ArrayList<Store>();
        expected.add(store3);
        expected.add(store4);
        List<Store> retStore = storeRepository.findByCountryAndStateAndCity("Country2", "State3", "City2");

        log.info(retStore.toString());

        assertNotNull(retStore);
        assertEquals(retStore.size(), 2);
        assertEquals(retStore, expected);
    }

    @Test
    @Order(5)
    public void testFindByCountryAndStateAndCityAndStreet() {
        List<Store> expected = new ArrayList<Store>();
        expected.add(store4);
        List<Store> retStore = storeRepository.findByCountryAndStateAndCityAndStreet("Country2", "State3", "City2", "Street4");

        log.info(retStore.toString());

        assertNotNull(retStore);
        assertEquals(retStore.size(), 1);
        assertEquals(retStore, expected);
    }

    @Test
    @Order(6)
    public void testFindByCountryAndStateAndCityAndStreetAndZipcode() {
        List<Store> expected = new ArrayList<Store>();
        expected.add(store4);
        List<Store> retStore = storeRepository.findByCountryAndStateAndCityAndStreetAndZipcode("Country2", "State3", "City2", "Street4", 33333);

        log.info(retStore.toString());

        assertNotNull(retStore);
        assertEquals(retStore.size(), 1);
        assertEquals(retStore, expected);
    }

    @Test
    @Order(7)
    public void testFindByZipcode() {
        List<Store> expected = new ArrayList<Store>();
        expected.add(store3);
        expected.add(store4);
        List<Store> retStore = storeRepository.findByZipcode(33333);

        log.info(retStore.toString());

        assertNotNull(retStore);
        assertEquals(retStore.size(), 2);
        assertEquals(retStore, expected);
    }

    @Test
    @Order(8)
    public void testDeleteByStoreId() {
        Long store3Id = store3.getId();
        storeRepository.deleteStoreByStoreId(store3Id);
        
        Store retStore = storeRepository.findByStoreId(store3Id);
        assertNull(retStore);
    }
}
