package com.ZTPAI2023.GoldenOaks;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
class UserAccountAssembler implements RepresentationModelAssembler<UserAccount, EntityModel<UserAccount>> {
    @Override
    public EntityModel<UserAccount> toModel(UserAccount userAccount) {
        return EntityModel.of(userAccount,
                linkTo(methodOn(UserAccountController.class).one(userAccount.getId())).withSelfRel(),
                linkTo(methodOn(UserAccountController.class).all()).withRel("users"));
    }
}
