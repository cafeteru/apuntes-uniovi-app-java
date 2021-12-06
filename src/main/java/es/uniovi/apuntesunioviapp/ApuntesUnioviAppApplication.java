package es.uniovi.apuntesunioviapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class ApuntesUnioviAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApuntesUnioviAppApplication.class, args);
    }

}
