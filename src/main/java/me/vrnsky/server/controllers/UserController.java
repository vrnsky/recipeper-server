package me.vrnsky.server.controllers;

import me.vrnsky.server.domain.User;
import me.vrnsky.server.dto.ResponseStatus;
import me.vrnsky.server.dto.authentication.AuthenticationRequest;
import me.vrnsky.server.dto.authentication.AuthenticationResponse;
import me.vrnsky.server.dto.registration.RegistrationRequest;
import me.vrnsky.server.dto.registration.RegistrationResponse;
import me.vrnsky.server.exception.UserNotFoundException;
import me.vrnsky.server.service.SecurityService;
import me.vrnsky.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private SecurityService securityService;
    private Validator validator;

    @Autowired
    public UserController(UserService service, SecurityService securityService, ValidatorFactory validatorFactory) {
        this.userService = service;
        this.securityService = securityService;
        this.validator = validatorFactory.getValidator();
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public RegistrationResponse registerUser(@RequestBody RegistrationRequest request) {
        RegistrationResponse response = new RegistrationResponse();
        Set<ConstraintViolation<RegistrationRequest>> validationResult = this.validator.validate(request);
        if (validationResult.isEmpty()) {
            User user = new User(request.getEmail(), request.getUsername(), request.getPassword());
            userService.registerOrUpdate(user);
            response.setStatus(ResponseStatus.SUCCESS);
            response.setMessage("");
        } else {
            ConstraintViolation<RegistrationRequest> violation = validationResult.iterator().next();
            response.setStatus(ResponseStatus.FAIL);
            response.setMessage(violation.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public AuthenticationResponse login(@RequestBody AuthenticationRequest request) {
        AuthenticationResponse response = new AuthenticationResponse();
        Set<ConstraintViolation<AuthenticationRequest>> validationResult = this.validator.validate(request);
        if (validationResult.isEmpty()) {
            try {
                securityService.login(request.getUsername(), request.getPassword());
                response.setStatus(ResponseStatus.SUCCESS);
                response.setMessage("Success");
            } catch (UserNotFoundException nfe) {
                response.setStatus(ResponseStatus.FAIL);
                response.setMessage(nfe.getMessage());
            }
        } else {
            ConstraintViolation<AuthenticationRequest> violation = validationResult.iterator().next();
            response.setStatus(ResponseStatus.FAIL);
            response.setMessage(violation.getMessage());
        }
        return response;
    }


}
