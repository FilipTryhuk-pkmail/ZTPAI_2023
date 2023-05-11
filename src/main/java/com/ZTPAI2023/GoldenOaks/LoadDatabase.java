package com.ZTPAI2023.GoldenOaks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabse(UserAccountRepository userAccountRepository, HistoryRepository historyRepository) {
        return args -> {
            log.info("Preloading " + userAccountRepository.save(new UserAccount("usermail@a-mail.com", "pass1234", "John", "Galt", "480-555-7632", "Phoenix", "Example Street 14", "85050")));
            log.info("Preloading " + userAccountRepository.save(new UserAccount("sample@abc-mail.com", "password", "Mike", "Anon", "281-555-4389", "Houston", "Other Street 26/5", "77214")));
            log.info("Preloading " + historyRepository.save(new History(1, 2, Status.DELIVERED)));
            log.info("Preloading " + historyRepository.save(new History(1, 3, Status.IN_PROGRESS)));
        };
    }
}

//curl -X POST localhost:8080/users -H 'Content-type:application/json' -d '{"email": "anon@mail", "pass": "1234", "name": "Anon", "surname": "Emoose", "phoneNumber": "111-555-3322", "city": "Dallas", "streetAddress": "SomeStreet 63", "postalCode": "555902"}'