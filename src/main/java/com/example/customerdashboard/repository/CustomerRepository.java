package com.example.customerdashboard.repository;

import com.example.customerdashboard.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // Find customers whose email contains the given keyword
    List<Customer> findByEmailContaining(String keyword);

    // Find customers registered after the given date
    List<Customer> findByRegisteredDateAfter(LocalDate date);

    // JPQL query to find customers by name
    @Query("SELECT c FROM Customer c WHERE c.name = :name")
    List<Customer> findByName(@Param("name") String name);

    // Native query to find customers by name
    @Query(value = "SELECT * FROM customers WHERE name = ?1", nativeQuery = true)
    List<Customer> findByNameNative(String name);
}
