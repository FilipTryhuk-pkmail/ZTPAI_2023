package com.ZTPAI2023.GoldenOaks.book;

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
public class BookController {

    private final BookRepository repository;

    private final BookAssember assembler;
    @Autowired
    BookController(BookRepository repository, BookAssember assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/books")
    CollectionModel<EntityModel<Book>> all() {
        List<EntityModel<Book>> books = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(books, linkTo(methodOn(BookController.class).all()).withSelfRel());
    }

    @GetMapping("/books/{id}")
    EntityModel<Book> one(@PathVariable Long id) {
        Book book  = repository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
        return assembler.toModel(book);
    }

    @PostMapping("/books")
    ResponseEntity<?> newBook(@RequestBody Book newBook) {
        EntityModel<Book> entityModel = assembler.toModel(repository.save(newBook));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping("/books/{id}")
    ResponseEntity<?> replaceBook(@RequestBody Book newBook, @PathVariable Long id) {
        Book updateBook = repository.findById(id)
                .map(book -> {
                    book.setAuthor(newBook.getAuthor());
                    book.setTitle(newBook.getTitle());
                    book.setCurrentOwner(newBook.getCurrentOwner());
                    book.setPublishingDate(newBook.getPublishingDate());
                    book.setSecondaryAuthors(newBook.getSecondaryAuthors());
                    book.setCoverArt(newBook.getCoverArt());
                    return repository.save(book);
                })
                .orElseGet(() -> {
                    newBook.setId(id);
                    return repository.save(newBook);
                });
        EntityModel<Book> entityModel = assembler.toModel(updateBook);
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/books/{id}")
    ResponseEntity<?> deleteBook(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
