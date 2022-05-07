package de.mlo.dev.tracer.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ServiceTest {

    @Autowired
    private Service service;

    @Test
    void testDelay(){
        service.delay(1000);
    }
}