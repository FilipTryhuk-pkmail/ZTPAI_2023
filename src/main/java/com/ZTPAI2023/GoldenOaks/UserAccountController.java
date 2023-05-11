package com.ZTPAI2023.GoldenOaks;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserAccountController {
    private final UserAccountRepository repository;
    private final UserAccountAssembler assembler;

    UserAccountController(UserAccountRepository repository, UserAccountAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/users")
    CollectionModel<EntityModel<UserAccount>> all() {
        List<EntityModel<UserAccount>> userAccounts = repository.findAll().stream()
                .map(userAccount -> EntityModel.of(userAccount,
                        linkTo(methodOn(UserAccountController.class).one(userAccount.getId())).withSelfRel(),
                        linkTo(methodOn(UserAccountController.class).all()).withRel("users")))
                .collect(Collectors.toList());
        return CollectionModel.of(userAccounts, linkTo(methodOn(UserAccountController.class).all()).withSelfRel());
    }
    // end::get-aggregate-root[]

    @PostMapping("/users")
    UserAccount newUserAccount(@RequestBody UserAccount newUserAccount) {
        return repository.save(newUserAccount);
    }

    @GetMapping("/users/{id}")
    EntityModel<UserAccount> one (@PathVariable Long id) {
        UserAccount userAccount = repository.findById(id)
                .orElseThrow(() -> new UserAccountNotFoundException(id));
        return EntityModel.of(userAccount,
                linkTo(methodOn(UserAccountController.class).one(id)).withSelfRel(),
                linkTo(methodOn(UserAccountController.class).all()).withRel("users"));
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