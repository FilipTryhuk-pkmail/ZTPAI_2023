package com.ZTPAI2023.GoldenOaks.transaction;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class TransactionController {
    private final TransactionRepository repository;
    private final TransactionAssembler assembler;

    @Autowired
    TransactionController(TransactionRepository repository, TransactionAssembler assembler) {
        this.assembler = assembler;
        this.repository = repository;
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/transactions")
    CollectionModel<EntityModel<Transaction>> all() {
        List<EntityModel<Transaction>> transactions = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(transactions, linkTo(methodOn(TransactionController.class).all()).withSelfRel());
    }

    @GetMapping("/transactions/{id}")
    EntityModel<Transaction> one(@PathVariable Long id) {
        Transaction transaction = repository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException(id));
        return assembler.toModel(transaction);
    }

    @PostMapping("/transactions")
    ResponseEntity<?> newTransaction(@RequestBody Transaction newTransaction) {
        EntityModel<Transaction> entityModel = assembler.toModel(repository.save(newTransaction));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping("/transactions/{id}")
    ResponseEntity<?> replaceTransaction(@RequestBody Transaction newTransaction, @PathVariable Long id) {
        Transaction updateTransaction = repository.findById(id)
                .map(transaction -> {
                    transaction.setTransactionDate(newTransaction.getTransactionDate());
                    transaction.setBookReceived(newTransaction.getBookReceived());
                    transaction.setBookId(newTransaction.getBookId());
                    transaction.setComments(newTransaction.getComments());
                    return repository.save(transaction);
                })
                .orElseGet(() -> {
                    newTransaction.setId(id);
                    return repository.save(newTransaction);
                });
        EntityModel<Transaction> entityModel = assembler.toModel(updateTransaction);
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/transactions/{id}")
    ResponseEntity<?> deleteTransaction(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
