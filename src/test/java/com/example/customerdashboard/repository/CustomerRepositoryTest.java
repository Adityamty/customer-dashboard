package com.example.customerdashboard.repository;

import com.example.customerdashboard.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testFindByEmailContaining() {
        Customer customer = new Customer("John Doe", "john.doe@example.com", LocalDate.now());
        customerRepository.save(customer);

        List<Customer> found = customerRepository.findByEmailContaining("john");
        assertFalse(found.isEmpty());
        assertTrue(found.stream().anyMatch(c -> c.getEmail().equals("john.doe@example.com")));
    }

    @Test
    public void testFindByRegisteredDateAfter() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        Customer customer = new Customer("Jane Smith", "jane.smith@example.com", LocalDate.now());
        customerRepository.save(customer);

        List<Customer> found = customerRepository.findByRegisteredDateAfter(yesterday);
        assertFalse(found.isEmpty());
        assertTrue(found.stream().anyMatch(c -> c.getName().equals("Jane Smith")));
    }

    @Test
    public void testFindByName() {
        Customer customer = new Customer("Alice", "alice@example.com", LocalDate.now());
        customerRepository.save(customer);

        List<Customer> found = customerRepository.findByName("Alice");
        assertFalse(found.isEmpty());
        assertEquals("Alice", found.get(0).getName());
    }

    @Test
    public void testFindByNameNative() {
        Customer customer = new Customer("Bob", "bob@example.com", LocalDate.now());
        customerRepository.save(customer);

        List<Customer> found = customerRepository.findByNameNative("Bob");
        assertFalse(found.isEmpty());
        assertEquals("Bob", found.get(0).getName());
    }
}
