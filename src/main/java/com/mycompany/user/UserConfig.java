package com.mycompany.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository){
        return args -> {
            User lourenz = new User(
                "lourenzengels@gmail.com",
                "King6",
                "Lourenz",
                "Engels"
            );

            /*userRepository.saveAll(
                    List.of(lourenz)
            );*/
        };
    }
}
