package com.ZTPAI2023.GoldenOaks.history;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class HistoryModelAssembler implements RepresentationModelAssembler<History, EntityModel<History>> {
    @Override
    public EntityModel<History> toModel(History history) {
        EntityModel<History> historyModel = EntityModel.of(history,
                linkTo(methodOn(HistoryController.class).one(history.getId())).withSelfRel(),
                linkTo(methodOn(HistoryController.class).all()).withRel("history"));

        if (history.getBookReceived() == Status.IN_PROGRESS) {
            historyModel.add(linkTo(methodOn(HistoryController.class).cancel(history.getId())).withRel("cancel"));
            historyModel.add(linkTo(methodOn(HistoryController.class).delivered(history.getId())).withRel("delivered"));
        }

        return historyModel;
    }
}
