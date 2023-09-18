package com.ZTPAI2023.GoldenOaks.userAccount;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserAccountController {
    private final UserAccountRepository repository;
    private final UserAccountAssembler assembler;

    @Autowired
    UserAccountController(UserAccountRepository repository, UserAccountAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/users")
    CollectionModel<EntityModel<UserAccount>> all() {
        List<EntityModel<UserAccount>> userAccounts = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(userAccounts, linkTo(methodOn(UserAccountController.class).all()).withSelfRel());
    }
    // end::get-aggregate-root[]

    @PostMapping("/users")
    ResponseEntity<?> newUserAccount(@RequestBody UserAccount newUserAccount) {
        EntityModel<UserAccount> entityModel = assembler.toModel(repository.save(newUserAccount));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @GetMapping("/users/{id}")
    EntityModel<UserAccount> one(@PathVariable Long id) {
        UserAccount userAccount = repository.findById(id)
                .orElseThrow(() -> new UserAccountNotFoundException(id));
        return assembler.toModel(userAccount);
    }

    @PutMapping("/users/{id}")
    ResponseEntity<?> replaceUserAccount(@RequestBody UserAccount newUserAccount, @PathVariable Long id) {
        UserAccount updatedUserAccount = repository.findById(id)
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
        EntityModel<UserAccount> entityModel = assembler.toModel(updatedUserAccount);
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/users/{id}")
    ResponseEntity<?> deleteUserAccount(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }
}