package com.example.programmers_auction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ProgrammerAuctionApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProgrammerAuctionApplication.class, args);
    }

}
