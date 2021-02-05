package be.fooda.backend.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableEurekaClient
public class StoreApp {
    public static void main(String[] args) {
        SpringApplication.run(StoreApp.class, args);
    }
}
