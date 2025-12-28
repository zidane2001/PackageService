package com.company.logistics.package_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
@EnableDiscoveryClient
public class PackageServiceApplication {

    static {
        try {

            Dotenv dotenv = Dotenv.configure()
                    .ignoreIfMissing()
                    .load();

            dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
            System.out.println(".env file loaded successfully");
        } catch (Exception e) {
            System.err.println("Failed to load .env file: " + e.getMessage());
           
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(PackageServiceApplication.class, args);
    }
}