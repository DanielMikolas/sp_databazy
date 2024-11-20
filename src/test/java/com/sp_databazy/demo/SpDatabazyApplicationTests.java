package com.sp_databazy.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;

@SpringBootTest
public class SpDatabazyApplicationTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    @Test
    public void testConnection() {
        try {
            // Tento SQL dotaz slúži na získanie počtu študentov
            Integer studentCount = jdbcTemplate.queryForObject("SELECT count(*) FROM student", Integer.class);
            System.out.println("Number of students: " + studentCount);
        } catch (Exception e) {
            // V prípade neúspešného pripojenia alebo chyby pri vykonávaní dotazu sa vypíše chyba
            System.out.println("Database connection failed: " + e.getMessage());
        }
    }
}
