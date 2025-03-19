package com.gov.cmr.transparisation_module;

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
//    @Bean
//    CommandLineRunner run(FichePortefeuilleService fichePortefeuilleService) {
//        return args -> {
//            // Use an absolute path for Windows
//            String filePath = "C:\\Users\\PC\\Desktop\\fp.xlsx"; // Update this to the actual file location
//            fichePortefeuilleService.importFromExcel(filePath);
//            System.out.println("Excel import completed.");
//        };
//    }

}
