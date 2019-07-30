package com.study.jong;

import com.study.jong.persistence.ch002.JpaMain;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

@SpringBootApplication
public class BootApplication implements CommandLineRunner {
    public static void main(String[] args){
        SpringApplication.run(BootApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {
        JpaMain main = new JpaMain();
        main.run();
    }
}
