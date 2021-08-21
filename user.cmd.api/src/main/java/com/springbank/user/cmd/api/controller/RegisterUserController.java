package com.springbank.user.cmd.api.controller;

import com.springbank.user.cmd.api.commands.RegisterUserCommand;
import com.springbank.user.cmd.api.dto.RegisterUserResponse;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/registerUser")
public class RegisterUserController {
    private final CommandGateway commandGateway;

    @Autowired
    public RegisterUserController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    public ResponseEntity<RegisterUserResponse> registerUser(@RequestBody RegisterUserCommand command) {
        command.setId(UUID.randomUUID().toString());

        try {
            commandGateway.sendAndWait(command);
            return new ResponseEntity<>(new RegisterUserResponse("User successfuly registered"), HttpStatus.CREATED);
        } catch (Exception e) {
            String safeErrorMessage = "Error while processing register user  request for id - " + command.getId();
            System.out.println(e.toString());

            return new ResponseEntity<>(new RegisterUserResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}