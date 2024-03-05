package csc366.jpademo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.*;
import java.util.Arrays;

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

// SupplierContract: Add, list, and remove SupplierContract instances

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
public class TestSupplierContract {
    private final static Logger log = LoggerFactory.getLogger(TestSupplierContract.class);

    @Autowired
    private SupplierContractRepository supplierContractRepository;
    private SupplierRepository supplierRepository;

    private final Supplier supplier = new Supplier();
    private final SupplierContract sc = new SupplierContract(supplier, Arrays.asList(new Store()));  // "reference" person
    
    @BeforeEach
    private void setup() {
        Supplier savedSupplier = supplierRepository.saveAndFlush(supplier);
        sc.setSupplier(savedSupplier);
	    supplierContractRepository.saveAndFlush(sc);
    }

    @Test
    @Order(1)
    public void testSaveSupplierContract() {
        Long scId = sc.getId();
	    
        SupplierContract sc2 = supplierContractRepository.findBySupplierContractId(scId);

	    log.info(sc2.toString());
	
	    assertNotNull(sc);
        assertEquals(sc2.getSupplier(), sc.getSupplier());
        assertEquals(sc2.getStores(), sc.getStores());
    }

    @Test
    @Order(2)
    public void testDeleteBySupplierContractId() {
        Long scId = sc.getId();
        supplierContractRepository.deleteBySupplierContractId(scId);

        SupplierContract retSc = supplierContractRepository.findBySupplierContractId(scId);
        assertNull(retSc);
    }
}