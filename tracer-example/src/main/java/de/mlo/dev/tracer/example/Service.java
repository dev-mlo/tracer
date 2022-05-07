package de.mlo.dev.tracer.example;

@org.springframework.stereotype.Service
public class Service {

    public void delay(long delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
