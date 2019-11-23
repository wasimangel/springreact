package com.myvision.springreact.web;

import com.myvision.springreact.domain.User;
import com.myvision.springreact.services.MapValidationErrorService;
import com.myvision.springreact.services.UserService;
import com.myvision.springreact.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {


    @Autowired
    private UserService userService;
    @Autowired
    private MapValidationErrorService validationErrorService;
    @Autowired
    private UserValidator userValidator;

    @PostMapping("/register")

    public ResponseEntity<?> createUser(@Valid @RequestBody User user, BindingResult bindingResult) {

        userValidator.validate(user, bindingResult);
        ResponseEntity<?> errormap = validationErrorService.mapValidationService(bindingResult);
        if (errormap != null) return errormap;
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }
}
