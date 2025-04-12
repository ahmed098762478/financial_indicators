package com.gov.cmr.transparisation_module;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class TransparisationModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransparisationModuleApplication.class, args);
    }

    @Bean
    public CommandLineRunner run() {
        return args -> {
            // Créer un instance de BCryptPasswordEncoder
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            // Afficher les mots de passe cryptés
            System.out.println("admin : " + encoder.encode("admin"));
            System.out.println("admin1 : " + encoder.encode("admin1"));
            System.out.println("admin2 : " + encoder.encode("admin2"));
            System.out.println("admin3 : " + encoder.encode("admin3"));
        };
    }
}
