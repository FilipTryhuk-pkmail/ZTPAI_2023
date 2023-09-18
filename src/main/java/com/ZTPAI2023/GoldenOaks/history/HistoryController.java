package com.ZTPAI2023.GoldenOaks.history;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class HistoryController {
    private final HistoryRepository historyRepository;
    private final HistoryModelAssembler assembler;
    @Autowired
    HistoryController(HistoryRepository historyRepository, HistoryModelAssembler assembler) {
        this.historyRepository = historyRepository;
        this.assembler = assembler;
    }

    @GetMapping("/history")
    CollectionModel<EntityModel<History>> all() {
        List<EntityModel<History>> historyAll = historyRepository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(historyAll, linkTo(methodOn(HistoryController.class).all()).withSelfRel());
    }

    @GetMapping("/history/{id}")
    EntityModel<History> one(@PathVariable Long id) {
        History history = historyRepository.findById(id).orElseThrow(() -> new HistoryNotFoundException(id));
        return assembler.toModel(history);
    }

    @PostMapping("/history")
    ResponseEntity<EntityModel<History>> newHistory(@RequestBody History history) {
        history.setBookReceived(Status.IN_PROGRESS);
        History newHistory = historyRepository.save(history);
        return ResponseEntity.created(linkTo(methodOn(HistoryController.class).one(newHistory.getId())).toUri())
                .body(assembler.toModel(newHistory));
    }

    @DeleteMapping("/history/{id}/cancel")
    ResponseEntity<?> cancel(@PathVariable Long id) {
        History history = historyRepository.findById(id).orElseThrow(() -> new HistoryNotFoundException(id));
        if (history.getBookReceived() == Status.IN_PROGRESS) {
            history.setBookReceived(Status.CANCELLED);
            return ResponseEntity.ok(assembler.toModel(historyRepository.save(history)));
        }
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(Problem.create().withTitle("Method not allowed").withDetail("History with the status: " + history.getBookReceived() + " cannot be canceled."));
    }

    @PutMapping("/history/{id}/delivered")
    ResponseEntity<?> delivered(@PathVariable Long id) {
        History history = historyRepository.findById(id).orElseThrow(() -> new HistoryNotFoundException(id));
        if (history.getBookReceived() == Status.IN_PROGRESS) {
            history.setBookReceived(Status.DELIVERED);
            return ResponseEntity.ok(assembler.toModel(historyRepository.save(history)));
        }
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(Problem.create().withTitle("Method not allowed").withDetail("History with the status: " + history.getBookReceived() + " cannot be marked as delivered."));
    }
}
