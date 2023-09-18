package com.ZTPAI2023.GoldenOaks;

import com.ZTPAI2023.GoldenOaks.book.Book;
import com.ZTPAI2023.GoldenOaks.book.BookRepository;
import com.ZTPAI2023.GoldenOaks.history.History;
import com.ZTPAI2023.GoldenOaks.history.HistoryRepository;
import com.ZTPAI2023.GoldenOaks.history.Status;
import com.ZTPAI2023.GoldenOaks.userAccount.UserAccount;
import com.ZTPAI2023.GoldenOaks.userAccount.UserAccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabse(UserAccountRepository userAccountRepository, HistoryRepository historyRepository, BookRepository bookRepository) {
        return args -> {
            log.info("Preloading " + userAccountRepository.save(new UserAccount("usermail@a-mail.com", "pass1234", "John", "Galt", "480-555-7632", "Phoenix", "Example Street 14", "85050")));
            log.info("Preloading " + userAccountRepository.save(new UserAccount("sample@abc-mail.com", "password", "Mike", "Anon", "281-555-4389", "Houston", "Other Street 26/5", "77214")));
            log.info("Preloading " + historyRepository.save(new History(1, 2, Status.DELIVERED)));
            log.info("Preloading " + historyRepository.save(new History(1, 3, Status.IN_PROGRESS)));
            log.info("Preloading " + bookRepository.save(new Book("Lord of the Rings", "J.R.R. Tolkien", "29.07.1954", "ART-STRING-HERE")));
        };
    }
}

//curl -X POST localhost:8080/users -H 'Content-type:application/json' -d '{"email": "anon@mail", "pass": "1234", "name": "Anon", "surname": "Emoose", "phoneNumber": "111-555-3322", "city": "Dallas", "streetAddress": "SomeStreet 63", "postalCode": "555902"}'
//curl -X POST localhost:8080/books -H 'Content-type:application/json' -d '{"title": "Lord of the Rings", "author": "J.R.R. Tolkien", "publishingDate": "29.07.1954", "coverart": "ART-STRING-HERE"}'
