package com.gov.cmr.transparisation_module;

import com.gov.cmr.transparisation_module.service.CategorieService;
import com.gov.cmr.transparisation_module.service.FichePortefeuilleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TransparisationModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransparisationModuleApplication.class, args);
    }


}
