package com.tecsup.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Ec4DawRodriguezJuanApplication implements CommandLineRunner {

    @Autowired
    private PasswordEncoder passwordEncoder; // ✅ correcto
    public static void main(String[] args) {
        SpringApplication.run(Ec4DawRodriguezJuanApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String password = "56789";
        for (int i = 0; i < 2; i++) {
            String bcryptPassword = passwordEncoder.encode(password);
            System.out.println(bcryptPassword);
        }
    }
}
