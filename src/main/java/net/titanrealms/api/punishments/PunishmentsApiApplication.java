package net.titanrealms.api.punishments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@EnableSpringDataWebSupport
@SpringBootApplication
public class PunishmentsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PunishmentsApiApplication.class, args);
    }

}
