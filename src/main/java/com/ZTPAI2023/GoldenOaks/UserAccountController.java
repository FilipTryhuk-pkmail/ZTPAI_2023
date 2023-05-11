package com.ZTPAI2023.GoldenOaks;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class UserAccountController {
    private final UserAccountRepository repository;

    UserAccountController(UserAccountRepository repository) {
        this.repository = repository;
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/users")
    List<UserAccount> all() {
        return repository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/users")
    UserAccount newUserAccount(@RequestBody UserAccount newUserAccount) {
        return repository.save(newUserAccount);
    }

    @GetMapping("/users/{id}")
    UserAccount one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new UserAccountNotFoundException(id));
    }

    @PutMapping("/users/{id}")
    UserAccount replaceUserAccount(@RequestBody UserAccount newUserAccount, @PathVariable Long id) {
        return repository.findById(id)
                .map(userAccount -> {
                    userAccount.setEmail(newUserAccount.getEmail());
                    userAccount.setPass(newUserAccount.getPass());
                    userAccount.setName(newUserAccount.getName());
                    userAccount.setSurname(newUserAccount.getSurname());
                    userAccount.setPhoneNumber(newUserAccount.getPhoneNumber());
                    userAccount.setCity(newUserAccount.getCity());
                    userAccount.setStreetAddress(newUserAccount.getStreetAddress());
                    userAccount.setPostalCode(newUserAccount.getPostalCode());
                    return repository.save(userAccount);
                })
                .orElseGet(() -> {
                    newUserAccount.setId(id);
                    return repository.save(newUserAccount);
                });
    }

    @DeleteMapping("/users/{id}")
    void deleteUserAccount(@PathVariable Long id) {
        repository.deleteById(id);
    }
}